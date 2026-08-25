# 🎨 Workhand Tools — Texturas necesarias para completar el mod

> Descripción de las texturas que faltan para que Workhand Tools tenga arte propio. El mod ya funciona con texturas placeholder de 16×16 px — esto es lo que queda por completar en la parte visual.

<br>

## 📋 Resumen

| Categoría | Cantidad | Formato |
|---|:---:|---|
| ⛏️ Texturas de herramienta (pico + pala × 3 materiales × 4 grados) | 24 | PNG · 16×16 px exactos · fondo transparente |
| ⛏️ Texturas de pico mejorado (improved pickaxe) | 4 | PNG · 16×16 px exactos · fondo transparente |
| 🪓 Texturas de hacha workhand (workhand axe) | 2 | PNG · 16×16 px exactos · fondo transparente |
| 🪵 Textura del nuevo item *Palo Robusto* | 1 | PNG · 16×16 px exactos · fondo transparente |
| **Total** | **31** | |

El icono del mod y el banner promocional **ya están hechos** — no forman parte de lo que queda pendiente.

<br>

## 📐 Especificación técnica (las 25 texturas de 16×16)

- **16×16 px exactos** — es el tamaño real de renderizado de los iconos de item en Minecraft, no un múltiplo escalado.
- Pixel art plano: sin antialiasing, sin degradados, bordes de píxel duros — el mismo estilo que las texturas vanilla del juego.
- Fondo transparente (canal alfa).
- Perspectiva vanilla: mango recto en la esquina inferior-izquierda, cabeza de la herramienta en diagonal ocupando la esquina superior-derecha.
- El mango es **igual en las 24 herramientas** (madera clara, sin variación por material ni grado) — solo cambia la cabeza.
- Referencia visual: abrir `stone_pickaxe.png`, `iron_pickaxe.png`, `diamond_pickaxe.png` (y sus `_shovel`) del propio juego como punto de partida de forma y proporción.

<br>

## 🎨 Paleta por material

| Material | Paleta | Notas |
|---|---|---|
| 🪨 Piedra | Gris piedra granulado | Textura simple, sin brillo; similar a vanilla stone_pickaxe |
| ⚪ Hierro | Plata clara | Brillo metálico sutil, sin llegar a blanco; similar a vanilla iron_pickaxe |
| 🔵 Diamante | Cian brillante | Reflejos angulares tipo cristal facetado; similar a vanilla diamond_pickaxe |

<br>

## ⭐ Diferenciación visual por grado

Las 4 versiones de un mismo material deben distinguirse **a simple vista** en el hotbar/inventario:

| Grado | Detalle visual progresivo |
|---|---|
| 1 · Base | Cabeza limpia, sin detalles extra — diseño base tipo vanilla |
| 2 · Avanzado | + un remache de refuerzo pequeño en la base de la cabeza |
| 3 · Experto | + dos remaches de refuerzo + contorno ligeramente más grueso |
| 4 · Profesional | + remaches + contorno grueso + una fina línea de brillo en el filo, del color del material |

<br>

## 🪵 Item nuevo: Palo Robusto

- Archivo: `robust_stick.png`.
- Concepto: como el palo vanilla pero más grueso, con una banda metálica gris oscuro envolviendo el centro.
- Se usa como ingrediente de crafteo en las herramientas de grado 3 y 4.

<br>

## ⛏️ Listado completo — 24 texturas de herramienta + 1 robust_stick

**Nombre de archivo final**: `<archivo>` (columna **Archivo**). 

Orden: picos (1-12) primero, luego palas (13-24); dentro de cada tipo, por material (Piedra → Diamante); dentro de cada material, por grado (Base → Profesional).

