# 🎨 Workhand Tools — Encargo de arte para el diseñador

> **Documento autocontenido** — pensado para compartir directamente con el diseñador, sin necesidad de acceso al resto del repositorio (el resto de `docs/` es interno y no es público). Todo el arte listado aquí está pospuesto en el desarrollo — el mod funciona hoy con texturas vanilla de placeholder — pero la lista ya está **cerrada** y no cambia salvo que se añadan materiales o grados nuevos.

<br>

## 📋 Resumen del encargo

| Categoría | Cantidad | Formato |
|---|:---:|---|
| 🖼️ Icono del mod | 1 | PNG · 64×64 px · fondo transparente |
| 🏳️ Banner promocional (opcional, solo CurseForge) | 1 | PNG/JPG · horizontal · ~900×250 px orientativo |
| ⛏️ Texturas de herramienta (pico + pala × 10 materiales × 4 grados) | 80 | PNG · 16×16 px exactos · fondo transparente |
| 🪵 Textura del item nuevo *Robust Stick* | 1 | PNG · 16×16 px exactos · fondo transparente |
| **Total** | **83** | |

<br>

## 📐 Especificación técnica (las 81 texturas de 16×16)

- **16×16 px exactos** — es el tamaño real de renderizado de los iconos de item en Minecraft, no un múltiplo escalado.
- Pixel art plano: sin antialiasing, sin degradados, bordes de píxel duros — el mismo estilo que las texturas vanilla del juego.
- Fondo transparente (canal alfa).
- Perspectiva vanilla: mango recto en la esquina inferior-izquierda, cabeza de la herramienta en diagonal ocupando la esquina superior-derecha.
- El mango es **igual en las 80 herramientas** (madera clara, sin variación por material ni grado) — solo cambia la cabeza.
- Referencia más rápida: abrir `wooden_pickaxe.png`, `stone_pickaxe.png`, `iron_pickaxe.png`, `golden_pickaxe.png`, `diamond_pickaxe.png`, `netherite_pickaxe.png` (y sus `_shovel`) del propio juego como punto de partida de forma y proporción.

<br>

## 🖼️ Icono del mod

64×64 px, pico y pala cruzados en X, pixel art plano, colores sólidos, sin texto.

<details>
<summary><strong>Prompt de referencia (opcional, si se usa IA como boceto de partida)</strong></summary>

```
A square Minecraft mod icon for "Workhand Tools", 1:1 aspect ratio, designed to read clearly at 64x64px. Subject: a pickaxe and a shovel crossed in an X, blade-and-head tools of a sturdy, industrious worker's toolkit — not shiny or magical, grounded and utilitarian. The pickaxe head is angular and metallic gray with a brushed-steel highlight along its top edge; the shovel head is a broad rounded iron-gray blade with a faint worn sheen; both handles are warm honey-oak wood with visible woodgrain shading, wrapped near the head with a dark leather strap for grip. Composition: tools crossed dead-center, symmetric, filling about 80% of the frame, angled diagonally like a classic crest/emblem, with a thin circular or hexagonal badge outline behind them in a muted dark slate color to anchor the silhouette. Lighting: soft single top-left light source, subtle cel-shaded gradients on the metal (2-3 shading bands, not smooth photographic gradients) to suggest weight and material without breaking the blocky Minecraft aesthetic. Color palette: iron gray and steel blue for the metal heads, warm oak brown for the handles, dark charcoal/slate for the background badge, one small warm amber accent (a rivet or a subtle glow line) as a focal highlight. Style: Minecraft voxel/pixel-art derived but polished mod-icon style — blocky forms, hard silhouette edges, no photorealism, no painterly blending, no text, no watermark, no signature. Background: transparent or flat dark slate, no scenery, no ground, no additional props.
```

</details>

<br>

## 🏳️ Banner promocional (opcional)

Solo para la página de CurseForge, no se empaqueta en el mod. Ya generado — no es parte de este encargo.

<br>

## 🎨 Paleta por material

