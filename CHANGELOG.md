# Changelog — Workhand Tools


## [1.13.0] - 2026-08-17

### Feature

- **Chunk Anchor + Anchor Tome — forced chunk loading**: new block/item pair, unrelated to the tool line. Place a Chunk Anchor pedestal on the ground and right-click it with an Anchor Tome to force-load its chunk permanently, no player needed nearby — the same mechanism behind vanilla's `/forceload`. Right-click empty-handed (or sneaking) to extract the tome and release the chunk; breaking the pedestal while a tome is inserted drops both items and releases the ticket immediately. The forced-chunk ticket is reconciled on world/chunk load, so it survives server restarts.
- **Chunk border indicator**: while a tome is inserted, the 4 vertical corner edges of the loaded chunk are outlined in white. Visibility is configurable via `chunkAnchorBorderMode` in the common config: `ALWAYS` (default, visible within render distance), `SNEAK_LOOKING` (only while sneaking and looking at the anchor, within 20 blocks), or `NEARBY` (within 20 blocks regardless of stance).
- **Anchor Tome visuals**: the inserted tome floats open above the pedestal, spinning continuously and turning its pages back and forth — the same animation technique as vanilla's Enchanting Table book — with green `enchant` particles orbiting around it.
- **Assets reused from Occultism (with permission)**: the Chunk Anchor's block model/texture and the Anchor Tome's item texture are adapted, with permission, from klikli-dev's Occultism mod (`otherstone_pedestal` and `book_of_binding_djinni` respectively) — see `README.md` → Credits.

## [1.12.1] - 2026-08-14

### Fix

- **Area preview ignored the disabled AoE state**: `AreaHighlighter` (the client-side outline shown while aiming at a block) still used the pre-1.11.0 mode logic and always drew the flat/cubic area outline, even when the tool's AoE was set to Disabled. It now reads the same grade-aware default as the mining and tooltip logic and, when the mode is Disabled, highlights only the single targeted block instead of the full pattern.

## [1.12.0] - 2026-08-14

### Feature

- **Full tree felling, including loose branch logs**: The Workhand Axes' felling flood-fill now searches all 26 neighboring blocks (including diagonals) instead of just the 6 faces, and can cross through leaf blocks to reach log blocks separated by a gap — the loose branch logs embedded in the canopy that vanilla and modded trees commonly have, which the previous logs-only, face-adjacent search left standing. Leaves themselves are never broken directly; they rely on the existing accelerated decay once their supporting logs are gone. The maximum consecutive leaf blocks crossable in one unbroken chain is configurable (`maxLeafDistanceFromLog`, unlimited by default).
- **Cross-mod log detection**: Felling and its log identification no longer depend solely on the vanilla `minecraft:logs` block tag. Any block whose registry id contains `_log` or `log_` (excluding stripped variants) is now also recognized as a log, so trees from mods that don't tag their logs correctly still fell as a whole.

## [1.11.0] - 2026-08-14

### Feature

- **AoE mining can now be fully disabled per tool**: Right-click no longer just toggles between area shapes — every pickaxe grade now cycles through a "Disabled" state too. Grades 1 and 3 (previously a fixed flat area with no right-click action) now toggle **Disabled → Flat → Disabled**. Grades 2 and 4 (previously Cubic/Flat only, always on) now cycle **Disabled → Flat → Cubic → Disabled**. Existing pickaxes keep mining in area mode by default — nothing changes unless the player right-clicks. Tooltips updated to show the disabled state and the right-click hint on every grade.

## [1.10.2] - 2026-08-13

### Fix

- **Server crash (`ConcurrentModificationException`) in leaf decay**: `LeafDecayHandler.onServerTick` iterated its scheduling map while ticking leaves; if a leaf decayed during that tick, the resulting `NeighborNotifyEvent` fired synchronously and re-entered `onNeighborNotify`, which mutated the same map mid-iteration. Ready entries are now removed from the map first, then ticked in a separate pass afterwards.

## [1.10.1] - 2026-08-12

### Change

