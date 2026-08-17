package com.skd.workhandtools;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.book.BookModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.EnchantTableRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

// Renders the Anchor Tome spinning above the Chunk Anchor while a tome is present, following the same
// technique as vanilla's floating enchanting-table book (EnchantTableRenderer): a BookModel kept open
// (like the Lectern's static open pose) driven by a continuous time-based Y rotation (see
// docs/DESIGN_WORKHAND_TOOLS.md, "Efectos visuales del Anchor Tome"). Reuses vanilla's enchanting-table
// book sprite for the 3D model itself (registering a custom block-atlas sprite for this is unnecessary);
// the item's own inventory icon still uses the dedicated anchor_tome texture.
public class ChunkAnchorRenderer implements BlockEntityRenderer<ChunkAnchorBlockEntity, ChunkAnchorRenderState> {
    private final SpriteGetter sprites;
    private final BookModel bookModel;

    public ChunkAnchorRenderer(BlockEntityRendererProvider.Context context) {
        this.sprites = context.sprites();
        this.bookModel = new BookModel(context.bakeLayer(ModelLayers.BOOK));
    }

    @Override
    public ChunkAnchorRenderState createRenderState() {
        return new ChunkAnchorRenderState();
    }

    @Override
    public void extractRenderState(ChunkAnchorBlockEntity blockEntity, ChunkAnchorRenderState state, float partialTicks,
            Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        // blockEntity.hasTome() reads the tome ItemStack, which only exists server-side (the block
        // entity's NBT is never synced to the client). ChunkAnchorBlock.HAS_TOME is part of the
        // BlockState instead, which the client receives automatically via normal block updates
        // (see ChunkAnchorBlock#useItemOn/#useWithoutItem, which keep both in lockstep).
        state.hasTome = blockEntity.getBlockState().getValue(ChunkAnchorBlock.HAS_TOME);
        state.time = blockEntity.getLevel() != null ? blockEntity.getLevel().getGameTime() + partialTicks : 0;
        state.flip = Mth.lerp(partialTicks, blockEntity.oFlip, blockEntity.flip);
    }

    @Override
    public void submit(ChunkAnchorRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (!state.hasTome) {
            return;
        }

        poseStack.pushPose();
        // Float noticeably above the pedestal's top surface (block model's post ends around y=1.0)
        // so the book reads as hovering, not sunk into the stone.
        poseStack.translate(0.5F, 1.4F, 0.5F);
        poseStack.translate(0.0F, 0.06F * Mth.sin(state.time * 0.1F), 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.time * 2.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(20.0F));
        poseStack.scale(0.6F, 0.6F, 0.6F);

        // openness = 1 keeps the book fully open (matches Lectern's static open pose). The two
        // page-flip values are derived from the animated `flip` state with vanilla's exact
        // EnchantTableRenderer formula, so pages visibly turn forward/back over time instead of
        // sitting in a fixed curl.
        float flipPage1 = Mth.clamp(Mth.frac(state.flip + 0.25F) * 1.6F - 0.3F, 0.0F, 1.0F);
        float flipPage2 = Mth.clamp(Mth.frac(state.flip + 0.75F) * 1.6F - 0.3F, 0.0F, 1.0F);
        BookModel.State openBook = BookModel.State.forAnimation(state.time, flipPage1, flipPage2, 1.0F);
        submitNodeCollector.submitModel(
                this.bookModel, openBook, poseStack, state.lightCoords, OverlayTexture.NO_OVERLAY, -1,
                EnchantTableRenderer.BOOK_TEXTURE, this.sprites, 0, state.breakProgress);

        poseStack.popPose();
    }
}
