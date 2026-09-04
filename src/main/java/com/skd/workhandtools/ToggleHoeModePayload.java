package com.skd.workhandtools;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

// Sent client -> server when the player presses the "cycle hoe mode" keybinding
// (see WorkhandToolsClient.CYCLE_HOE_MODE_KEY). Carries no data: the server looks at
// whichever hand is holding a Workhand Hoe and cycles its mode itself.
public record ToggleHoeModePayload() implements CustomPacketPayload {
    public static final Type<ToggleHoeModePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(WorkhandTools.MODID, "toggle_hoe_mode"));

    public static final StreamCodec<FriendlyByteBuf, ToggleHoeModePayload> STREAM_CODEC =
            StreamCodec.unit(new ToggleHoeModePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
