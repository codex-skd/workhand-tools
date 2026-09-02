package com.skd.workhandtools;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

// Tape measure tool. Right-click a block: first click sets a start position and creates a
// MeasurementBox; right-click again sets the end position and finishes the box. Shift+right-click
// undoes/removes the last box. Ported (and simplified) from Mrbysco's "Measurements" mod (MIT),
// see TapeMeasureHandler for the client-side rendering glue.
public class TapeMeasureItem extends Item {
    public TapeMeasureItem(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        // useOn runs on both logical sides for a normal (non-BlockItem) item. BoxHandler's box
        // list is client-only static state, so this must only run client-side — otherwise a
        // single right-click gets processed twice (once per side) and the box is immediately
        // marked finished with start == end before the player's second click ever lands.
        if (player == null || player.isSpectator() || !player.level().isClientSide()) {
            return super.useOn(context);
        }
        return BoxHandler.addBox(player, context.getClickedPos());
    }
}
