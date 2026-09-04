package com.skd.workhandtools;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

// Sent client -> server when the player presses the "cycle AoE mode" keybinding
// (see WorkhandToolsClient.CYCLE_AOE_MODE_KEY). Carries no data: the server looks at
// whichever hand is holding a Workhand pickaxe/shovel and cycles its mode itself.
public record ToggleAoEModePayload() implements CustomPacketPayload {
    public static final Type<ToggleAoEModePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(WorkhandTools.MODID, "toggle_aoe_mode"));

    public static final StreamCodec<FriendlyByteBuf, ToggleAoEModePayload> STREAM_CODEC =
            StreamCodec.unit(new ToggleAoEModePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
