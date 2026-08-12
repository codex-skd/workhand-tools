# Workhand Tools

Workhand Tools is a Minecraft 26.2 (NeoForge) mod that adds upgraded pickaxes and shovels with area mining capabilities.

Each material tier (Stone, Iron, Diamond) offers 4 grades of tools — from basic 3x3 flat mining to professional 5x5x5 cubic excavation. All tools match the durability, mining speed, and enchantability of their vanilla counterparts.

## Features

- **12 pickaxes + 12 shovels** across 3 materials x 4 grades
- **Area mining** progression: 3x3x1 -> 3x3x3 -> 5x5x1 -> 5x5x5
- **Cubic / Flat mode toggle** on Advanced and Professional grades
- **Area preview highlight** showing exactly which blocks will be mined
- **10 language translations** including Spanish, French, German, Portuguese, Russian, Chinese, Japanese, Italian, and Korean
- **Descriptive tooltips** showing area dimensions and controls
- Full enchantment support (Efficiency, Fortune, Silk Touch, Unbreaking, Mending)
- Vanilla-standard durability, speed, and combat stats per material
- Custom crafting recipes with progression (Robust Stick component for Expert/Professional)
- **Tape Measure** utility item: right-click to set a measurement box, right-click again to finish it; live-updating wireframe with per-axis length labels while the box is in progress. Shift+right-click undoes the last box.

## Tools

<details>
<summary><strong>Full item list — durability and purpose (32 items)</strong></summary>

### Pickaxes & Shovels (24 — 3 materials × 4 grades)

| | Name | Material | Durability | Purpose |
|---|---|---|:---:|---|
| <img src="src/main/resources/assets/workhand_tools/textures/item/stone_workhand_pickaxe.png" width="32"> | Stone Workhand Pickaxe | Stone | 131 | Area mining 3x3x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/stone_workhand_advanced_pickaxe.png" width="32"> | Stone Workhand Advanced Pickaxe | Stone | 131 | Area mining 3x3x3 cubic / 3x3x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/stone_workhand_expert_pickaxe.png" width="32"> | Stone Workhand Expert Pickaxe | Stone | 131 | Area mining 5x5x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/stone_workhand_professional_pickaxe.png" width="32"> | Stone Workhand Professional Pickaxe | Stone | 131 | Area mining 5x5x5 cubic / 5x5x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_pickaxe.png" width="32"> | Iron Workhand Pickaxe | Iron | 250 | Area mining 3x3x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_advanced_pickaxe.png" width="32"> | Iron Workhand Advanced Pickaxe | Iron | 250 | Area mining 3x3x3 cubic / 3x3x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_expert_pickaxe.png" width="32"> | Iron Workhand Expert Pickaxe | Iron | 250 | Area mining 5x5x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_professional_pickaxe.png" width="32"> | Iron Workhand Professional Pickaxe | Iron | 250 | Area mining 5x5x5 cubic / 5x5x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_pickaxe.png" width="32"> | Diamond Workhand Pickaxe | Diamond | 1561 | Area mining 3x3x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_advanced_pickaxe.png" width="32"> | Diamond Workhand Advanced Pickaxe | Diamond | 1561 | Area mining 3x3x3 cubic / 3x3x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_expert_pickaxe.png" width="32"> | Diamond Workhand Expert Pickaxe | Diamond | 1561 | Area mining 5x5x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_professional_pickaxe.png" width="32"> | Diamond Workhand Professional Pickaxe | Diamond | 1561 | Area mining 5x5x5 cubic / 5x5x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/stone_workhand_shovel.png" width="32"> | Stone Workhand Shovel | Stone | 131 | Area digging 3x3x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/stone_workhand_advanced_shovel.png" width="32"> | Stone Workhand Advanced Shovel | Stone | 131 | Area digging 3x3x3 cubic / 3x3x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/stone_workhand_expert_shovel.png" width="32"> | Stone Workhand Expert Shovel | Stone | 131 | Area digging 5x5x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/stone_workhand_professional_shovel.png" width="32"> | Stone Workhand Professional Shovel | Stone | 131 | Area digging 5x5x5 cubic / 5x5x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_shovel.png" width="32"> | Iron Workhand Shovel | Iron | 250 | Area digging 3x3x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_advanced_shovel.png" width="32"> | Iron Workhand Advanced Shovel | Iron | 250 | Area digging 3x3x3 cubic / 3x3x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_expert_shovel.png" width="32"> | Iron Workhand Expert Shovel | Iron | 250 | Area digging 5x5x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_professional_shovel.png" width="32"> | Iron Workhand Professional Shovel | Iron | 250 | Area digging 5x5x5 cubic / 5x5x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_shovel.png" width="32"> | Diamond Workhand Shovel | Diamond | 1561 | Area digging 3x3x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_advanced_shovel.png" width="32"> | Diamond Workhand Advanced Shovel | Diamond | 1561 | Area digging 3x3x3 cubic / 3x3x1 flat (right-click toggles mode) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_expert_shovel.png" width="32"> | Diamond Workhand Expert Shovel | Diamond | 1561 | Area digging 5x5x1 (single layer) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_professional_shovel.png" width="32"> | Diamond Workhand Professional Shovel | Diamond | 1561 | Area digging 5x5x5 cubic / 5x5x1 flat (right-click toggles mode) |

