# Prompts de imagen — Workhand Tools

> Prompts para generación de arte (logo del mod) con modelos de imagen (Midjourney, DALL-E, GPT Image, Stable Diffusion, etc.). Los prompts se escriben en inglés porque los modelos de imagen rinden mejor con prompts en inglés, aunque el resto de esta doc es en castellano.
> **Decisión (v2)**: diseñar arte propio para las 80 texturas de herramienta de golpe no es viable ahora mismo (demasiado tiempo). Fase temporal: **reutilizar texturas vanilla existentes** como placeholder — ver `## Texturas de herramientas (placeholder vanilla)`. Los prompts de generación de arte custom quedan pospuestos a una fase futura (`## Fase futura — arte custom`), no se descartan.

## Logo del mod

Banner ya generado (`ChatGPT Image 5 ago 2026, 13_21_34.png`, en Descargas) — solo falta el icono cuadrado. Usado en `assets/workhand_tools/icon.png` (64×64, `neoforge.mods.toml` → `logoFile`) y como icono de proyecto en CurseForge/GitLab. Al ser un icono de mod (no una textura de item 16×16 in-game), admite algo más de detalle/sombreado que las texturas de herramienta — se genera a alta resolución (1024×1024) y se reduce a 64×64 al final.

### Prompt — icono cuadrado, principal

```
A square Minecraft mod icon for "Workhand Tools", 1:1 aspect ratio, designed to read clearly at 64x64px. Subject: a pickaxe and a shovel crossed in an X, blade-and-head tools of a sturdy, industrious worker's toolkit — not shiny or magical, grounded and utilitarian. The pickaxe head is angular and metallic gray with a brushed-steel highlight along its top edge; the shovel head is a broad rounded iron-gray blade with a faint worn sheen; both handles are warm honey-oak wood with visible woodgrain shading, wrapped near the head with a dark leather strap for grip. Composition: tools crossed dead-center, symmetric, filling about 80% of the frame, angled diagonally like a classic crest/emblem, with a thin circular or hexagonal badge outline behind them in a muted dark slate color to anchor the silhouette. Lighting: soft single top-left light source, subtle cel-shaded gradients on the metal (2-3 shading bands, not smooth photographic gradients) to suggest weight and material without breaking the blocky Minecraft aesthetic. Color palette: iron gray and steel blue for the metal heads, warm oak brown for the handles, dark charcoal/slate for the background badge, one small warm amber accent (a rivet or a subtle glow line) as a focal highlight. Style: Minecraft voxel/pixel-art derived but polished mod-icon style (comparable to official Minecraft mod list icons) — blocky forms, hard silhouette edges, no photorealism, no painterly blending, no text, no watermark, no signature. Background: transparent or flat dark slate, no scenery, no ground, no additional props.
```

### Prompt — variante alternativa (más plana, fiel 100% a pixel-art vanilla)

Por si la principal sale "demasiado ilustrada" y prefieres algo más cercano al estilo plano de un item vanilla escalado a icono:

```
A square Minecraft mod icon, strict flat pixel-art style with visible hard-edged pixel blocks (as if built on a 64x64 grid, no smooth curves), depicting a pickaxe and a shovel crossed in an X at the center. Pickaxe head: angular double point, flat mid-gray with a single lighter gray highlight band and a single darker gray shadow band, no gradients beyond those two bands. Shovel head: flat rounded blade, same gray tones. Both handles: flat warm brown, matching vanilla Minecraft tool handle color, no woodgrain detail. Thin black pixel outline around the whole silhouette. Centered, symmetric, filling most of the frame. Flat dark slate background or fully transparent. No text, no logos, no extra ornamentation, no anti-aliasing, no gradients beyond the described shading bands, consistent with vanilla Minecraft item/mod icon aesthetics.
```

### Notas para elegir/ajustar

- Si el generador mete texto o marca de agua pese al `no text`, añade explícitamente `no letters, no typography, no watermark, no signature` al final.
- Si sale demasiado "3D/render" (photorealistic metal, reflejos de estudio), refuerza `flat colors, cel-shaded, not photorealistic, not 3D render, illustration only`.
- El badge circular/hexagonal de fondo en la variante principal es opcional — quítalo del prompt (`with a thin circular or hexagonal badge outline behind them...`) si prefieres las herramientas sueltas sin marco, más parecido al banner que ya tienes.

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
- [x] Banner CurseForge — generado (`ChatGPT Image 5 ago 2026, 13_21_34.png`), usado solo en `project_description.md`, no se empaqueta en el jar
- [x] ~~80 texturas de herramienta~~ — pospuesto, se usan placeholders vanilla (ver tabla arriba), no bloquea el desarrollo
- [x] ~~Textura de `robust_stick`~~ — pospuesto, placeholder = textura vanilla de `minecraft:stick`
