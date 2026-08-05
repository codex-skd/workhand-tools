package com.skd.workhandtools;

import net.neoforged.neoforge.common.ModConfigSpec;

// Config placeholder. Real options (e.g. per-material durability/efficiency overrides) get added
// here once the tool items are implemented — see docs/DESIGN_WORKHAND_TOOLS.md.
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Pitch threshold for the vertical anchoring of 5-high AoE patterns (see
    // docs/DESIGN_WORKHAND_TOOLS.md, "Anclaje vertical para patrones de 5 de alto").
    // Minecraft pitch is negative when looking up (-90 = straight up, 0 = horizontal).
    // Looking up past this threshold anchors the pattern symmetrically (2 above / 2 below);
    // otherwise it anchors with 1 below / 3 above the aimed block.
    public static final ModConfigSpec.DoubleValue PITCH_THRESHOLD_DEGREES = BUILDER
            .comment("Pitch threshold (degrees) for the vertical anchoring of 5-high AoE patterns.",
                    "When the player looks up more than this many degrees above horizontal, the 5-high",
                    "pattern is centered on the aimed block (2 blocks above and 2 below).",
                    "Otherwise it is anchored with 1 block below and 3 above the aimed block.")
            .defineInRange("pitchThresholdDegrees", -10.0, -89.0, 89.0);

    static final ModConfigSpec SPEC = BUILDER.build();
}
