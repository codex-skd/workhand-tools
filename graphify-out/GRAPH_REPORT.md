# Graph Report - 26.2  (2026-08-24)

## Corpus Check
- 266 files · ~48,302 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 701 nodes · 1016 edges · 109 communities (87 shown, 22 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 11 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `2e785839`
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
- [1.5.0] - 2026-08-10
- [1.10.1] - 2026-08-12
- [1.12.1] - 2026-08-14
- [1.2.1] - 2026-08-08
- [1.2.2] - 2026-08-08
- [1.4.0] - 2026-08-09
- [1.9.0] - 2026-08-12
- [1.4.0] - 2026-08-09
- [1.15.0] - 2026-08-19
- [1.5.0] - 2026-08-10
- [1.9.0] - 2026-08-12
- [1.1.1] - 2026-08-08
- [1.6.0] - 2026-08-10
- [1.2.2] - 2026-08-08
- [1.4.0] - 2026-08-09
- [1.8.0] - 2026-08-11

## God Nodes (most connected - your core abstractions)
1. `Changelog — Workhand Tools` - 39 edges
2. `ChunkAnchorBlockEntity` - 23 edges
3. `TextColor` - 21 edges
4. `LineColor` - 20 edges
5. `Diseño técnico — Workhand Tools` - 20 edges
6. `ModItems` - 19 edges
7. `MeasurementBox` - 18 edges
8. `AoEMode` - 16 edges
9. `ChunkAnchorBlock` - 13 edges
10. `MiningHelper` - 13 edges

## Surprising Connections (you probably didn't know these)
- `depth()` --references--> `AoEMode`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/Grade.java → src/main/java/com/skd/workhandtools/AoEMode.java
- `heightForMode()` --references--> `AoEMode`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/Grade.java → src/main/java/com/skd/workhandtools/AoEMode.java
- `lateralHalfForMode()` --references--> `AoEMode`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/Grade.java → src/main/java/com/skd/workhandtools/AoEMode.java
- `ModBlocks` --references--> `ChunkAnchorBlock`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModBlocks.java → src/main/java/com/skd/workhandtools/ChunkAnchorBlock.java
- `ChunkAnchorRenderer` --references--> `ChunkAnchorBlockEntity`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ChunkAnchorRenderer.java → src/main/java/com/skd/workhandtools/ChunkAnchorBlockEntity.java

## Import Cycles
- None detected.

## Communities (109 total, 22 thin omitted)

### Community 1 - "Mod Initialization"
Cohesion: 0.67
Nodes (3): [1.1.2] - 2026-08-08, Fix, Technical

### Community 2 - "Item Data Management"
Cohesion: 0.08
Nodes (21): BlockItem, DeferredItem, Item, ShovelItem, AnchorTomeItem, Grade, GRADE_1, GRADE_2 (+13 more)

### Community 3 - "Grade System"
Cohesion: 0.24
Nodes (9): Direction, AoEPatterns, BlockPos, Level, Player, Vec3, AreaHighlighter, SubmitCustomGeometryEvent (+1 more)

### Community 4 - "AoE Mode"
Cohesion: 0.06
Nodes (32): DataComponentType, AoEMode, CUBIC, CUBIC_3, CUBIC_5, DISABLED, FLAT, FLAT_3 (+24 more)

### Community 5 - "Tool Materials"
Cohesion: 0.10
Nodes (25): Component, Identifier, AoEMiningHandler, BreakBlockEvent, ItemTooltipEvent, RightClickItem, SubscribeEvent, Block (+17 more)

### Community 6 - "Data Components"
Cohesion: 0.05
Nodes (42): Anclaje vertical para patrones de 5 de alto (5×5×1 / 5×5×5), Assets reutilizados (con permiso) de Occultism, Chunk Anchor + Anchor Tome (chunk loading), Chunk loading, Clic izquierdo vs. clic derecho (grados 2 y 4) — confirmado, Concepto, Configuración y compatibilidad con Configured, Creative tab (+34 more)

### Community 7 - "Client-Side Workhand Tools"
Cohesion: 0.08
Nodes (28): AABB, Camera, LoggingIn, LoggingOut, Matrix4fc, NotNull, ResourceKey, BoxHandler (+20 more)

### Community 8 - "Configuration Management"
Cohesion: 0.22
Nodes (10): ChunkAnchorBorderMode, ALWAYS, NEARBY, SNEAK_LOOKING, Config, Builder, DoubleValue, EnumValue (+2 more)

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
Cohesion: 0.12
Nodes (16): [1.0.0] - 2026-08-07, [1.11.0] - 2026-08-14, [1.12.1] - 2026-08-14, [1.13.1] - 2026-08-18, [1.14.2] - 2026-08-19, [1.2.0] - 2026-08-08, [1.2.2] - 2026-08-08, [1.6.0] - 2026-08-10 (+8 more)

### Community 18 - "Workhand Tools"
Cohesion: 0.13
Nodes (14): Controls, Credits, Features, Improved Pickaxes — vein mining (4), Installation, Kennestroyer Ultimate Tools (2), License, Pickaxes & Shovels (24 — 3 materials × 4 grades) (+6 more)

### Community 19 - "Workhand Tools 0.0.0-beta.5"
Cohesion: 0.40
Nodes (4): Compatibility, Download, What's Fixed, Workhand Tools 0.0.0-beta.5

### Community 20 - "CLAUDE.md — workhand_tools (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — workhand_tools (26.2), Prioridad de instrucciones, Workflow del mod

### Community 41 - "Grade"
Cohesion: 0.08
Nodes (27): getColor(), DyeColor, LineColor, BLACK, BLUE, BROWN, CYAN, GRAY (+19 more)

### Community 46 - "MeasurementBox"
Cohesion: 0.12
Nodes (15): BlockEntity, RandomSource, ChunkAnchorBlockEntity, BlockPos, BlockState, ItemStack, Level, Override (+7 more)

### Community 47 - "TextColor"
Cohesion: 0.09
Nodes (22): Axis, getColor(), DyeColor, TextColor, BLACK, BLUE, BROWN, CYAN (+14 more)

### Community 52 - "WorkhandToolsClient.java"
Cohesion: 0.25
Nodes (6): EventBusSubscriber, RegisterRenderers, IEventBus, Mod, ModContainer, WorkhandToolsClient

### Community 56 - "AoEMiningHandler.java"
Cohesion: 0.18
Nodes (18): BaseEntityBlock, BlockEntityTicker, BlockHitResult, BooleanProperty, MapCodec, ChunkAnchorBlock, Block, BlockEntity (+10 more)

### Community 58 - "[1.1.0] - 2026-08-08"
Cohesion: 0.40
Nodes (5): [1.1.0] - 2026-08-08, Feature, Fix, Refactor, Technical

### Community 59 - "[1.10.0] - 2026-08-12"
Cohesion: 0.67
Nodes (3): [1.10.0] - 2026-08-12, Balance, Documentation

### Community 60 - "[1.1.2] - 2026-08-08"
Cohesion: 0.50
Nodes (4): [1.21.0] - 2026-08-23, Content, Feature, Technical

### Community 61 - "[1.3.0] - 2026-08-08"
Cohesion: 0.67
Nodes (3): [1.3.0] - 2026-08-08, Included Features, Release

### Community 62 - "[1.6.1] - 2026-08-10"
Cohesion: 0.67
Nodes (3): [1.6.1] - 2026-08-10, Fix, Update

### Community 63 - "[1.10.1] - 2026-08-12"
Cohesion: 0.27
Nodes (8): NeighborNotifyEvent, ServerLevel, BlockPos, Post, SubscribeEvent, LeafDecayHandler, LeafKey, Unload

### Community 64 - "[1.11.0] - 2026-08-14"
Cohesion: 0.11
Nodes (18): CreativeModeTab, FMLCommonSetupEvent, FMLLoadCompleteEvent, Items, Logger, PlayerLoggedInEvent, ServerStartingEvent, GuideBookGrantHandler (+10 more)

### Community 65 - "[1.12.0] - 2026-08-14"
Cohesion: 0.67
Nodes (3): [1.19.0] - 2026-08-21, Feature, Fix

### Community 66 - "[1.1.1] - 2026-08-08"
Cohesion: 0.67
Nodes (3): [1.16.0] - 2026-08-20, Feature, Fix

### Community 67 - "[1.2.0] - 2026-08-08"
Cohesion: 0.19
Nodes (13): BlockEntityRenderer, BlockEntityRenderState, BookModel, CameraRenderState, Context, @Nullable CrumblingOverlay, SpriteGetter, ChunkAnchorRenderer (+5 more)

### Community 68 - "[1.2.1] - 2026-08-08"
Cohesion: 0.18
Nodes (12): LeftClickBlock, RightClickBlock, CropHarvestHandler, BlockPos, BlockState, InteractionHand, ItemStack, ItemTooltipEvent (+4 more)

### Community 69 - "[1.2.2] - 2026-08-08"
Cohesion: 0.40
Nodes (5): [1.20.0] - 2026-08-23, Change, Content, Feature, Translation

### Community 72 - "[1.7.0] - 2026-08-11"
Cohesion: 0.33
Nodes (7): Blocks, DeferredBlock, Block, BlockEntityType, DeferredHolder, DeferredRegister, ModBlocks

### Community 74 - "[1.9.0] - 2026-08-12"
Cohesion: 0.50
Nodes (4): [1.18.0] - 2026-08-21, Change, Feature, Fix

## Knowledge Gaps
- **198 isolated node(s):** `CUBIC`, `FLAT`, `DISABLED`, `FLAT_3`, `CUBIC_3` (+193 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **22 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TextColor` connect `TextColor` to `Grade`?**
  _High betweenness centrality (0.076) - this node is a cross-community bridge._
- **Why does `ChunkAnchorBlockEntity` connect `MeasurementBox` to `[1.7.0] - 2026-08-11`, `[1.2.0] - 2026-08-08`?**
  _High betweenness centrality (0.052) - this node is a cross-community bridge._
- **Why does `AoEMode` connect `AoE Mode` to `Grade System`, `Tool Materials`?**
  _High betweenness centrality (0.048) - this node is a cross-community bridge._
- **What connects `CUBIC`, `FLAT`, `DISABLED` to the rest of the system?**
  _198 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Item Data Management` be split into smaller, more focused modules?**
  _Cohesion score 0.07682926829268293 - nodes in this community are weakly interconnected._
- **Should `AoE Mode` be split into smaller, more focused modules?**
  _Cohesion score 0.06463414634146342 - nodes in this community are weakly interconnected._
- **Should `Tool Materials` be split into smaller, more focused modules?**
  _Cohesion score 0.09608843537414966 - nodes in this community are weakly interconnected._