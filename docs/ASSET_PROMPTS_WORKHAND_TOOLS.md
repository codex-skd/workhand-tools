# Prompts de imagen — Workhand Tools

> Prompts para generación de arte (logo del mod) con modelos de imagen (Midjourney, DALL-E, GPT Image, Stable Diffusion, etc.). Los prompts se escriben en inglés porque los modelos de imagen rinden mejor con prompts en inglés, aunque el resto de esta doc es en castellano.
> **Decisión (v2)**: diseñar arte propio para las 80 texturas de herramienta de golpe no es viable ahora mismo (demasiado tiempo). Fase temporal: **reutilizar texturas vanilla existentes** como placeholder — ver `## Texturas de herramientas (placeholder vanilla)`. Los prompts de generación de arte custom quedan pospuestos a una fase futura (`## Fase futura — arte custom`), no se descartan.

## Logo del mod

Sigue haciendo falta (son solo 2 imágenes, no 80). Usado en `assets/workhand_tools/icon.png` (64×64, `neoforge.mods.toml` → `logoFile`) y como logo de CurseForge/GitLab.

### Prompt — icono cuadrado (64×64 / mods.toml + CurseForge project icon)

```
A square Minecraft mod icon, flat pixel-art style, 64x64px grid, depicting a crossed pickaxe and shovel forming an X, both with wooden handles and simple gray/brown tool heads, centered composition, bold thick outlines, flat solid colors, no gradients, no anti-aliasing, transparent or dark slate background, blocky voxel aesthetic consistent with vanilla Minecraft item icons, no text
```

### Prompt — banner horizontal (CurseForge header / footer del `project_description.md`)

```
Wide horizontal banner, flat pixel-art Minecraft style, a crossed pickaxe and shovel icon on the left, bold sans-serif pixel-style text "WORKHAND TOOLS" on the right, color palette of iron gray, oak brown and diamond cyan accents, dark background, no gradients, no anti-aliasing, blocky voxel aesthetic
```

## Texturas de herramientas (placeholder vanilla)

Los 80 items **no llevan textura propia por ahora**: el modelo de cada item apunta directamente a la textura vanilla del pico/pala más parecido, referenciando el namespace `minecraft:` desde el modelo del mod (no hace falta copiar ni duplicar el `.png`, solo el JSON de modelo referencia la textura vanilla). Los 4 grados de un mismo material comparten la misma textura placeholder — no hay diferenciación visual por grado en esta fase (se retoma en la fase futura de arte custom).

| Material | Textura vanilla reutilizada (pico) | Textura vanilla reutilizada (pala) |
|---|---|---|
| Wood | `minecraft:item/wooden_pickaxe` | `minecraft:item/wooden_shovel` |
| Stone | `minecraft:item/stone_pickaxe` | `minecraft:item/stone_shovel` |
| Copper | `minecraft:item/iron_pickaxe` *(sin equivalente vanilla, se reutiliza hierro por tono metálico similar)* | `minecraft:item/iron_shovel` |
| Deepslate | `minecraft:item/stone_pickaxe` *(sin equivalente vanilla, se reutiliza piedra)* | `minecraft:item/stone_shovel` |
| Iron | `minecraft:item/iron_pickaxe` | `minecraft:item/iron_shovel` |
| Blackstone | `minecraft:item/netherite_pickaxe` *(sin equivalente vanilla, se reutiliza netherite por tono oscuro)* | `minecraft:item/netherite_shovel` |
| Gold | `minecraft:item/golden_pickaxe` | `minecraft:item/golden_shovel` |
| Diamond | `minecraft:item/diamond_pickaxe` | `minecraft:item/diamond_shovel` |
| Obsidian | `minecraft:item/diamond_pickaxe` *(sin equivalente vanilla, se reutiliza diamante por aspecto de gema/cristal)* | `minecraft:item/diamond_shovel` |
| Netherite | `minecraft:item/netherite_pickaxe` | `minecraft:item/netherite_shovel` |

`robust_stick` (item nuevo, no es herramienta): placeholder `minecraft:item/stick` (mismo modelo/textura que el palo vanilla).

Implementación (modelo de item, ejemplo `assets/workhand_tools/models/item/iron_workhand_advanced_pickaxe.json`):

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "minecraft:item/iron_pickaxe"
  }
}
```

Icono del mod: sin cambios, sigue haciendo falta un `icon.png` propio (ver prompts arriba) — no hay placeholder vanilla razonable para el logo del mod en sí.

## Fase futura — arte custom (pospuesta)

Cuando se aborde el arte propio, la estrategia de generación queda documentada aquí para no perderla:

- Las texturas de herramientas de Minecraft son pixel art de **16×16 px**, formato que la mayoría de generadores de imagen no reproducen bien de forma nativa. Camino recomendado: generar a alta resolución como *referencia de forma/paleta* y recrear la textura final a mano en Blockbench/Aseprite partiendo de la silueta vanilla (mango recto + cabeza en diagonal) y recoloreando según el material.
- Paleta sugerida por material (para cuando se retome): Wood tostado mate, Stone gris granulado, Copper naranja-cobrizo, Deepslate gris carbón denso, Iron plata clara, Blackstone negro con motas violáceas, Gold amarillo saturado, Diamond cian brillante con reflejos, Obsidian púrpura-negro cristalino, Netherite gunmetal casi negro.
- Diferenciación visual por grado sugerida: grado 1 sin detalles, grado 2 un remache, grado 3 dos remaches + contorno más grueso, grado 4 remaches + contorno + leve brillo en el filo del color del material.
- Prompt base a reconstruir cuando llegue el momento: `16x16 pixel art texture, Minecraft item icon style, <tool shape>, <material description>, <grade detail>, flat colors, hard pixel edges, no anti-aliasing, no gradients, transparent background, diagonal tool head in the upper-right pointing down-left, wooden stick handle in the lower-left, matching the proportions of vanilla Minecraft pickaxe/shovel icons`.

## Checklist de assets pendientes

- [ ] `assets/workhand_tools/icon.png` (64×64, logo cuadrado) — único asset de imagen real pendiente ahora mismo
- [ ] Banner CurseForge (usado solo en `project_description.md`, no se empaqueta en el jar)
- [x] ~~80 texturas de herramienta~~ — pospuesto, se usan placeholders vanilla (ver tabla arriba), no bloquea el desarrollo
- [x] ~~Textura de `robust_stick`~~ — pospuesto, placeholder = textura vanilla de `minecraft:stick`
