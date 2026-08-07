# Changelog — Workhand Tools

## [0.0.0-beta.11] - 2026-08-07

### Feature

- **Custom texture assets integrated**: All 25 PNG texture files (24 tools + robust_stick) with final designed art delivered and deployed. Stone, Iron, and Diamond tools now display distinct visual appearance with grade progression (1-4) visible at a glance. Robust Stick shows thickened handle with metal reinforcement band. Mod icon (64×64) updated to custom emblem. Replaces all placeholder PNGs with production-quality textures.

### Visual

- Icons now display custom pixel art in inventory, creative tab, hotbar, and held state
- Material differentiation: stone (gray), iron (metallic silver), diamond (cyan crystalline)
- Grade progression: base design → +reinforcements → +thicker outline → +brillo filo (grades 1-4)
- All 16×16 textures render crisp and clear in-game

## [0.0.0-beta.10] - 2026-08-07

### Breaking Change

- **Material scope reduction: 10 → 3 materials** (80 items → 24 items). Removed Wooden, Copper, Deepslate, Blackstone, Gold, Obsidian, and Netherite materials entirely. Kept only Stone, Iron, and Diamond — all 4 grades per material remain (24 tools + robust_stick = 25 items total).

### Feature

- **Local PNG texture files for all items**: Created 25 dedicated PNG files in `textures/item/` directory. Each file has exact naming pattern ready for designer art replacement (e.g., `stone_workhand_pickaxe.png`, `iron_workhand_advanced_shovel.png`, `diamond_workhand_expert_pickaxe.png`, `robust_stick.png`). Placeholder PNGs are 16×16 px with material-specific colors; designer can replace files directly without any code changes needed.

### Refactor

- **Java code cleanup**: `ModItems.java` MATERIALS list reduced from 10 to 3 entries. `ModToolMaterials.java` stripped to empty (no custom materials needed for vanilla Stone/Iron/Diamond tiers).
- **JSON assets trimmed**: 56 model JSON files, 56 recipe JSON files, and corresponding tag entries removed for deleted materials. Remaining 25 models now reference local PNG textures instead of vanilla placeholders.
- **Documentation updated**: `DESIGN_WORKHAND_TOOLS.md`, `ASSET_LIST_WORKHAND_TOOLS.md`, `ASSET_PROMPTS_WORKHAND_TOOLS.md` all updated to reflect 3-material scope and local texture approach.

### Note

This beta closes scope to 3 core materials for focused art delivery. All gameplay mechanics (AoE mining, enchantments, durability, recipes) remain fully functional on remaining materials and grades. Worlds from beta.9 will lose the 56 removed items but existing crafted Stone/Iron/Diamond tools remain playable.

## [0.0.0-beta.9] - 2026-08-06

### Feature

- **Item model JSON templates**: Generated 81 item model files (80 tools + robust_stick) with vanilla texture placeholders. All items now display icons correctly in inventory and creative tab. Models reference vanilla equivalent textures for each material (copper → iron, deepslate → stone, blackstone/obsidian use appropriate vanilla equivalents). Localization already complete from previous beta (all 81 item names in en_us.json). Completes Phase 7 (asset placeholder phase) of implementation roadmap.

## [0.0.0-beta.8] - 2026-08-06

### Fix

- **Modelos de items generados correctamente**: Los modelos JSON se generan ahora en `src/generated/resources` durante la compilación. Todos los 80 tools (pickaxes y shovels) y robust_stick usan texturas vanilla como placeholder (wooden_pickaxe, stone_shovel, diamond_pickaxe, etc.). Ahora los iconos de items se ven correctamente ingame sin texturas de error.

## [0.0.0-beta.7] - 2026-08-06

### Fix

- **Modelo de items registrado correctamente**: Los archivos JSON de modelo (`assets/workhand_tools/models/item/`) ahora se cargan correctamente. Todos los 80 tools y robust_stick usan texturas vanilla como placeholder mientras se implementan assets propios. Verificado que los modelos JSON usan parent `minecraft:item/handheld` y texturas vanilla correctas (wooden_pickaxe, stone_shovel, etc.).

## 0.0.0-beta.5

