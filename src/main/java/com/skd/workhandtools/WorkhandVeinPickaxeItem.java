package com.skd.workhandtools;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

// Marker class for the improved pickaxes that mine ore veins. Detected by VeinMiningHandler.
public class WorkhandVeinPickaxeItem extends PickaxeItem {
    public WorkhandVeinPickaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
}
