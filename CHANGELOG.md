# Changelog — Workhand Tools


## [1.22.4] - 2026-08-25

### Fix

- **Reinforced Deepslate Pickaxe could still mine other blocks (slowly, with no drop)**: mining speed against non-target blocks was 1.0 (default speed), which meant Survival players could technically still break stone and other blocks with it, very slowly and without receiving the material. Now returns 0 speed against every block except Reinforced Deepslate, so mining progress never advances — the pickaxe is truly unable to break anything else in Survival.
- **Reinforced Deepslate Pickaxe didn't drop the block when mining Reinforced Deepslate with itself**: the loot table override only recognized the Diamond Workhand Professional Improved Pickaxe (needed to break the circularity of crafting the very first one), so using the finished Reinforced Deepslate Pickaxe on its own target block dropped nothing. It's now also a valid tool in the loot table condition.

## [1.22.3] - 2026-08-25

### Fix

- **Reinforced Deepslate Pickaxe recipe used the wrong Netherite item**: the recipe was implemented with 7 Netherite Ingots instead of 7 Netherite Blocks as originally intended. Fixed — the recipe now requires Netherite Blocks.

## [1.22.2] - 2026-08-25

### Fix

- **Kennestroyer Pickaxe and Shovel had no item texture**: both items were added in a previous release without a texture, item model, or item definition file, so they rendered with Minecraft's "missing texture" checkerboard in the inventory and in the in-game guide book. Final art received and applied.

## [1.22.1] - 2026-08-25

### Fix

- **Kennestroyer recipes failed to load**: `kennestroyer_pickaxe.json` and `kennestroyer_shovel.json` used shaped-recipe patterns with spaces between key letters (e.g. `"n d n"`) for readability, but in Minecraft's shaped recipe format every character — including spaces — counts as one grid cell, so a 5-character row like `"n d n"` was parsed as 5 columns against a 3-wide crafting grid. Both recipes failed to load (`Invalid pattern: too many columns, 3 is maximum`), making the Kennestroyer Pickaxe and Shovel uncraftable. Fixed by removing the spaces (`"ndn"`, `"oBo"`, `"nDn"`) — the intended 3x3 layout is unchanged.

## [1.22.0] - 2026-08-25

### Feature

- **Reinforced Deepslate Pickaxe**: a standalone pickaxe that mines Reinforced Deepslate at very high speed (equivalent to how fast a Diamond Pickaxe breaks stone) but is otherwise a non-functional pickaxe that cannot efficiently mine any other block. Crafted from Netherite Ingots, a Diamond Workhand Professional Improved Pickaxe, and a Reinforced Deepslate block.
- **Reinforced Deepslate loot table override**: Reinforced Deepslate now drops itself when broken with a Diamond Workhand Professional Improved Pickaxe specifically (not any other tool), enabling the crafting loop for the new pickaxe.

### Fix

- **Kennestroyer pickaxe/shovel mode cycle looked incomplete**: a freshly crafted Kennestroyer defaulted to a leftover two-state `CUBIC` value instead of `DISABLED`, so the first right-click silently landed back on the 5x5x5 mode instead of starting the cycle, and the action-bar/tooltip message only ever said generic "Mode: Cubic"/"Mode: Flat" without indicating size — making the 5 real modes (Disabled, 3x3x1, 3x3x3, 5x5x1, 5x5x5) look like fewer than 5. Default is now `DISABLED`, and mode feedback shows the exact area (e.g. "Mode: 3x3x1") for each of the 5 states.

## [1.21.2] - 2026-08-25

### Fix

- **Critical: the mod crashed on startup ("Bootstrap" error) for every player**, failing with `IllegalArgumentException: Method ... onLoadComplete ... has @SubscribeEvent annotation, but takes an argument that is not valid for this bus`. `onLoadComplete` listens for `FMLLoadCompleteEvent`, a mod-lifecycle event that is only valid on the mod event bus, but it was annotated `@SubscribeEvent` and swept up by `NeoForge.EVENT_BUS.register(this)` alongside `onServerStarting`, which only accepts game events. Fixed by registering `onLoadComplete` explicitly on the mod event bus (`modEventBus.addListener(...)`) instead of via the `@SubscribeEvent` scan on `NeoForge.EVENT_BUS`.

## [1.21.1] - 2026-08-24

### Fix