- **Fix crítico**: `0.0.0-beta.4` crasheaba durante la carga con `NullPointerException: Item id not set` y `ExceptionInInitializerError`. Causa: bloque `static { }` en `WorkhandTools.java` intentaba inicializar `ModItems.PICKAXES` demasiado temprano (antes de que `DeferredRegister` estuviera completamente listo para registrar items), causando que los items se crearan sin IDs asignados. Revertidos cambios innecesarios de `Supplier<ToolMaterial>` en `ModToolMaterials.java` y getters en `ModItems.java`. El mod ahora carga correctamente sin crashear. Funcionalidad de lookups (`buildLookups()`) temporalmente comentada mientras se investiguen issues de inicialización estática más profundos.

## 0.0.0-beta.4

- **Fix crítico**: `0.0.0-beta.3` cargaba (el bug del jar ya estaba resuelto) pero crasheaba al arrancar con `ExceptionInInitializerError` durante `FMLCommonSetupEvent`, confirmado contra un crash report real. Causa: en `ModItems.java` los campos `PICKAXES`/`SHOVELS` (que llaman a `registerTools(...)`) estaban declarados **antes** que `MATERIALS`, `GRADES` y `BY_ID`, de los que dependen — en Java los inicializadores de campos estáticos se ejecutan en orden textual, así que esas dependencias todavía eran `null` cuando `registerTools` las usaba. Reordenados los campos (dependencias primero).

## 0.0.0-beta.3

- **Fix crítico**: `0.0.0-beta.2` no cargaba en absoluto en el juego (`WARN: Skipping jar. File ... is not a valid mod file`, detectado probando en una instancia real de CurseForge). Causa: `build.gradle` apuntaba la tarea `generateModMetadata` a `src/main/templates` (carpeta inexistente) en vez de `src/main/resources/templates`, así que la tarea quedaba `NO-SOURCE` y el jar nunca incluía un `META-INF/neoforge.mods.toml` resuelto — solo la copia sin procesar en `templates/META-INF/`. Bug heredado del scaffold `codex-docs/mod_template` (comparado con `ageforged_armor`, que sí tiene la ruta correcta). Corregido aquí y en el template para que no se repita en mods futuros.

## 0.0.0-beta.2

- Primera subida a CurseForge con contenido jugable real (hasta ahora solo el scaffold vacío de `0.0.0-beta.1`): los 80 items, recetas, encantamientos y la mecánica de minado en área completa. Release notes: `docs/curseforge/versions/0.0.0-beta.2.md`.

## 0.0.0-beta.1

