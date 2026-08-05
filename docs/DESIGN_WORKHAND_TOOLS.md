# Diseño técnico — Workhand Tools

> Documento de diseño y especificación funcional. No es el workflow (ver `docs/WORKFLOW_WORKHAND_TOOLS_26-2.md`). Sirve de base para la implementación (propia o delegada en OpenCode) y para las siguientes fases del roadmap.

## Concepto

Una pala y un pico "de obrero" (Workhand), craftables en los 6 materiales base de Minecraft, cada uno con la durabilidad, velocidad de minado y encantabilidad **idénticas** a la herramienta vanilla equivalente. No son herramientas nuevas con stats propios: son una segunda línea de la misma pala/pico vanilla, con textura y nombre propios, para permitir progresión/estética alternativa sin desequilibrar el juego.

12 items en total: 6 tiers × 2 tipos de herramienta.

## Naming e IDs

Convención: `<material>_workhand_<tool>`, Title Case en inglés para el nombre mostrado: `<Material> Workhand <Tool>`.

| Material (tier) | ID pico | ID pala | Nombre pico | Nombre pala |
|---|---|---|---|---|
| Wood | `wooden_workhand_pickaxe` | `wooden_workhand_shovel` | Wooden Workhand Pickaxe | Wooden Workhand Shovel |
| Stone | `stone_workhand_pickaxe` | `stone_workhand_shovel` | Stone Workhand Pickaxe | Stone Workhand Shovel |
| Iron | `iron_workhand_pickaxe` | `iron_workhand_shovel` | Iron Workhand Pickaxe | Iron Workhand Shovel |
| Gold | `golden_workhand_pickaxe` | `golden_workhand_shovel` | Golden Workhand Pickaxe | Golden Workhand Shovel |
| Diamond | `diamond_workhand_pickaxe` | `diamond_workhand_shovel` | Diamond Workhand Pickaxe | Diamond Workhand Shovel |
| Netherite | `netherite_workhand_pickaxe` | `netherite_workhand_shovel` | Netherite Workhand Pickaxe | Netherite Workhand Shovel |

`golden_` (no `gold_`) sigue la convención vanilla (`golden_pickaxe`, `golden_shovel`).

## Stats por tier (idénticos a vanilla `net.minecraft.world.item.Tiers`)

| Tier | Durabilidad (uses) | Velocidad de minado (speed) | Encantabilidad (enchantment value) | Bonus de daño de ataque | Item de reparación |
|---|---|---|---|---|---|
| Wood | 59 | 2.0 | 15 | 0 | `#minecraft:planks` |
| Stone | 131 | 4.0 | 5 | 1 | `#minecraft:stone_tool_materials` |
| Iron | 250 | 6.0 | 14 | 2 | `minecraft:iron_ingot` |
| Gold | 32 | 12.0 | 22 | 0 | `minecraft:gold_ingot` |
| Diamond | 1561 | 8.0 | 10 | 3 | `minecraft:diamond` |
| Netherite | 2031 | 9.0 | 15 | 4 | `minecraft:netherite_ingot` |

Daño de ataque final = base del tipo de herramienta (pico `1 + bonus`, pala `1.5 + bonus`) tal como calcula vanilla `PickaxeItem`/`ShovelItem`. Velocidad de ataque: pico `-2.8`, pala `-3.0` (igual que vanilla).

## Nota de implementación clave

**No redefinir stats a mano.** Registrar los items usando directamente `net.minecraft.world.item.Tiers` (`Tiers.WOOD`, `Tiers.STONE`, `Tiers.IRON`, `Tiers.GOLD`, `Tiers.DIAMOND`, `Tiers.NETHERITE`) como `Tier` de `PickaxeItem`/`ShovelItem` — igual que hace vanilla. Esto garantiza:
- Reglas de bloque correcto/velocidad de minado idénticas (el `Tool` component se construye igual que en el pico/pala vanilla).
- Que un futuro cambio de balance de Mojang se herede automáticamente, sin mantenimiento manual de números.

