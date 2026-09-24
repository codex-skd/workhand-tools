package com.skd.workhandtools;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.EnchantTableRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;

// Renders the Anchor Tome spinning above the Chunk Anchor while a tome is present, following the same
// technique as vanilla's floating enchanting-table book (EnchantTableRenderer): a BookModel kept open
// (like the Lectern's static open pose) driven by a continuous time-based Y rotation (see
// docs/DESIGN_WORKHAND_TOOLS.md, "Efectos visuales del Anchor Tome"). Reuses vanilla's enchanting-table
// book sprite for the 3D model itself (registering a custom block-atlas sprite for this is unnecessary);
// the item's own inventory icon still uses the dedicated anchor_tome texture.
public class ChunkAnchorRenderer implements BlockEntityRenderer<ChunkAnchorBlockEntity> {
    private final BookModel bookModel;

    public ChunkAnchorRenderer(BlockEntityRendererProvider.Context context) {
        this.bookModel = new BookModel(context.bakeLayer(ModelLayers.BOOK));
    }

    @Override
    public void render(ChunkAnchorBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffers, int packedLight, int packedOverlay) {
        boolean hasTome = blockEntity.getBlockState().getValue(ChunkAnchorBlock.HAS_TOME);
        if (!hasTome) {
            return;
        }

        float time = blockEntity.getLevel() != null ? blockEntity.getLevel().getGameTime() + partialTick : 0;
        float flip = Mth.lerp(partialTick, blockEntity.oFlip, blockEntity.flip);

        poseStack.pushPose();
        // Float noticeably above the pedestal's top surface (block model's post ends around y=1.0)
        // so the book reads as hovering, not sunk into the stone.
        poseStack.translate(0.5F, 1.4F, 0.5F);
        poseStack.translate(0.0F, 0.06F * Mth.sin(time * 0.1F), 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(time * 2.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(20.0F));
        poseStack.scale(0.6F, 0.6F, 0.6F);

        // openness = 1 keeps the book fully open (matches Lectern's static open pose). The two
        // page-flip values are derived from the animated `flip` state with vanilla's exact
        // EnchantTableRenderer formula, so pages visibly turn forward/back over time instead of
        // sitting in a fixed curl.
        float flipPage1 = Mth.clamp(Mth.frac(flip + 0.25F) * 1.6F - 0.3F, 0.0F, 1.0F);
        float flipPage2 = Mth.clamp(Mth.frac(flip + 0.75F) * 1.6F - 0.3F, 0.0F, 1.0F);
        this.bookModel.setupAnim(time, flipPage1, flipPage2, 1.0F);
        var vc = EnchantTableRenderer.BOOK_LOCATION.buffer(buffers, RenderType::entitySolid);
        this.bookModel.render(poseStack, vc, packedLight, packedOverlay, -1);

        poseStack.popPose();
    }
}
