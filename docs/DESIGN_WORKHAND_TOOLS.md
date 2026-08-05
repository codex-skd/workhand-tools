# Diseño técnico — Workhand Tools

> Documento de diseño y especificación funcional. No es el workflow (ver `docs/WORKFLOW_WORKHAND_TOOLS_26-2.md`). Sirve de base para la implementación (propia o delegada en OpenCode) y para las siguientes fases del roadmap.
> **Estado**: v2 — amplía el diseño inicial (6 materiales × 1 grado) con 4 materiales nuevos y un sistema de 4 grados por material con minado en área (AoE). Ver `## Historial de decisiones` al final.

## Concepto

Una línea de picos y palas "de obrero" (Workhand), en **10 materiales** y **4 grados** cada uno. La diferencia entre materiales es durabilidad/velocidad/encantabilidad (calcada de vanilla cuando existe equivalente). La diferencia entre grados **no es de stats**: es la receta y el **patrón de minado en área (AoE)** que activa la herramienta.

**Total: 10 materiales × 4 grados × 2 herramientas = 80 items.**

## Materiales y tiers

6 materiales tienen tier vanilla directo (`net.minecraft.world.item.Tiers`) y se implementan reutilizando esos valores tal cual — ver `## Nota de implementación clave`. Los 4 materiales nuevos (cobre, pizarra/deepslate, roca negra/blackstone, obsidiana) no existen como herramienta en vanilla: los valores de la tabla son una progresión equilibrada, **confirmados**.

Orden de progresión (accesibilidad in-game, de más temprano a más tardío):

| # | Material | Origen stats | Durabilidad (uses) | Velocidad (speed) | Encantabilidad | Bonus daño ataque | Item de reparación |
|---|---|---|---|---|---|---|---|
| 1 | Wood | Vanilla (`Tiers.WOOD`) | 59 | 2.0 | 15 | 0 | `#minecraft:planks` |
| 2 | Stone | Vanilla (`Tiers.STONE`) | 131 | 4.0 | 5 | 1 | `#minecraft:stone_tool_materials` |
| 3 | Copper | Confirmado | 185 | 4.5 | 8 | 1 | `minecraft:copper_ingot` |
| 4 | Deepslate (pizarra) | Confirmado | 205 | 5.0 | 6 | 1 | `minecraft:cobbled_deepslate` |
| 5 | Iron | Vanilla (`Tiers.IRON`) | 250 | 6.0 | 14 | 2 | `minecraft:iron_ingot` |
| 6 | Blackstone (roca negra) | Confirmado | 270 | 6.5 | 9 | 2 | `minecraft:blackstone` |
| 7 | Gold | Vanilla (`Tiers.GOLD`) | 32 | 12.0 | 22 | 0 | `minecraft:gold_ingot` |
| 8 | Diamond | Vanilla (`Tiers.DIAMOND`) | 1561 | 8.0 | 10 | 3 | `minecraft:diamond` |
| 9 | Obsidian | Confirmado | 1800 | 7.0 | 12 | 3 | `minecraft:obsidian` |
| 10 | Netherite | Vanilla (`Tiers.NETHERITE`) | 2031 | 9.0 | 15 | 4 | `minecraft:netherite_ingot` |

Justificación de las propuestas:
- **Copper**: entre Stone e Iron — se obtiene con esfuerzo similar (necesita horno), algo más accesible que hierro.
- **Deepslate**: ligeramente por encima de Copper — material denso (en vanilla tarda ~3× más que stone en romperse a mano), pero sigue siendo temprano/medio.
- **Blackstone**: justo por encima de Iron — requiere acceso al Nether, en paralelo a la carrera del oro.
- **Obsidian**: entre Diamond y Netherite — solo se puede minar con pico de diamante o superior en vanilla, coherente con ser un tier tardío; velocidad más baja que diamante (material denso/pesado) pero muy duradero.

Gold mantiene su rareza vanilla (baja durabilidad, alta velocidad, alta encantabilidad) — no se "arregla", es intencional en el juego base.

## Nota de implementación clave (materiales vanilla)

Para los 6 materiales con tier vanilla, **no redefinir stats a mano**: registrar usando directamente `net.minecraft.world.item.Tiers` (`Tiers.WOOD/STONE/IRON/GOLD/DIAMOND/NETHERITE`) como `Tier` de `PickaxeItem`/`ShovelItem`, igual que vanilla. Para los 4 materiales nuevos, crear un `SimpleTier`/`Tier` propio por material con los valores confirmados de la tabla.

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

