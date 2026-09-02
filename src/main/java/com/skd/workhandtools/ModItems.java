package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

public final class ModItems {
    private ModItems() {
    }

    private static final Map<String, DeferredHolder<Item, ? extends Item>> BY_ID = new HashMap<>();
    private static final Map<Item, Grade> ITEM_GRADES = new HashMap<>();

    public static final DeferredHolder<Item, ? extends Item> ROBUST_STICK =
            WorkhandTools.ITEMS.registerSimpleItem("robust_stick");
    public static final DeferredItem<AnchorTomeItem> ANCHOR_TOME =
            WorkhandTools.ITEMS.registerItem("anchor_tome",
                    props -> new AnchorTomeItem(props),
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE));

    public static final DeferredItem<BlockItem> CHUNK_ANCHOR =
            WorkhandTools.ITEMS.registerSimpleBlockItem("chunk_anchor", ModBlocks.CHUNK_ANCHOR);

// Stone Pickaxes
    public static final DeferredItem<WorkhandPickaxeItem> STONE_WORKHAND_PICKAXE =
            WorkhandTools.ITEMS.registerItem("stone_workhand_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.STONE_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.STONE_WORKHAND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandPickaxeItem> STONE_WORKHAND_ADVANCED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("stone_workhand_advanced_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.STONE_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.STONE_WORKHAND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandPickaxeItem> STONE_WORKHAND_EXPERT_PICKAXE =
            WorkhandTools.ITEMS.registerItem("stone_workhand_expert_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.STONE_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.STONE_WORKHAND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandPickaxeItem> STONE_WORKHAND_PROFESSIONAL_PICKAXE =
            WorkhandTools.ITEMS.registerItem("stone_workhand_professional_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.STONE_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.STONE_WORKHAND, 1.0F, -2.8F)));

    // Iron Pickaxes
    public static final DeferredItem<WorkhandPickaxeItem> IRON_WORKHAND_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.IRON_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.IRON_WORKHAND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandPickaxeItem> IRON_WORKHAND_ADVANCED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_advanced_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.IRON_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.IRON_WORKHAND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandPickaxeItem> IRON_WORKHAND_EXPERT_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_expert_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.IRON_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.IRON_WORKHAND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandPickaxeItem> IRON_WORKHAND_PROFESSIONAL_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_professional_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.IRON_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.IRON_WORKHAND, 1.0F, -2.8F)));

    // Diamond Pickaxes
    public static final DeferredItem<WorkhandPickaxeItem> DIAMOND_WORKHAND_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.DIAMOND_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.DIAMOND_WORKHAND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandPickaxeItem> DIAMOND_WORKHAND_ADVANCED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_advanced_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.DIAMOND_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.DIAMOND_WORKHAND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandPickaxeItem> DIAMOND_WORKHAND_EXPERT_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_expert_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.DIAMOND_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.DIAMOND_WORKHAND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandPickaxeItem> DIAMOND_WORKHAND_PROFESSIONAL_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_professional_pickaxe",
                    props -> new WorkhandPickaxeItem(ModToolMaterials.DIAMOND_WORKHAND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.DIAMOND_WORKHAND, 1.0F, -2.8F)));

    // Improved Pickaxes (vein mining). Same grade as their base counterpart (advanced = GRADE_2,
    // professional = GRADE_4) but with the improved tool material for much higher durability.
    public static final DeferredItem<WorkhandVeinPickaxeItem> IRON_WORKHAND_ADVANCED_IMPROVED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_advanced_improved_pickaxe",
                    props -> new WorkhandVeinPickaxeItem(ModToolMaterials.IMPROVED_IRON, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.IMPROVED_IRON, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandVeinPickaxeItem> IRON_WORKHAND_PROFESSIONAL_IMPROVED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_professional_improved_pickaxe",
                    props -> new WorkhandVeinPickaxeItem(ModToolMaterials.IMPROVED_IRON, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.IMPROVED_IRON, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandVeinPickaxeItem> DIAMOND_WORKHAND_ADVANCED_IMPROVED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_advanced_improved_pickaxe",
                    props -> new WorkhandVeinPickaxeItem(ModToolMaterials.IMPROVED_DIAMOND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.IMPROVED_DIAMOND, 1.0F, -2.8F)));

    public static final DeferredItem<WorkhandVeinPickaxeItem> DIAMOND_WORKHAND_PROFESSIONAL_IMPROVED_PICKAXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_professional_improved_pickaxe",
                    props -> new WorkhandVeinPickaxeItem(ModToolMaterials.IMPROVED_DIAMOND, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.IMPROVED_DIAMOND, 1.0F, -2.8F)));

    // Stone Shovels
    public static final DeferredHolder<Item, ? extends Item> STONE_WORKHAND_SHOVEL =
            WorkhandTools.ITEMS.registerItem("stone_workhand_shovel",
                    props -> new ShovelItem(ModToolMaterials.STONE_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.STONE_WORKHAND, 1.5F, -3.0F)));

    public static final DeferredHolder<Item, ? extends Item> STONE_WORKHAND_ADVANCED_SHOVEL =
            WorkhandTools.ITEMS.registerItem("stone_workhand_advanced_shovel",
                    props -> new ShovelItem(ModToolMaterials.STONE_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.STONE_WORKHAND, 1.5F, -3.0F)));

    public static final DeferredHolder<Item, ? extends Item> STONE_WORKHAND_EXPERT_SHOVEL =
            WorkhandTools.ITEMS.registerItem("stone_workhand_expert_shovel",
                    props -> new ShovelItem(ModToolMaterials.STONE_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.STONE_WORKHAND, 1.5F, -3.0F)));

    public static final DeferredHolder<Item, ? extends Item> STONE_WORKHAND_PROFESSIONAL_SHOVEL =
            WorkhandTools.ITEMS.registerItem("stone_workhand_professional_shovel",
                    props -> new ShovelItem(ModToolMaterials.STONE_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.STONE_WORKHAND, 1.5F, -3.0F)));

    // Iron Shovels
    public static final DeferredHolder<Item, ? extends Item> IRON_WORKHAND_SHOVEL =
            WorkhandTools.ITEMS.registerItem("iron_workhand_shovel",
                    props -> new ShovelItem(ModToolMaterials.IRON_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.IRON_WORKHAND, 1.5F, -3.0F)));

    public static final DeferredHolder<Item, ? extends Item> IRON_WORKHAND_ADVANCED_SHOVEL =
            WorkhandTools.ITEMS.registerItem("iron_workhand_advanced_shovel",
                    props -> new ShovelItem(ModToolMaterials.IRON_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.IRON_WORKHAND, 1.5F, -3.0F)));

    public static final DeferredHolder<Item, ? extends Item> IRON_WORKHAND_EXPERT_SHOVEL =
            WorkhandTools.ITEMS.registerItem("iron_workhand_expert_shovel",
                    props -> new ShovelItem(ModToolMaterials.IRON_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.IRON_WORKHAND, 1.5F, -3.0F)));

    public static final DeferredHolder<Item, ? extends Item> IRON_WORKHAND_PROFESSIONAL_SHOVEL =
            WorkhandTools.ITEMS.registerItem("iron_workhand_professional_shovel",
                    props -> new ShovelItem(ModToolMaterials.IRON_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.IRON_WORKHAND, 1.5F, -3.0F)));

    // Diamond Shovels
    public static final DeferredHolder<Item, ? extends Item> DIAMOND_WORKHAND_SHOVEL =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_shovel",
                    props -> new ShovelItem(ModToolMaterials.DIAMOND_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.DIAMOND_WORKHAND, 1.5F, -3.0F)));

    public static final DeferredHolder<Item, ? extends Item> DIAMOND_WORKHAND_ADVANCED_SHOVEL =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_advanced_shovel",
                    props -> new ShovelItem(ModToolMaterials.DIAMOND_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.DIAMOND_WORKHAND, 1.5F, -3.0F)));

    public static final DeferredHolder<Item, ? extends Item> DIAMOND_WORKHAND_EXPERT_SHOVEL =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_expert_shovel",
                    props -> new ShovelItem(ModToolMaterials.DIAMOND_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.DIAMOND_WORKHAND, 1.5F, -3.0F)));

    public static final DeferredHolder<Item, ? extends Item> DIAMOND_WORKHAND_PROFESSIONAL_SHOVEL =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_professional_shovel",
                    props -> new ShovelItem(ModToolMaterials.DIAMOND_WORKHAND, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.DIAMOND_WORKHAND, 1.5F, -3.0F)));

    // Workhand Axes (tree felling). Right-click toggles felling; see TreeFellingHandler.
    public static final DeferredItem<WorkhandAxeItem> IRON_WORKHAND_AXE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_axe",
                    props -> new WorkhandAxeItem(ModToolMaterials.IMPROVED_IRON, props),
                    new Item.Properties().attributes(AxeItem.createAttributes(ModToolMaterials.IMPROVED_IRON, 6.0F, -3.1F)));

    public static final DeferredItem<WorkhandAxeItem> DIAMOND_WORKHAND_AXE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_axe",
                    props -> new WorkhandAxeItem(ModToolMaterials.IMPROVED_DIAMOND, props),
                    new Item.Properties().attributes(AxeItem.createAttributes(ModToolMaterials.IMPROVED_DIAMOND, 5.0F, -3.0F)));

    // Workhand Hoes (crop harvesting). See CropHarvestHandler.
    public static final DeferredItem<WorkhandHoeItem> IRON_WORKHAND_HOE =
            WorkhandTools.ITEMS.registerItem("iron_workhand_hoe",
                    props -> new WorkhandHoeItem(ModToolMaterials.IMPROVED_IRON, props),
                    new Item.Properties().attributes(HoeItem.createAttributes(ModToolMaterials.IMPROVED_IRON, 0.0F, -3.0F)));

    public static final DeferredItem<WorkhandHoeItem> DIAMOND_WORKHAND_HOE =
            WorkhandTools.ITEMS.registerItem("diamond_workhand_hoe",
                    props -> new WorkhandHoeItem(ModToolMaterials.IMPROVED_DIAMOND, props),
                    new Item.Properties().attributes(HoeItem.createAttributes(ModToolMaterials.IMPROVED_DIAMOND, 0.0F, -3.0F)));

    // Kennestroyer Ultimate Tools
    public static final DeferredItem<KennestroyerPickaxeItem> KENNESTROYER_PICKAXE =
            WorkhandTools.ITEMS.registerItem("kennestroyer_pickaxe",
                    props -> new KennestroyerPickaxeItem(ModToolMaterials.KENNESTROYER, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.KENNESTROYER, 1.0F, -2.8F)));

    public static final DeferredItem<KennestroyerShovelItem> KENNESTROYER_SHOVEL =
            WorkhandTools.ITEMS.registerItem("kennestroyer_shovel",
                    props -> new KennestroyerShovelItem(ModToolMaterials.KENNESTROYER, props),
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterials.KENNESTROYER, 1.5F, -3.0F)));

    // Reinforced Deepslate Pickaxe - single-purpose pickaxe
    public static final DeferredItem<ReinforcedDeepslatePickaxeItem> REINFORCED_DEEPSLATE_PICKAXE =
            WorkhandTools.ITEMS.registerItem("reinforced_deepslate_pickaxe",
                    props -> new ReinforcedDeepslatePickaxeItem(ModToolMaterials.REINFORCED_DEEPSLATE, props),
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterials.REINFORCED_DEEPSLATE, 1.0F, -2.8F)));

    // Tape Measure. Standalone utility tool (no Grade, not in PICKAXES/SHOVELS/AXES). Right-click
    // set/finish a measurement box, shift+right-click undo; see TapeMeasureItem/TapeMeasureHandler.
    public static final DeferredItem<TapeMeasureItem> TAPE_MEASURE =
            WorkhandTools.ITEMS.registerItem("tape_measure",
                    props -> new TapeMeasureItem(props), new Item.Properties());

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
            DIAMOND_WORKHAND_PROFESSIONAL_PICKAXE,
            IRON_WORKHAND_ADVANCED_IMPROVED_PICKAXE,
            IRON_WORKHAND_PROFESSIONAL_IMPROVED_PICKAXE,
            DIAMOND_WORKHAND_ADVANCED_IMPROVED_PICKAXE,
            DIAMOND_WORKHAND_PROFESSIONAL_IMPROVED_PICKAXE,
            KENNESTROYER_PICKAXE,
            REINFORCED_DEEPSLATE_PICKAXE
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
            DIAMOND_WORKHAND_PROFESSIONAL_SHOVEL,
            KENNESTROYER_SHOVEL
    );

    public static final List<DeferredHolder<Item, ? extends Item>> AXES = List.of(
            IRON_WORKHAND_AXE,
            DIAMOND_WORKHAND_AXE
    );

    public static final List<DeferredHolder<Item, ? extends Item>> HOES = List.of(
            IRON_WORKHAND_HOE,
            DIAMOND_WORKHAND_HOE
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

    static void buildLookups() {
        BY_ID.put("robust_stick", ROBUST_STICK);

        BY_ID.put("stone_workhand_pickaxe", STONE_WORKHAND_PICKAXE);
        BY_ID.put("stone_workhand_advanced_pickaxe", STONE_WORKHAND_ADVANCED_PICKAXE);
        BY_ID.put("stone_workhand_expert_pickaxe", STONE_WORKHAND_EXPERT_PICKAXE);
        BY_ID.put("stone_workhand_professional_pickaxe", STONE_WORKHAND_PROFESSIONAL_PICKAXE);
        BY_ID.put("iron_workhand_pickaxe", IRON_WORKHAND_PICKAXE);
        BY_ID.put("iron_workhand_advanced_pickaxe", IRON_WORKHAND_ADVANCED_PICKAXE);
        BY_ID.put("iron_workhand_expert_pickaxe", IRON_WORKHAND_EXPERT_PICKAXE);
        BY_ID.put("iron_workhand_professional_pickaxe", IRON_WORKHAND_PROFESSIONAL_PICKAXE);
        BY_ID.put("diamond_workhand_pickaxe", DIAMOND_WORKHAND_PICKAXE);
        BY_ID.put("diamond_workhand_advanced_pickaxe", DIAMOND_WORKHAND_ADVANCED_PICKAXE);
        BY_ID.put("diamond_workhand_expert_pickaxe", DIAMOND_WORKHAND_EXPERT_PICKAXE);
        BY_ID.put("diamond_workhand_professional_pickaxe", DIAMOND_WORKHAND_PROFESSIONAL_PICKAXE);
        BY_ID.put("iron_workhand_advanced_improved_pickaxe", IRON_WORKHAND_ADVANCED_IMPROVED_PICKAXE);
        BY_ID.put("iron_workhand_professional_improved_pickaxe", IRON_WORKHAND_PROFESSIONAL_IMPROVED_PICKAXE);
        BY_ID.put("diamond_workhand_advanced_improved_pickaxe", DIAMOND_WORKHAND_ADVANCED_IMPROVED_PICKAXE);
        BY_ID.put("diamond_workhand_professional_improved_pickaxe", DIAMOND_WORKHAND_PROFESSIONAL_IMPROVED_PICKAXE);

        BY_ID.put("stone_workhand_shovel", STONE_WORKHAND_SHOVEL);
        BY_ID.put("stone_workhand_advanced_shovel", STONE_WORKHAND_ADVANCED_SHOVEL);
        BY_ID.put("stone_workhand_expert_shovel", STONE_WORKHAND_EXPERT_SHOVEL);
        BY_ID.put("stone_workhand_professional_shovel", STONE_WORKHAND_PROFESSIONAL_SHOVEL);
        BY_ID.put("iron_workhand_shovel", IRON_WORKHAND_SHOVEL);
        BY_ID.put("iron_workhand_advanced_shovel", IRON_WORKHAND_ADVANCED_SHOVEL);
        BY_ID.put("iron_workhand_expert_shovel", IRON_WORKHAND_EXPERT_SHOVEL);
        BY_ID.put("iron_workhand_professional_shovel", IRON_WORKHAND_PROFESSIONAL_SHOVEL);
        BY_ID.put("diamond_workhand_shovel", DIAMOND_WORKHAND_SHOVEL);
        BY_ID.put("diamond_workhand_advanced_shovel", DIAMOND_WORKHAND_ADVANCED_SHOVEL);
        BY_ID.put("diamond_workhand_expert_shovel", DIAMOND_WORKHAND_EXPERT_SHOVEL);
        BY_ID.put("diamond_workhand_professional_shovel", DIAMOND_WORKHAND_PROFESSIONAL_SHOVEL);
        BY_ID.put("iron_workhand_axe", IRON_WORKHAND_AXE);
        BY_ID.put("diamond_workhand_axe", DIAMOND_WORKHAND_AXE);
        BY_ID.put("tape_measure", TAPE_MEASURE);

        BY_ID.put("iron_workhand_hoe", IRON_WORKHAND_HOE);
        BY_ID.put("diamond_workhand_hoe", DIAMOND_WORKHAND_HOE);
        BY_ID.put("kennestroyer_pickaxe", KENNESTROYER_PICKAXE);
        BY_ID.put("kennestroyer_shovel", KENNESTROYER_SHOVEL);
        BY_ID.put("reinforced_deepslate_pickaxe", REINFORCED_DEEPSLATE_PICKAXE);

        ITEM_GRADES.put(STONE_WORKHAND_PICKAXE.get(), Grade.GRADE_1);
        ITEM_GRADES.put(STONE_WORKHAND_ADVANCED_PICKAXE.get(), Grade.GRADE_2);
        ITEM_GRADES.put(STONE_WORKHAND_EXPERT_PICKAXE.get(), Grade.GRADE_3);
        ITEM_GRADES.put(STONE_WORKHAND_PROFESSIONAL_PICKAXE.get(), Grade.GRADE_4);
        ITEM_GRADES.put(IRON_WORKHAND_PICKAXE.get(), Grade.GRADE_1);
        ITEM_GRADES.put(IRON_WORKHAND_ADVANCED_PICKAXE.get(), Grade.GRADE_2);
        ITEM_GRADES.put(IRON_WORKHAND_EXPERT_PICKAXE.get(), Grade.GRADE_3);
        ITEM_GRADES.put(IRON_WORKHAND_PROFESSIONAL_PICKAXE.get(), Grade.GRADE_4);
        ITEM_GRADES.put(DIAMOND_WORKHAND_PICKAXE.get(), Grade.GRADE_1);
        ITEM_GRADES.put(DIAMOND_WORKHAND_ADVANCED_PICKAXE.get(), Grade.GRADE_2);
        ITEM_GRADES.put(DIAMOND_WORKHAND_EXPERT_PICKAXE.get(), Grade.GRADE_3);
        ITEM_GRADES.put(DIAMOND_WORKHAND_PROFESSIONAL_PICKAXE.get(), Grade.GRADE_4);
        ITEM_GRADES.put(IRON_WORKHAND_ADVANCED_IMPROVED_PICKAXE.get(), Grade.GRADE_2);
        ITEM_GRADES.put(IRON_WORKHAND_PROFESSIONAL_IMPROVED_PICKAXE.get(), Grade.GRADE_4);
        ITEM_GRADES.put(DIAMOND_WORKHAND_ADVANCED_IMPROVED_PICKAXE.get(), Grade.GRADE_2);
        ITEM_GRADES.put(DIAMOND_WORKHAND_PROFESSIONAL_IMPROVED_PICKAXE.get(), Grade.GRADE_4);
        ITEM_GRADES.put(KENNESTROYER_PICKAXE.get(), Grade.KENNESTROYER);
        ITEM_GRADES.put(KENNESTROYER_SHOVEL.get(), Grade.KENNESTROYER);

        ITEM_GRADES.put(STONE_WORKHAND_SHOVEL.get(), Grade.GRADE_1);
        ITEM_GRADES.put(STONE_WORKHAND_ADVANCED_SHOVEL.get(), Grade.GRADE_2);
        ITEM_GRADES.put(STONE_WORKHAND_EXPERT_SHOVEL.get(), Grade.GRADE_3);
        ITEM_GRADES.put(STONE_WORKHAND_PROFESSIONAL_SHOVEL.get(), Grade.GRADE_4);
        ITEM_GRADES.put(IRON_WORKHAND_SHOVEL.get(), Grade.GRADE_1);
        ITEM_GRADES.put(IRON_WORKHAND_ADVANCED_SHOVEL.get(), Grade.GRADE_2);
        ITEM_GRADES.put(IRON_WORKHAND_EXPERT_SHOVEL.get(), Grade.GRADE_3);
        ITEM_GRADES.put(IRON_WORKHAND_PROFESSIONAL_SHOVEL.get(), Grade.GRADE_4);
        ITEM_GRADES.put(DIAMOND_WORKHAND_SHOVEL.get(), Grade.GRADE_1);
        ITEM_GRADES.put(DIAMOND_WORKHAND_ADVANCED_SHOVEL.get(), Grade.GRADE_2);
        ITEM_GRADES.put(DIAMOND_WORKHAND_EXPERT_SHOVEL.get(), Grade.GRADE_3);
        ITEM_GRADES.put(DIAMOND_WORKHAND_PROFESSIONAL_SHOVEL.get(), Grade.GRADE_4);
    }
}
