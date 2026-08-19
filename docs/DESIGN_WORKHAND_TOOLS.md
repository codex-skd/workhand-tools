# Diseño técnico — Workhand Tools

> Documento de diseño y especificación funcional. No es el workflow (ver `docs/WORKFLOW_WORKHAND_TOOLS_26-2.md`). Sirve de base para la implementación (propia o delegada en OpenCode) y para las siguientes fases del roadmap.
> **Estado**: v3 — amplía el diseño inicial (6 materiales × 1 grado) con 4 materiales nuevos, un sistema de 4 grados por material con minado en área (AoE), recetas cerradas y assets pospuestos a placeholders vanilla. Ver `## Historial de decisiones` al final.

## Concepto

Una línea de picos, palas y hachas "de obrero" (Workhand), en **3 materiales** (Stone, Iron, Diamond) y **4 grados** cada uno. La diferencia entre materiales es durabilidad/velocidad/encantabilidad (calcada de vanilla). La diferencia entre grados **no es de stats**: es la receta y el **patrón de minado en área (AoE)** que activa la herramienta. Además, 4 picos mejorados (Improved) con vena minera y 2 hachas con tala de árboles.

**Total: 3 materiales × 4 grados × 2 herramientas base + 4 picos mejorados + 2 hachas = 30 items.**

## Materiales y tiers

Los 3 materiales tienen tier vanilla directo (`net.minecraft.world.item.ToolMaterial`) y se implementan reutilizando esos valores tal cual — ver `## Nota de implementación clave`.

Orden de progresión (accesibilidad in-game, de más temprano a más tardío):

| # | Material | Origen stats | Durabilidad (uses) | Velocidad (speed) | Encantabilidad | Bonus daño ataque | Item de reparación |
|---|---|---|---|---|---|---|---|
| 1 | Stone | Custom (`ModToolMaterials.STONE_WORKHAND`) | 655 | 4.0 | 5 | 1 | `#minecraft:stone_tool_materials` |
| 2 | Iron | Custom (`ModToolMaterials.IRON_WORKHAND`) | 1250 | 6.0 | 14 | 2 | `#minecraft:iron_tool_materials` |
| 3 | Diamond | Custom (`ModToolMaterials.DIAMOND_WORKHAND`) | 7805 | 8.0 | 10 | 3 | `#minecraft:diamond_tool_materials` |

## Nota de implementación clave (materiales custom, 5x durabilidad vanilla)

Los 3 materiales base **ya no usan el tier vanilla directo** — `ModToolMaterials.java` registra `STONE_WORKHAND`/`IRON_WORKHAND`/`DIAMOND_WORKHAND`, cada uno una copia exacta de `ToolMaterial.STONE`/`IRON`/`DIAMOND` (mismo speed, bonus de daño, encantabilidad y tag de reparación) salvo la durabilidad, multiplicada ×5 (el coste de fabricación no compensaba con la durabilidad vanilla — feedback de juego). Igual multiplicador aplicado a los materiales mejorados (`IMPROVED_IRON` 750→3750, `IMPROVED_DIAMOND` 4683→23415).

## Grados y minado en área (AoE)

Cada material tiene 4 grados. La diferencia entre grados es **solo receta + patrón de rotura**, nunca durabilidad/velocidad/encantabilidad (esas las pone el material).

| Grado | Nombre (en) | Patrón | Función |
|---|---|---|---|
| 1 — de obrero | *(sin sufijo)* | 3×3×1 (una capa, perpendicular a la mira) | Única |
| 2 — avanzado | Advanced | 3×3×3 (clic izq.) / 3×3×1 (clic der.) | Doble |
| 3 — experto | Expert | 5×5×1 (una capa) | Única |
| 4 — profesional | Professional | 5×5×5 (clic izq.) / 5×5×1 (clic der.) | Doble |

### Reglas comunes

- **Agachado (sneak)**: rompe solo el bloque apuntado, sin AoE, sea cual sea el grado. Anula toda la lógica de abajo.
- **Ancho lateral**: siempre simétrico respecto al bloque apuntado — 2 a cada lado en patrones de 5, 1 a cada lado en patrones de 3.
- **Bloque correcto por bloque**: cada bloque del área se evalúa individualmente contra las reglas de la herramienta (mismo comportamiento que el pico/pala vanilla del mismo tier) — si un bloque del área no es minable con esa herramienta, se ignora (no rompe, no gasta durabilidad extra por él).
- **Durabilidad**: cada bloque roto por el AoE consume 1 punto de durabilidad, igual que si se rompiera uno a uno (comportamiento estándar de mods de minado en área).

