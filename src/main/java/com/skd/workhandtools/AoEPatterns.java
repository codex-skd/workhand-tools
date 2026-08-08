package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;

public final class AoEPatterns {
    private AoEPatterns() {
    }

    public static List<BlockPos> computePattern(BlockPos center, Player player, Grade grade, AoEMode mode, boolean lookingUp) {
        List<BlockPos> positions = new ArrayList<>();
        int half = grade.lateralHalf();
        int depth = grade.depth(mode);
        Direction facing = player.getDirection();
        Direction lateral = facing.getClockWise();
        int[] vertical = verticalOffsets(grade.height(), lookingUp);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int dy : vertical) {
            for (int dx = -half; dx <= half; dx++) {
                for (int dz = 0; dz < depth; dz++) {
                    mutable.set(center).move(Direction.UP, dy).move(lateral, dx).move(facing, dz);
                    positions.add(mutable.immutable());
                }
            }
        }
        return positions;
    }

    static int[] verticalOffsets(int height, boolean lookingUp) {
        if (height == 3) {
            return new int[]{-1, 0, 1};
        }
        if (lookingUp) {
            return new int[]{-2, -1, 0, 1, 2};
        }
        return new int[]{-1, 0, 1, 2, 3};
    }
}
