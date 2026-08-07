package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

public final class ModItems {
    private ModItems() {
    }

    private static final Map<String, DeferredHolder<Item, ? extends Item>> BY_ID = new HashMap<>();
    private static final Map<Item, Grade> ITEM_GRADES = new HashMap<>();

    public static final DeferredHolder<Item, ? extends Item> ROBUST_STICK =
            WorkhandTools.ITEMS.registerItem("robust_stick", Item::new, Item.Properties::new);

    // Stone Pickaxes
    public static final DeferredItem<WorkhandPickaxeItem> STONE_WORKHAND_PICKAXE =
            WorkhandTools.ITEMS.registerItem("stone_workhand_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.STONE, 1.0F, -2.8F));

    public static final DeferredItem<WorkhandPickaxeItem> STONE_WORKHAND_ADVANCED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("stone_workhand_advanced_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.STONE, 1.0F, -2.8F));

    public static final DeferredItem<WorkhandPickaxeItem> STONE_WORKHAND_EXPERT_PICKAXE =
            WorkhandTools.ITEMS.registerItem("stone_workhand_expert_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.STONE, 1.0F, -2.8F));

    public static final DeferredItem<WorkhandPickaxeItem> STONE_WORKHAND_PROFESSIONAL_PICKAXE =
            WorkhandTools.ITEMS.registerItem("stone_workhand_professional_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.STONE, 1.0F, -2.8F));

    // Iron Pickaxes
    public static final DeferredItem<WorkhandPickaxeItem> IRON_WORKHAND_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.IRON, 1.0F, -2.8F));

    public static final DeferredItem<WorkhandPickaxeItem> IRON_WORKHAND_ADVANCED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_advanced_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.IRON, 1.0F, -2.8F));

