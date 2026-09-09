# Workhand Tools (1.21.1) — Changelog

Branch `minecraft/1.21.1/neoforge-21.1.249/production`. History independent of the 26.2 branch.

## [1.2.0] - 2026-09-09

### Added

- **Advancements.** A dedicated Workhand Tools advancement tab with nine milestone
  advancements, each unlocked by the `minecraft:recipe_crafted` trigger the moment the matching
  tool is crafted: *Workhand Tools* (Robust Stick, root) → *Getting to Work* (any base pickaxe/
  shovel) → *Bigger Is Better* (any Professional pickaxe/shovel) → *The Kennestroyer* (challenge)
  and *Overbuilt* (Reinforced Deepslate Pickaxe); *Follow the Vein* (any Improved vein pickaxe),
  *Timber!* (any Workhand Axe) and *Reap What You Sow* (any Workhand Hoe) branch off *Getting to
  Work*; *Stay Loaded* (Chunk Anchor) branches off the root. Data-only: nine files under
  `data/workhand_tools/advancement/` plus `advancement.workhand_tools.*` strings in `en_us` and
  `es_es` (other locales fall back to English). Verified with `./gradlew runServer` — datapack
  loads, 0 parse errors.

## [1.1.0] - 2026-09-09

### Added

- **Tree-felling axes now respect sneak.** Holding Shift while breaking a log suppresses the
  felling cascade — the targeted log still breaks via vanilla, but connected logs stay standing.
  Releasing Shift restores normal felling in whichever mode (Simple / Compound) the axe is set
  to. This matches the sneak-to-disable behaviour the AoE pickaxes and shovels already had, so
  every Workhand tool now reacts to Shift the same way. `TreeFellingHandler.onBlockBreak` returns
  early on `player.isShiftKeyDown()`; the axe tooltip gains the shared
  `tooltip.workhand_tools.hold_shift` line.

## [1.0.0] - 2026-09-09

First stable release for **Minecraft 1.21.1 / NeoForge 21.1.249** (Java 21). Consolidates the
`0.0.0-beta.1` → `0.0.0-beta.7` line with no further code changes. This build has been running in
the *(Develop) Mystical Realms* modded-server pack.

### Summary of the beta line

- **beta.1** — initial API port of the 26.2 source (41 classes, no mixins). Full tool line
  unchanged: 12 pickaxes + 12 shovels (Stone/Iron/Diamond × 4 grades) with 3×3→5×5 area mining
  and the Cubic/Flat toggle; 4 vein-mining pickaxes; 2 tree-felling axes; 2 crop-harvesting hoes;
  Kennestroyer Pickaxe/Shovel; Reinforced Deepslate Pickaxe; Robust Stick; Tape Measure; Chunk
  Anchor + Anchor Tome. Tool material system ported from the 26.2 `ToolMaterial` record to
  `SimpleTier` with explicit repair ingredients; client render layer reverted from the 26.2
  render-graph to `RenderLevelStageEvent` + `LevelRenderer.renderLineBox`. Guide book via
  Vellumli.
- **beta.2** — fixed a client mod-loading crash: `WorkhandToolsClient` carried
  `@EventBusSubscriber` with no `@SubscribeEvent` methods, so `AutomaticEventSubscriber` aborted
  mod loading. Annotation removed.
- **beta.3** — added the missing `models/item/chunk_anchor.json`, so the Chunk Anchor no longer
  renders as the missing-texture placeholder (1.21.1 resolves a `BlockItem`'s inventory model
  from `models/item/<name>.json`; the `assets/.../items/` folder is inert on this version).
- **beta.4 – beta.5** — the Hoe / AoE-mining / tree-felling mode toggles moved off
  right-click-event cancellation (which raced vanilla and other mods) onto dedicated, rebindable
  keybindings (Options → Controls → Workhand Tools), defaulted to the right mouse button; the
  optional network channel is now `.optional()` so an older-jar server no longer disconnects
  clients.