- **Nombre de JAR con versión del cargador**: el artefacto ahora se compila como `workhand_tools-26.2-neoforge-26.2.0.37-beta-1.10.1.jar` (se añade la versión de cargador/NeoForge al nombre del archivo). Empaquetado y documentación; sin cambios de funcionalidad.

## [1.10.0] - 2026-08-12

### Balance

- **Durability increased 5x across all tools**: crafting cost was steep enough that vanilla-tier durability made these tools feel disposable. Stone 131 → 655, Iron 250 → 1250, Diamond 1561 → 7805, Improved Iron (improved pickaxes + axes) 750 → 3750, Improved Diamond 4683 → 23415. All other stats (speed, attack bonus, enchantability, repair material) stay identical to vanilla.

### Documentation

- Full item catalog added to README.md, the CurseForge project description and `docs/ASSET_LIST_WORKHAND_TOOLS.md`: all 32 items listed with a photo, material, durability and a one-line purpose description.

## [1.9.0] - 2026-08-12

### Feature

- **Faster leaf decay**: leaves disconnected from wood now decay noticeably faster. When a block next to leaves is removed (by hand, an axe, an explosion, etc.), an early decay check is forced a few ticks later instead of waiting on vanilla's natural random-tick interval. Global effect, not tied to a specific tool.

## [1.8.0] - 2026-08-11

### Feature

- **Final tool artwork completed**: Placeholder textures for the remaining 24 tools (Stone/Iron/Diamond × Base/Advanced/Expert/Professional × Pickaxe/Shovel) replaced with the designer's final art. Combined with the improved pickaxes, workhand axes and Robust Stick finalized earlier, every item in the mod now displays final custom artwork — no vanilla or placeholder textures remain.

## [1.7.0] - 2026-08-11

### Feature

- **Tape Measure**: new item to measure the area between two blocks. Right-click a block to set the start point, right-click again to set the end point and draw a wireframe box with X/Y/Z length labels; shift+right-click undoes the last box. The pending box's end point live-updates from the crosshair before it's confirmed. Line/text color and size are configurable in a new client config screen. Adapted from Mrbysco's "Measurements" mod (MIT License), see Credits in README.md.

## [1.6.1] - 2026-08-10

### Fix

- **Cubic mode mining fixed**: Area mining in Cubic mode only ever broke a single `NxNx1` layer instead of the full `NxNxN` volume, and aiming slightly off-center from the block's face would flip the whole pattern onto the wrong axis. The digging direction is now derived from a real block raytrace along the player's view instead of an approximation based on the player's stance position, and side faces are inverted the same way top/bottom faces already were — so depth now extends into the wall regardless of small aiming deviations.
- **Duplicate tooltip line removed**: Improved (vein-mining) pickaxes showed "Hold Shift for normal mining" twice, added independently by both the AoE and vein-mining tooltip handlers.
- **Broken color codes in tooltips**: `hold_shift` and `right_click` tooltip lines were missing their color-format digit, causing the first letter of the following word to be consumed as a formatting code (e.g. red or strikethrough) instead of rendering as text. `vein_mining` had a double-escaped color code rendering as a literal string. Fixed across all 10 languages.

### Update

- **Final tool artwork applied**: Placeholder textures for all 24 tools, the 4 improved pickaxes and the 2 workhand axes replaced with the designer's final art.

## [1.6.0] - 2026-08-10

### Feature

- **Workhand Axes**: Iron and diamond axes with toggleable tree felling (right-click to enable/disable; felling a log chops the whole connected tree).
- **Vein-mining improved pickaxes**: Advanced and Professional improved pickaxes (iron/diamond) that, in addition to area mining, chain-break connected ore blocks of the same type.

## [1.5.0] - 2026-08-10

### Update

- **Item textures revamped**: All item textures replaced with new artwork.

## [1.4.0] - 2026-08-09

### Feature

- **Face-based mining direction**: The AoE mining direction is now derived from the face of the aimed block instead of the camera pitch. A side face (wall) digs forward into the wall, the top face (floor) digs down, and the bottom face (ceiling) digs up. Applies equally to pickaxes and shovels, and the area preview uses the same logic so highlight and mining always match.

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
