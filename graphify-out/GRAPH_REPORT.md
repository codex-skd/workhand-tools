# Graph Report - 26.2  (2026-08-08)

## Corpus Check
- 135 files · ~18,509 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 220 nodes · 248 edges · 39 communities (37 shown, 2 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 1 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `b2f061ec`
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
- ModDataComponents

## God Nodes (most connected - your core abstractions)
1. `Diseño técnico — Workhand Tools` - 15 edges
2. `ModItems` - 11 edges
3. `Flujo de trabajo — Workhand Tools (NeoForge)` - 11 edges
4. `WorkhandTools` - 10 edges
5. `AoEMode` - 9 edges
6. `Grade` - 9 edges
7. `🎨 Workhand Tools — Texturas necesarias para completar el mod` - 9 edges
8. `AoEMiningHandler` - 7 edges
9. `CurseForge — Variables del proyecto` - 7 edges
10. `AoEPatterns` - 6 edges

## Surprising Connections (you probably didn't know these)
- `depth()` --references--> `AoEMode`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/Grade.java → src/main/java/com/skd/workhandtools/AoEMode.java
- `ModItems` --references--> `Grade`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModItems.java → src/main/java/com/skd/workhandtools/Grade.java
- `ModDataComponents` --references--> `AoEMode`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModDataComponents.java → src/main/java/com/skd/workhandtools/AoEMode.java
- `ModItems` --references--> `WorkhandPickaxeItem`  [EXTRACTED]
  src/main/java/com/skd/workhandtools/ModItems.java → src/main/java/com/skd/workhandtools/WorkhandPickaxeItem.java

## Import Cycles
- None detected.

## Communities (39 total, 2 thin omitted)

### Community 0 - "Block Break Handling"
Cohesion: 0.22
Nodes (10): BlockState, BreakBlockEvent, ItemTooltipEvent, Level, RightClickItem, AoEMiningHandler, BlockPos, ItemStack (+2 more)

### Community 1 - "Mod Initialization"
Cohesion: 0.21
Nodes (12): CreativeModeTab, FMLCommonSetupEvent, IEventBus, Items, Logger, ServerStartingEvent, DeferredHolder, DeferredRegister (+4 more)

### Community 2 - "Item Data Management"
Cohesion: 0.22
Nodes (7): DeferredItem, Item, DeferredHolder, Item, ItemStack, ModItems, WorkhandPickaxeItem

### Community 3 - "Grade System"
Cohesion: 0.40
Nodes (4): Direction, AoEPatterns, BlockPos, Player

### Community 4 - "AoE Mode"
Cohesion: 0.20
Nodes (11): DataComponentType, Override, AoEMode, CUBIC, FLAT, fromName(), getSerializedName(), DeferredHolder (+3 more)

### Community 6 - "Data Components"
Cohesion: 0.08
Nodes (23): Anclaje vertical para patrones de 5 de alto (5×5×1 / 5×5×5), Clic izquierdo vs. clic derecho (grados 2 y 4) — confirmado, Concepto, Configuración y compatibilidad con Configured, Creative tab, Diseño técnico — Workhand Tools, Encantamientos, Grados y minado en área (AoE) (+15 more)

### Community 7 - "Client-Side Workhand Tools"
Cohesion: 0.24
Nodes (7): EventBusSubscriber, ExtractLevelRenderStateEvent, AreaHighlighter, SubscribeEvent, Mod, ModContainer, WorkhandToolsClient

### Community 8 - "Configuration Management"
Cohesion: 0.50
Nodes (4): Builder, DoubleValue, ModConfigSpec, Config

### Community 9 - "Build Script"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 13 - "Flujo de trabajo — Workhand Tools (NeoForge)"
Cohesion: 0.15
Nodes (12): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Workhand Tools (NeoForge), Flujo por tarea, Idioma (+4 more)

### Community 14 - "🎨 Workhand Tools — Texturas necesarias para completar el mod"
Cohesion: 0.20
Nodes (9): ⭐ Diferenciación visual por grado, 📐 Especificación técnica (las 25 texturas de 16×16), ✅ Estado actual, 🪵 Item nuevo: Palo Robusto, ⛏️ Listado completo — 24 texturas de herramienta + 1 robust_stick, 🎨 Paleta por material, 📋 Resumen, 🌐 Tabla de traducciones (castellano → inglés) (+1 more)

### Community 15 - "Prompts de imagen — Workhand Tools"
Cohesion: 0.22
Nodes (8): Checklist de assets pendientes, Fase futura — arte custom (pospuesta), Logo del mod, Notas para elegir/ajustar, Prompt — icono cuadrado, principal, Prompt — variante alternativa (más plana, fiel 100% a pixel-art vanilla), Prompts de imagen — Workhand Tools, Texturas de herramientas (archivos PNG locales)

### Community 16 - "CurseForge — Variables del proyecto"
Cohesion: 0.25
Nodes (7): CurseForge — Variables del proyecto, Nota, Proyecto, Rama, Tag, Tokens, Variables para script (lectura automática)

### Community 17 - "Changelog — Workhand Tools"
Cohesion: 0.13
Nodes (14): [1.0.0] - 2026-08-07, [1.1.0] - 2026-08-08, [1.1.1] - 2026-08-08, [1.1.2] - 2026-08-08, [1.2.0] - 2026-08-08, Changelog — Workhand Tools, Feature, Feature (+6 more)

### Community 18 - "Workhand Tools"
Cohesion: 0.29
Nodes (6): Controls, Features, Installation, License, Requirements, Workhand Tools

### Community 19 - "Workhand Tools 0.0.0-beta.5"
Cohesion: 0.40
Nodes (4): Compatibility, Download, What's Fixed, Workhand Tools 0.0.0-beta.5

### Community 20 - "CLAUDE.md — workhand_tools (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — workhand_tools (26.2), Prioridad de instrucciones, Workflow del mod

### Community 34 - "ModDataComponents"
Cohesion: 0.20
Nodes (6): depth(), Grade, GRADE_1, GRADE_2, GRADE_3, GRADE_4

## Knowledge Gaps
- **76 isolated node(s):** `CUBIC`, `FLAT`, `GRADE_1`, `GRADE_2`, `GRADE_3` (+71 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **2 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AoEMode` connect `AoE Mode` to `Block Break Handling`, `ModDataComponents`, `Grade System`?**
  _High betweenness centrality (0.048) - this node is a cross-community bridge._
- **Why does `AoEMiningHandler` connect `Block Break Handling` to `Mod Initialization`?**
  _High betweenness centrality (0.045) - this node is a cross-community bridge._
- **Why does `ModItems` connect `Item Data Management` to `Block Break Handling`, `ModDataComponents`?**
  _High betweenness centrality (0.044) - this node is a cross-community bridge._
- **What connects `CUBIC`, `FLAT`, `GRADE_1` to the rest of the system?**
  _76 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Data Components` be split into smaller, more focused modules?**
  _Cohesion score 0.08333333333333333 - nodes in this community are weakly interconnected._
- **Should `Changelog — Workhand Tools` be split into smaller, more focused modules?**
  _Cohesion score 0.13333333333333333 - nodes in this community are weakly interconnected._