No es necesario crear un `ToolMaterial` propio ni subclases de `PickaxeItem`/`ShovelItem`: instanciar las clases vanilla directamente con `new PickaxeItem(Tiers.IRON, new Item.Properties()...)`, igual que el registro de items vanilla en `Items.java`.

## Encantamientos

Para que acepten exactamente los mismos encantamientos que el pico/pala vanilla (Eficiencia, Fortuna, Toque de Seda, Irrompibilidad, Reparación, Maldición de Desvanecimiento, etc.), añadir cada item nuevo a los **mismos tags de item vanilla** que ya usan `minecraft:pickaxe`/`minecraft:shovel` vía datapack (`src/main/resources/data/minecraft/tags/item/...`, tags que extienden, no reemplazan):

- `enchantable/pickaxe.json` (picos) / `enchantable/shovel.json` (palas)
- `enchantable/mining.json` (Eficiencia)
- `enchantable/mining_loot.json` (Fortuna, Toque de Seda)
- `enchantable/durability.json` (Irrompibilidad)
- `enchantable/curse.json` y `enchantable/vanishing_curse.json` (Maldición de Desvanecimiento)
- `enchantable/equippable.json` si aplica a Mending (Reparación) — verificar contra las tags reales del build 26.2 antes de implementar

No se necesita lógica Java para esto: es 100% data-driven vía tags.

## Recetas

Shaped, mismo patrón que la herramienta vanilla del mismo tipo, sustituyendo el material vanilla por el ingrediente de su tier y usando `minecraft:stick` igual que vanilla:

```
Pico:        Pala:
X X X         X
_ # _         # 
_ # _         #
```
(`X` = material del tier, `#` = stick)

Ingredientes por tier: `minecraft:planks` (cualquier tabla, vía tag `#minecraft:planks`), `minecraft:cobblestone`/`#minecraft:stone_tool_materials`, `minecraft:iron_ingot`, `minecraft:gold_ingot`, `minecraft:diamond`, `minecraft:netherite_ingot` (vía smithing, no shaped — ver nota).

**Netherite**: igual que vanilla, no se craftea por receta shaped directa. Se aplica una **Smithing Transform Recipe** (netherite upgrade template + diamond workhand tool + netherite ingot → netherite workhand tool), replicando `minecraft:diamond_pickaxe` → `minecraft:netherite_pickaxe`.

## Texturas y modelos

- Modelo de item: parent `minecraft:item/handheld` (mismo que picos/palas vanilla), con `layer0` apuntando a la textura propia.
- Textura: 16×16 px, mismo formato/perspectiva que las herramientas vanilla (mango + cabeza diagonal). Ver prompts en `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md`.
- Icono del mod (`neoforge.mods.toml` `logoFile` + `assets/workhand_tools/icon.png` 64×64): ver mismo documento de prompts.

## Creative tab

Un tab propio `workhand_tab` (ya scaffoldeado en `WorkhandTools.java`), icono = `iron_workhand_pickaxe`, `displayItems` con los 12 items en orden: por tipo (picos primero, luego palas) y dentro de cada tipo por tier ascendente (wood → netherite).

## Localización (`en_us.json`)

Claves por item: `item.workhand_tools.<id>` → nombre Title Case de la tabla de naming. Se generan las 12 entradas al implementar los items.

## Roadmap de implementación (fases)

1. **Fase 1 — Items base**: registrar los 12 `PickaxeItem`/`ShovelItem` con `Tiers` vanilla, lang keys, creative tab wireado.
2. **Fase 2 — Recetas**: shaped recipes por tier + smithing transform para netherite.
3. **Fase 3 — Tags de encantamiento**: extender tags vanilla `enchantable/*` con los 12 items.
4. **Fase 4 — Assets**: texturas finales de las 12 herramientas + icono del mod (ver `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md`).
5. **Fase 5 — QA**: validar en juego que durabilidad/velocidad/encantamientos/reparación se comportan igual que el pico/pala vanilla del mismo tier.

Cambios a este documento requieren confirmación del usuario antes de implementarse (no asumir variaciones de balance sin pedirlo explícitamente).
