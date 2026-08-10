package com.skd.workhandtools;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

// Improved tool materials: same stats as the vanilla tier (iron/diamond) but with much higher
// durability, so the improved pickaxes and workhand axes last far longer than conventional tools.
public final class ModToolMaterials {
    private ModToolMaterials() {
    }

    public static final ToolMaterial IMPROVED_IRON = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 750, 6.0F, 2.0F, 14, ItemTags.IRON_TOOL_MATERIALS);

    public static final ToolMaterial IMPROVED_DIAMOND = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 4683, 8.0F, 3.0F, 10, ItemTags.DIAMOND_TOOL_MATERIALS);
}