| Material | Paleta | Notas |
|---|---|---|
| 🪵 Wood | Marrón tostado mate | Igual que `oak_planks` |
| 🪨 Stone | Gris piedra granulado | Textura simple, sin brillo |
| 🟠 Copper | Naranja-cobrizo cálido | Leve brillo metálico, sin pátina verde |
| ⚫ Deepslate | Gris carbón denso | Más oscuro y "compacto" que Stone |
| ⚪ Iron | Plata clara | Brillo metálico sutil, sin llegar a blanco |
| ⬛ Blackstone | Negro con motas violáceas | Más "roca" que "metal", mate |
| 🟡 Gold | Amarillo dorado saturado | Brillo metálico en el filo |
| 🔵 Diamond | Cian brillante | Reflejos angulares tipo cristal facetado |
| 🟣 Obsidian | Púrpura-negro cristalino | Más oscuro que Diamond, reflejos tipo vidrio |
| ⬛ Netherite | Gunmetal casi negro | Sutil tinte violeta |

<br>

## ⭐ Diferenciación visual por grado

Las 4 versiones de un mismo material deben distinguirse **a simple vista** en el hotbar/inventario:

| Grado | Detalle visual progresivo |
|---|---|
| 1 · Base *(sin sufijo)* | Cabeza limpia, sin detalles extra — diseño base tipo vanilla |
| 2 · Advanced | + un remache de refuerzo pequeño en la base de la cabeza |
| 3 · Expert | + dos remaches de refuerzo + contorno ligeramente más grueso |
| 4 · Professional | + remaches + contorno grueso + una fina línea de brillo en el filo, del color del material |

<br>

## 🪵 Item nuevo: Robust Stick

- Archivo: `robust_stick.png`.
- Concepto: como el palo vanilla pero más grueso, con una banda metálica gris oscuro envolviendo el centro.
- Se usa como ingrediente de crafteo en las herramientas de grado 3 y 4.

<br>

## ⛏️ Listado completo — 80 texturas de herramienta

Nombre de archivo final: `<archivo>` (columna **Archivo**). Orden: picos (1-40) primero, luego palas (41-80); dentro de cada tipo, por material (Wood → Netherite); dentro de cada material, por grado (1 → Professional).

<details>
<summary><strong>▶ Ver las 80 filas (pulsa para desplegar)</strong></summary>

