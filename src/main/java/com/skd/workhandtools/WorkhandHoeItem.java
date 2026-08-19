package com.skd.workhandtools;

import net.minecraft.world.item.Item;

// Workhand hoe. A plain Item built with Item.Properties.hoe(...) so it has hoe-like stats.
// All behavior lives in the event handler (CropHarvestHandler).
public class WorkhandHoeItem extends Item {
    public WorkhandHoeItem(Item.Properties properties) {
        super(properties);
    }
}