### Clic izquierdo vs. clic derecho (grados 2 y 4)

- **Clic izquierdo** = minado normal de Minecraft (mantener para romper) → patrón "grande" (3×3×3 o 5×5×5).
- **Clic derecho** = patrón "plano" (3×3×1 o 5×5×1), replicando el comportamiento del grado inferior/superior de una sola capa.

Los grados 1 y 3 (obrero, experto) solo tienen la función plana — no reaccionan a clic derecho de forma distinta al minado normal.

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

Sin cambios respecto al diseño original — aplica a los 80 items por igual. Añadir cada item a los tags vanilla de encantamiento vía datapack (`src/main/resources/data/minecraft/tags/item/enchantable/...`):

- `enchantable/pickaxe.json` (picos) / `enchantable/shovel.json` (palas)
- `enchantable/mining.json` (Eficiencia)
- `enchantable/mining_loot.json` (Fortuna, Toque de Seda)
- `enchantable/durability.json` (Irrompibilidad)
- `enchantable/curse.json` y `enchantable/vanishing_curse.json`
- `enchantable/equippable.json` si aplica a Mending — verificar contra las tags reales del build 26.2

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

- Modelo de item: parent `minecraft:item/handheld`, `layer0` con la textura propia. Igual para los 80 items (el modelo no cambia, solo la textura referenciada).
- Textura: 16×16 px. Prompts para los 6 materiales originales + los 4 nuevos en `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md`.
- **Diferenciación visual por grado** (grados 2-4 deben distinguirse a simple vista del grado 1 del mismo material): ver sección específica en `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md` — propuesta de escalado visual (remaches/refuerzos/tamaño de cabeza progresivo).
- Icono del mod: sin cambios respecto a v1.

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

1. **Fase 1 — Materiales**: confirmar stats de Copper/Deepslate/Blackstone/Obsidian, crear sus `Tier` propios.
2. **Fase 2 — Items base (grado 1, los 10 materiales)**: 20 items, reusando el trabajo de la v1 para los 6 materiales vanilla + los 4 nuevos + `robust_stick`.
3. **Fase 3 — Grados 2-4**: 60 items adicionales (mismas clases `PickaxeItem`/`ShovelItem` y mismo `Tier` que el grado 1 del mismo material — el grado no cambia stats).
4. **Fase 4 — Recetas**: cerradas (ver `## Recetas (confirmadas)`) — 9 líneas shaped (pico+pala×4 grados por material) + `robust_stick` + 4 smithing transform de netherite. Recetas y JEI son data-driven, JEI las muestra gratis.
5. **Fase 5 — Mecánica de minado en área**: la parte de código NeoForge más sustancial del mod (evento de rotura de bloque, detección de patrón según grado/clic/agachado/pitch, comprobación de bloque correcto por bloque, consumo de durabilidad). Se delega en OpenCode por su envergadura, con este documento como especificación.
6. **Fase 6 — Tags de encantamiento**: extender tags vanilla `enchantable/*` con los 80 items.
7. **Fase 7 — Assets**: texturas de los 80 items + diferenciación visual por grado + textura de `robust_stick`.
8. **Fase 8 — Localización**: traducción a los idiomas objetivo.
9. **Fase 9 — QA**: validar en juego stats, AoE (incluido el anclaje de pitch), encantamientos y reparación para cada material/grado.

## Historial de decisiones

- **v1**: 6 materiales vanilla, 1 grado, minado 1×1 estándar.
- **v2**: +4 materiales (Copper, Deepslate, Blackstone, Obsidian) con stats propuestos (confirmados en v3) · +4 grados por material con patrones de minado en área (3×3×1 / 3×3×3 / 5×5×1 / 5×5×5), doble función clic izq./der. en grados 2 y 4, override total al agacharse, y anclaje vertical dependiente del pitch para los patrones de 5 de alto · total de items pasa de 12 a 80.
- **v3**: recetas de los 4 grados cerradas (pico y pala, forma distinta entre ambos) + nuevo item `robust_stick` (Robust Stick, 6 stick → 1) usado como ingrediente en grados 3-4 · tabla `m`/`a` por material confirmada · JEI: exposición automática vía recetas vanilla estándar, sin plugin propio.

Cambios a este documento requieren confirmación del usuario antes de implementarse (no asumir variaciones de balance ni de mecánica sin pedirlo explícitamente).
