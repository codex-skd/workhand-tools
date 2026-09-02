package com.skd.workhandtools;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import com.skd.vellumli.common.item.VellumliDataComponents;
import com.skd.vellumli.common.item.VellumliItems;

// Only referenced when Vellumli is confirmed loaded (see GuideBookGrantHandler's ModList check) —
// Vellumli is an optional/soft dependency, so no code here may run if it's absent.
final class VellumliCompat {
    private VellumliCompat() {
    }

    static ItemStack createGuideBookStack() {
        ItemStack stack = new ItemStack(VellumliItems.BOOK);
        stack.set(VellumliDataComponents.BOOK, ResourceLocation.fromNamespaceAndPath(WorkhandTools.MODID, "workhand_guide"));
        return stack;
    }
}