### Improved Pickaxes — vein mining (4)

| | Name | Material | Durability | Purpose |
|---|---|---|:---:|---|
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_advanced_improved_pickaxe.png" width="32"> | Iron Workhand Advanced Improved Pickaxe | Improved Iron | 750 | 3x3x3/3x3x1 area mining + vein mining: breaking an ore chain-breaks all connected blocks of the same ore (up to 128) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_professional_improved_pickaxe.png" width="32"> | Iron Workhand Professional Improved Pickaxe | Improved Iron | 750 | 5x5x5/5x5x1 area mining + vein mining |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_advanced_improved_pickaxe.png" width="32"> | Diamond Workhand Advanced Improved Pickaxe | Improved Diamond | 4683 | 3x3x3/3x3x1 area mining + vein mining |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_professional_improved_pickaxe.png" width="32"> | Diamond Workhand Professional Improved Pickaxe | Improved Diamond | 4683 | 5x5x5/5x5x1 area mining + vein mining |

### Workhand Axes — tree felling (2)

| | Name | Material | Durability | Purpose |
|---|---|---|:---:|---|
| <img src="src/main/resources/assets/workhand_tools/textures/item/iron_workhand_axe.png" width="32"> | Iron Workhand Axe | Improved Iron | 750 | Toggleable tree felling (right-click): breaking a log fells the whole connected tree (up to 256 blocks) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/diamond_workhand_axe.png" width="32"> | Diamond Workhand Axe | Improved Diamond | 4683 | Toggleable tree felling (right-click) |

### Utility items (2)

| | Name | Material | Durability | Purpose |
|---|---|---|:---:|---|
| <img src="src/main/resources/assets/workhand_tools/textures/item/robust_stick.png" width="32"> | Robust Stick | — | No durability (ingredient) | Crafting ingredient for Expert and Professional grade tools (3-4) |
| <img src="src/main/resources/assets/workhand_tools/textures/item/tape_measure.png" width="32"> | Tape Measure | — | No durability (unbreakable) | Measures the area between two points: right-click sets the start, right-click again sets the end and draws a box with X/Y/Z lengths. Shift+right-click undoes the last measurement |

</details>

## Controls

| Action | Key |
|--------|-----|
| Area mine | Left-click (no sneaking) |
| Single block | Shift + Left-click |
| Toggle mode | Right-click (Grades 2 & 4) |
| Set / finish measurement | Right-click block (Tape Measure) |
| Undo last measurement | Shift + Right-click (Tape Measure) |

## Requirements

| Component | Version |
|---|---|
| Minecraft | 26.2 |
| NeoForge | 26.2.0.37-beta+ |
| Java | 25+ |

## Installation

1. Install [NeoForge](https://neoforge.net/) for Minecraft 26.2.
2. Download the mod jar and place it in your `mods/` folder.

## License

All Rights Reserved.

## Credits

- **Tape Measure** tool adapted from [Measurements](https://github.com/Mrbysco/Measurements) by Mrbysco (MIT License). The wireframe rendering and length-label placement were ported essentially as-is; the multi-loader plumbing was dropped to fit this single-loader NeoForge mod. The original `MeasurementBox` algorithm itself credits [MadeBaruna/BlockMeter](https://github.com/MadeBaruna/BlockMeter). The `tape_measure.png` item texture is reused from the same MIT-licensed source.

