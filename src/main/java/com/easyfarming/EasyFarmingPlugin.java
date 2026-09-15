package com.easyfarming;

import com.easyfarming.customrun.CustomRun;
import com.easyfarming.customrun.CustomRunStorage;
import com.easyfarming.customrun.LocationCatalog;
import com.google.gson.Gson;
import com.google.inject.Provides;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.ImageUtil;

import com.easyfarming.customrun.RunLocation;


@PluginDescriptor(
        name = "Better Farming",
        description = "Show item requirements and highlights for farming runs."
)
public class EasyFarmingPlugin extends Plugin
{
    @Inject
    private ItemManager itemManager;

    @Inject
    private ConfigManager configManager;

    @Inject
    private Gson gson;

    @Getter
    @Inject
    private Client client;

    @Inject
    private EventBus eventBus;

    @Inject
    private ClientThread clientThread;

    @Inject
    private ClientToolbar clientToolbar;

    @Inject
    private EasyFarmingConfig config;

    @Inject
    public OverlayManager overlayManager;

    @Inject
    public InfoBoxManager infoBoxManager;

    @Getter
    @Inject
    private FarmingTeleportOverlay farmingTeleportOverlay;

    @Inject
    private FarmingTeleportSceneOverlay farmingTeleportSceneOverlay;

    @Inject
    private EasyFarmingOverlayInfoBox farmingHelperOverlayInfoBox;

    @Inject
    private EasyFarmingOverlay farmingHelperOverlay;

    @Inject
    private com.easyfarming.overlays.highlighting.SeedHighlightOverlay seedHighlightOverlay;


    private LocationCatalog locationCatalog;
    private CustomRunStorage customRunStorage;

    private EasyFarmingPanel farmingHelperPanel;

    public EasyFarmingPanel panel;

    private NavigationButton navButton;


    /*
     * The custom run currently selected in the Better Farming sidebar.
     *
     * This is deliberately separate from whether a run has actually
     * started. Seed highlighting is based on the selected run.
     */
    @Getter
    @Setter
    private CustomRun selectedCustomRun;


    @Getter
    @Setter
    private boolean isTeleportOverlayActive = false;

    @Getter
    @Setter
    private boolean isOverlayActive = true;

    @Setter
    private boolean itemsCollected = false;


    private boolean customRunIncludeSecateurs = true;
    private boolean customRunIncludeDibber = true;
    private boolean customRunIncludeRake = true;


    @Getter
    private String lastMessage = "";


    public LocationCatalog getLocationCatalog()
    {
        if (locationCatalog == null)
        {
            locationCatalog = new LocationCatalog(this);
        }

        return locationCatalog;
    }


    public CustomRunStorage getCustomRunStorage()
    {
        if (customRunStorage == null)
        {
            customRunStorage = new CustomRunStorage(configManager, gson);
        }

        return customRunStorage;
    }


    public void runOnClientThread(Runnable task)
    {
        clientThread.invokeLater(task);
    }


    public EasyFarmingConfig getConfig()
    {
        return config;
    }


    public EasyFarmingOverlayInfoBox getEasyFarmingOverlayInfoBox()
    {
        return farmingHelperOverlayInfoBox;
    }


    public EasyFarmingOverlay getEasyFarmingOverlay()
    {
        return farmingHelperOverlay;
    }


    @Subscribe
    public void onChatMessage(ChatMessage event)
    {
        String message = event.getMessage();

        if (event.getType() == ChatMessageType.GAMEMESSAGE)
        {
            lastMessage = message;
        }
        else if (event.getType() == ChatMessageType.SPAM)
        {
            lastMessage = message;
        }
    }


    public boolean checkMessage(String targetMessage, String lastMessage)
    {
        return lastMessage.trim().equalsIgnoreCase(targetMessage.trim());
    }


    public void clearLastMessage()
    {
        lastMessage = "";
    }


    public boolean areItemsCollected()
    {
        return itemsCollected;
    }


    public void skipCurrentStep()
    {
        if (farmingTeleportOverlay == null
                || !farmingTeleportOverlay.isCustomRunMode())
        {
            return;
        }

        clearLastMessage();
        farmingTeleportOverlay.skipCurrentStep();
    }


    public void completeItemGatheringPhase()
    {
        itemsCollected = true;
        isTeleportOverlayActive = true;

        if (farmingHelperOverlay != null)
        {
            farmingHelperOverlay.clearAllInfoBoxes();
        }
    }


