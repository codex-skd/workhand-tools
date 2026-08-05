# Prompts de imagen — Workhand Tools

> Prompts para generación de arte (logo del mod + texturas de herramientas) con modelos de imagen (Midjourney, DALL-E, GPT Image, Stable Diffusion, etc.). Los prompts se escriben en inglés porque los modelos de imagen rinden mejor con prompts en inglés, aunque el resto de esta doc es en castellano.

## Nota importante sobre texturas de item (16×16 px)

Las texturas de herramientas de Minecraft son pixel art de **16×16 px**, un formato muy específico que la mayoría de generadores de imagen no reproducen bien de forma nativa (generan a 512-1024px con "estilo pixel art" pero sin cuadrícula real de 16px). Dos caminos:

1. **Recomendado**: generar a alta resolución con los prompts de abajo como *referencia de forma/paleta*, y luego recrear la textura final a mano en Blockbench o Aseprite, partiendo de la silueta del pico/pala vanilla (mango recto + cabeza en diagonal ocupando la esquina) y recoloreando según el material — es el método más fiable para que encaje pixel a pixel con el resto de vanilla.
2. **Alternativa rápida**: usar los prompts igualmente en un generador con modo "pixel art 16x16" o "Minecraft texture" si el modelo lo soporta explícitamente, y post-procesar (downscale + reducción de paleta) con un script o Aseprite.

En ambos casos, la instrucción de estilo común a todos los prompts de textura es:

> `16x16 pixel art texture, Minecraft item icon style, flat colors, hard pixel edges, no anti-aliasing, no gradients, transparent background, isometric-diagonal tool head like vanilla Minecraft pickaxe/shovel icons`

## Logo del mod

Usado en `assets/workhand_tools/icon.png` (64×64, `neoforge.mods.toml` → `logoFile`) y como logo de CurseForge/GitLab.

### Prompt — icono cuadrado (64×64 / mods.toml + CurseForge project icon)

```
A square Minecraft mod icon, flat pixel-art style, 64x64px grid, depicting a crossed pickaxe and shovel forming an X, both with wooden handles and simple gray/brown tool heads, centered composition, bold thick outlines, flat solid colors, no gradients, no anti-aliasing, transparent or dark slate background, blocky voxel aesthetic consistent with vanilla Minecraft item icons, no text
```

### Prompt — banner horizontal (CurseForge header / footer del `project_description.md`)

```
Wide horizontal banner, flat pixel-art Minecraft style, a crossed pickaxe and shovel icon on the left, bold sans-serif pixel-style text "WORKHAND TOOLS" on the right, color palette of iron gray, oak brown and diamond cyan accents, dark background, no gradients, no anti-aliasing, blocky voxel aesthetic
```

## Texturas de herramientas (80 items = 10 materiales × 4 grados × 2 tipos)

Prompt base común (sustituir `<TOOL_SHAPE>`, `<MATERIAL_DESC>` y `<GRADE_DESC>` por fila de las tablas de abajo):

```
16x16 pixel art texture, Minecraft item icon style, <TOOL_SHAPE>, <MATERIAL_DESC>, <GRADE_DESC>, flat colors, hard pixel edges, no anti-aliasing, no gradients, transparent background, diagonal tool head in the upper-right pointing down-left, wooden stick handle in the lower-left, matching the proportions of vanilla Minecraft pickaxe/shovel icons
```

`<TOOL_SHAPE>`:
- Pico: `a pickaxe with a wide triangular double-pointed head`
- Pala: `a shovel with a flat rounded blade head`

### Materiales (10)

