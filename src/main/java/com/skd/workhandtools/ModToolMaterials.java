package com.skd.workhandtools;

import java.util.function.Supplier;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.SimpleTier;

// Custom tool materials. Base grades (STONE_WORKHAND/IRON_WORKHAND/DIAMOND_WORKHAND) mirror the
// vanilla Tier constants exactly (speed, attack bonus, enchantment value, repair ingredient) but
// with 5x durability — crafting cost is steep enough that vanilla-tier durability made these tools
// feel disposable. Improved materials keep the same 5x multiplier applied to their original
// (already-boosted) durability.
public final class ModToolMaterials {
    private ModToolMaterials() {
    }

    public static final Supplier<Ingredient> COBBLESTONE_INGREDIENT = () -> Ingredient.of(Blocks.COBBLESTONE, Blocks.BLACKSTONE);
    public static final Supplier<Ingredient> IRON_INGREDIENT = () -> Ingredient.of(net.minecraft.world.item.Items.IRON_INGOT);
    public static final Supplier<Ingredient> DIAMOND_INGREDIENT = () -> Ingredient.of(net.minecraft.world.item.Items.DIAMOND);
    public static final Supplier<Ingredient> NETHERITE_INGREDIENT = () -> Ingredient.of(net.minecraft.world.item.Items.NETHERITE_INGOT);

    public static final Tier STONE_WORKHAND = new SimpleTier(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 655, 4.0F, 1.0F, 5, COBBLESTONE_INGREDIENT);

    public static final Tier IRON_WORKHAND = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1250, 6.0F, 2.0F, 14, IRON_INGREDIENT);

    public static final Tier DIAMOND_WORKHAND = new SimpleTier(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 7805, 8.0F, 3.0F, 10, DIAMOND_INGREDIENT);

    public static final Tier IMPROVED_IRON = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 3750, 6.0F, 2.0F, 14, IRON_INGREDIENT);

    public static final Tier IMPROVED_DIAMOND = new SimpleTier(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 23415, 8.0F, 3.0F, 10, DIAMOND_INGREDIENT);

    // Kennestroyer ultimate tools - highest tier
    public static final Tier KENNESTROYER = new SimpleTier(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 50000, 12.0F, 4.0F, 15, NETHERITE_INGREDIENT);

    // Reinforced Deepslate Pickaxe - single-purpose tool material
    public static final Tier REINFORCED_DEEPSLATE = new SimpleTier(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 50000, 12.0F, 4.0F, 15, NETHERITE_INGREDIENT);
}
