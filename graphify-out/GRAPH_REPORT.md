# Graph Report - .  (2026-08-05)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 97 nodes · 148 edges · 13 communities (12 shown, 1 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 1 edges (avg confidence: 0.8)
- Token cost: 455 input · 122 output

## Graph Freshness
- Built from commit: `e6a34a87`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Block Break Handling
- Mod Initialization
- Item Data Management
- Grade System
- AoE Mode
- Tool Materials
- Data Components
- Client-Side Workhand Tools
- Configuration Management
- Build Script
- Mod Icon

## God Nodes (most connected - your core abstractions)
1. `ModItems` - 13 edges
2. `WorkhandTools` - 10 edges
3. `AoEMiningHandler` - 8 edges
4. `AoEMode` - 8 edges
5. `Grade` - 8 edges
6. `ModDataComponents` - 6 edges
7. `Config` - 4 edges
8. `MaterialData` - 4 edges
9. `ModToolMaterials` - 4 edges
10. `WorkhandToolsClient` - 4 edges

## Surprising Connections (you probably didn't know these)
- `depth()` --references--> `AoEMode`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/Grade.java → src/main/java/com/skd/workhandtools/AoEMode.java
- `ModDataComponents` --references--> `AoEMode`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModDataComponents.java → src/main/java/com/skd/workhandtools/AoEMode.java
- `ModItems` --references--> `Grade`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModItems.java → src/main/java/com/skd/workhandtools/Grade.java

## Import Cycles
- None detected.

## Communities (13 total, 1 thin omitted)

### Community 0 - "Block Break Handling"
Cohesion: 0.23
Nodes (10): BlockPos, BlockState, BreakBlockEvent, ItemTooltipEvent, Level, Player, RightClickItem, AoEMiningHandler (+2 more)

### Community 1 - "Mod Initialization"
Cohesion: 0.19
Nodes (12): CreativeModeTab, FMLCommonSetupEvent, IEventBus, Items, Logger, ServerStartingEvent, DeferredHolder, DeferredRegister (+4 more)

### Community 2 - "Item Data Management"
Cohesion: 0.30
Nodes (7): GradeData, DeferredHolder, Item, ItemStack, ToolMaterial, MaterialData, ModItems

### Community 3 - "Grade System"
Cohesion: 0.20
Nodes (6): depth(), Grade, GRADE_1, GRADE_2, GRADE_3, GRADE_4

### Community 4 - "AoE Mode"
Cohesion: 0.32
Nodes (7): Override, AoEMode, CUBIC, FLAT, fromName(), getSerializedName(), StringRepresentable

### Community 5 - "Tool Materials"
Cohesion: 0.43
Nodes (4): Item, ToolMaterial, ModToolMaterials, TagKey

### Community 6 - "Data Components"
Cohesion: 0.53
Nodes (4): DataComponentType, DeferredHolder, DeferredRegister, ModDataComponents

### Community 7 - "Client-Side Workhand Tools"
Cohesion: 0.53
Nodes (4): EventBusSubscriber, Mod, ModContainer, WorkhandToolsClient

### Community 8 - "Configuration Management"
Cohesion: 0.50
Nodes (4): Builder, DoubleValue, ModConfigSpec, Config

### Community 9 - "Build Script"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **7 isolated node(s):** `CUBIC`, `FLAT`, `GRADE_1`, `GRADE_2`, `GRADE_3` (+2 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **1 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AoEMode` connect `AoE Mode` to `Block Break Handling`, `Grade System`, `Data Components`?**
  _High betweenness centrality (0.184) - this node is a cross-community bridge._
- **Why does `ModItems` connect `Item Data Management` to `Mod Initialization`, `Grade System`?**
  _High betweenness centrality (0.175) - this node is a cross-community bridge._
- **Why does `Grade` connect `Grade System` to `Block Break Handling`, `Item Data Management`?**
  _High betweenness centrality (0.166) - this node is a cross-community bridge._
- **What connects `CUBIC`, `FLAT`, `GRADE_1` to the rest of the system?**
  _7 weakly-connected nodes found - possible documentation gaps or missing edges._