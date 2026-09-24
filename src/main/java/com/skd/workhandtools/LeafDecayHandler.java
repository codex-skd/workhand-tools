package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

// Speeds up leaf decay: when a block adjacent to leaves is removed (by hand, an axe, an explosion,
// etc.), forces an early persistence/decay check on those leaves instead of waiting for vanilla's
// natural random-tick interval, which can leave "floating" leaves around for a long time. Inspired
// by FallingTree's LeafBreakingHandler (see lib_ext/FallingTree-minecraft-26.2), simplified to use
// the vanilla `minecraft:leaves` tag directly instead of a configurable allow/deny list.
public class LeafDecayHandler {
    private static final int DECAY_DELAY_TICKS = 4;

    private record LeafKey(ServerLevel level, BlockPos pos) {
    }

    private final Map<LeafKey, Integer> scheduled = new HashMap<>();

    @SubscribeEvent
    public void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level) || !event.getState().isAir()) {
            return;
        }
        BlockPos eventPos = event.getPos();
        for (Direction direction : event.getNotifiedSides()) {
            BlockPos neighborPos = eventPos.relative(direction);
            if (!level.isLoaded(neighborPos)) {
                continue;
            }
            BlockState neighborState = level.getBlockState(neighborPos);
            if (neighborState.is(BlockTags.LEAVES)) {
                scheduled.putIfAbsent(new LeafKey(level, neighborPos.immutable()), DECAY_DELAY_TICKS);
            }
        }
    }

    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Post event) {
        if (scheduled.isEmpty()) {
            return;
        }
        List<LeafKey> ready = new ArrayList<>();
        Iterator<Map.Entry<LeafKey, Integer>> iterator = scheduled.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<LeafKey, Integer> entry = iterator.next();
            int remaining = entry.getValue() - 1;
            if (remaining > 0) {
                entry.setValue(remaining);
                continue;
            }
            iterator.remove();
            ready.add(entry.getKey());
        }

        // state.tick/randomTick may decay the leaf and trigger a synchronous NeighborNotifyEvent,
        // which re-enters onNeighborNotify and mutates `scheduled` — must run after we're done
        // iterating/mutating the map above, not interleaved with it.
        for (LeafKey key : ready) {
            ServerLevel level = key.level();
            BlockPos pos = key.pos();
            if (!level.isLoaded(pos)) {
                continue;
            }
            BlockState state = level.getBlockState(pos);
            state.tick(level, pos, level.getRandom());
            if (state.isRandomlyTicking()) {
                state.randomTick(level, pos, level.getRandom());
            }
        }
    }

    @SubscribeEvent
    public void onLevelUnload(LevelEvent.Unload event) {
        if (event.getLevel() instanceof ServerLevel level) {
            scheduled.keySet().removeIf(key -> key.level() == level);
        }
    }
}