| # | Archivo | Nombre en el juego (English) | Material | Grado | Tipo |
|---|---|---|---|---|---|
| **PICKAXES** | | | | | |
| 1 | `stone_workhand_pickaxe.png` | Stone Workhand Pickaxe | Piedra | Base | Pico |
| 2 | `stone_workhand_advanced_pickaxe.png` | Stone Workhand Advanced Pickaxe | Piedra | Avanzado | Pico |
| 3 | `stone_workhand_expert_pickaxe.png` | Stone Workhand Expert Pickaxe | Piedra | Experto | Pico |
| 4 | `stone_workhand_professional_pickaxe.png` | Stone Workhand Professional Pickaxe | Piedra | Profesional | Pico |
| 5 | `iron_workhand_pickaxe.png` | Iron Workhand Pickaxe | Hierro | Base | Pico |
| 6 | `iron_workhand_advanced_pickaxe.png` | Iron Workhand Advanced Pickaxe | Hierro | Avanzado | Pico |
| 7 | `iron_workhand_expert_pickaxe.png` | Iron Workhand Expert Pickaxe | Hierro | Experto | Pico |
| 8 | `iron_workhand_professional_pickaxe.png` | Iron Workhand Professional Pickaxe | Hierro | Profesional | Pico |
| 9 | `diamond_workhand_pickaxe.png` | Diamond Workhand Pickaxe | Diamante | Base | Pico |
| 10 | `diamond_workhand_advanced_pickaxe.png` | Diamond Workhand Advanced Pickaxe | Diamante | Avanzado | Pico |
| 11 | `diamond_workhand_expert_pickaxe.png` | Diamond Workhand Expert Pickaxe | Diamante | Experto | Pico |
| 12 | `diamond_workhand_professional_pickaxe.png` | Diamond Workhand Professional Pickaxe | Diamante | Profesional | Pico |
| **SHOVELS** | | | | | |
| 13 | `stone_workhand_shovel.png` | Stone Workhand Shovel | Piedra | Base | Pala |
| 14 | `stone_workhand_advanced_shovel.png` | Stone Workhand Advanced Shovel | Piedra | Avanzado | Pala |
| 15 | `stone_workhand_expert_shovel.png` | Stone Workhand Expert Shovel | Piedra | Experto | Pala |
| 16 | `stone_workhand_professional_shovel.png` | Stone Workhand Professional Shovel | Piedra | Profesional | Pala |
| 17 | `iron_workhand_shovel.png` | Iron Workhand Shovel | Hierro | Base | Pala |
| 18 | `iron_workhand_advanced_shovel.png` | Iron Workhand Advanced Shovel | Hierro | Avanzado | Pala |
| 19 | `iron_workhand_expert_shovel.png` | Iron Workhand Expert Shovel | Hierro | Experto | Pala |
| 20 | `iron_workhand_professional_shovel.png` | Iron Workhand Professional Shovel | Hierro | Profesional | Pala |
| 21 | `diamond_workhand_shovel.png` | Diamond Workhand Shovel | Diamante | Base | Pala |
| 22 | `diamond_workhand_advanced_shovel.png` | Diamond Workhand Advanced Shovel | Diamante | Avanzado | Pala |
| 23 | `diamond_workhand_expert_shovel.png` | Diamond Workhand Expert Shovel | Diamante | Experto | Pala |
| 24 | `diamond_workhand_professional_shovel.png` | Diamond Workhand Professional Shovel | Diamante | Profesional | Pala |
| **IMPROVED PICKAXES** | | | | | |
| 25 | `iron_workhand_advanced_improved_pickaxe.png` | Iron Workhand Advanced Improved Pickaxe | Hierro | Avanzado | Pico mejorado |
| 26 | `iron_workhand_professional_improved_pickaxe.png` | Iron Workhand Professional Improved Pickaxe | Hierro | Profesional | Pico mejorado |
| 27 | `diamond_workhand_advanced_improved_pickaxe.png` | Diamond Workhand Advanced Improved Pickaxe | Diamante | Avanzado | Pico mejorado |
| 28 | `diamond_workhand_professional_improved_pickaxe.png` | Diamond Workhand Professional Improved Pickaxe | Diamante | Profesional | Pico mejorado |
| **WORKHAND AXES** | | | | | |
| 29 | `iron_workhand_axe.png` | Iron Workhand Axe | Hierro | N/A | Hacha |
| 30 | `diamond_workhand_axe.png` | Diamond Workhand Axe | Diamante | N/A | Hacha |
| **ITEM NUEVO** | | | | | |
| 31 | `robust_stick.png` | Robust Stick | N/A | N/A | Palo |

<br>

## 🌐 Tabla de traducciones (castellano → inglés)

Los nombres de archivo y los nombres que se ven en el juego están en inglés (convención del proyecto):

| Castellano | Inglés (real, en código/juego) |
|---|---|
| **Nombre base de la línea** | |
| de Obrero | Workhand |
| **Herramientas** | |
| Pico | Pickaxe |
| Pala | Shovel |
| **Materiales** | |
| Piedra | Stone / `stone` |
| Hierro | Iron / `iron` |
| Diamante | Diamond / `diamond` |
| **Grados** | |
| Base *(sin sufijo)* | Base *(no suffix)* |
| Avanzado | Advanced |
| Experto | Expert |
| Profesional | Professional |
| **Item nuevo** | |
| Palo Robusto | Robust Stick / `robust_stick` |

<br>

## ✅ Estado actual

