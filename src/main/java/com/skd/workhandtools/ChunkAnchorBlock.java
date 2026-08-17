package com.skd.workhandtools;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ChunkAnchorBlock extends BaseEntityBlock {
    public static final MapCodec<ChunkAnchorBlock> CODEC = simpleCodec(ChunkAnchorBlock::new);

    // Tracks whether an Anchor Tome is currently placed, so light emission and the block model can
    // react to it without needing to query the block entity every frame (see docs/DESIGN_WORKHAND_TOOLS.md,
    // "Chunk Anchor + Anchor Tome"). The actual tome ItemStack lives in ChunkAnchorBlockEntity.
    public static final BooleanProperty HAS_TOME = BooleanProperty.create("has_tome");

    public ChunkAnchorBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(HAS_TOME, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_TOME);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChunkAnchorBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player,
            InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof ChunkAnchorBlockEntity anchor)) {
            return InteractionResult.PASS;
        }

        if (!anchor.hasTome() && stack.is(ModItems.ANCHOR_TOME.get())) {
            if (!level.isClientSide()) {
                ItemStack tome = stack.copyWithCount(1);
                anchor.setTome(tome);
                stack.shrink(1);
                level.setBlock(pos, state.setValue(HAS_TOME, true), 3);
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof ChunkAnchorBlockEntity anchor) || !anchor.hasTome()) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            ItemStack extracted = anchor.extractTome();
            if (!player.getInventory().add(extracted)) {
                player.drop(extracted, false);
            }
            level.setBlock(pos, state.setValue(HAS_TOME, false), 3);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        // Client: animates page-flip state, same split as vanilla's EnchantingTableBlock (bookAnimationTick
        // runs client-side only, purely visual). Server: spawns the orbiting particles.
        return level.isClientSide()
                ? createTickerHelper(type, ModBlocks.CHUNK_ANCHOR_BLOCK_ENTITY.get(), ChunkAnchorBlockEntity::clientTick)
                : createTickerHelper(type, ModBlocks.CHUNK_ANCHOR_BLOCK_ENTITY.get(), ChunkAnchorBlockEntity::serverTick);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof ChunkAnchorBlockEntity anchor && anchor.hasTome()) {
            ItemStack extracted = anchor.extractTome();
            Block.popResource(level, pos, extracted);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }
}
