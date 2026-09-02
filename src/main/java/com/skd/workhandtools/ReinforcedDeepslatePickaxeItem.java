package com.skd.workhandtools;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class ReinforcedDeepslatePickaxeItem extends PickaxeItem {
    // Speed equivalent to a diamond pickaxe (8.0F) on stone (1.5F) against reinforced_deepslate (55.0F):
    // 8.0 / 1.5 / 30 = 0.1778 progress/tick → 0.1778 * 55.0 * 30 = 293.3
    private static final float REINFORCED_DEEPSLATE_SPEED = 293.3F;

    public ReinforcedDeepslatePickaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public float getDestroySpeed(net.minecraft.world.item.ItemStack stack, net.minecraft.world.level.block.state.BlockState state) {
        if (state.is(net.minecraft.world.level.block.Blocks.REINFORCED_DEEPSLATE)) {
            return REINFORCED_DEEPSLATE_SPEED;
        }
        // 0 speed means mining progress never advances, making every other block unbreakable
        // with this pickaxe in Survival (Creative always breaks instantly regardless of speed).
        return 0.0F;
    }

    @Override
    public boolean isCorrectToolForDrops(net.minecraft.world.item.ItemStack stack, net.minecraft.world.level.block.state.BlockState state) {
        return state.is(net.minecraft.world.level.block.Blocks.REINFORCED_DEEPSLATE);
    }
}