### Anclaje vertical para patrones de 5 de alto (5×5×1 / 5×5×5)

Al apuntar de pie a una pared, el área de rotura vertical se ancla según el **pitch** (ángulo de mirada) del jugador:

- **Mirando claramente hacia arriba** respecto al punto medio (pitch por encima de un umbral, propuesto: mirando por encima de ~-10° respecto a la horizontal): el área se centra simétrica sobre el bloque apuntado — 2 bloques por debajo / 2 por encima — llegando hasta el suelo.
- **Mirando en horizontal / al frente** (pitch cercano a 0°, altura de ojos): el área se desplaza para dejar **1 bloque por debajo** del punto apuntado y **3 por encima** — pensado para minar un túnel cómodo de pie, con hueco de sobra hacia arriba sin tener que agachar la vista hasta el suelo.

El patrón de 3×3 (alto=3) no necesita este ajuste: 1 abajo / 1 arriba ya es el único reparto simétrico posible.

> **Umbral de pitch propuesto, no confirmado**: hay que ajustarlo en pruebas de juego una vez implementado — el valor exacto (grados) es una decisión de *feel*, no de diseño cerrado.

### Clic izquierdo vs. clic derecho (grados 2 y 4) — confirmado

**El clic derecho no rompe nada por sí mismo: alterna el modo activo de la herramienta.** El minado sigue siendo siempre el normal de Minecraft (mantener clic izquierdo sobre un bloque) — lo único que cambia es qué patrón de AoE se aplica cuando ese minado normal rompe el bloque objetivo:

- Clic derecho (sobre bloque o aire, sin romper nada) → alterna el modo del item entre **Cúbico** (3×3×3 / 5×5×5) y **Plano** (3×3×1 / 5×5×1). El modo se guarda en el propio ItemStack (data component/NBT) y persiste hasta el siguiente clic derecho.
- Clic izquierdo (minado normal, tal cual vanilla) → al completarse la rotura del bloque objetivo, aplica el patrón del modo actualmente activo en el item.
- Modo por defecto al craftear/obtener el item: Cúbico (3×3×3 / 5×5×5).
- Debe mostrarse feedback al jugador del modo activo (action bar / tooltip del item) para que sea visible sin adivinar.

Los grados 1 y 3 (obrero, experto) no tienen modo ni reaccionan al clic derecho — su patrón (3×3×1 / 5×5×1 respectivamente) es fijo y se aplica siempre al minar normal.

## Naming e IDs

Convención: `<material>_workhand[_<grado>]_<tool>` → `<Material> Workhand[ <Grado>] <Tool>` (grado 1 sin sufijo).

Ejemplo completo para Iron:

| Grado | ID pico | Nombre pico |
|---|---|---|
| 1 | `iron_workhand_pickaxe` | Iron Workhand Pickaxe |
| 2 | `iron_workhand_advanced_pickaxe` | Iron Workhand Advanced Pickaxe |
| 3 | `iron_workhand_expert_pickaxe` | Iron Workhand Expert Pickaxe |
| 4 | `iron_workhand_professional_pickaxe` | Iron Workhand Professional Pickaxe |

(Análogo para `_shovel`, y para los 10 materiales. Prefijos de material: `wooden`, `stone`, `copper`, `deepslate`, `iron`, `blackstone`, `golden`, `diamond`, `obsidian`, `netherite` — `golden_`/`wooden_` siguen la convención vanilla, el resto usa el nombre del material tal cual.)

## Encantamientos

Aplica a los 80 items por igual. **Implementado y verificado contra el jar real de Minecraft 26.2** — no existen tags separados `pickaxe`/`shovel` ni `curse`/`vanishing_curse` (nombres reales: `vanishing`, sin tag `curse` propio). Camino real:

