package com.skd.workhandtools;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

// Workhand hoe. Built with Item.Properties.hoe(...) so it has hoe-like stats.
// All behavior lives in the event handler (CropHarvestHandler).
public class WorkhandHoeItem extends HoeItem {
    public WorkhandHoeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
}
