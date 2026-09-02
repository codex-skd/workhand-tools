package com.skd.workhandtools;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ChunkAnchorBlockEntity extends BlockEntity {
    private static final RandomSource FLIP_RANDOM = RandomSource.create();

    private ItemStack tomeItem = ItemStack.EMPTY;

    // Client-only page-flip animation state, same fields/formulas as vanilla's
    // EnchantingTableBlockEntity (flip/oFlip/flipT/flipA), driven by clientTick below.
    public float flip;
    public float oFlip;
    private float flipT;
    private float flipA;

    public ChunkAnchorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.CHUNK_ANCHOR_BLOCK_ENTITY.get(), pos, state);
    }

    public boolean hasTome() {
        return !tomeItem.isEmpty();
    }

    public ItemStack getTome() {
        return tomeItem;
    }

    public void setTome(ItemStack stack) {
        this.tomeItem = stack.copy();
        setChanged();
        if (level != null && !level.isClientSide()) {
            applyForcedTicket();
        }
    }

    public ItemStack extractTome() {
        ItemStack result = tomeItem.copy();
        tomeItem = ItemStack.EMPTY;
        setChanged();
        if (level != null && !level.isClientSide()) {
            removeForcedTicket();
        }
        return result;
    }

    private void applyForcedTicket() {
        if (level instanceof ServerLevel serverLevel) {
            int chunkX = getBlockPos().getX() >> 4;
            int chunkZ = getBlockPos().getZ() >> 4;
            boolean updated = serverLevel.setChunkForced(chunkX, chunkZ, true);
            WorkhandTools.LOGGER.info("Chunk Anchor at {}: forcing chunk [{}, {}] (changed={})",
                    getBlockPos(), chunkX, chunkZ, updated);
        }
    }

    private void removeForcedTicket() {
        if (level instanceof ServerLevel serverLevel) {
            int chunkX = getBlockPos().getX() >> 4;
            int chunkZ = getBlockPos().getZ() >> 4;
            boolean updated = serverLevel.setChunkForced(chunkX, chunkZ, false);
            WorkhandTools.LOGGER.info("Chunk Anchor at {}: releasing chunk [{}, {}] (changed={})",
                    getBlockPos(), chunkX, chunkZ, updated);
        }
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        if (level != null && level.isClientSide()) {
            ChunkAnchorBorderRenderer.trackClientAnchor(this);
            return;
        }
        // Reconcile the forced-chunk ticket on (re)load: if the tome is present when this block
        // entity attaches to a server level, re-apply the ticket, since forced tickets do not
        // themselves persist across a chunk unload/reload or a server restart.
        if (level != null && hasTome()) {
            applyForcedTicket();
        }
    }

    @Override
    public void setRemoved() {
        if (level != null && level.isClientSide()) {
            ChunkAnchorBorderRenderer.untrackClientAnchor(this);
        } else if (level != null && hasTome()) {
            removeForcedTicket();
        }
        super.setRemoved();
    }

    // Spawns particles orbiting the floating tome, matching ChunkAnchorRenderer's book position/height
    // (see docs/DESIGN_WORKHAND_TOOLS.md, "Efectos visuales del Anchor Tome"). Runs server-side and
    // is sent to all nearby clients via ServerLevel#sendParticles, same as vanilla ambient effects.
    public static void serverTick(Level level, BlockPos pos, BlockState state, ChunkAnchorBlockEntity blockEntity) {
        if (!(level instanceof ServerLevel serverLevel) || !state.getValue(ChunkAnchorBlock.HAS_TOME)) {
            return;
        }
        if (serverLevel.getGameTime() % 2 != 0) {
            return;
        }

        double centerX = pos.getX() + 0.5;
        double centerY = pos.getY() + 1.15;
        double centerZ = pos.getZ() + 0.5;
        double radius = 0.6;
        float angle = (serverLevel.getGameTime() % 360) * 0.05F;

        for (int i = 0; i < 2; i++) {
            float particleAngle = angle + i * (float) Math.PI;
            double x = centerX + Math.cos(particleAngle) * radius;
            double z = centerZ + Math.sin(particleAngle) * radius;
            serverLevel.sendParticles(ParticleTypes.ENCHANT, x, centerY, z, 1, 0.0, 0.02, 0.0, 0.0);
        }
    }

    // Animates the book's page-flip state (flip/flipT/flipA), ported from vanilla's
    // EnchantingTableBlockEntity#bookAnimationTick minus the player-tracking rotation (this book's
    // spin is already driven by ChunkAnchorRenderer's time-based rotation, not player position).
    // Runs client-side only, purely visual (see ChunkAnchorBlock#getTicker).
    public static void clientTick(Level level, BlockPos pos, BlockState state, ChunkAnchorBlockEntity blockEntity) {
        if (!state.getValue(ChunkAnchorBlock.HAS_TOME)) {
            return;
        }

        blockEntity.oFlip = blockEntity.flip;
        if (FLIP_RANDOM.nextInt(40) == 0) {
            float old = blockEntity.flipT;
            do {
                blockEntity.flipT = blockEntity.flipT + (FLIP_RANDOM.nextInt(4) - FLIP_RANDOM.nextInt(4));
            } while (old == blockEntity.flipT);
        }

        float diff = Mth.clamp((blockEntity.flipT - blockEntity.flip) * 0.4F, -0.2F, 0.2F);
        blockEntity.flipA = blockEntity.flipA + (diff - blockEntity.flipA) * 0.9F;
        blockEntity.flip = blockEntity.flip + blockEntity.flipA;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!tomeItem.isEmpty()) {
            tag.put("Tome", tomeItem.save(registries));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        tomeItem = ItemStack.parse(registries, tag.getCompound("Tome")).orElse(ItemStack.EMPTY);
    }
}
