package com.skd.workhandtools;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

// Sent client -> server when the player presses the "cycle felling mode" keybinding
// (see WorkhandToolsClient.CYCLE_FELLING_MODE_KEY). Carries no data: the server looks at
// whichever hand is holding a Workhand axe and cycles its mode itself.
public record ToggleFellingModePayload() implements CustomPacketPayload {
    public static final Type<ToggleFellingModePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(WorkhandTools.MODID, "toggle_felling_mode"));

    public static final StreamCodec<FriendlyByteBuf, ToggleFellingModePayload> STREAM_CODEC =
            StreamCodec.unit(new ToggleFellingModePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
