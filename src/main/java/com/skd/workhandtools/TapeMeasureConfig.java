package com.skd.workhandtools;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

// Client-side config for the tape measure (line/text color, sizes, widths). Separate from the
// existing COMMON Config because these options only matter on the client. Mirrors the structure of
// Mrbysco's "Measurements" mod (MIT) MeasurementConfig.Client.
public class TapeMeasureConfig {
    public static class Client {

        public final ModConfigSpec.DoubleValue lineWidth;
        public final ModConfigSpec.IntValue lineWidthMax;
        public final ModConfigSpec.DoubleValue textSize;
        public final ModConfigSpec.EnumValue<LineColor> lineColor;
        public final ModConfigSpec.EnumValue<TextColor> textColor;

        Client(ModConfigSpec.Builder builder) {
            builder.comment("Client settings")
                    .push("client");

            lineColor = builder
                    .comment("Set line color. [Default: YELLOW]")
                    .defineEnum("lineColor", LineColor.YELLOW);
            textColor = builder
                    .comment("Set text color. [Default: YELLOW]")
                    .defineEnum("textColor", TextColor.YELLOW);
            textSize = builder
                    .comment("Set text size [Default: 0.02]")
                    .defineInRange("textSize", 0.02D, 0.01, 0.10);
            lineWidth = builder
                    .comment("Set line width (thickness). [Default: 2]")
                    .defineInRange("lineWidth", 2.0D, 1, 16);
            lineWidthMax = builder
                    .comment("Set line width when further away (thickness). [Default: 2]")
                    .defineInRange("lineWidthMax", 2, 1, 16);

            builder.pop();
        }
    }

    public static final ModConfigSpec clientSpec;
    public static final Client CLIENT;

    static {
        final Pair<Client, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }
}
