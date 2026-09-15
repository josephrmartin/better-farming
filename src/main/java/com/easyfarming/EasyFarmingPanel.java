package com.easyfarming;

import com.easyfarming.customrun.CustomRun;
import com.easyfarming.ui.CustomRunDetailPanel;
import com.easyfarming.ui.OverviewPanel;

import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.swing.*;
import java.awt.*;


public class EasyFarmingPanel extends PluginPanel
{
    private static final String OVERVIEW_PANEL = "OVERVIEW";
    private static final String DETAIL_PANEL = "DETAIL";


    private final EasyFarmingPlugin plugin;
    private final OverlayManager overlayManager;
    private final FarmingTeleportOverlay farmingTeleportOverlay;
    private final ItemManager itemManager;


    private final JPanel cardContainer;
    private final CardLayout cardLayout;


    private OverviewPanel overviewPanel;
    private JPanel currentDetailPanel;


    public EasyFarmingPanel(
            EasyFarmingPlugin plugin,
            OverlayManager overlayManager,
            FarmingTeleportOverlay farmingTeleportOverlay,
            ItemManager itemManager)
    {
        super(false);

        this.plugin = plugin;
        this.overlayManager = overlayManager;
        this.farmingTeleportOverlay = farmingTeleportOverlay;
        this.itemManager = itemManager;


        setLayout(new BorderLayout());


        cardLayout = new CardLayout();
        cardContainer = new JPanel(cardLayout);


        overviewPanel = new OverviewPanel(plugin, this);

        cardContainer.add(
                overviewPanel,
                OVERVIEW_PANEL);


        add(
                cardContainer,
                BorderLayout.CENTER);
    }


    /*
     * Returning to the overview means there is no selected run.
     */
    public void showOverview()
    {
        plugin.setSelectedCustomRun(null);


        if (overviewPanel != null)
        {
            overviewPanel.rebuildList();
        }


        cardLayout.show(
                cardContainer,
                OVERVIEW_PANEL);


        if (currentDetailPanel != null)
        {
            cardContainer.remove(currentDetailPanel);
            currentDetailPanel = null;
        }
    }


    public void refreshOverviewList()
    {
        if (overviewPanel != null)
        {
            overviewPanel.rebuildList();
        }
    }


    public void onCustomRunStarted()
    {
        refreshOverviewList();

        if (currentDetailPanel instanceof CustomRunDetailPanel)
        {
            ((CustomRunDetailPanel) currentDetailPanel)
                    .refreshActiveRunControls();
        }
    }


    public void onCustomRunEnded()
    {
        refreshOverviewList();

        if (currentDetailPanel instanceof CustomRunDetailPanel)
        {
            ((CustomRunDetailPanel) currentDetailPanel)
                    .refreshActiveRunControls();
        }
    }


    /*
     * This is the important part for seed highlighting.
     *
     * As soon as the user opens a custom run, that run becomes the
     * selected run on the plugin.
     *
     * The SeedHighlightOverlay can therefore highlight the correct
     * seeds BEFORE the run is started.
     */
    public void showRunDetail(CustomRun customRun)
    {
        plugin.setSelectedCustomRun(customRun);


        if (currentDetailPanel != null)
        {
            cardContainer.remove(currentDetailPanel);
        }


        currentDetailPanel =
                new CustomRunDetailPanel(
                        plugin,
                        this,
                        customRun,
                        itemManager);


        cardContainer.add(
                currentDetailPanel,
                DETAIL_PANEL);


        cardLayout.show(
                cardContainer,
                DETAIL_PANEL);
    }


    /*
     * Starting a run also makes sure that run remains selected.
     */
    public void startCustomRun(CustomRun customRun)
    {
        plugin.setSelectedCustomRun(customRun);


        SwingUtilities.invokeLater(() ->
        {
            plugin.setOverlayActive(true);

            farmingTeleportOverlay.startCustomRun(customRun);


            EasyFarmingOverlay overlay =
                    plugin.getEasyFarmingOverlay();


            if (overlay != null)
            {
                overlayManager.add(overlay);
            }


            overlayManager.add(
                    farmingTeleportOverlay);


            onCustomRunStarted();
        });
    }
}