package com.skd.workhandtools;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

// Workhand hoe. Built with Item.Properties.hoe(...) so it has hoe-like stats.
// All behavior lives in the event handler (CropHarvestHandler). The hoe is a pure mode tool:
// the primary button only runs the selected mode (till / harvest), it never mines a block.
public class WorkhandHoeItem extends HoeItem {
    public WorkhandHoeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    // Mirrors SwordItem: block breaking is disabled in survival so the primary button is
    // reserved for the mode action driven by CropHarvestHandler. Creative players can still
    // break blocks normally.
    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }
}