- Los 80 items extienden `data/minecraft/tags/item/pickaxes.json` / `shovels.json` (tags `#minecraft:pickaxes`/`#minecraft:shovels`), de los que ya cuelgan `enchantable/durability`, `enchantable/mining` y `enchantable/mining_loot` en vanilla — esto ya cubre Eficiencia, Fortuna, Toque de Seda e Irrompibilidad automáticamente.
- Además, listados explícitamente (redundante pero inofensivo) en `enchantable/durability.json`, `enchantable/mining.json`, `enchantable/mining_loot.json` y `enchantable/vanishing.json` (Maldición de Desvanecimiento).
- **Mending (Reparación)**: no necesita tag propio — el encantamiento vanilla `mending.json` usa `supported_items: "#minecraft:enchantable/durability"`, ya cubierto arriba.
- `enchantable/equippable.json` es solo para armadura/elytra/cascos, no aplica a herramientas — no se toca.

100% data-driven, sin lógica Java.

## Recetas (confirmadas)

Leyenda:
- `x` = vacío
- `m` = material básico del tier — lingote si es mineral, bloque básico si no lo es (ver tabla `m`/`a` más abajo)
- `a` = bloque del material — bloque compacto (9×) si es mineral, bloque "cocido"/fundido si no es mineral y tiene variante, o el mismo bloque que `m` si no tiene variante cocida (caso obsidiana)
- `p` = `minecraft:stick`
- `r` = `workhand_tools:robust_stick` (item nuevo, ver abajo)

### Robust Stick — item nuevo

ID `workhand_tools:robust_stick` ("Robust Stick"). Receta en mesa de crafteo, sin material de tier (solo palos):

```
x p p
x p p
x p p
```

6 × `minecraft:stick` → **1** × `workhand_tools:robust_stick`.

### Pico, por grado (igual para los 9 materiales con receta shaped — netherite excluido, ver más abajo)

```
Grado 1        Grado 2        Grado 3        Grado 4
m m m          a a a          m a m          a a a
x p m          x p a          x r a          x r a
x p x          x p x          x r x          x r x
```

### Pala, por grado

```
Grado 1        Grado 2        Grado 3        Grado 4
x m m          x a a          x a m          x a a
x p x          x p x          x r x          x r x
x p x          x p x          x r x          x r x
```

### Tabla `m` / `a` por material (9 materiales con receta shaped)

| Material | ¿Mineral? | `m` (grados 1-2 top / grado 3 mixto) | `a` (grados 2-4) |
|---|---|---|---|
| Wood | No | `#minecraft:planks` | `#minecraft:planks` *(sin variante cocida, igual que `m`)* |
| Stone | No | `minecraft:cobblestone` | `minecraft:stone` *(cocido de cobblestone)* |
| Copper | Sí | `minecraft:copper_ingot` | `minecraft:copper_block` |
| Deepslate | No | `minecraft:cobbled_deepslate` | `minecraft:deepslate` *(cocido)* |
| Iron | Sí | `minecraft:iron_ingot` | `minecraft:iron_block` |
| Blackstone | No | `minecraft:blackstone` | `minecraft:blackstone` *(sin variante cocida)* |
| Gold | Sí | `minecraft:gold_ingot` | `minecraft:gold_block` |
| Diamond | Sí | `minecraft:diamond` | `minecraft:diamond_block` |
| Obsidian | No | `minecraft:obsidian` | `minecraft:obsidian` *(sin variante cocida)* |

### Netherite (los 4 grados)

Sin receta shaped — **smithing transform**, igual que vanilla `diamond_pickaxe` → `netherite_pickaxe`:

`minecraft:netherite_upgrade_smithing_template` + `diamond_workhand[_<grado>]_<tool>` + `minecraft:netherite_ingot` → `netherite_workhand[_<grado>]_<tool>`

Es decir, cada grado de netherite se fabrica mejorando el mismo grado en diamante (grado 3 netherite viene de grado 3 diamante, etc.), no desde cero.

## Texturas y modelos

- Modelo de item: parent `minecraft:item/handheld`, `layer0` con la textura. Igual estructura para los 80 items (el modelo no cambia, solo la textura referenciada).
- **Decisión (v3)**: diseñar arte propio para las 80 texturas de golpe no es viable a corto plazo. Fase actual: cada modelo de item apunta a la textura vanilla del pico/pala más parecido (`layer0` referencia `minecraft:item/<vanilla_id>`, sin duplicar el `.png`) — ver mapeo completo material→textura vanilla en `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md` → `## Texturas de herramientas (placeholder vanilla)`. No hay diferenciación visual por grado mientras se usen placeholders.
- El encargo completo de arte propio (80 texturas + icono + Robust Stick, con nombres de archivo exactos, specs técnicas y paleta por material) está documentado para pasar a diseño en `docs/ASSET_LIST_WORKHAND_TOOLS.md`. No bloquea el desarrollo.
- Icono del mod: sin placeholder razonable (no hay icono vanilla que sustituya al logo propio) — sigue pendiente, ver `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md` → `## Logo del mod`.

