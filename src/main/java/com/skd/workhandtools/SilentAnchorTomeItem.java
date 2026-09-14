package com.skd.workhandtools;

import net.minecraft.world.item.Item;

// No Enchantable data component is set, so this item is not enchantable by default (see
// docs/DESIGN_WORKHAND_TOOLS.md, "Chunk Anchor + Anchor Tome").
public class SilentAnchorTomeItem extends Item {
    public SilentAnchorTomeItem(Item.Properties properties) {
        super(properties);
    }
}
