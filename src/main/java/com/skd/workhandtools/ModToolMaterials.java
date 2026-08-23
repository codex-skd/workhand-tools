package com.skd.workhandtools;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

// Custom tool materials. Base grades (STONE_WORKHAND/IRON_WORKHAND/DIAMOND_WORKHAND) mirror the
// vanilla ToolMaterial.STONE/IRON/DIAMOND constants exactly (speed, attack bonus, enchantment
// value, repair tag) but with 5x durability — crafting cost is steep enough that vanilla-tier
// durability made these tools feel disposable. Improved materials keep the same 5x multiplier
// applied to their original (already-boosted) durability.
public final class ModToolMaterials {
    private ModToolMaterials() {
    }

    public static final ToolMaterial STONE_WORKHAND = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 655, 4.0F, 1.0F, 5, ItemTags.STONE_TOOL_MATERIALS);

    public static final ToolMaterial IRON_WORKHAND = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1250, 6.0F, 2.0F, 14, ItemTags.IRON_TOOL_MATERIALS);

    public static final ToolMaterial DIAMOND_WORKHAND = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 7805, 8.0F, 3.0F, 10, ItemTags.DIAMOND_TOOL_MATERIALS);

    public static final ToolMaterial IMPROVED_IRON = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 3750, 6.0F, 2.0F, 14, ItemTags.IRON_TOOL_MATERIALS);

    public static final ToolMaterial IMPROVED_DIAMOND = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 23415, 8.0F, 3.0F, 10, ItemTags.DIAMOND_TOOL_MATERIALS);

    // Kennestroyer ultimate tools - highest tier
    public static final ToolMaterial KENNESTROYER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 50000, 12.0F, 4.0F, 15, ItemTags.NETHERITE_TOOL_MATERIALS);
}