## Creative tab

Orden en `displayItems`: por tipo (picos primero, luego palas), dentro de cada tipo por material según la tabla de progresión (Wood → Netherite), dentro de cada material por grado ascendente (1 → 4). Icono del tab: `iron_workhand_pickaxe` (grado 1, sin cambios).

## Localización

**Objetivo: cubrir el máximo número de idiomas soportados por Minecraft** (todos los `assets/minecraft/lang/*.json` del juego base como referencia de códigos de idioma soportados por el launcher/juego).

- Las 80 claves de nombre (`item.workhand_tools.<id>`) son puramente compositivas (`<Material> Workhand [<Grado>] <Tool>`), lo que facilita traducir por plantilla en vez de traducir cada uno de los 80 nombres a mano en cada idioma.
- Fase de localización se aborda **después** de cerrar los items/recetas/AoE (evita retraducir si cambian nombres/grados durante el desarrollo).
- Alcance real (cuántos idiomas y con qué nivel de revisión humana vs. traducción automática) se decide al llegar a esa fase — no bloquea el resto del roadmap.

## Integración con JEI

Todas las recetas del mod usan **tipos de receta vanilla estándar** (`minecraft:crafting_shaped` y `minecraft:smithing_transform`), tanto para las 9 líneas de material con shaped como para las 10 líneas de netherite con smithing (incluye Robust Stick, que también es `crafting_shaped`). JEI detecta y muestra automáticamente cualquier receta de esos tipos registrada vía datapack — **no hace falta ningún plugin ni código JEI propio**. Confirmado alcance: solo exposición automática de recetas, sin categoría custom para el patrón AoE (eso no es una receta, es comportamiento en juego, no tiene sentido en JEI).

Único requisito técnico: declarar JEI como dependencia `optional` en `neoforge.mods.toml` si se quiere evitar un warning de "unknown mod" en el log cuando JEI no está instalado (el mod funciona igual sin JEI, es puramente informativo).

## Configuración y compatibilidad con Configured

Si se añade configuración (p. ej. activar/desactivar el AoE por grado, ajustar el umbral de pitch, permitir romper bloques de otros mods en el área), debe seguir usando `net.neoforged.neoforge.common.ModConfigSpec` (como ya hace `Config.java` desde el scaffold inicial) — **Configured** lee automáticamente cualquier `ModConfigSpec` registrado vía `ModContainer.registerConfig`, no requiere integración manual ni dependencia añadida. Solo hay que evitar tipos de config fuera de `ModConfigSpec` (JSON custom, etc.) si se quiere mantener esa compatibilidad gratis.

## Roadmap de implementación (fases)

