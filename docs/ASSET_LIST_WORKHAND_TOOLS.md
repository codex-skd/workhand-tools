# 🎨 Workhand Tools — Texturas necesarias para completar el mod

> Descripción de las texturas que faltan para que Workhand Tools tenga arte propio. El mod ya funciona con texturas placeholder de 16×16 px — esto es lo que queda por completar en la parte visual.

<br>

## 📋 Resumen

| Categoría | Cantidad | Formato |
|---|:---:|---|
| ⛏️ Texturas de herramienta (pico + pala × 3 materiales × 4 grados) | 24 | PNG · 16×16 px exactos · fondo transparente |
| 🪵 Textura del nuevo item *Palo Robusto* | 1 | PNG · 16×16 px exactos · fondo transparente |
| **Total** | **25** | |

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
| **ITEM NUEVO** | | | | | |
| 25 | `robust_stick.png` | Robust Stick | N/A | N/A | Palo |

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
- [ ] 24 texturas de herramienta (PNGs placeholders creados — listos para arte final)
- [ ] 1 textura de Palo Robusto (PNG placeholder creado — listo para arte final)

**Nota**: Los archivos PNG placeholder (16×16 px) están creados en `src/main/resources/assets/workhand_tools/textures/item/`. El diseñador puede reemplazarlos directamente sin cambios de código.
