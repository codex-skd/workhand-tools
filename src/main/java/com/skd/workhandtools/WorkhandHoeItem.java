package com.skd.workhandtools;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

// Workhand hoe. Built with Item.Properties.hoe(...) so it has hoe-like stats.
// All behavior lives in the event handler (CropHarvestHandler).
public class WorkhandHoeItem extends HoeItem {
    public WorkhandHoeItem(ToolMaterial tier, float attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }
}