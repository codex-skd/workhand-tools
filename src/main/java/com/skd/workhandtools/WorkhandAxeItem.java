package com.skd.workhandtools;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

// Workhand axe. Built with Item.Properties.axe(...) so it digs wood like an axe but
// does NOT strip logs on right-click: right-click is used to toggle tree felling instead.
public class WorkhandAxeItem extends AxeItem {
    public WorkhandAxeItem(ToolMaterial tier, float attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }
}