    /*
     * Returns the seed item IDs that should currently be highlighted.
     *
     * IMPORTANT:
     * This uses the SELECTED custom run, not the active/started run.
     *
     * Example:
     *   Herb + Allotment run
     *       -> configured herb seed
     *       -> configured allotment seed
     *
     *   Hops-only run
     *       -> configured hops seed
     *
     *   No selected run
     *       -> nothing
     */
    public Set<Integer> getSelectedRunSeedIds()
    {
        Set<Integer> ids = new HashSet<>();

        CustomRun run = selectedCustomRun;

        if (run == null)
        {
            return ids;
        }

        if (run.getLocations() == null)
        {
            return ids;
        }

        for (RunLocation location : run.getLocations())
        {
            if (location == null || location.getPatchTypes() == null)
            {
                continue;
            }

            for (String patchType : location.getPatchTypes())
            {
                if ("HERB".equalsIgnoreCase(patchType))
                {
                    addSeedId(ids, config.herbSeed().getItemId());
                }
                else if ("ALLOTMENT".equalsIgnoreCase(patchType))
                {
                    addSeedId(ids, config.allotmentSeed().getItemId());
                }
                else if ("FLOWER".equalsIgnoreCase(patchType))
                {
                    addSeedId(ids, config.flowerSeed().getItemId());
                }
                else if ("HOPS".equalsIgnoreCase(patchType))
                {
                    addSeedId(ids, config.hopsSeed().getItemId());
                }
            }
        }

        return ids;
    }


    private void addSeedId(Set<Integer> ids, int itemId)
    {
        if (itemId > 0)
        {
            ids.add(itemId);
        }
    }


    public void setCustomRunToolInclusion(
            boolean secateurs,
            boolean dibber,
            boolean rake)
    {
        customRunIncludeSecateurs = secateurs;
        customRunIncludeDibber = dibber;
        customRunIncludeRake = rake;
    }


    public boolean getCustomRunIncludeSecateurs()
    {
        return customRunIncludeSecateurs;
    }


    public boolean getCustomRunIncludeDibber()
    {
        return customRunIncludeDibber;
    }


    public boolean getCustomRunIncludeRake()
    {
        return customRunIncludeRake;
    }


    public void addTextToInfoBox(String text)
    {
        farmingHelperOverlayInfoBox.setText(text);
    }


    public void addDebugTextToInfoBox(String debugText)
    {
        farmingHelperOverlayInfoBox.setDebugText(debugText);
    }


    @Provides
    EasyFarmingConfig getConfig(ConfigManager configManager)
    {
        return configManager.getConfig(EasyFarmingConfig.class);
    }


    @Provides
    com.easyfarming.overlays.utils.ColorProvider provideColorProvider(
            EasyFarmingConfig config)
    {
        return new com.easyfarming.overlays.utils.ColorProvider(config);
    }


    @Provides
    EasyFarmingOverlay provideEasyFarmingOverlay(
            Client client,
            EasyFarmingPlugin plugin,
            ItemManager itemManager,
            InfoBoxManager infoBoxManager)
    {
        return new EasyFarmingOverlay(
                client,
                plugin,
                itemManager,
                infoBoxManager);
    }


    @Override
    protected void startUp()
    {
        selectedCustomRun = null;

        farmingHelperOverlay =
                new EasyFarmingOverlay(
                        client,
                        this,
                        itemManager,
                        infoBoxManager);

        panel = new EasyFarmingPanel(
                this,
                overlayManager,
                farmingTeleportOverlay,
                itemManager);

        final BufferedImage icon =
                ImageUtil.loadImageResource(getClass(), "/icon.png");

        navButton = NavigationButton.builder()
                .tooltip("Better Farming")
                .icon(icon)
                .priority(6)
                .panel(panel)
                .build();

        clientToolbar.addNavigation(navButton);

        overlayManager.add(farmingHelperOverlay);
        overlayManager.add(farmingTeleportSceneOverlay);
        overlayManager.add(farmingTeleportOverlay);
        overlayManager.add(farmingHelperOverlayInfoBox);
        overlayManager.add(seedHighlightOverlay);

        isOverlayActive = false;

        eventBus.register(this);
    }


    @Override
    protected void shutDown()
    {
        selectedCustomRun = null;

        if (navButton != null)
        {
            clientToolbar.removeNavigation(navButton);
        }

        overlayManager.remove(farmingHelperOverlay);
        overlayManager.remove(farmingTeleportSceneOverlay);
        overlayManager.remove(farmingTeleportOverlay);
        overlayManager.remove(farmingHelperOverlayInfoBox);
        overlayManager.remove(seedHighlightOverlay);

        eventBus.unregister(this);
    }
}