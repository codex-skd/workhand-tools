package com.skd.workhandtools;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    private ModBlocks() {
    }

    private static final Map<String, DeferredBlock<? extends Block>> BLOCK_BY_ID = new HashMap<>();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WorkhandTools.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, WorkhandTools.MODID);

    public static final DeferredBlock<ChunkAnchorBlock> CHUNK_ANCHOR =
            BLOCKS.registerBlock("chunk_anchor", ChunkAnchorBlock::new,
                    Block.Properties.ofFullCopy(Blocks.OBSIDIAN).noOcclusion());

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChunkAnchorBlockEntity>> CHUNK_ANCHOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("chunk_anchor",
                    () -> BlockEntityType.Builder.of(ChunkAnchorBlockEntity::new, CHUNK_ANCHOR.get()).build(null));

    static void buildLookups() {
        BLOCK_BY_ID.put("chunk_anchor", CHUNK_ANCHOR);
    }
}