    public static final DeferredItem<WorkhandPickaxeItem> IRON_WORKHAND_EXPERT_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_expert_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.IRON, 1.0F, -2.8F));

    public static final DeferredItem<WorkhandPickaxeItem> IRON_WORKHAND_PROFESSIONAL_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_professional_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.IRON, 1.0F, -2.8F));

    // Diamond Pickaxes
    public static final DeferredItem<WorkhandPickaxeItem> DIAMOND_WORKHAND_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.DIAMOND, 1.0F, -2.8F));

    public static final DeferredItem<WorkhandPickaxeItem> DIAMOND_WORKHAND_ADVANCED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_advanced_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.DIAMOND, 1.0F, -2.8F));

    public static final DeferredItem<WorkhandPickaxeItem> DIAMOND_WORKHAND_EXPERT_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_expert_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.DIAMOND, 1.0F, -2.8F));

    public static final DeferredItem<WorkhandPickaxeItem> DIAMOND_WORKHAND_PROFESSIONAL_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_professional_pickaxe",
                    WorkhandPickaxeItem::new, () -> new Item.Properties().pickaxe(ToolMaterial.DIAMOND, 1.0F, -2.8F));

    // Stone Shovels
    public static final DeferredHolder<Item, ? extends Item> STONE_WORKHAND_SHOVEL =
            WorkhandTools.ITEMS.registerItem("stone_workhand_shovel",
                    props -> new ShovelItem(ToolMaterial.STONE, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    public static final DeferredHolder<Item, ? extends Item> STONE_WORKHAND_ADVANCED_SHOVEL =
            WorkhandTools.ITEMS.registerItem("stone_workhand_advanced_shovel",
                    props -> new ShovelItem(ToolMaterial.STONE, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    public static final DeferredHolder<Item, ? extends Item> STONE_WORKHAND_EXPERT_SHOVEL =
            WorkhandTools.ITEMS.registerItem("stone_workhand_expert_shovel",
                    props -> new ShovelItem(ToolMaterial.STONE, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    public static final DeferredHolder<Item, ? extends Item> STONE_WORKHAND_PROFESSIONAL_SHOVEL =
            WorkhandTools.ITEMS.registerItem("stone_workhand_professional_shovel",
                    props -> new ShovelItem(ToolMaterial.STONE, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    // Iron Shovels
    public static final DeferredHolder<Item, ? extends Item> IRON_WORKHAND_SHOVEL =
            WorkhandTools.ITEMS.registerItem("iron_workhand_shovel",
                    props -> new ShovelItem(ToolMaterial.IRON, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    public static final DeferredHolder<Item, ? extends Item> IRON_WORKHAND_ADVANCED_SHOVEL =
            WorkhandTools.ITEMS.registerItem("iron_workhand_advanced_shovel",
                    props -> new ShovelItem(ToolMaterial.IRON, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    public static final DeferredHolder<Item, ? extends Item> IRON_WORKHAND_EXPERT_SHOVEL =
            WorkhandTools.ITEMS.registerItem("iron_workhand_expert_shovel",
                    props -> new ShovelItem(ToolMaterial.IRON, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    public static final DeferredHolder<Item, ? extends Item> IRON_WORKHAND_PROFESSIONAL_SHOVEL =
            WorkhandTools.ITEMS.registerItem("iron_workhand_professional_shovel",
                    props -> new ShovelItem(ToolMaterial.IRON, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    // Diamond Shovels
    public static final DeferredHolder<Item, ? extends Item> DIAMOND_WORKHAND_SHOVEL =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_shovel",
                    props -> new ShovelItem(ToolMaterial.DIAMOND, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    public static final DeferredHolder<Item, ? extends Item> DIAMOND_WORKHAND_ADVANCED_SHOVEL =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_advanced_shovel",
                    props -> new ShovelItem(ToolMaterial.DIAMOND, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    public static final DeferredHolder<Item, ? extends Item> DIAMOND_WORKHAND_EXPERT_SHOVEL =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_expert_shovel",
                    props -> new ShovelItem(ToolMaterial.DIAMOND, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    public static final DeferredHolder<Item, ? extends Item> DIAMOND_WORKHAND_PROFESSIONAL_SHOVEL =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_professional_shovel",
                    props -> new ShovelItem(ToolMaterial.DIAMOND, 1.5F, -3.0F, props),
                    () -> new Item.Properties());

    // Lists for creative tab and utility
    public static final List<DeferredHolder<Item, ? extends Item>> PICKAXES = List.of(
            STONE_WORKHAND_PICKAXE,
            STONE_WORKHAND_ADVANCED_PICKAXE,
            STONE_WORKHAND_EXPERT_PICKAXE,
            STONE_WORKHAND_PROFESSIONAL_PICKAXE,
            IRON_WORKHAND_PICKAXE,
            IRON_WORKHAND_ADVANCED_PICKAXE,
            IRON_WORKHAND_EXPERT_PICKAXE,
            IRON_WORKHAND_PROFESSIONAL_PICKAXE,
            DIAMOND_WORKHAND_PICKAXE,
            DIAMOND_WORKHAND_ADVANCED_PICKAXE,
            DIAMOND_WORKHAND_EXPERT_PICKAXE,
            DIAMOND_WORKHAND_PROFESSIONAL_PICKAXE
    );

    public static final List<DeferredHolder<Item, ? extends Item>> SHOVELS = List.of(
            STONE_WORKHAND_SHOVEL,
            STONE_WORKHAND_ADVANCED_SHOVEL,
            STONE_WORKHAND_EXPERT_SHOVEL,
            STONE_WORKHAND_PROFESSIONAL_SHOVEL,
            IRON_WORKHAND_SHOVEL,
            IRON_WORKHAND_ADVANCED_SHOVEL,
            IRON_WORKHAND_EXPERT_SHOVEL,
            IRON_WORKHAND_PROFESSIONAL_SHOVEL,
            DIAMOND_WORKHAND_SHOVEL,
            DIAMOND_WORKHAND_ADVANCED_SHOVEL,
            DIAMOND_WORKHAND_EXPERT_SHOVEL,
            DIAMOND_WORKHAND_PROFESSIONAL_SHOVEL
    );

    public static DeferredHolder<Item, ? extends Item> get(String id) {
        return BY_ID.get(id);
    }

    public static boolean isWorkhandTool(ItemStack stack) {
        return !stack.isEmpty() && ITEM_GRADES.containsKey(stack.getItem());
    }

    public static Grade gradeOf(ItemStack stack) {
        return stack.isEmpty() ? null : ITEM_GRADES.get(stack.getItem());
    }
}
