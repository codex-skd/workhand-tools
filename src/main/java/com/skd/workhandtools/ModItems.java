package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class ModItems {
    private ModItems() {
    }

    private static final Map<String, DeferredHolder<Item, ? extends Item>> BY_ID = new LinkedHashMap<>();
    private static final Map<Item, Grade> ITEM_GRADES = new HashMap<>();

    private record MaterialData(String prefix, String displayName, ToolMaterial material, boolean fireResistant) {
    }

    private static final List<MaterialData> MATERIALS = List.of(
            new MaterialData("wooden", "Wooden", ToolMaterial.WOOD, false),
            new MaterialData("stone", "Stone", ToolMaterial.STONE, false),
            new MaterialData("copper", "Copper", ModToolMaterials.COPPER, false),
            new MaterialData("deepslate", "Deepslate", ModToolMaterials.DEEPSLATE, false),
            new MaterialData("iron", "Iron", ToolMaterial.IRON, false),
            new MaterialData("blackstone", "Blackstone", ModToolMaterials.BLACKSTONE, false),
            new MaterialData("golden", "Golden", ToolMaterial.GOLD, false),
            new MaterialData("diamond", "Diamond", ToolMaterial.DIAMOND, false),
            new MaterialData("obsidian", "Obsidian", ModToolMaterials.OBSIDIAN, false),
            new MaterialData("netherite", "Netherite", ToolMaterial.NETHERITE, true));

    private record GradeData(String suffix, String displaySuffix) {
    }

    private static final List<GradeData> GRADES = List.of(
            new GradeData("", ""),
            new GradeData("advanced", " Advanced"),
            new GradeData("expert", " Expert"),
            new GradeData("professional", " Professional"));

    public static final DeferredHolder<Item, ? extends Item> ROBUST_STICK =
            WorkhandTools.ITEMS.registerItem("robust_stick", Item::new, Item.Properties::new);

    // Creative tab order: pickaxes first, then shovels; within each tool by material
    // progression (Wood -> Netherite) and by grade ascending (1 -> 4).
    public static final List<DeferredHolder<Item, ? extends Item>> PICKAXES =
            registerTools("pickaxe", "Pickaxe", (material, properties) -> new Item(properties.pickaxe(material, 1.0F, -2.8F)));
    public static final List<DeferredHolder<Item, ? extends Item>> SHOVELS =
            registerTools("shovel", "Shovel", (material, properties) -> new ShovelItem(material, 1.5F, -3.0F, properties));

    public static DeferredHolder<Item, ? extends Item> get(String id) {
        return BY_ID.get(id);
    }

    public static boolean isWorkhandTool(ItemStack stack) {
        return !stack.isEmpty() && ITEM_GRADES.containsKey(stack.getItem());
    }

    public static Grade gradeOf(ItemStack stack) {
        return stack.isEmpty() ? null : ITEM_GRADES.get(stack.getItem());
    }

    // Populated once the registry is filled (from commonSetup). Holder order is
    // material-major, grade-minor, so grade is index modulo grade count.
    static void buildLookups() {
        try {
            for (int i = 0; i < PICKAXES.size(); i++) {
                DeferredHolder<Item, ? extends Item> holder = PICKAXES.get(i);
                if (holder != null && holder.isBound()) {
                    Item item = holder.get();
                    if (item != null) {
                        ITEM_GRADES.put(item, Grade.values()[i % GRADES.size()]);
                    }
                }
            }
            for (int i = 0; i < SHOVELS.size(); i++) {
                DeferredHolder<Item, ? extends Item> holder = SHOVELS.get(i);
                if (holder != null && holder.isBound()) {
                    Item item = holder.get();
                    if (item != null) {
                        ITEM_GRADES.put(item, Grade.values()[i % GRADES.size()]);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to build lookups", e);
        }
    }

    private static List<DeferredHolder<Item, ? extends Item>> registerTools(
            String tool, String toolDisplayName, BiFunction<ToolMaterial, Item.Properties, ? extends Item> factory) {
        List<DeferredHolder<Item, ? extends Item>> holders = new ArrayList<>();
        for (MaterialData material : MATERIALS) {
            for (GradeData grade : GRADES) {
                String id = id(material, grade, tool);
                ToolMaterial toolMaterial = material.material();
                boolean fireResistant = material.fireResistant();
                // registerItem sets the item id on Item.Properties before the factory runs (26.2 API);
                // the plain register(String, Supplier) path leaves the id unset and throws "Item id not set".
                DeferredHolder<Item, ? extends Item> holder = WorkhandTools.ITEMS.registerItem(
                        id,
                        (properties) -> factory.apply(toolMaterial, properties),
                        () -> {
                            Item.Properties properties = new Item.Properties();
                            if (fireResistant) {
                                properties.fireResistant();
                            }
                            return properties;
                        });
                holders.add(holder);
                BY_ID.put(id, holder);
            }
        }
        return holders;
    }

    static String id(MaterialData material, GradeData grade, String tool) {
        return material.prefix() + "_workhand" + (grade.suffix().isEmpty() ? "" : "_" + grade.suffix()) + "_" + tool;
    }
}