- [x] Icono del mod
- [x] Banner promocional
- [x] 24 texturas de herramienta (arte final del diseñador aplicado)
- [x] 4 texturas de pico mejorado (arte final del diseñador aplicado)
- [x] 2 texturas de hacha workhand (arte final del diseñador aplicado)
- [x] 1 textura de Palo Robusto (arte final del diseñador aplicado)

**Nota**: Los archivos PNG placeholder (16×16 px) están creados en `src/main/resources/assets/workhand_tools/textures/item/`. El diseñador puede reemplazarlos directamente sin cambios de código.

**Item añadido tras el encargo original (fuera de los 31 anteriores)**: `reinforced_deepslate_pickaxe.png` — arte final ya aplicado (32×32 px, mismo tamaño real que el resto de picos/palas), textura recibida directamente del usuario. Item aún pendiente de implementación en código (ver `docs/DESIGN_WORKHAND_TOOLS.md`).

**Gap detectado y corregido (2026-08-25)**: `kennestroyer_pickaxe.png` / `kennestroyer_shovel.png` — estos 2 items se implementaron en el commit `3a757a0` (Fase de Kennestroyer) sin textura, modelo de item ni definición de item (hueco que pasó desapercibido hasta que el usuario los probó en juego y aparecían con la textura "ausente" de Minecraft). Arte final recibido directamente del usuario y aplicado (32×32 px). No estaban listados en este documento — añadidos ahora a la tabla de catálogo de más abajo.

<br>

## 📊 Catálogo completo — durabilidad y para qué vale cada herramienta

Las 32 herramientas/items del mod (arte final ya aplicado a las 31 primeras — ver `## ✅ Estado actual` arriba; `tape_measure` usa una textura reutilizada, ver `docs/WORKFLOW_WORKHAND_TOOLS_26-2.md` créditos). Imágenes servidas desde `https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/<archivo>`.

### ⛏️ Picos y palas base (24 — 3 materiales × 4 grados)

| Imagen | Nombre | Material | Durabilidad | Para qué vale |
|---|---|---|:---:|---|
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/stone_workhand_pickaxe.png" width="32"> | Stone Workhand Pickaxe | Piedra | 655 | Minado en área 3×3×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/stone_workhand_advanced_pickaxe.png" width="32"> | Stone Workhand Advanced Pickaxe | Piedra | 655 | Minado en área 3×3×3 cúbico / 3×3×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/stone_workhand_expert_pickaxe.png" width="32"> | Stone Workhand Expert Pickaxe | Piedra | 655 | Minado en área 5×5×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/stone_workhand_professional_pickaxe.png" width="32"> | Stone Workhand Professional Pickaxe | Piedra | 655 | Minado en área 5×5×5 cúbico / 5×5×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_pickaxe.png" width="32"> | Iron Workhand Pickaxe | Hierro | 1250 | Minado en área 3×3×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_advanced_pickaxe.png" width="32"> | Iron Workhand Advanced Pickaxe | Hierro | 1250 | Minado en área 3×3×3 cúbico / 3×3×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_expert_pickaxe.png" width="32"> | Iron Workhand Expert Pickaxe | Hierro | 1250 | Minado en área 5×5×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_professional_pickaxe.png" width="32"> | Iron Workhand Professional Pickaxe | Hierro | 1250 | Minado en área 5×5×5 cúbico / 5×5×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_pickaxe.png" width="32"> | Diamond Workhand Pickaxe | Diamante | 7805 | Minado en área 3×3×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_advanced_pickaxe.png" width="32"> | Diamond Workhand Advanced Pickaxe | Diamante | 7805 | Minado en área 3×3×3 cúbico / 3×3×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_expert_pickaxe.png" width="32"> | Diamond Workhand Expert Pickaxe | Diamante | 7805 | Minado en área 5×5×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_professional_pickaxe.png" width="32"> | Diamond Workhand Professional Pickaxe | Diamante | 7805 | Minado en área 5×5×5 cúbico / 5×5×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/stone_workhand_shovel.png" width="32"> | Stone Workhand Shovel | Piedra | 655 | Cavado en área 3×3×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/stone_workhand_advanced_shovel.png" width="32"> | Stone Workhand Advanced Shovel | Piedra | 655 | Cavado en área 3×3×3 cúbico / 3×3×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/stone_workhand_expert_shovel.png" width="32"> | Stone Workhand Expert Shovel | Piedra | 655 | Cavado en área 5×5×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/stone_workhand_professional_shovel.png" width="32"> | Stone Workhand Professional Shovel | Piedra | 655 | Cavado en área 5×5×5 cúbico / 5×5×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_shovel.png" width="32"> | Iron Workhand Shovel | Hierro | 1250 | Cavado en área 3×3×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_advanced_shovel.png" width="32"> | Iron Workhand Advanced Shovel | Hierro | 1250 | Cavado en área 3×3×3 cúbico / 3×3×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_expert_shovel.png" width="32"> | Iron Workhand Expert Shovel | Hierro | 1250 | Cavado en área 5×5×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_professional_shovel.png" width="32"> | Iron Workhand Professional Shovel | Hierro | 1250 | Cavado en área 5×5×5 cúbico / 5×5×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_shovel.png" width="32"> | Diamond Workhand Shovel | Diamante | 7805 | Cavado en área 3×3×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_advanced_shovel.png" width="32"> | Diamond Workhand Advanced Shovel | Diamante | 7805 | Cavado en área 3×3×3 cúbico / 3×3×1 plano (clic derecho alterna modo) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_expert_shovel.png" width="32"> | Diamond Workhand Expert Shovel | Diamante | 7805 | Cavado en área 5×5×1 (una capa) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_professional_shovel.png" width="32"> | Diamond Workhand Professional Shovel | Diamante | 7805 | Cavado en área 5×5×5 cúbico / 5×5×1 plano (clic derecho alterna modo) |