- **Compound tree felling was clearing adjacent trees**: the axe's Compound felling mode used a flood-fill that could cross leaf gaps with no limit (`maxLeafDistanceFromLog` defaulted to `-1`, unlimited), letting it jump from one tree's canopy into a neighboring tree of the same wood type and fell it too. Default is now `2` consecutive leaf blocks — enough to cross a diagonal branch or offset canopy within the same tree, not enough to reach a separately planted tree nearby.
- **Chunk Anchor border was visible from unlimited distance**: in the default `ALWAYS` border mode, the corner-outline renderer used an unbounded max distance instead of the client's actual render distance, so the 384-block-tall corner lines stayed visible far beyond where anything else would render. Now capped to the player's effective render distance (respects server-side render distance caps too).
- **Guide book had corrupted/invalid content**: `entries/pickaxes.json` and `entries/shovels.json` (both en_us and es_es) each contained two JSON objects concatenated without a separator (the category's main entry directly followed by the Kennestroyer entry, no comma/brackets) — invalid JSON that could not be parsed. Split into separate valid files (`kennestroyer_pickaxe.json`, `kennestroyer_shovel.json`).
- **Guide book recipe grade labels were swapped**: crafting pages for Advanced (Grade 2) and Expert (Grade 3) pickaxes/shovels had their grade numbers exchanged (e.g. "Advanced Pickaxe (Grade 3)" instead of Grade 2); Vein-Mining Pickaxe pages had Advanced/Professional swapped entirely (Grade 2 shown as 4 and vice versa). Corrected across en_us and es_es.

## [1.21.0] - 2026-08-23

### Feature

- **Kennestroyer Ultimate Tools**: added Kennestroyer Pickaxe and Kennestroyer Shovel — the ultimate tools with 5 mining/digging modes cycled via right-click:
  - Disabled: No area mining
  - Range 3 Flat: 3x3x1 (single layer)
  - Range 3 Cubic: 3x3x3
  - Range 5 Flat: 5x5x1 (single layer)
  - Range 5 Cubic: 5x5x5
- **Kennestroyer Pickaxe includes Vein Mining**: breaks up to 128 connected ores of the same type automatically
- **New tool material**: KENNESTROYER (50,000 durability, 12.0 speed, +4 attack, 15 enchantability, repaired with Netherite Ingots)
- **Crafting recipes**: both tools crafted from Netherite Ingots, Diamond Blocks, Obsidian, and the top-tier Diamond Workhand tools (Advanced/Professional Improved Pickaxe for pickaxe, Advanced/Professional Shovel for shovel)

### Content

- **Guide Book**: added Kennestroyer Pickaxe and Shovel entries with full mode descriptions, controls, stats, and crafting recipes in both English and Spanish

### Technical

- Extended `AoEMode` enum with `FLAT_3`, `CUBIC_3`, `FLAT_5`, `CUBIC_5` modes
- Extended `Grade` enum with `KENNESTROYER` grade supporting all 5 modes with dynamic lateral/height/depth calculation
- Updated `AoEMiningHandler` and `AoEPatterns` to handle 5-mode cycling and pattern computation for Kennestroyer grade

## [1.20.0] - 2026-08-23

### Feature

- **Vellumli is now a required dependency**: the mod will fail to load with a clear error message if Vellumli is not installed. This ensures the in-game guide book is always available.
- **Complete recipe integration in the guide book**: every tool and utility item now has its crafting recipe displayed directly in the Vellumli guide book using the native `vellumli:crafting` page type. No need for JEI to see recipes.

### Content

- **Pickaxes**: 18 recipes (Stone/Iron/Diamond × all grades 1-4 + improved variants)
- **Shovels**: 12 recipes (Stone/Iron/Diamond × all grades 1-4)
- **Axes**: 2 recipes (Iron, Diamond)
- **Hoes**: 2 recipes (Iron, Diamond)
- **Vein-Mining Pickaxes**: 4 recipes (Improved Iron/Diamond Professional + Advanced)
- **Utility Items**: 5 recipes (Tape Measure, Robust Stick, Chunk Anchor, Anchor Tome, Guide Book)

### Change

- Vellumli dependency changed from optional to required in `neoforge.mods.toml`
- Startup validation added: mod throws `IllegalStateException` if Vellumli is missing

### Translation

- All new recipe pages added in both English (en_us) and Spanish (es_es)

## [1.19.0] - 2026-08-21

### Feature

- **Spanish translation for the guide book**: all 12 category/entry files of the in-game guide book are now available in Spanish (es_es), alongside the existing English content.

### Fix

- **Utility Items category description was inaccurate**: it claimed the Chunk Anchor sets the world spawn point and the Anchor Tome teleports to it — neither is real. The actual behavior is force-loading the chunk the anchor sits in; there's no teleport.

## [1.18.0] - 2026-08-21

### Feature

- **Guide book given on first join**: every player now receives the Workhand Tools guide book automatically the first time they log in (tracked per-player, survives death/respawn). If lost afterward, it has to be crafted again (book + iron ingot + diamond) — it's a one-time gift, not a repeatable freebie.

### Fix

- **Guide book missing from the creative tab / JEI**: `book.json`'s `creative_tab` field was a bare `workhand_tab` string, which Vellumli parses as a namespaced Identifier and silently resolved to `minecraft:workhand_tab` (a tab that doesn't exist) — the book never made it into our own creative tab. Fixed to `workhand_tools:workhand_tab`.
- **Book name/subtitle were too long for the tooltip**: shortened `book.name` and `book.subtitle` to 2 words max across all 10 languages; the longer wording lives in `book.landing` instead.