| Material | `<MATERIAL_DESC>` | Notas de paleta |
|---|---|---|
| Wood | `head made of light oak wood planks, matte tan-brown color` | Igual que `oak_planks`, sin brillo |
| Stone | `head made of rough gray cobblestone, matte stone gray` | Textura granulada simple |
| Copper | `head made of raw copper metal, warm orange-brown with subtle metallic sheen` | Naranja rojizo, sin la pátina verde del cobre oxidado |
| Deepslate | `head made of dense dark gray deepslate rock, matte charcoal gray, slightly darker and denser-looking than cobblestone` | Más oscuro y "compacto" que stone |
| Iron | `head made of polished iron metal, light silver-gray with subtle metallic highlights` | Gris más claro que stone, sin ser blanco |
| Blackstone | `head made of dark polished blackstone rock, near-black with subtle purple-gray flecks` | Negro mate con motas violáceas, más "roca" que "metal" |
| Gold | `head made of polished gold metal, bright yellow-gold with metallic shine` | Amarillo saturado, brillo sutil en el filo |
| Diamond | `head made of faceted diamond crystal, bright cyan-blue with sharp light reflections` | Cian brillante, reflejos angulares |
| Obsidian | `head made of dark faceted obsidian glass, deep purple-black with sharp glassy reflections` | Más oscuro y "cristalino" que netherite, reflejos angulares como el diamante |
| Netherite | `head made of dark netherite metal, near-black gunmetal with subtle purple-gray sheen` | Casi negro, sutil tinte violeta |

Handle (mango) en los 10: `plain oak wood stick handle, brown color, same as vanilla tool handles` (constante, no cambia por material ni grado).

### Diferenciación visual por grado (4)

Los 4 grados de un mismo material comparten paleta y forma base, pero deben distinguirse a simple vista en el hotbar/inventario. Propuesta de escalado visual progresivo vía `<GRADE_DESC>`:

| Grado | `<GRADE_DESC>` | Idea |
|---|---|---|
| 1 — *(sin sufijo)* | `plain tool head, no extra details` | Diseño base, igual que vanilla |
| 2 — Advanced | `tool head with a single small reinforcement rivet at the base` | Un detalle sutil |
| 3 — Expert | `tool head with two reinforcement rivets and a slightly thicker outline` | Refuerzo visible + contorno más grueso |
| 4 — Professional | `tool head with reinforcement rivets, a thicker outline, and a small glowing accent line along the edge matching the material color` | Máximo detalle, ligero "glow" en el filo |

Esto mantiene la silueta reconocible (pico/pala + material) mientras el grado se lee por nivel de detalle, sin necesitar un rediseño de forma por grado.

### Ejemplos completos

**Wooden Workhand Pickaxe** (material Wood, grado 1):
```
16x16 pixel art texture, Minecraft item icon style, a pickaxe with a wide triangular double-pointed head, head made of light oak wood planks, matte tan-brown color, plain tool head, no extra details, flat colors, hard pixel edges, no anti-aliasing, no gradients, transparent background, diagonal tool head in the upper-right pointing down-left, plain oak wood stick handle in the lower-left, matching the proportions of vanilla Minecraft pickaxe/shovel icons
```

**Netherite Workhand Professional Shovel** (material Netherite, grado 4):
```
16x16 pixel art texture, Minecraft item icon style, a shovel with a flat rounded blade head, head made of dark netherite metal, near-black gunmetal with subtle purple-gray sheen, tool head with reinforcement rivets, a thicker outline, and a small glowing accent line along the edge matching the material color, flat colors, hard pixel edges, no anti-aliasing, no gradients, transparent background, diagonal tool head in the upper-right pointing down-left, plain oak wood stick handle in the lower-left, matching the proportions of vanilla Minecraft pickaxe/shovel icons
```

## Checklist de assets pendientes

- [ ] `assets/workhand_tools/icon.png` (64×64, logo cuadrado)
- [ ] Banner CurseForge (usado solo en `project_description.md`, no se empaqueta en el jar)
- [ ] 80 texturas de herramienta en `assets/workhand_tools/textures/item/<id>.png`, una por cada combinación de material (10) × grado (4) × tipo (2) definida en `docs/DESIGN_WORKHAND_TOOLS.md` → tabla de Naming e IDs. No se listan una a una aquí porque el nombre de archivo es siempre `<id>.png` con el `id` exacto de esa tabla.
