package com.skd.workhandtools;

import net.neoforged.neoforge.common.ModConfigSpec;

// Config placeholder. Real options (e.g. per-material durability/efficiency overrides) get added
// here once the tool items are implemented — see docs/DESIGN_WORKHAND_TOOLS.md.
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static final ModConfigSpec SPEC = BUILDER.build();
}