### Change

- Updated the bundled Vellumli dependency to 1.2.0 — adds JEI search support for the guide book and drops Vellumli's bundled demo book.

## [1.17.0] - 2026-08-20

### Feature

- **Workhand Hoes: Till/Harvest mode**: replaced the harvest enable/disable toggle with a Till/Harvest mode cycle (right-click, regardless of whether a block is targeted). Left-click now performs the action: Till mode tills a single tillable block (never an area, no matter the tier); Harvest mode harvests and replants a square area of mature crops/cocoa/nether wart (radius 1 for the iron hoe, radius 2 for the diamond hoe), with an area preview shown while aiming in Harvest mode. Left-clicking anything else falls through to normal breaking.
- **Workhand Axes: Simple/Compound felling modes**: replaced the boolean felling toggle with a 3-state cycle (Disabled → Simple → Compound → Disabled, right-click). Simple fells only directly-connected logs (no crossing leaf gaps); Compound keeps the previous behavior of crossing leaf gaps to reach offset or diagonal logs. The existing "must be resting on solid ground" requirement for felling to cascade now has an orphan-tree fallback: if no grounded log is found within 4 hops of log-to-log connectivity, the fragment is treated as floating (e.g. after the tree's base was destroyed) and felled anyway.
- **In-game guide book**: added a craftable guide book (book + iron ingot + diamond) via Vellumli, an in-house documentation-book mod. Documents every tool category — Pickaxes, Shovels, Vein-Mining Pickaxes, Axes, Hoes, and Utility items — with controls and mechanics. English only for now; Vellumli is an optional dependency, the recipe is unavailable if it isn't installed.

## [1.16.1] - 2026-08-20

### Change

- **NeoForge update**: updated from 26.2.0.45-beta to 26.2.0.57.
- **JAR name with loader version**: the artifact now builds as `workhand_tools-26.2-neoforge-26.2.0.57-1.16.1.jar`.
- **Workflow documentation**: updated `docs/WORKFLOW_WORKHAND_TOOLS_26-2.md` to reflect the new working branch.

## [1.16.0] - 2026-08-20

### Fix

- **Beetroot harvest could be duplicated with Workhand Hoes**: the crop age reset after harvesting hardcoded `CropBlock.AGE`, but `BeetrootBlock` uses its own age property (0-3 range) instead of `CropBlock`'s (0-7 range). Setting the wrong property threw at runtime *after* drops had already been granted, so the beetroot stayed mature and could be harvested again for free. The age property is now looked up dynamically from the block's own state definition, fixing wheat, carrots, potatoes, beetroot, nether wart, and cocoa alike.
- **Right-click mode/effect toggles played no swing animation**: toggling the hoe's harvest effect, the axe's tree felling, or the pickaxe/shovel's AoE mode cancelled the interaction without triggering the arm swing, so the tool looked static. All three toggles now swing the tool.
- **Tree felling ignored where in the trunk the log was broken**: any log break with felling enabled cascaded into the full tree, even at eye height mid-trunk. The cascade now only triggers when the broken log is resting on solid ground (the base of the trunk); breaking a log elsewhere in the trunk breaks only that single block.

### Feature

- **Area preview for Workhand Hoes**: aiming a hoe at a mature crop, cocoa, or nether wart now outlines the exact square area that will be harvested, matching the preview pickaxes/axes/shovels already had.
- **Harvest enabled toggle for Workhand Hoes**: right-click with no target to enable/disable the hoe's harvest effect (enabled by default). When disabled, hoes behave like a vanilla hoe (e.g. tilling dirt).

## [1.15.0] - 2026-08-19

### Feature

- **Final artwork for Workhand Hoes**: the placeholder textures for `iron_workhand_hoe` and `diamond_workhand_hoe` (copies of the vanilla hoe textures, added in 1.14.1) have been replaced with the designer's final art. Every item in the mod now displays final custom artwork — no vanilla or placeholder textures remain.

## [1.14.2] - 2026-08-19

### Change

- **Config files now live under `config/workhand_tools/`**: both the common config (`workhand_tools-common.toml`) and the client config (`workhand_tools-client.toml`) are now registered with an explicit `workhand_tools/` subfolder, instead of sitting directly in `config/` alongside every other mod's files.

## [1.14.1] - 2026-08-19

### Fix

- **Workhand Hoes used the vanilla hoe texture directly instead of the mod's own placeholder file**: `iron_workhand_hoe.json`/`diamond_workhand_hoe.json` item models referenced `minecraft:item/iron_hoe`/`diamond_hoe` directly, breaking the convention every other tool in this mod follows (its own local placeholder PNG under `workhand_tools:item/`, ready to be swapped for final art without code changes). Added `iron_workhand_hoe.png`/`diamond_workhand_hoe.png` (copies of the vanilla textures) and pointed the models at them. No visual change in-game.
- **README.md was missing the Workhand Hoes**: added the "Workhand Hoes — crop harvesting" section and a Features bullet, introduced in 1.14.0 but left out of the docs at the time.

## [1.14.0] - 2026-08-19

### Feature

- **Workhand Hoes — crop right-click harvesting**: two new tools, `iron_workhand_hoe` and `diamond_workhand_hoe` (Improved Iron/Diamond materials, matching the Workhand Axes' durability). Right-click a mature crop, cocoa, or nether wart block to harvest and instantly replant it in place — no seeds required, drops respect Fortune and other mining enchantments. Harvests spread to every other mature crop on the same level in a square area centered on the targeted block: 3×3 for the iron hoe, 5×5 for the diamond hoe (plants that grow by stacking multiple blocks, like sugar cane or cactus, are out of scope). Each harvested block consumes 1 durability point.

## [1.13.3] - 2026-08-19

### Fix

- **AoE outline could highlight blocks the tool couldn't actually break**: `AreaHighlighter` only checked `isCorrectToolForDrops` once, against the initially targeted block, then drew the outline for every non-air position in the AoE pattern. `AoEMiningHandler`, which performs the real breaking, checks `isCorrectToolForDrops` per block instead — so a block the tool can't mine could stay highlighted even though it would never actually break. The per-position loop now applies the same per-block check the mining handler uses, so the outline always matches the set of blocks that will actually be broken.

## [1.13.2] - 2026-08-19

### Fix

- **Chunk Anchor was nearly unbreakable**: the block copied Obsidian's full `Properties` (`Block.Properties.ofFullCopy(Blocks.OBSIDIAN)`), which brought along its 50-second base destroy time, but never added the block to the `minecraft:mineable/pickaxe` tag — so no pickaxe, not even diamond or netherite, got its mining-speed bonus applied. In practice the block took the full uncut destroy time to break by hand. It now belongs to `mineable/pickaxe` while keeping Obsidian's original toughness, so any pickaxe mines it at the expected speed.

## [1.13.1] - 2026-08-18

### Change

- **Actualización de NeoForge**: actualizado de 26.2.0.37-beta a 26.2.0.45-beta.
- **Nombre de JAR con versión del cargador**: el artefacto ahora se compila como `workhand_tools-26.2-neoforge-26.2.0.45-beta-1.13.1.jar`.
- **Documentación del workflow**: actualizada `docs/WORKFLOW_WORKHAND_TOOLS_26-2.md` para reflejar la nueva rama de trabajo.


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
