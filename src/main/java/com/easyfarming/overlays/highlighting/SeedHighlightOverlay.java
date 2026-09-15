package com.easyfarming.overlays.highlighting;

import com.easyfarming.EasyFarmingConfig;
import com.easyfarming.EasyFarmingPlugin;

import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

import javax.inject.Inject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Set;


public class SeedHighlightOverlay extends WidgetItemOverlay
{
    private final EasyFarmingConfig config;
    private final ItemManager itemManager;
    private final EasyFarmingPlugin plugin;


    @Inject
    public SeedHighlightOverlay(
            EasyFarmingConfig config,
            ItemManager itemManager,
            EasyFarmingPlugin plugin)
    {
        this.config = config;
        this.itemManager = itemManager;
        this.plugin = plugin;


        showOnBank();
        showOnInventory();
    }


    @Override
    public void renderItemOverlay(
            Graphics2D graphics,
            int itemId,
            WidgetItem widgetItem)
    {
        /*
         * User-controlled master switch.
         */
        if (!config.highlightSeedsInBank())
        {
            return;
        }


        /*
         * Get the seeds for the run currently selected
         * in the Better Farming sidebar.
         */
        Set<Integer> seedIds =
                plugin.getSelectedRunSeedIds();


        /*
         * No run selected = highlight nothing.
         *
         * This is intentional. We do NOT fall back to highlighting
         * all four configured seed types.
         */
        if (seedIds.isEmpty())
        {
            return;
        }


        /*
         * RuneLite can provide item variants.
         *
         * Canonicalizing makes sure the configured seed ID matches
         * the inventory/bank item.
         */
        int canonicalId =
                itemManager.canonicalize(itemId);


        if (!seedIds.contains(canonicalId))
        {
            return;
        }


        Rectangle bounds =
                widgetItem.getCanvasBounds();


        if (bounds == null)
        {
            return;
        }


        Color color =
                config.seedHighlightColor();


        /*
         * Semi-transparent fill.
         */
        Color fillColor =
                new Color(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        Math.min(color.getAlpha(), 120));


        graphics.setColor(fillColor);
        graphics.fill(bounds);


        /*
         * Solid outline.
         */
        graphics.setColor(color);
        graphics.draw(bounds);
    }
}