# Graph Report - 1.21.1  (2026-09-08)

## Corpus Check
- 239 files · ~41,888 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 638 nodes · 1001 edges · 67 communities (41 shown, 26 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 14 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `c1334584`
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
- Project Settings
- Flujo de trabajo — Workhand Tools (NeoForge)
- 🎨 Workhand Tools — Texturas necesarias para completar el mod
- CurseForge — Variables del proyecto
- Changelog — Workhand Tools
- Workhand Tools
- Workhand Tools 0.0.0-beta.5
- CLAUDE.md — workhand_tools (26.2)
- 0.0.0-beta.1.md
- FellingMode
- 0.0.0-beta.6.md
- 0.0.0-beta.10.md
- 0.0.0-beta.11.md
- MeasurementBox
- WorkhandToolsClient.java
- WorkhandToolsClient.java
- AoEMiningHandler.java
- [1.2.0] - 2026-08-08
- [1.2.1] - 2026-08-08
- [1.2.3] - 2026-08-08
- [1.6.0] - 2026-08-10
- [1.7.0] - 2026-08-11
- [1.8.0] - 2026-08-11
- [1.2.0] - 2026-08-08
- [1.2.1] - 2026-08-08
- 1.23.2.md
- Player
- Builder
- DoubleValue
- EnumValue
- IntValue
- ModConfigSpec
- Override
- Override
- DyeColor
- DeferredHolder
- DeferredRegister
- Builder
- DoubleValue
- EnumValue
- IntValue
- ModConfigSpec
- InteractionResult
- Override
- DyeColor

## God Nodes (most connected - your core abstractions)
1. `ChunkAnchorBlockEntity` - 23 edges
2. `Diseño técnico — Workhand Tools` - 21 edges
3. `ModItems` - 20 edges
4. `TextColor` - 20 edges
5. `LineColor` - 19 edges
6. `MeasurementBox` - 18 edges
7. `AoEMode` - 14 edges
8. `ChunkAnchorBlock` - 13 edges
9. `MiningHelper` - 13 edges
10. `WorkhandTools` - 12 edges

## Surprising Connections (you probably didn't know these)
- `ModBlocks` --references--> `ChunkAnchorBlock`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModBlocks.java → src/main/java/com/skd/workhandtools/ChunkAnchorBlock.java
- `ChunkAnchorRenderer` --references--> `ChunkAnchorBlockEntity`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ChunkAnchorRenderer.java → src/main/java/com/skd/workhandtools/ChunkAnchorBlockEntity.java
- `ModBlocks` --references--> `ChunkAnchorBlockEntity`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModBlocks.java → src/main/java/com/skd/workhandtools/ChunkAnchorBlockEntity.java
- `ModItems` --references--> `Grade`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModItems.java → src/main/java/com/skd/workhandtools/Grade.java
- `Client` --references--> `LineColor`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/TapeMeasureConfig.java → src/main/java/com/skd/workhandtools/LineColor.java

## Import Cycles
- None detected.

## Communities (67 total, 26 thin omitted)

### Community 0 - "Block Break Handling"
Cohesion: 0.14
Nodes (17): CustomPacketPayload, FriendlyByteBuf, Override, StreamCodec, Type, ToggleAoEModePayload, FriendlyByteBuf, Override (+9 more)

### Community 1 - "Mod Initialization"
Cohesion: 0.10
Nodes (18): LineColor, BLACK, BLUE, BROWN, CYAN, GRAY, GREEN, LIGHT_BLUE (+10 more)

### Community 2 - "Item Data Management"
Cohesion: 0.05
Nodes (32): AxeItem, BlockItem, DeferredItem, AnchorTomeItem, TapeMeasureItem, HoeItem, Item, NotNull (+24 more)

### Community 3 - "Grade System"
Cohesion: 0.10
Nodes (19): CreativeModeTab, FMLCommonSetupEvent, FMLLoadCompleteEvent, Items, Logger, PlayerLoggedInEvent, RegisterPayloadHandlersEvent, ServerStartingEvent (+11 more)

### Community 4 - "AoE Mode"
Cohesion: 0.14
Nodes (12): DataComponentType, FellingMode, COMPOUND, DISABLED, SIMPLE, fromName(), fromName(), HoeMode (+4 more)

### Community 5 - "Tool Materials"
Cohesion: 0.12
Nodes (20): ResourceLocation, Block, BlockPos, BlockState, ItemStack, Level, Player, LogSearchNode (+12 more)

### Community 6 - "Data Components"
Cohesion: 0.12
Nodes (16): 📊 Catálogo completo — durabilidad y para qué vale cada herramienta, ⭐ Diferenciación visual por grado, 📐 Especificación técnica (las 25 texturas de 16×16), ✅ Estado actual, 🪓 Hachas Workhand — tala de árboles (2), 🪵 Item nuevo: Palo Robusto, 🪵📏 Items de utilidad (2), 💀⚡ Kennestroyer — herramientas definitivas (2) (+8 more)

### Community 7 - "Client-Side Workhand Tools"
Cohesion: 0.08
Nodes (26): AABB, BlockHitResult, Camera, Component, BoxHandler, LoggingIn, LoggingOut, Matrix4f (+18 more)

### Community 8 - "Configuration Management"
Cohesion: 0.13
Nodes (14): Controls, Credits, Features, Improved Pickaxes — vein mining (4), Installation, Kennestroyer Ultimate Tools (2), License, Pickaxes & Shovels (24 — 3 materials × 4 grades) (+6 more)

### Community 9 - "Build Script"
Cohesion: 0.14
Nodes (13): Concepto, Configuración y compatibilidad con Configured, Creative tab, Diseño técnico — Workhand Tools, Encantamientos, Historial de decisiones, Integración con JEI, Localización (+5 more)

### Community 12 - "Project Settings"
Cohesion: 0.14
Nodes (13): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Workhand Tools (NeoForge), Flujo por tarea, Historial de versiones del workflow (+5 more)

### Community 13 - "Flujo de trabajo — Workhand Tools (NeoForge)"
Cohesion: 0.18
Nodes (10): Checklist de assets pendientes, Fase futura — arte custom (pospuesta), Logo del mod, Notas para elegir/ajustar, Prompt — icono cuadrado, principal, Prompt — variante alternativa (más plana, fiel 100% a pixel-art vanilla), Prompts de imagen — Workhand Tools, Texturas de hachas workhand (Workhand Axes) (+2 more)

### Community 14 - "🎨 Workhand Tools — Texturas necesarias para completar el mod"
Cohesion: 0.22
Nodes (9): Assets reutilizados (con permiso) de Occultism, Chunk Anchor + Anchor Tome (chunk loading), Chunk loading, Efectos visuales del Anchor Tome, Fases de implementación, Indicador visual del chunk (borde de esquinas), Items, Mecánica de colocación (+1 more)

### Community 16 - "CurseForge — Variables del proyecto"
Cohesion: 0.29
Nodes (7): Assets, Comportamiento del pico, Contexto — por qué hace falta código custom, Naming e IDs, Obtención del bloque — loot table custom, Receta (confirmada), Reinforced Deepslate Pickaxe (herramienta especial de propósito único)

### Community 17 - "Changelog — Workhand Tools"
Cohesion: 0.22
Nodes (8): Nota, Project Variables — Workhand Tools (1.21.1), Proyecto, Rama, Repo GitLab, Tag, Tokens, Variables para script (lectura automática)

### Community 18 - "Workhand Tools"
Cohesion: 0.33
Nodes (6): Netherite (los 4 grados), Pala, por grado, Pico, por grado (igual para los 9 materiales con receta shaped — netherite excluido, ver más abajo), Recetas (confirmadas), Robust Stick — item nuevo, Tabla `m` / `a` por material (9 materiales con receta shaped)

### Community 19 - "Workhand Tools 0.0.0-beta.5"
Cohesion: 0.40
Nodes (5): ChunkAnchorBorderMode, ALWAYS, NEARBY, SNEAK_LOOKING, Config

### Community 20 - "CLAUDE.md — workhand_tools (26.2)"
Cohesion: 0.11
Nodes (17): [0.0.0-beta.1] - 2026-09-02, [0.0.0-beta.2] - 2026-09-02, [0.0.0-beta.3] - 2026-09-02, [0.0.0-beta.4] - 2026-09-04, [0.0.0-beta.5] - 2026-09-04, [0.0.0-beta.6] - 2026-09-05, [0.0.0-beta.7] - 2026-09-08, Added (+9 more)

### Community 22 - "0.0.0-beta.1.md"
Cohesion: 0.50
Nodes (3): CLAUDE.md — workhand_tools (26.2), Prioridad de instrucciones, Workflow del mod

### Community 25 - "FellingMode"
Cohesion: 0.08
Nodes (27): Direction, AoEMode, CUBIC, CUBIC_3, CUBIC_5, DISABLED, FLAT, FLAT_3 (+19 more)

### Community 26 - "0.0.0-beta.6.md"
Cohesion: 0.50
Nodes (4): Anclaje vertical para patrones de 5 de alto (5×5×1 / 5×5×5), Clic izquierdo vs. clic derecho (grados 2 y 4) — confirmado, Grados y minado en área (AoE), Reglas comunes

### Community 27 - "0.0.0-beta.10.md"
Cohesion: 0.50
Nodes (4): Mecánica, Naming e IDs, Recetas, Workhand Hoes (Crop Harvesting)

### Community 28 - "0.0.0-beta.11.md"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 46 - "MeasurementBox"
Cohesion: 0.12
Nodes (16): BlockEntity, CompoundTag, Provider, RandomSource, ChunkAnchorBlockEntity, BlockPos, BlockState, ItemStack (+8 more)

### Community 49 - "WorkhandToolsClient.java"
Cohesion: 0.27
Nodes (8): NeighborNotifyEvent, ServerLevel, BlockPos, Post, SubscribeEvent, LeafDecayHandler, LeafKey, Unload

### Community 52 - "WorkhandToolsClient.java"
Cohesion: 0.24
Nodes (7): KeyMapping, RegisterKeyMappingsEvent, RegisterRenderers, IEventBus, Mod, ModContainer, WorkhandToolsClient

### Community 56 - "AoEMiningHandler.java"
Cohesion: 0.18
Nodes (18): BaseEntityBlock, BlockEntityTicker, BooleanProperty, ItemInteractionResult, MapCodec, ChunkAnchorBlock, Block, BlockEntity (+10 more)

### Community 67 - "[1.2.0] - 2026-08-08"
Cohesion: 0.07
Nodes (29): Axis, BlockEntityRenderer, BookModel, Context, Client, TapeMeasureConfig, getColor(), TextColor (+21 more)

### Community 68 - "[1.2.1] - 2026-08-08"
Cohesion: 0.17
Nodes (12): LeftClickBlock, RightClickBlock, CropHarvestHandler, BlockPos, BlockState, InteractionHand, ItemStack, ItemTooltipEvent (+4 more)

### Community 72 - "[1.7.0] - 2026-08-11"
Cohesion: 0.21
Nodes (10): Blocks, DeferredBlock, Ingredient, Block, BlockEntityType, DeferredHolder, DeferredRegister, ModBlocks (+2 more)

## Knowledge Gaps
- **159 isolated node(s):** `CUBIC`, `FLAT`, `DISABLED`, `FLAT_3`, `CUBIC_3` (+154 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **26 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `ModItems` connect `Item Data Management` to `FellingMode`?**
  _High betweenness centrality (0.101) - this node is a cross-community bridge._
- **Why does `ChunkAnchorBlockEntity` connect `MeasurementBox` to `[1.7.0] - 2026-08-11`, `[1.2.0] - 2026-08-08`?**
  _High betweenness centrality (0.065) - this node is a cross-community bridge._
- **What connects `CUBIC`, `FLAT`, `DISABLED` to the rest of the system?**
  _159 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Block Break Handling` be split into smaller, more focused modules?**
  _Cohesion score 0.14492753623188406 - nodes in this community are weakly interconnected._
- **Should `Mod Initialization` be split into smaller, more focused modules?**
  _Cohesion score 0.1 - nodes in this community are weakly interconnected._
- **Should `Item Data Management` be split into smaller, more focused modules?**
  _Cohesion score 0.05493863237872589 - nodes in this community are weakly interconnected._
- **Should `Grade System` be split into smaller, more focused modules?**
  _Cohesion score 0.09885057471264368 - nodes in this community are weakly interconnected._