1. **Fase 1 — Materiales**: ✅ hecho. `ModToolMaterials.java` con `ToolMaterial` propio para Copper/Deepslate/Blackstone/Obsidian, valores exactos de la tabla.
2. **Fase 2 — Items base (grado 1, los 10 materiales)**: ✅ hecho, junto con la Fase 3.
3. **Fase 3 — Grados 2-4**: ✅ hecho. Los 80 items registrados en `ModItems.java` (picos: `Item` genérico + `Item.Properties#pickaxe(...)`; palas: clase `ShovelItem`; ambos con el `ToolMaterial` correspondiente — nota: en 26.2 `Tier`/`Tiers` fue sustituido por `ToolMaterial`, ver `ModToolMaterials.java`) + `robust_stick`, todos en el creative tab en el orden del diseño. Modelos de item (los 80 + `robust_stick`) con `layer0` apuntando a la textura vanilla placeholder de `ASSET_PROMPTS_WORKHAND_TOOLS.md`.
4. **Fase 4 — Recetas**: ✅ hecho. 81 recetas (72 shaped por material/grado + `robust_stick` + 8 smithing transform de netherite), formas verificadas contra `## Recetas (confirmadas)`.
5. **Fase 5 — Mecánica de minado en área**: ✅ hecho. `AoEMiningHandler.java` escucha `net.neoforged.neoforge.event.level.block.BreakBlockEvent` (en 26.2 sustituye al antiguo `BlockEvent.BreakEvent`), calcula el patrón según grado/modo/pitch (`Grade.java`, `AoEMode.java`) y rompe los bloques extra con `Block.dropResources` + `hurtAndBreak` por bloque, respetando bloque-correcto y durabilidad. El modo Cúbico/Plano de los grados 2 y 4 es un `DataComponentType` propio (`ModDataComponents.java`) que se alterna con clic derecho (`PlayerInteractEvent.RightClickItem`) sin romper nada, con feedback en action bar y tooltip. El umbral de pitch es configurable (`Config.PITCH_THRESHOLD_DEGREES`, compatible con Configured). **Caveat conocido**: las palas heredan el comportamiento vanilla de convertir césped en camino de tierra al clic derecho sobre un bloque de césped — en ese caso concreto puede interferir con el toggle de modo; no se ha corregido, pendiente de validar en juego si molesta.
6. **Fase 6 — Tags de encantamiento**: ✅ hecho. Los 80 items extienden `#minecraft:pickaxes`/`#minecraft:shovels` (con lo que heredan durability/mining/mining_loot/vanishing automáticamente) además de estar listados explícitamente en esos 4 tags de `enchantable/`. Nota: los nombres reales de los tags vanilla en 26.2 son `mining`/`mining_loot`/`durability`/`vanishing` (sin sufijo `_curse`) y no existen tags separados `pickaxe`/`shovel` ni `curse` — verificado contra el jar de Minecraft 26.2, se corrige respecto a la mención inicial de este documento.
7. **Fase 7 — Assets**: pospuesta a cuando el diseñador entregue arte (encargo completo en `docs/ASSET_LIST_WORKHAND_TOOLS.md`). Mientras tanto los 80 items + `robust_stick` usan placeholders de texturas vanilla — no bloquea el resto de fases.
8. **Fase 8 — Localización**: traducción a los idiomas objetivo.
9. **Fase 9 — QA**: validar en juego stats, AoE (incluido el anclaje de pitch), encantamientos y reparación para cada material/grado.

## Improved Pickaxes (Vein Mining)

| Item ID | Grade | Material | Durability |
|---|---|---|---|
| `iron_workhand_advanced_improved_pickaxe` | Advanced (GRADE_2) | IMPROVED_IRON | 3750 |
| `iron_workhand_professional_improved_pickaxe` | Professional (GRADE_4) | IMPROVED_IRON | 3750 |
| `diamond_workhand_advanced_improved_pickaxe` | Advanced (GRADE_2) | IMPROVED_DIAMOND | 23415 |
| `diamond_workhand_professional_improved_pickaxe` | Professional (GRADE_4) | IMPROVED_DIAMOND | 23415 |

Improved pickaxes keep the base grade AoE pattern (3x3x3 for advanced, 5x5x5 for professional) AND add Ore Vein Mining:
- Works always (sneak or not); sneak only disables AoE mining.
- When an ore block is broken, a BFS flood-fill (6 directions, max 128 blocks) mines all connected blocks of the same ore type.
- If AoE catches an ore, it also mines connected ores.
- Supported ores: coal, copper, diamond, emerald, gold, iron, lapis, redstone, nether_quartz, nether_gold (plus deepslate variants).
- Material: `IMPROVED_IRON` (BlockTags.INCORRECT_FOR_IRON_TOOL, durability 3750, speed 6.0, attack 2.0, enchant 14, repair iron_ingot) and `IMPROVED_DIAMOND` (BlockTags.INCORRECT_FOR_DIAMOND_TOOL, durability 23415, speed 8.0, attack 3.0, enchant 10, repair diamond).

### Recipes

**Improved pickaxes**: Base pickaxe in center, surrounded by 8 ores:
- Iron: `#minecraft:iron_ores` (iron_ore + deepslate_iron_ore)
- Diamond: `#minecraft:diamond_ores` (diamond_ore + deepslate_diamond_ore)

## Workhand Axes (Tree Felling)

