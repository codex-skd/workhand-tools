# Changelog — Workhand Tools

## [1.3.0] - 2026-08-08

### Release

- **Functional release**: All core features stable and working as intended. Consolidates all fixes from 1.2.x into a clean release.

### Included Features

- 12 pickaxes + 12 shovels across 3 materials x 4 grades
- Area mining with adaptive direction: horizontal, vertical down, vertical up
- Cubic / Flat mode toggle (right-click on Advanced & Professional)
- White outline preview on solid blocks showing affected area
- 10 language translations with descriptive tooltips
- Full enchantment support matching vanilla material tiers

## [1.2.3] - 2026-08-08

### Fix

- **Outline only on solid blocks**: Skip air blocks when rendering area preview. Cleaner look without empty wireframes.

## [1.2.2] - 2026-08-08

### Fix

- **Area highlight crash fixed**: Replaced manual `VertexConsumer` line drawing with `submitShapeOutline`. The previous approach caused `Missing elements in vertex` crash because `LINES_TRANSLUCENT` requires additional vertex attributes (UV) not set by the custom draw calls. Using the built-in `SubmitNodeCollector.submitShapeOutline()` handles the vertex format correctly.

## [1.2.1] - 2026-08-08

### Fix

- **Highlight system rewritten**: Switched from `ExtractLevelRenderStateEvent` + gizmos to `SubmitCustomGeometryEvent` + `RenderTypes.LINES_TRANSLUCENT` + `submitCustomGeometry`. This renders soft white line borders around affected blocks — the same technique used by CoKTools. The previous gizmo-based approach would flicker or fail depending on update timing.
- **Vertical threshold lowered**: Reduced from 60° to 50° for a more natural transition between horizontal and vertical mining modes.

## [1.2.0] - 2026-08-08

### Feature

- **Adaptive mining direction**: The AoE pattern now adapts to where you're looking. Looking horizontally: pattern extends forward into the wall. Looking down (pitch > 60°): pattern is centered and extends downward (floor mining). Looking up (pitch < -60°): pattern is centered and extends upward (ceiling mining). Lateral width is always centered on the aimed block.

## [1.1.2] - 2026-08-08

### Fix

- **Area preview highlight restored**: Reimplemented using `ExtractLevelRenderStateEvent` instead of the abstract `RenderLevelStageEvent`. Gizmos are now added during the extract phase where the collector is active, fixing the startup crash from v1.1.0.
- **Mining direction corrected**: AoE pattern now extends only forward from the aimed block (into the wall the player faces), instead of being centered on the aimed block. Previously half the pattern extended behind the player. Lateral width and vertical anchoring remain unchanged.

### Technical

- **`AreaHighlighter`**: Subscribes to `ExtractLevelRenderStateEvent` (fires during `LevelExtractor.extract()`). Uses `Gizmos.cuboid(pos, GizmoStyle.fill(0x40FFFF00))` for yellow semi-transparent block highlights.
- **`AoEPatterns`**: Depth loop changed from `[-depthHalf, +depthHalf]` to `[0, depth-1]` so the entire pattern extends in the player's facing direction.

## [1.1.1] - 2026-08-08

### Fix

- **Removed area preview highlight**: The `AreaHighlighter` caused a crash on startup (`Cannot register listeners for abstract class RenderLevelStageEvent`). In NeoForge 26.2, `RenderLevelStageEvent` is abstract with concrete subclasses (`AfterSky`, `AfterTranslucentBlocks`, etc.), and the gizmos system is inaccessible from render-level events (gizmos are collected during the extract phase before rendering). The highlight feature will be reimplemented using `SubmitCustomGeometryEvent` in a future version.

## [1.1.0] - 2026-08-08

### Fix

- **AoE mining system restored**: `buildLookups()` now correctly populates the internal grade/item maps during `FMLCommonSetupEvent` via `enqueueWork`. Previously the lookups were commented out due to a static initializer issue, causing all workhand tools to behave as vanilla tools with no area mining whatsoever. All 24 tools now properly activate their AoE patterns when used without sneaking.

### Feature

- **10 language translations**: Full localization support added for English (`en_us`), Spanish (`es_es`), Brazilian Portuguese (`pt_br`), French (`fr_fr`), German (`de_de`), Russian (`ru_ru`), Simplified Chinese (`zh_cn`), Japanese (`ja_jp`), Italian (`it_it`), and Korean (`ko_kr`). All tool names, mode messages, and tooltip descriptions translated.
- **Descriptive tooltips**: Every workhand tool now displays its mining area dimensions in the tooltip (e.g., "Area: 3x3x3" in gold). Mode-capable tools (Grades 2 and 4) additionally show the current mode (Cubic/Flat) and control hints ("Right-click to toggle mode", "Hold Shift for normal mining").
- **Area preview highlight**: When holding a workhand tool and looking at a mineable block, the affected area is highlighted with yellow semi-transparent cubes using the native MC 26.2 gizmos system. The preview updates in real time and hides while sneaking.

### Refactor

- **`AoEPatterns` utility class**: Pattern computation logic extracted from `AoEMiningHandler` into a shared utility class, enabling both server-side area mining and client-side preview highlighting to use the same pattern algorithm.

### Technical

- **`ModItems.buildLookups()`**: Added explicit registration of all 24 tools + robust_stick into `BY_ID` and `ITEM_GRADES` maps. Called safely via `FMLCommonSetupEvent.enqueueWork()` to avoid static initialization ordering issues.
- **`AreaHighlighter`**: Client-side class using MC 26.2 `Gizmos.cuboid()` API for area preview. Registered on NeoForge event bus from `WorkhandToolsClient`.

## [1.0.0] - 2026-08-07