| # | Archivo | Nombre in-game | Material | Grado |
|---|---|---|---|---|
| 1 | `wooden_workhand_pickaxe.png` | Wooden Workhand Pickaxe | Wooden | 1 (base) |
| 2 | `wooden_workhand_advanced_pickaxe.png` | Wooden Workhand Advanced Pickaxe | Wooden | Advanced |
| 3 | `wooden_workhand_expert_pickaxe.png` | Wooden Workhand Expert Pickaxe | Wooden | Expert |
| 4 | `wooden_workhand_professional_pickaxe.png` | Wooden Workhand Professional Pickaxe | Wooden | Professional |
| 5 | `stone_workhand_pickaxe.png` | Stone Workhand Pickaxe | Stone | 1 (base) |
| 6 | `stone_workhand_advanced_pickaxe.png` | Stone Workhand Advanced Pickaxe | Stone | Advanced |
| 7 | `stone_workhand_expert_pickaxe.png` | Stone Workhand Expert Pickaxe | Stone | Expert |
| 8 | `stone_workhand_professional_pickaxe.png` | Stone Workhand Professional Pickaxe | Stone | Professional |
| 9 | `copper_workhand_pickaxe.png` | Copper Workhand Pickaxe | Copper | 1 (base) |
| 10 | `copper_workhand_advanced_pickaxe.png` | Copper Workhand Advanced Pickaxe | Copper | Advanced |
| 11 | `copper_workhand_expert_pickaxe.png` | Copper Workhand Expert Pickaxe | Copper | Expert |
| 12 | `copper_workhand_professional_pickaxe.png` | Copper Workhand Professional Pickaxe | Copper | Professional |
| 13 | `deepslate_workhand_pickaxe.png` | Deepslate Workhand Pickaxe | Deepslate | 1 (base) |
| 14 | `deepslate_workhand_advanced_pickaxe.png` | Deepslate Workhand Advanced Pickaxe | Deepslate | Advanced |
| 15 | `deepslate_workhand_expert_pickaxe.png` | Deepslate Workhand Expert Pickaxe | Deepslate | Expert |
| 16 | `deepslate_workhand_professional_pickaxe.png` | Deepslate Workhand Professional Pickaxe | Deepslate | Professional |
| 17 | `iron_workhand_pickaxe.png` | Iron Workhand Pickaxe | Iron | 1 (base) |
| 18 | `iron_workhand_advanced_pickaxe.png` | Iron Workhand Advanced Pickaxe | Iron | Advanced |
| 19 | `iron_workhand_expert_pickaxe.png` | Iron Workhand Expert Pickaxe | Iron | Expert |
| 20 | `iron_workhand_professional_pickaxe.png` | Iron Workhand Professional Pickaxe | Iron | Professional |
| 21 | `blackstone_workhand_pickaxe.png` | Blackstone Workhand Pickaxe | Blackstone | 1 (base) |
| 22 | `blackstone_workhand_advanced_pickaxe.png` | Blackstone Workhand Advanced Pickaxe | Blackstone | Advanced |
| 23 | `blackstone_workhand_expert_pickaxe.png` | Blackstone Workhand Expert Pickaxe | Blackstone | Expert |
| 24 | `blackstone_workhand_professional_pickaxe.png` | Blackstone Workhand Professional Pickaxe | Blackstone | Professional |
| 25 | `golden_workhand_pickaxe.png` | Golden Workhand Pickaxe | Golden | 1 (base) |
| 26 | `golden_workhand_advanced_pickaxe.png` | Golden Workhand Advanced Pickaxe | Golden | Advanced |
| 27 | `golden_workhand_expert_pickaxe.png` | Golden Workhand Expert Pickaxe | Golden | Expert |
| 28 | `golden_workhand_professional_pickaxe.png` | Golden Workhand Professional Pickaxe | Golden | Professional |
| 29 | `diamond_workhand_pickaxe.png` | Diamond Workhand Pickaxe | Diamond | 1 (base) |
| 30 | `diamond_workhand_advanced_pickaxe.png` | Diamond Workhand Advanced Pickaxe | Diamond | Advanced |
| 31 | `diamond_workhand_expert_pickaxe.png` | Diamond Workhand Expert Pickaxe | Diamond | Expert |
| 32 | `diamond_workhand_professional_pickaxe.png` | Diamond Workhand Professional Pickaxe | Diamond | Professional |
| 33 | `obsidian_workhand_pickaxe.png` | Obsidian Workhand Pickaxe | Obsidian | 1 (base) |
| 34 | `obsidian_workhand_advanced_pickaxe.png` | Obsidian Workhand Advanced Pickaxe | Obsidian | Advanced |
| 35 | `obsidian_workhand_expert_pickaxe.png` | Obsidian Workhand Expert Pickaxe | Obsidian | Expert |
| 36 | `obsidian_workhand_professional_pickaxe.png` | Obsidian Workhand Professional Pickaxe | Obsidian | Professional |
| 37 | `netherite_workhand_pickaxe.png` | Netherite Workhand Pickaxe | Netherite | 1 (base) |
| 38 | `netherite_workhand_advanced_pickaxe.png` | Netherite Workhand Advanced Pickaxe | Netherite | Advanced |
| 39 | `netherite_workhand_expert_pickaxe.png` | Netherite Workhand Expert Pickaxe | Netherite | Expert |
| 40 | `netherite_workhand_professional_pickaxe.png` | Netherite Workhand Professional Pickaxe | Netherite | Professional |
| 41 | `wooden_workhand_shovel.png` | Wooden Workhand Shovel | Wooden | 1 (base) |
| 42 | `wooden_workhand_advanced_shovel.png` | Wooden Workhand Advanced Shovel | Wooden | Advanced |
| 43 | `wooden_workhand_expert_shovel.png` | Wooden Workhand Expert Shovel | Wooden | Expert |
| 44 | `wooden_workhand_professional_shovel.png` | Wooden Workhand Professional Shovel | Wooden | Professional |
| 45 | `stone_workhand_shovel.png` | Stone Workhand Shovel | Stone | 1 (base) |
| 46 | `stone_workhand_advanced_shovel.png` | Stone Workhand Advanced Shovel | Stone | Advanced |
| 47 | `stone_workhand_expert_shovel.png` | Stone Workhand Expert Shovel | Stone | Expert |
| 48 | `stone_workhand_professional_shovel.png` | Stone Workhand Professional Shovel | Stone | Professional |
| 49 | `copper_workhand_shovel.png` | Copper Workhand Shovel | Copper | 1 (base) |
| 50 | `copper_workhand_advanced_shovel.png` | Copper Workhand Advanced Shovel | Copper | Advanced |
| 51 | `copper_workhand_expert_shovel.png` | Copper Workhand Expert Shovel | Copper | Expert |
| 52 | `copper_workhand_professional_shovel.png` | Copper Workhand Professional Shovel | Copper | Professional |
| 53 | `deepslate_workhand_shovel.png` | Deepslate Workhand Shovel | Deepslate | 1 (base) |
| 54 | `deepslate_workhand_advanced_shovel.png` | Deepslate Workhand Advanced Shovel | Deepslate | Advanced |
| 55 | `deepslate_workhand_expert_shovel.png` | Deepslate Workhand Expert Shovel | Deepslate | Expert |
| 56 | `deepslate_workhand_professional_shovel.png` | Deepslate Workhand Professional Shovel | Deepslate | Professional |
| 57 | `iron_workhand_shovel.png` | Iron Workhand Shovel | Iron | 1 (base) |
| 58 | `iron_workhand_advanced_shovel.png` | Iron Workhand Advanced Shovel | Iron | Advanced |
| 59 | `iron_workhand_expert_shovel.png` | Iron Workhand Expert Shovel | Iron | Expert |
| 60 | `iron_workhand_professional_shovel.png` | Iron Workhand Professional Shovel | Iron | Professional |
| 61 | `blackstone_workhand_shovel.png` | Blackstone Workhand Shovel | Blackstone | 1 (base) |
| 62 | `blackstone_workhand_advanced_shovel.png` | Blackstone Workhand Advanced Shovel | Blackstone | Advanced |
| 63 | `blackstone_workhand_expert_shovel.png` | Blackstone Workhand Expert Shovel | Blackstone | Expert |
| 64 | `blackstone_workhand_professional_shovel.png` | Blackstone Workhand Professional Shovel | Blackstone | Professional |
| 65 | `golden_workhand_shovel.png` | Golden Workhand Shovel | Golden | 1 (base) |
| 66 | `golden_workhand_advanced_shovel.png` | Golden Workhand Advanced Shovel | Golden | Advanced |
| 67 | `golden_workhand_expert_shovel.png` | Golden Workhand Expert Shovel | Golden | Expert |
| 68 | `golden_workhand_professional_shovel.png` | Golden Workhand Professional Shovel | Golden | Professional |
| 69 | `diamond_workhand_shovel.png` | Diamond Workhand Shovel | Diamond | 1 (base) |
| 70 | `diamond_workhand_advanced_shovel.png` | Diamond Workhand Advanced Shovel | Diamond | Advanced |
| 71 | `diamond_workhand_expert_shovel.png` | Diamond Workhand Expert Shovel | Diamond | Expert |
| 72 | `diamond_workhand_professional_shovel.png` | Diamond Workhand Professional Shovel | Diamond | Professional |
| 73 | `obsidian_workhand_shovel.png` | Obsidian Workhand Shovel | Obsidian | 1 (base) |
| 74 | `obsidian_workhand_advanced_shovel.png` | Obsidian Workhand Advanced Shovel | Obsidian | Advanced |
| 75 | `obsidian_workhand_expert_shovel.png` | Obsidian Workhand Expert Shovel | Obsidian | Expert |
| 76 | `obsidian_workhand_professional_shovel.png` | Obsidian Workhand Professional Shovel | Obsidian | Professional |
| 77 | `netherite_workhand_shovel.png` | Netherite Workhand Shovel | Netherite | 1 (base) |
| 78 | `netherite_workhand_advanced_shovel.png` | Netherite Workhand Advanced Shovel | Netherite | Advanced |
| 79 | `netherite_workhand_expert_shovel.png` | Netherite Workhand Expert Shovel | Netherite | Expert |
| 80 | `netherite_workhand_professional_shovel.png` | Netherite Workhand Professional Shovel | Netherite | Professional |

</details>

<br>

## ✅ Estado actual

El mod ya funciona hoy con placeholders — cada una de las 80 texturas de esta lista apunta temporalmente a la textura vanilla más parecida, así que **este encargo no bloquea nada** del desarrollo. Es el trabajo para cuando el diseñador tenga disponibilidad.

- [ ] Icono del mod
- [ ] 80 texturas de herramienta
- [ ] Textura de Robust Stick
