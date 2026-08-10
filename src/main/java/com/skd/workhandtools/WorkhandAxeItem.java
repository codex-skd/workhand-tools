package com.skd.workhandtools;

import net.minecraft.world.item.Item;

// Workhand axe. A plain Item built with Item.Properties.axe(...) so it digs wood like an axe but
// does NOT strip logs on right-click: right-click is used to toggle tree felling instead.
public class WorkhandAxeItem extends Item {
    public WorkhandAxeItem(Item.Properties properties) {
        super(properties);
    }
}