- Scaffold inicial desde el esqueleto `codex-docs/mod_template/neoforge/26.2-26.2.0.32-beta` (NeoForge 26.2 / NeoForge 26.2.0.32-beta).
- Repo creado en `stalking-dragons/minecraft/workhand-tools`.
- Diseño técnico documentado en `docs/DESIGN_WORKHAND_TOOLS.md`: pala y pico de obrero para los 6 tiers vanilla de material (madera, piedra, hierro, oro, diamante, netherite), con durabilidad/eficiencia/encantabilidad calcadas de las herramientas base.
- Prompts de imagen para logo y texturas documentados en `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md`.
- Ampliado el diseño a 10 materiales (+ Copper, Deepslate, Blackstone, Obsidian, stats confirmados) y 4 grados por material (obrero/avanzado/experto/profesional) con minado en área (3×3×1, 3×3×3, 5×5×1, 5×5×5), doble función clic izq./der. en grados 2 y 4, override al agacharse y anclaje vertical dependiente del pitch para los patrones de 5 de alto. Total de items: 12 → 80.
- Cerradas las recetas de los 4 grados (pico y pala, formas distintas) + nuevo item `robust_stick` (Robust Stick) usado en grados 3-4 + tabla `m`/`a` de ingredientes por material. JEI se integra gratis vía recetas vanilla estándar, sin plugin propio.
- Corregido `archivesName` en `build.gradle` para seguir la convención `<mod_id>-<mc>-neoforge`.
- Primera subida a CurseForge para validar el proyecto mientras se desarrollan los 80 items y la mecánica de minado en área.
- Assets pospuestos: los 80 items usan placeholders de texturas vanilla (sin arte propio por ahora) — mapeo completo en `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md`. Encargo completo de arte para diseñador (80 texturas + robust_stick, nombres de archivo exactos, specs y paleta) documentado en `docs/ASSET_LIST_WORKHAND_TOOLS.md`.
- Icono del mod (`assets/workhand_tools/icon.png`) y banner de CurseForge generados y wireados en `neoforge.mods.toml`.
- Proyecto de CurseForge dado de alta (`1640361`), `docs/curseforge/project_vars.md` completado con project ID, tokens y game_versions.
- Fases 1-4 y 6 implementadas (delegado en OpenCode, verificado manualmente): `ModToolMaterials` con los 4 tiers propios, los 80 items (`ModItems`) con modelos placeholder y creative tab, las 81 recetas (72 shaped + robust_stick + 8 smithing de netherite), y los tags de encantamiento (`#minecraft:pickaxes`/`#minecraft:shovels` + `enchantable/durability|mining|mining_loot|vanishing`).
- Fase 5 implementada (delegado en OpenCode, corregido y verificado manualmente compilando y leyendo el código): mecánica de minado en área completa — `AoEMiningHandler` (patrón 3×3×1/3×3×3/5×5×1/5×5×5 según grado, ancho lateral simétrico, anclaje vertical por pitch, override al agacharse, chequeo de bloque correcto y consumo de durabilidad por bloque), `ModDataComponents` (modo Cúbico/Plano persistente en el ItemStack para grados 2 y 4, alternado con clic derecho sin romper nada, con feedback en action bar y tooltip), y `Config.PITCH_THRESHOLD_DEGREES` configurable. Caveat conocido: las palas heredan el comportamiento vanilla de crear camino de tierra al clic derecho sobre césped, puede interferir puntualmente con el toggle de modo — pendiente de validar en juego.---

## [0.0.0-beta.6] - 2026-08-05

### Change

- **Recompilado contra NeoForge `26.2.0.37-beta`**: bump de `neo_version` en `gradle.properties` (`26.2.0.32-beta` -> `26.2.0.37-beta`). Verificado con `runServer` (arranque sin errores).
- **Fixed item registration (NeoForge 26.2 requires registerItem so the item id is set on Item.Properties) and converted all 81 recipes to the 26.2 string ingredient format.**

## [angelog — Workhand Tools

## 0.0.0-beta.5

- **Fix crítico**: `0.0.0-beta.4` crasheaba durante la carga con `NullPointerException: Item id not set` y `ExceptionInInitializerError`. Causa: bloque `static { }` en `WorkhandTools.java` intentaba inicializar `ModItems.PICKAXES` demasiado temprano (antes de que `DeferredRegister` estuviera completamente listo para registrar items), causando que los items se crearan sin IDs asignados. Revertidos cambios innecesarios de `Supplier<ToolMaterial>` en `ModToolMaterials.java` y getters en `ModItems.java`. El mod ahora carga correctamente sin crashear. Funcionalidad de lookups (`buildLookups()`) temporalmente comentada mientras se investiguen issues de inicialización estática más profundos.

## 0.0.0-beta.4

- **Fix crítico**: `0.0.0-beta.3` cargaba (el bug del jar ya estaba resuelto) pero crasheaba al arrancar con `ExceptionInInitializerError` durante `FMLCommonSetupEvent`, confirmado contra un crash report real. Causa: en `ModItems.java` los campos `PICKAXES`/`SHOVELS` (que llaman a `registerTools(...)`) estaban declarados **antes** que `MATERIALS`, `GRADES` y `BY_ID`, de los que dependen — en Java los inicializadores de campos estáticos se ejecutan en orden textual, así que esas dependencias todavía eran `null` cuando `registerTools` las usaba. Reordenados los campos (dependencias primero).

## 0.0.0-beta.3

- **Fix crítico**: `0.0.0-beta.2` no cargaba en absoluto en el juego (`WARN: Skipping jar. File ... is not a valid mod file`, detectado probando en una instancia real de CurseForge). Causa: `build.gradle` apuntaba la tarea `generateModMetadata` a `src/main/templates` (carpeta inexistente) en vez de `src/main/resources/templates`, así que la tarea quedaba `NO-SOURCE` y el jar nunca incluía un `META-INF/neoforge.mods.toml` resuelto — solo la copia sin procesar en `templates/META-INF/`. Bug heredado del scaffold `codex-docs/mod_template` (comparado con `ageforged_armor`, que sí tiene la ruta correcta). Corregido aquí y en el template para que no se repita en mods futuros.

