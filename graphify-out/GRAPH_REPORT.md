# Graph Report - 26.2  (2026-08-14)

## Corpus Check
- 187 files · ~30,599 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 453 nodes · 634 edges · 76 communities (62 shown, 14 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 9 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `0df8a00c`
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
- Flujo de trabajo — Workhand Tools (NeoForge)
- 🎨 Workhand Tools — Texturas necesarias para completar el mod
- Prompts de imagen — Workhand Tools
- CurseForge — Variables del proyecto
- Changelog — Workhand Tools
- Workhand Tools
- Workhand Tools 0.0.0-beta.5
- CLAUDE.md — workhand_tools (26.2)
- Grade
- MeasurementBox
- TextColor
- WorkhandToolsClient.java
- WorkhandToolsClient.java
- AoEMiningHandler.java
- [1.1.0] - 2026-08-08
- [1.10.0] - 2026-08-12
- [1.1.2] - 2026-08-08
- [1.3.0] - 2026-08-08
- [1.6.1] - 2026-08-10
- [1.10.1] - 2026-08-12
- [1.11.0] - 2026-08-14
- [1.12.0] - 2026-08-14
- [1.1.1] - 2026-08-08
- [1.2.0] - 2026-08-08
- [1.2.1] - 2026-08-08
- [1.2.2] - 2026-08-08
- [1.2.3] - 2026-08-08
- [1.6.0] - 2026-08-10
- [1.7.0] - 2026-08-11
- [1.8.0] - 2026-08-11
- [1.9.0] - 2026-08-12

## God Nodes (most connected - your core abstractions)
1. `Changelog — Workhand Tools` - 22 edges
2. `TextColor` - 21 edges
3. `LineColor` - 20 edges
4. `MeasurementBox` - 18 edges
5. `Diseño técnico — Workhand Tools` - 17 edges
6. `ModItems` - 14 edges
7. `MiningHelper` - 12 edges
8. `Flujo de trabajo — Workhand Tools (NeoForge)` - 12 edges
9. `AoEMode` - 10 edges
10. `WorkhandTools` - 10 edges

## Surprising Connections (you probably didn't know these)
- `depth()` --references--> `AoEMode`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/Grade.java → src/main/java/com/skd/workhandtools/AoEMode.java
- `BoxHandler` --references--> `MeasurementBox`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/BoxHandler.java → src/main/java/com/skd/workhandtools/MeasurementBox.java
- `Client` --references--> `TextColor`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/TapeMeasureConfig.java → src/main/java/com/skd/workhandtools/TextColor.java
- `ModDataComponents` --references--> `AoEMode`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModDataComponents.java → src/main/java/com/skd/workhandtools/AoEMode.java
- `ModItems` --references--> `Grade`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModItems.java → src/main/java/com/skd/workhandtools/Grade.java

## Import Cycles
- None detected.

## Communities (76 total, 14 thin omitted)

### Community 1 - "Mod Initialization"
Cohesion: 0.21
Nodes (12): CreativeModeTab, FMLCommonSetupEvent, IEventBus, Items, Logger, ServerStartingEvent, DeferredHolder, DeferredRegister (+4 more)

### Community 2 - "Item Data Management"
Cohesion: 0.09
Nodes (19): DeferredItem, Item, NotNull, Grade, GRADE_1, GRADE_2, GRADE_3, GRADE_4 (+11 more)

### Community 3 - "Grade System"
Cohesion: 0.24
Nodes (9): Direction, AoEPatterns, BlockPos, Level, Player, Vec3, AreaHighlighter, SubmitCustomGeometryEvent (+1 more)

### Community 4 - "AoE Mode"
Cohesion: 0.13
Nodes (13): DataComponentType, AoEMode, CUBIC, DISABLED, FLAT, fromName(), getSerializedName(), Override (+5 more)

### Community 5 - "Tool Materials"
Cohesion: 0.14
Nodes (17): BlockState, Identifier, BlockPos, ItemStack, Level, Player, MiningHelper, TreeSearchNode (+9 more)

### Community 6 - "Data Components"
Cohesion: 0.07
Nodes (27): Anclaje vertical para patrones de 5 de alto (5×5×1 / 5×5×5), Clic izquierdo vs. clic derecho (grados 2 y 4) — confirmado, Concepto, Configuración y compatibilidad con Configured, Creative tab, Diseño técnico — Workhand Tools, Encantamientos, Grados y minado en área (AoE) (+19 more)

### Community 7 - "Client-Side Workhand Tools"
Cohesion: 0.13
Nodes (10): LoggingIn, LoggingOut, BoxHandler, BlockPos, InteractionResult, Player, Post, SubmitCustomGeometryEvent (+2 more)

### Community 8 - "Configuration Management"
Cohesion: 0.40
Nodes (5): Config, Builder, DoubleValue, IntValue, ModConfigSpec

### Community 9 - "Build Script"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 13 - "Flujo de trabajo — Workhand Tools (NeoForge)"
Cohesion: 0.14
Nodes (13): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Workhand Tools (NeoForge), Flujo por tarea, Historial de versiones del workflow (+5 more)

### Community 14 - "🎨 Workhand Tools — Texturas necesarias para completar el mod"
Cohesion: 0.13
Nodes (14): 📊 Catálogo completo — durabilidad y para qué vale cada herramienta, ⭐ Diferenciación visual por grado, 📐 Especificación técnica (las 25 texturas de 16×16), ✅ Estado actual, 🪓 Hachas Workhand — tala de árboles (2), 🪵 Item nuevo: Palo Robusto, 🪵📏 Items de utilidad (2), ⛏️ Listado completo — 24 texturas de herramienta + 1 robust_stick (+6 more)

### Community 15 - "Prompts de imagen — Workhand Tools"
Cohesion: 0.18
Nodes (10): Checklist de assets pendientes, Fase futura — arte custom (pospuesta), Logo del mod, Notas para elegir/ajustar, Prompt — icono cuadrado, principal, Prompt — variante alternativa (más plana, fiel 100% a pixel-art vanilla), Prompts de imagen — Workhand Tools, Texturas de hachas workhand (Workhand Axes) (+2 more)

### Community 16 - "CurseForge — Variables del proyecto"
Cohesion: 0.25
Nodes (7): CurseForge — Variables del proyecto, Nota, Proyecto, Rama, Tag, Tokens, Variables para script (lectura automática)

### Community 17 - "Changelog — Workhand Tools"
Cohesion: 0.22
Nodes (8): [1.0.0] - 2026-08-07, [1.10.2] - 2026-08-13, [1.4.0] - 2026-08-09, [1.5.0] - 2026-08-10, Changelog — Workhand Tools, Feature, Fix, Update

### Community 18 - "Workhand Tools"
Cohesion: 0.15
Nodes (12): Controls, Credits, Features, Improved Pickaxes — vein mining (4), Installation, License, Pickaxes & Shovels (24 — 3 materials × 4 grades), Requirements (+4 more)

### Community 19 - "Workhand Tools 0.0.0-beta.5"
Cohesion: 0.40
Nodes (4): Compatibility, Download, What's Fixed, Workhand Tools 0.0.0-beta.5

### Community 20 - "CLAUDE.md — workhand_tools (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — workhand_tools (26.2), Prioridad de instrucciones, Workflow del mod

### Community 41 - "Grade"
Cohesion: 0.08
Nodes (27): EnumValue, getColor(), DyeColor, LineColor, BLACK, BLUE, BROWN, CYAN (+19 more)

### Community 46 - "MeasurementBox"
Cohesion: 0.22
Nodes (14): AABB, Camera, Component, Matrix4fc, PoseStack, ResourceKey, BlockPos, DyeColor (+6 more)

### Community 47 - "TextColor"
Cohesion: 0.09
Nodes (22): Axis, getColor(), DyeColor, TextColor, BLACK, BLUE, BROWN, CYAN (+14 more)

### Community 49 - "WorkhandToolsClient.java"
Cohesion: 0.27
Nodes (8): NeighborNotifyEvent, ServerLevel, BlockPos, Post, SubscribeEvent, LeafDecayHandler, LeafKey, Unload

### Community 52 - "WorkhandToolsClient.java"
Cohesion: 0.43
Nodes (4): EventBusSubscriber, Mod, ModContainer, WorkhandToolsClient

### Community 56 - "AoEMiningHandler.java"
Cohesion: 0.33
Nodes (5): AoEMiningHandler, BreakBlockEvent, ItemTooltipEvent, RightClickItem, SubscribeEvent

### Community 58 - "[1.1.0] - 2026-08-08"
Cohesion: 0.40
Nodes (5): [1.1.0] - 2026-08-08, Feature, Fix, Refactor, Technical

### Community 59 - "[1.10.0] - 2026-08-12"
Cohesion: 0.67
Nodes (3): [1.10.0] - 2026-08-12, Balance, Documentation

### Community 60 - "[1.1.2] - 2026-08-08"
Cohesion: 0.67
Nodes (3): [1.1.2] - 2026-08-08, Fix, Technical

### Community 61 - "[1.3.0] - 2026-08-08"
Cohesion: 0.67
Nodes (3): [1.3.0] - 2026-08-08, Included Features, Release

### Community 62 - "[1.6.1] - 2026-08-10"
Cohesion: 0.67
Nodes (3): [1.6.1] - 2026-08-10, Fix, Update

## Knowledge Gaps
- **145 isolated node(s):** `CUBIC`, `FLAT`, `DISABLED`, `GRADE_1`, `GRADE_2` (+140 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **14 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TextColor` connect `TextColor` to `Grade`?**
  _High betweenness centrality (0.104) - this node is a cross-community bridge._
- **Why does `Client` connect `Grade` to `TextColor`?**
  _High betweenness centrality (0.066) - this node is a cross-community bridge._
- **What connects `CUBIC`, `FLAT`, `DISABLED` to the rest of the system?**
  _145 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Item Data Management` be split into smaller, more focused modules?**
  _Cohesion score 0.09090909090909091 - nodes in this community are weakly interconnected._
- **Should `AoE Mode` be split into smaller, more focused modules?**
  _Cohesion score 0.12631578947368421 - nodes in this community are weakly interconnected._
- **Should `Tool Materials` be split into smaller, more focused modules?**
  _Cohesion score 0.1365079365079365 - nodes in this community are weakly interconnected._
- **Should `Data Components` be split into smaller, more focused modules?**
  _Cohesion score 0.07142857142857142 - nodes in this community are weakly interconnected._