### ⛏️✨ Picos mejorados — vena minera (4)

| Imagen | Nombre | Material | Durabilidad | Para qué vale |
|---|---|---|:---:|---|
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_advanced_improved_pickaxe.png" width="32"> | Iron Workhand Advanced Improved Pickaxe | Hierro mejorado | 3750 | Minado 3×3×3/3×3×1 + vena minera: al romper un mineral, encadena y rompe los bloques conectados del mismo mineral (hasta 128) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_professional_improved_pickaxe.png" width="32"> | Iron Workhand Professional Improved Pickaxe | Hierro mejorado | 3750 | Minado 5×5×5/5×5×1 + vena minera |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_advanced_improved_pickaxe.png" width="32"> | Diamond Workhand Advanced Improved Pickaxe | Diamante mejorado | 23415 | Minado 3×3×3/3×3×1 + vena minera |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_professional_improved_pickaxe.png" width="32"> | Diamond Workhand Professional Improved Pickaxe | Diamante mejorado | 23415 | Minado 5×5×5/5×5×1 + vena minera |

### 🪓 Hachas Workhand — tala de árboles (2)

| Imagen | Nombre | Material | Durabilidad | Para qué vale |
|---|---|---|:---:|---|
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/iron_workhand_axe.png" width="32"> | Iron Workhand Axe | Hierro mejorado | 3750 | Tala de árboles activable con clic derecho: al romper un tronco, tala automáticamente todo el árbol conectado (hasta 256 bloques) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/diamond_workhand_axe.png" width="32"> | Diamond Workhand Axe | Diamante mejorado | 23415 | Tala de árboles activable con clic derecho |

### 💀⚡ Kennestroyer — herramientas definitivas (2)

| Imagen | Nombre | Material | Durabilidad | Para qué vale |
|---|---|---|:---:|---|
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/kennestroyer_pickaxe.png" width="32"> | Kennestroyer Pickaxe | Kennestroyer | 50.000 | 5 modos (Desactivado/3×3×1/3×3×3/5×5×1/5×5×5, clic derecho alterna) + vena minera integrada |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/kennestroyer_shovel.png" width="32"> | Kennestroyer Shovel | Kennestroyer | 50.000 | 5 modos (Desactivado/3×3×1/3×3×3/5×5×1/5×5×5, clic derecho alterna) |

### 🪨⚡ Pico especial — Pizarra Profunda Reforzada (1)

| Imagen | Nombre | Material | Durabilidad | Para qué vale |
|---|---|---|:---:|---|
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/reinforced_deepslate_pickaxe.png" width="32"> | Reinforced Deepslate Pickaxe | Especial | 50.000 | Único propósito: minar Pizarra Profunda Reforzada a velocidad altísima (equivalente a diamante vs. piedra normal). Inútil contra cualquier otro bloque. Sin minado en área, siempre 1×1. Acepta Eficiencia y el resto de encantamientos de pico. |

### 🪵📏 Items de utilidad (2)

| Imagen | Nombre | Material | Durabilidad | Para qué vale |
|---|---|---|:---:|---|
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/robust_stick.png" width="32"> | Robust Stick | — | Sin durabilidad (ingrediente) | Ingrediente de crafteo para las herramientas de grado Experto y Profesional (3-4) |
| <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/Mods/Workhand%20Tools/Items/tape_measure.png" width="32"> | Tape Measure | — | Sin durabilidad (irrompible) | Mide el área entre dos puntos: clic derecho fija el primer punto, clic derecho de nuevo fija el segundo y dibuja una caja con las medidas X/Y/Z. Shift+clic derecho deshace la última medición |
