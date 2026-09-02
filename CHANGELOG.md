# Workhand Tools (1.21.1) — Changelog

Branch `minecraft/1.21.1/neoforge-21.1.249/production`. History independent of the 26.2 branch.

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
