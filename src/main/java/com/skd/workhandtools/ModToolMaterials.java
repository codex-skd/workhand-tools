package com.skd.workhandtools;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public final class ModToolMaterials {
    public static final ToolMaterial COPPER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_COPPER_TOOL, 185, 4.5F, 1.0F, 8, ItemTags.COPPER_TOOL_MATERIALS);
    public static final ToolMaterial DEEPSLATE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_COPPER_TOOL, 205, 5.0F, 1.0F, 6, repairTag("deepslate_tool_materials"));
    public static final ToolMaterial BLACKSTONE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 270, 6.5F, 2.0F, 9, repairTag("blackstone_tool_materials"));
    public static final ToolMaterial OBSIDIAN = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1800, 7.0F, 3.0F, 12, repairTag("obsidian_tool_materials"));

    private static TagKey<Item> repairTag(String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(WorkhandTools.MODID, path));
    }

    private ModToolMaterials() {
    }
}