| Item ID | Material | Durability |
|---|---|---|
| `iron_workhand_axe` | IMPROVED_IRON | 3750 |
| `diamond_workhand_axe` | IMPROVED_DIAMOND | 23415 |

Right-click toggles tree felling (data component `felling_enabled`, default false). When enabled, breaking a log block uses BFS flood-fill (6 directions, max 256 blocks) to fell the entire tree:
- Only blocks in `BlockTags.LOGS` are considered, excluding stripped logs (path starts with "stripped").
- Same log type required for connected blocks.
- Durability is consumed per block broken.

### Recipes

- Iron axe: `BBI / IR  /  R ` where B=iron_block, I=iron_ingot, R=robust_stick
- Diamond axe: same pattern with diamond_block/diamond

## Workhand Hoes (Crop Harvesting)

Réplica adaptada de la mecánica de clic-derecho-cosecha del mod de referencia [RightClickHarvest](https://github.com/JamCoreModding/right-click-harvest) (no es dependencia ni port, solo referencia de comportamiento), integrada con el sistema de grado/radio ya existente en Workhand Tools en vez del propio sistema de tiers de hoes del mod original.

| Item ID | Material | Durability | Radio de cosecha |
|---|---|---|---|
| `iron_workhand_hoe` | IMPROVED_IRON | 3750 | 3×3 (lateralHalf=1, igual que Grade.GRADE_1) |
| `diamond_workhand_hoe` | IMPROVED_DIAMOND | 23415 | 5×5 (lateralHalf=2, igual que Grade.GRADE_3) |

Solo 2 items (iron/diamond), sin tier stone — mismo patrón que Workhand Axes.

### Mecánica

- Clic derecho sobre un bloque `CropBlock` (trigo, zanahoria, patata, remolacha y cualquier crop modded que extienda esa clase), `CocoaBlock` o `NetherWartBlock` que esté en **edad máxima** (maduro):
  - Se sueltan los drops normales de romper el bloque (`Block.getDrops`, respeta fortune/encantamientos de la azada).
  - El bloque **no se rompe**: su propiedad `AGE` se resetea a 0 in-place (`level.setBlockAndUpdate`), como si se replantara al instante — no requiere semillas en el inventario.
  - Se aplica también a todos los bloques maduros del área (radio según la tabla de arriba, centrada en el bloque apuntado, mismo Y — sin componente vertical, a diferencia del AoE de picos/palas).
  - Cada bloque cosechado consume 1 punto de durabilidad de la azada.
- **Fuera de alcance deliberadamente** (a diferencia del mod de referencia): plantas que crecen apilándose en varios bloques (`SugarCaneBlock`, `CactusBlock`, `BambooBlock`) — la cosecha por clic derecho solo aplica a cultivos que crecen "sobre el mismo bloque" (edad in-place), no a columnas de altura variable. Config de hambre/XP, tags de blacklist y warning de "usa una azada" del mod original tampoco se portan — no aplican al diseño de Workhand Tools.
- Si el bloque apuntado no está maduro, el clic derecho no hace nada especial (comportamiento vanilla del `HoeItem`, p. ej. convertir tierra en farmland si aplica).
- **Sin sneak-override**: a diferencia del AoE de picos/palas, agacharse no limita la cosecha a 1 bloque — el radio se aplica siempre igual.
- Requiere `NeoForge.EVENT_BUS.register(new CropHarvestHandler())` en `WorkhandTools.java`, análogo a `TreeFellingHandler`. El evento a escuchar es `PlayerInteractEvent.RightClickBlock` (no `RightClickItem`, ya que aquí sí importa el bloque apuntado).

### Recetas

- Iron hoe: mismo patrón que `iron_workhand_axe` (`BBI / IR  /  R `, B=iron_block, I=iron_ingot, R=robust_stick) pero con forma de azada en vez de hacha.
- Diamond hoe: mismo patrón con diamond_block/diamond.

### Naming e IDs

`iron_workhand_hoe` / `diamond_workhand_hoe` → "Iron Workhand Hoe" / "Diamond Workhand Hoe", mismo estilo que el resto del mod.

## Chunk Anchor + Anchor Tome (chunk loading)

Conjunto de 2 items nuevos, sin relación con la línea de herramientas: aplican **chunk loading forzado** (mantiene el chunk cargado sin jugador cerca) al chunk donde está colocado el pedestal.

### Items

| Item | ID | Tipo |
|---|---|---|
| Chunk Anchor | `workhand_tools:chunk_anchor` | Bloque + block entity |
| Anchor Tome | `workhand_tools:anchor_tome` | Item (libro) |

### Assets reutilizados (con permiso) de Occultism

El mod **Occultism** (klikli-dev, https://github.com/klikli-dev/occultism) ha dado permiso para reutilizar 2 assets suyos en este mod. Ya extraídos y adaptados al namespace `workhand_tools`:

- **Chunk Anchor**: modelo + textura del bloque `occultism:otherstone_pedestal` → copiados como `assets/workhand_tools/models/block/chunk_anchor.json` (basado en `otherstone_pedestal.json`) y `assets/workhand_tools/textures/block/chunk_anchor.png` (basado en `pedestal_base.png`). Blockstate e item model del bloque creados en el mismo estilo (`assets/workhand_tools/blockstates/chunk_anchor.json`, `assets/workhand_tools/items/chunk_anchor.json`).
- **Anchor Tome**: textura del item `occultism:book_of_binding_djinni` → copiada como `assets/workhand_tools/textures/item/anchor_tome.png`, con modelo de item estándar (`minecraft:item/generated` + `layer0`) en `assets/workhand_tools/models/item/anchor_tome.json` y `assets/workhand_tools/items/anchor_tome.json`.

Esto **no son placeholders vanilla** como el resto del mod: son arte real cedido por Occultism. Pendiente: añadir la entrada correspondiente en `README.md` → `## Credits` (mismo formato que la entrada existente de Measurements/Tape Measure) al implementar.

### Mecánica de colocación

- El Chunk Anchor se coloca como cualquier bloque, en el suelo.
- Clic derecho con el Anchor Tome en mano sobre el Chunk Anchor → inserta el tome en el slot interno del block entity; el item se consume del inventario del jugador y pasa a renderizarse sobre el pedestal.
- Clic derecho vacío (o agachado) sobre un Chunk Anchor con tome puesto → lo extrae, vuelve al inventario del jugador.
- Romper el Chunk Anchor con el tome puesto → dropea ambos items y libera el chunk forzado inmediatamente (no debe quedar un ticket huérfano).

### Chunk loading

- Alcance: **solo el chunk que contiene el Chunk Anchor**, sin radio ni chunks vecinos.
- Implementación: ticket de forced chunk vanilla (mismo mecanismo que el comando `/forceload`, vía `ServerLevel#setChunkForced`/`ForcedChunksSavedData`), asociado a la `BlockPos` del pedestal — persiste a través de descarga/recarga de chunk y de reinicios del servidor.
- Permanente mientras el tome esté puesto: sin coste, sin degradación, sin fuel. Se libera al extraer el tome o al romper el altar (incluyendo el caso de que el chunk se rompa/descargue por otros medios — hay que reconciliar el ticket al cargar el mundo, por si el bloque ya no existe).

### Indicador visual del chunk (borde de esquinas)

- Config nueva en `Config.java` (compatible con Configured): `CHUNK_ANCHOR_BORDER_MODE`, enum con 3 valores:
  - `ALWAYS` (**por defecto**): se renderiza siempre que el jugador esté dentro del render distance del cliente.
  - `SNEAK_LOOKING`: solo si el jugador está agachado y mirando hacia el Chunk Anchor, dentro de 20 bloques.
  - `NEARBY`: solo si el jugador está a ≤20 bloques del Chunk Anchor (sin importar postura/mirada).
- Render client-side (`RenderLevelStageEvent`), solo las 4 aristas verticales de las esquinas del chunk (16×16), color blanco — sin grid completo del chunk, solo esquinas.

### Efectos visuales del Anchor Tome

Mientras está puesto en el pedestal:

- **Rotación continua**: el libro gira sobre sí mismo (eje Y) igual que el libro flotante sobre una mesa de encantamientos vanilla — mismo tipo de animación que `EnchantmentTableBlockEntity`/`BookModel` (ángulo de rotación basado en tiempo de juego, sin input del jugador). Requiere un `BlockEntityRenderer` custom (el item no puede rotar solo vía blockstate) posicionado sobre el pedestal.
- **Partículas orbitando** alrededor del libro + **glow/luz sutil** (igual que se acordó antes para la bola, se mantiene con el libro).

### Receta (confirmada)

Tier alto — forzar carga de chunks es una mecánica potente, no debe ser accesible pronto:

- **Chunk Anchor**: núcleo de bloque de diamante + marco de obsidiana + lingote de netherite (receta shaped, cara). Forma exacta pendiente de cerrar en implementación.
- **Anchor Tome**: `minecraft:book` + amatista + diamante. Forma exacta pendiente de cerrar en implementación.

### Fases de implementación

1. Bloque `ChunkAnchorBlock` + `ChunkAnchorBlockEntity` (slot interno, inserción/extracción por clic derecho).
2. Item `AnchorOrbItem`.
3. Integración con forced chunk tickets (alta/baja + reconciliación al cargar el mundo).
4. Render del borde de esquinas (client-side) + `Config.CHUNK_ANCHOR_BORDER_MODE`.
5. Partículas/glow del orb puesto.
6. Recetas + creative tab + localización (en, es-ES si aplica al resto del mod).
7. QA: persistencia tras reinicio de servidor, extracción/rotura del altar, los 3 modos de borde.

## Historial de decisiones

- **v6**: pivote del item colocado sobre el Chunk Anchor — de "Anchor Orb" (bola) a **Anchor Tome** (libro), con rotación continua estilo mesa de encantamientos añadida a los efectos visuales ya acordados (partículas + glow). Ambos items del conjunto dejan de usar placeholders vanilla: assets reales cedidos con permiso por **Occultism** (klikli-dev) — pedestal (`otherstone_pedestal`) para Chunk Anchor, libro (`book_of_binding_djinni`) para Anchor Tome, ya extraídos y adaptados al namespace `workhand_tools`. Receta del tome ajustada a `minecraft:book` + amatista + diamante (antes ojo de ender + amatista + diamante). Primer intento de implementación (delegado en Nvidia/nemotron sobre el diseño v5) falló con 57 errores de compilación por asumir una versión de Minecraft incorrecta — descartado, no llegó a commitearse.
- **v5**: nuevo conjunto Chunk Anchor + Anchor Orb — chunk loading forzado del chunk del altar (sin radio), permanente mientras el orb esté puesto, indicador visual de esquinas de chunk configurable (Always/SneakLooking/Nearby, 20 bloques), partículas+glow en el orb, receta de tier alto (diamante+obsidiana+netherite / ojo de ender+amatista+diamante). No forma parte de la línea de herramientas Workhand.
- **v1**: 6 materiales vanilla, 1 grado, minado 1×1 estándar.
- **v2**: +4 materiales (Copper, Deepslate, Blackstone, Obsidian) con stats propuestos (confirmados en v3) · +4 grados por material con patrones de minado en área (3×3×1 / 3×3×3 / 5×5×1 / 5×5×5), doble función clic izq./der. en grados 2 y 4, override total al agacharse, y anclaje vertical dependiente del pitch para los patrones de 5 de alto · total de items pasa de 12 a 80.
- **v4**: durabilidad ×5 en todos los materiales de herramienta — feedback del usuario tras publicar: el coste de fabricación no compensaba con la durabilidad vanilla. `STONE_WORKHAND`/`IRON_WORKHAND`/`DIAMOND_WORKHAND` (`ModToolMaterials.java`) reemplazan el uso directo de `ToolMaterial.STONE`/`IRON`/`DIAMOND` en `ModItems.java`; `IMPROVED_IRON`/`IMPROVED_DIAMOND` también ×5. Solo cambia durabilidad, el resto de stats (velocidad, encantabilidad, bonus de daño, tag de reparación) se mantiene igual que el vanilla original.
- **v3**: recetas de los 4 grados cerradas (pico y pala, forma distinta entre ambos) + nuevo item `robust_stick` (Robust Stick, 6 stick → 1) usado como ingrediente en grados 3-4 · tabla `m`/`a` por material confirmada · JEI: exposición automática vía recetas vanilla estándar, sin plugin propio · stats de los 4 materiales nuevos confirmados · assets pospuestos: los 80 items usan placeholders de texturas vanilla, encargo completo para diseñador documentado en `docs/ASSET_LIST_WORKHAND_TOOLS.md`.

Cambios a este documento requieren confirmación del usuario antes de implementarse (no asumir variaciones de balance ni de mecánica sin pedirlo explícitamente).