- **beta.6** — fixed AoE / vein / felling duplicating the drops of multi-part or special blocks
  (waystones, beds, tall flowers, this mod's own Chunk Anchor): `MiningHelper.breakBlock` now
  mirrors the vanilla player-break sequence, so every block drops exactly once.
- **beta.7** — completed the Spanish (`es_es`) locale (Chunk Anchor / Anchor Tome keys).

### Notes

- No code change relative to `0.0.0-beta.7`. Verified: `./gradlew clean build` is green;
  `./gradlew runServer` reaches `Done` with 0 FATAL and 0 recipe/datapack parse errors.
- Same CurseForge project as the 26.2 line (`1640361`); pick the file that matches your Minecraft
  version. Requires Vellumli for the in-game guide book.

## [0.0.0-beta.7] - 2026-09-08

### Fixed

- **Spanish (`es_es`) locale**: added the 3 keys present in `en_us` but missing from `es_es`
  — `item`/`block.workhand_tools.chunk_anchor` ("Ancla de chunk") and
  `item.workhand_tools.anchor_tome` ("Tomo de anclaje"). Values from the Mystical Realms
  Translation & Fixes resource-pack QA pass, moved here so they ship with the mod. Also a tab
  re-indent of the locale file. No code change.

## [0.0.0-beta.6] - 2026-09-05

### Fixed

- **AoE mining, vein mining and tree felling duplicated the drops of multi-part or
  special blocks.** Breaking such a block (a Waystones waystone, a bed, a tall
  flower, this mod's own Chunk Anchor, etc.) as a *secondary* block of an AoE /
  vein / felling action dropped its item twice - roughly once per sub-block it
  occupies. `MiningHelper.breakBlock` hand-rolled the break as
  `playerWillDestroy` + `Block.dropResources` + `setBlock`, but many of these
  blocks already drop their item from inside `playerWillDestroy`, so
  `Block.dropResources` was an extra drop on top. The helper now mirrors the
  vanilla player-break sequence (`playerWillDestroy` -> `onDestroyedByPlayer` ->
  `Block.destroy` -> `Block.playerDestroy`), so every block drops exactly once,
  just like breaking it by hand. The player's directly targeted block was never
  affected - only the extra blocks broken by the tool's area/vein/felling pass.

## [0.0.0-beta.5] - 2026-09-04

### Fixed

- **Network channel could disconnect players from a server on an older Workhand Tools version.**
  The `toggle_hoe_mode` channel added in beta.4 was registered as required on both sides;
  connecting to a server still running an older jar (missing the channel) triggered NeoForge's
  mod-mismatch disconnect screen instead of just degrading gracefully. The registrar is now
  `.optional()`.
- **AoE mining mode (pickaxes/shovels) and tree felling mode (axes) could silently stop
  responding to right-click** in modpacks where another mod intercepts right-click on those item
  types before our listener runs (e.g. a combat-rework mod treating axes/pickaxes as weapons with
  their own right-click ability). Both now use a dedicated, rebindable keybinding (Options >
  Controls > Workhand Tools) defaulted to the right mouse button, same as the Hoe fix in beta.4.
  Right-click still suppresses the vanilla interaction; the keybinding does the actual toggle.

## [0.0.0-beta.4] - 2026-09-04

### Changed

- **Workhand Hoe mode toggle now uses a dedicated, rebindable keybinding** (Options > Controls >
  Workhand Tools), defaulted to the right mouse button so behavior is unchanged out of the box.
  Previously the toggle relied on canceling `PlayerInteractEvent.RightClickBlock`/`RightClickItem`,
  racing vanilla `HoeItem.useOn` on every click - this worked inconsistently. Right-click still
  suppresses vanilla tilling on a held hoe; the mode itself now only changes through the keybinding.

## [0.0.0-beta.3] - 2026-09-02

### Fixed

- **Chunk Anchor rendered as the missing-texture placeholder** (black/magenta) in the inventory,
  hand and on the ground. `chunk_anchor` was the only registered item without an
  `assets/workhand_tools/models/item/chunk_anchor.json`. On 1.21.1 the inventory model of a
  `BlockItem` is resolved from `models/item/<name>.json`; the `assets/workhand_tools/items/`
  folder (the 1.21.4+ *item model definition* format, carried over from the 26.2 source) is inert
  on this version. Added the missing model, parented to `workhand_tools:block/chunk_anchor` so the
  item reuses the block's Blockbench model and `gui` display transform, matching how every other
  block item works in vanilla.

## [0.0.0-beta.2] - 2026-09-02

### Fixed

- **Client mod-loading crash** (`WorkhandToolsClient has no @SubscribeEvent methods, but register
  was called anyway`). The client entry class carried `@EventBusSubscriber` but has no static
  `@SubscribeEvent` methods — its only listener is registered manually via `modEventBus.addListener`
  in the constructor. NeoForge's `AutomaticEventSubscriber` then called `bus.register(class)` and
  aborted mod loading. Removed the annotation (same fix already applied to the main `WorkhandTools`
  class). Dedicated-server loading was unaffected, so this only surfaced on a real client.

## [0.0.0-beta.1] - 2026-09-02

### Added

- **Initial port to Minecraft 1.21.1 / NeoForge 21.1.249** (Java 21). API port of the 26.2
  source (41 classes, no mixins). Full tool line unchanged: 12 pickaxes + 12 shovels across
  Stone/Iron/Diamond × 4 grades with 3×3→5×5 area mining and the Cubic/Flat toggle; 4 improved
  vein-mining pickaxes; 2 tree-felling axes; 2 crop-harvesting hoes; Kennestroyer Pickaxe/Shovel
  (5 modes); Reinforced Deepslate Pickaxe; Robust Stick; Tape Measure; Chunk Anchor + Anchor
  Tome (forced chunk loading). Guide book still delivered through Vellumli.

### Technical

- **Tool material system**: `net.minecraft.world.item.ToolMaterial` (26.2 record) →
  `net.neoforged.neoforge.common.SimpleTier` (`Tier`), with explicit `Ingredient` repair items
  instead of `#minecraft:*_tool_materials` tag references. The `Item.Properties.pickaxe()/axe()/
  hoe()/shovel()` builders don't exist in 1.21.1, so every tool item now `extends` its vanilla
  class (`PickaxeItem`/`AxeItem`/`HoeItem`/`ShovelItem`) and attributes come from
  `XxxItem.createAttributes(tier, dmg, spd)` in the registered `Item.Properties`.
- **Client render layer** reverted from the 26.2 render-graph:
  `SubmitCustomGeometryEvent` / `SubmitNodeCollector` → `RenderLevelStageEvent` +
  `LevelRenderer.renderLineBox` on a `RenderType.lines()` buffer (`AreaHighlighter`,
  `ChunkAnchorBorderRenderer`, `MeasurementBox`/`TapeMeasureHandler`); `net.minecraft.gizmos`
  removed; in-world text via `Font.drawInBatch`; the `ChunkAnchorRenderer` render-state block
  entity renderer collapsed to the classic single-`render()` form and `ChunkAnchorRenderState`
  removed; `BookModel` moved to `net.minecraft.client.model` with `setupAnim`/`render`.
- **Trivial API**: `Identifier` → `ResourceLocation`; `ResourceKey<Level>.identifier()` →
  `.location()`; `net.minecraft.util.ARGB` → `FastColor.ARGB32`; `BlockState.typeHolder().is(tag)`
  → `state.is(tag)`; `BlockEntity#saveAdditional/loadAdditional` take a `HolderLookup.Provider`;
  `ChunkAnchorBlock#useItemOn` returns `ItemInteractionResult`; block-break handlers on
  `net.neoforged.neoforge.event.level.BlockEvent.BreakEvent`; `BlockEntityType.Builder.of(...)`;
  NBT `getCompound`/`getBoolean` return values (not `Optional`).
- **Data**: crafting recipe ingredient values wrapped from bare strings to `{"item":…}` /
  `{"tag":…}` objects (1.21.1 requirement); recipe results keep the `{"id":…,"count":…}` form.
- **Build**: `net.neoforged.moddev` template retargeted to NeoForge 21.1.249 / Java 21;
  `modLoader="javafml"` + `loaderVersion` added to `neoforge.mods.toml` (26.2 omits them);
  Vellumli dependency range lowered to the 1.21.1 build (`[0.0.0-beta.1,)`).
- **Verified**: `./gradlew build` OK; `./gradlew runServer` → `Done (6.6s)`, 0 FATAL, 0 recipe
  or datapack parse errors, mod registers cleanly with Vellumli present. Client rendering
  (floating tome, area highlight, tape-measure wireframe) compiles but was not run in a client.

### Not ported

- Same scope boundary as the 26.2 build: no changes to the mod's feature set. This is an API
  down-port only.
