package com.skd.workhandtools;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public final class MiningHelper {
    private static final int MAX_VEIN_BLOCKS = 128;
    private static final int MAX_TREE_BLOCKS = 256;

    // Ore Vein Miner reference block ids (see lib_ext/Ore_Vein_Miner-main/data/svm/function/reset_config.mcfunction).
    private static final Set<Identifier> ORE_IDS = Set.of(
            Identifier.fromNamespaceAndPath("minecraft", "coal_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "copper_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "diamond_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "emerald_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "gold_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "iron_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "lapis_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "redstone_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "deepslate_coal_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "deepslate_copper_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "deepslate_diamond_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "deepslate_emerald_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "deepslate_gold_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "deepslate_iron_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "deepslate_lapis_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "deepslate_redstone_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "nether_quartz_ore"),
            Identifier.fromNamespaceAndPath("minecraft", "nether_gold_ore")
    );

    private MiningHelper() {
    }

    static void breakBlock(Level level, BlockPos pos, BlockState state, Player player, ItemStack stack) {
        FluidState fluidState = level.getFluidState(pos);
        BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;

        state.getBlock().playerWillDestroy(level, pos, state, player);
        level.levelEvent(2001, pos, Block.getId(state));

        if (!player.getAbilities().instabuild) {
            Block.dropResources(state, level, pos, blockEntity, player, stack);
            int xp = state.getExpDrop(level, pos, blockEntity, player, stack);
            if (xp > 0) {
                player.giveExperiencePoints(xp);
            }
        }

        level.setBlock(pos, fluidState.createLegacyBlock(), 3);
    }

    static boolean isOre(BlockState state) {
        Identifier id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
        return id != null && ORE_IDS.contains(id);
    }

    static boolean isVeinPickaxe(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof WorkhandVeinPickaxeItem;
    }

    static boolean isFellingAxe(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof WorkhandAxeItem;
    }

    static boolean isFellingEnabled(ItemStack stack) {
        return isFellingAxe(stack) && Boolean.TRUE.equals(stack.get(ModDataComponents.FELLING_ENABLED));
    }

    // BFS flood-fill the same block type connected via 6 directions, breaking matching blocks.
    // The center block (start) is skipped — it's handled by the caller (vanilla break or AoE).
    // Returns true if at least one extra block was broken.
    static boolean mineVein(Level level, BlockPos start, BlockState startState, Player player, ItemStack stack) {
        if (!isOre(startState) || !isVeinPickaxe(stack)) {
            return false;
        }
        Block targetBlock = startState.getBlock();
        Set<BlockPos> visited = new HashSet<>();
        ArrayDeque<BlockPos> queue = new ArrayDeque<>();
        queue.add(start.immutable());
        int broken = 0;

        while (!queue.isEmpty() && broken < MAX_VEIN_BLOCKS) {
            BlockPos pos = queue.poll();
            if (!visited.add(pos)) {
                continue;
            }
            if (pos.equals(start)) {
                for (Direction dir : Direction.values()) {
                    queue.add(pos.relative(dir));
                }
                continue;
            }
            if (!level.isLoaded(pos)) {
                continue;
            }
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() != targetBlock) {
                continue;
            }
            breakBlock(level, pos, state, player, stack);
            broken++;
            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            if (stack.isEmpty() && !player.getAbilities().instabuild) {
                break;
            }
            for (Direction dir : Direction.values()) {
                queue.add(pos.relative(dir));
            }
        }
        return broken > 0;
    }

    // BFS flood-fill of connected logs (same block type, BlockTags.LOGS, excluding stripped).
    // The center block (start) is skipped.
    static boolean fellTree(Level level, BlockPos start, BlockState startState, Player player, ItemStack stack) {
        if (!isFellingEnabled(stack) || !isLog(startState)) {
            return false;
        }
        Block targetBlock = startState.getBlock();
        Set<BlockPos> visited = new HashSet<>();
        ArrayDeque<BlockPos> queue = new ArrayDeque<>();
        queue.add(start.immutable());
        int broken = 0;

        while (!queue.isEmpty() && broken < MAX_TREE_BLOCKS) {
            BlockPos pos = queue.poll();
            if (!visited.add(pos)) {
                continue;
            }
            if (pos.equals(start)) {
                for (Direction dir : Direction.values()) {
                    queue.add(pos.relative(dir));
                }
                continue;
            }
            if (!level.isLoaded(pos)) {
                continue;
            }
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() != targetBlock || !isLog(state)) {
                continue;
            }
            breakBlock(level, pos, state, player, stack);
            broken++;
            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            if (stack.isEmpty() && !player.getAbilities().instabuild) {
                break;
            }
            for (Direction dir : Direction.values()) {
                queue.add(pos.relative(dir));
            }
        }
        return broken > 0;
    }

    private static boolean isLog(BlockState state) {
        if (!state.typeHolder().is(BlockTags.LOGS)) {
            return false;
        }
        Identifier id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
        return id != null && !id.getPath().startsWith("stripped");
    }
}
