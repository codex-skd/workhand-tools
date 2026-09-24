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

    // Maximum consecutive leaf blocks the tree felling flood-fill (MiningHelper.fellTree) may cross
    // to reach log blocks separated by a gap (diagonal branches, offset canopies). Resets to 0 every
    // time a log block is found again. -1 means unlimited (only MAX_TREE_BLOCKS still caps it).
    public static final ModConfigSpec.IntValue MAX_LEAF_DISTANCE_FROM_LOG = BUILDER
            .comment("Maximum consecutive leaf blocks the tree felling algorithm may traverse to reach",
                    "log blocks separated by leaf gaps (e.g. diagonal branches, offset canopies).",
                    "The counter resets to 0 every time a log block is found again. -1 means unlimited (only MAX_TREE_BLOCKS still caps it).")
            .defineInRange("maxLeafDistanceFromLog", 2, -1, 64);

    // Chunk Anchor border rendering mode
    public enum ChunkAnchorBorderMode {
        ALWAYS,
        SNEAK_LOOKING,
        NEARBY
    }

    public static final ModConfigSpec.EnumValue<ChunkAnchorBorderMode> CHUNK_ANCHOR_BORDER_MODE = BUILDER
            .comment("When to render the chunk border corners for active Chunk Anchors.",
                    "ALWAYS = always render within render distance (default).",
                    "SNEAK_LOOKING = only when sneaking and looking at the anchor within 20 blocks.",
                    "NEARBY = only when within 20 blocks of the anchor (regardless of looking/sneaking).")
            .defineEnum("chunkAnchorBorderMode", ChunkAnchorBorderMode.ALWAYS);

    public static final ModConfigSpec SPEC = BUILDER.build();
}