## 0.0.0-beta.2

- Primera subida a CurseForge con contenido jugable real (hasta ahora solo el scaffold vacío de `0.0.0-beta.1`): los 80 items, recetas, encantamientos y la mecánica de minado en área completa. Release notes: `docs/curseforge/versions/0.0.0-beta.2.md`.

## 0.0.0-beta.1

- Scaffold inicial desde el esqueleto `codex-docs/mod_template/neoforge/26.2-26.2.0.32-beta` (NeoForge 26.2 / NeoForge 26.2.0.32-beta).
- Repo creado en `stalking-dragons/minecraft/workhand-tools`.
- Diseño técnico documentado en `docs/DESIGN_WORKHAND_TOOLS.md`: pala y pico de obrero para los 6 tiers vanilla de material (madera, piedra, hierro, oro, diamante, netherite), con durabilidad/eficiencia/encantabilidad calcadas de las herramientas base.
- Prompts de imagen para logo y texturas documentados en `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md`.
- Ampliado el diseño a 10 materiales (+ Copper, Deepslate, Blackstone, Obsidian, stats confirmados) y 4 grados por material (obrero/avanzado/experto/profesional) con minado en área (3×3×1, 3×3×3, 5×5×1, 5×5×5), doble función clic izq./der. en grados 2 y 4, override al agacharse y anclaje vertical dependiente del pitch para los patrones de 5 de alto. Total de items: 12 → 80.
- Cerradas las recetas de los 4 grados (pico y pala, formas distintas) + nuevo item `robust_stick` (Robust Stick) usado en grados 3-4 + tabla `m`/`a` de ingredientes por material. JEI se integra gratis vía recetas vanilla estándar, sin plugin propio.
- Corregido `archivesName` en `build.gradle` para seguir la convención `<mod_id>-<mc>-neoforge`.
- Primera subida a CurseForge para validar el proyecto mientras se desarrollan los 80 items y la mecánica de minado en área.
- Assets pospuestos: los 80 items usan placeholders de texturas vanilla (sin arte propio por ahora) — mapeo completo en `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md`. Encargo completo de arte para diseñador (80 texturas + robust_stick, nombres de archivo exactos, specs y paleta) documentado en `docs/ASSET_LIST_WORKHAND_TOOLS.md`.
- Icono del mod (`assets/workhand_tools/icon.png`) y banner de CurseForge generados y wireados en `neoforge.mods.toml`.
- Proyecto de CurseForge dado de alta (`1640361`), `docs/curseforge/project_vars.md` completado con project ID, tokens y game_versions.
- Fases 1-4 y 6 implementadas (delegado en OpenCode, verificado manualmente): `ModToolMaterials` con los 4 tiers propios, los 80 items (`ModItems`) con modelos placeholder y creative tab, las 81 recetas (72 shaped + robust_stick + 8 smithing de netherite), y los tags de encantamiento (`#minecraft:pickaxes`/`#minecraft:shovels` + `enchantable/durability|mining|mining_loot|vanishing`).
- Fase 5 implementada (delegado en OpenCode, corregido y verificado manualmente compilando y leyendo el código): mecánica de minado en área completa — `AoEMiningHandler` (patrón 3×3×1/3×3×3/5×5×1/5×5×5 según grado, ancho lateral simétrico, anclaje vertical por pitch, override al agacharse, chequeo de bloque correcto y consumo de durabilidad por bloque), `ModDataComponents` (modo Cúbico/Plano persistente en el ItemStack para grados 2 y 4, alternado con clic derecho sin romper nada, con feedback en action bar y tooltip), y `Config.PITCH_THRESHOLD_DEGREES` configurable. Caveat conocido: las palas heredan el comportamiento vanilla de crear camino de tierra al clic derecho sobre césped, puede interferir puntualmente con el toggle de modo — pendiente de validar en juego.
