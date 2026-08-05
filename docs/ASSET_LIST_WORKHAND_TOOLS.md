# 🎨 Workhand Tools — Texturas necesarias para completar el mod

> Descripción de las texturas que faltan para que Workhand Tools tenga arte propio. El mod ya funciona hoy con texturas vanilla de placeholder, así que nada de esto bloquea el desarrollo — es lo que queda por completar en la parte visual.

<br>

## 📋 Resumen

| Categoría | Cantidad | Formato |
|---|:---:|---|
| ⛏️ Texturas de herramienta (pico + pala × 10 materiales × 4 grados) | 80 | PNG · 16×16 px exactos · fondo transparente |
| 🪵 Textura del nuevo item *Palo Robusto* | 1 | PNG · 16×16 px exactos · fondo transparente |
| **Total** | **81** | |

El icono del mod y el banner promocional **ya están hechos** — no forman parte de lo que queda pendiente.

<br>

## 📐 Especificación técnica (las 81 texturas de 16×16)

- **16×16 px exactos** — es el tamaño real de renderizado de los iconos de item en Minecraft, no un múltiplo escalado.
- Pixel art plano: sin antialiasing, sin degradados, bordes de píxel duros — el mismo estilo que las texturas vanilla del juego.
- Fondo transparente (canal alfa).
- Perspectiva vanilla: mango recto en la esquina inferior-izquierda, cabeza de la herramienta en diagonal ocupando la esquina superior-derecha.
- El mango es **igual en las 80 herramientas** (madera clara, sin variación por material ni grado) — solo cambia la cabeza.
- Referencia más rápida: abrir `wooden_pickaxe.png`, `stone_pickaxe.png`, `iron_pickaxe.png`, `golden_pickaxe.png`, `diamond_pickaxe.png`, `netherite_pickaxe.png` (y sus `_shovel`) del propio juego como punto de partida de forma y proporción.

<br>

## 🎨 Paleta por material

| Material | Paleta | Notas |
|---|---|---|
| 🪵 Madera | Marrón tostado mate | Igual que la tabla de madera (`oak_planks`) |
| 🪨 Piedra | Gris piedra granulado | Textura simple, sin brillo |
| 🟠 Cobre | Naranja-cobrizo cálido | Leve brillo metálico, sin pátina verde |
| ⚫ Pizarra | Gris carbón denso | Más oscuro y "compacto" que la piedra |
| ⚪ Hierro | Plata clara | Brillo metálico sutil, sin llegar a blanco |
| ⬛ Roca negra | Negro con motas violáceas | Más "roca" que "metal", mate |
| 🟡 Oro | Amarillo dorado saturado | Brillo metálico en el filo |
| 🔵 Diamante | Cian brillante | Reflejos angulares tipo cristal facetado |
| 🟣 Obsidiana | Púrpura-negro cristalino | Más oscuro que el diamante, reflejos tipo vidrio |
| ⬛ Netherita | Gunmetal casi negro | Sutil tinte violeta |

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

## ⛏️ Listado completo — 80 texturas de herramienta

Nombre de archivo final: `<archivo>` (columna **Archivo**). Orden: picos (1-40) primero, luego palas (41-80); dentro de cada tipo, por material (Madera → Netherita); dentro de cada material, por grado (Base → Profesional).

<details>
<summary><strong>▶ Ver las 80 filas (pulsa para desplegar)</strong></summary>

| # | Archivo | Nombre en el juego (castellano) | Material | Grado |
|---|---|---|---|---|
| 1 | `wooden_workhand_pickaxe.png` | Pico de Obrero de Madera | Madera | Base |
| 2 | `wooden_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Madera | Madera | Avanzado |
| 3 | `wooden_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Madera | Madera | Experto |
| 4 | `wooden_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Madera | Madera | Profesional |
| 5 | `stone_workhand_pickaxe.png` | Pico de Obrero de Piedra | Piedra | Base |
| 6 | `stone_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Piedra | Piedra | Avanzado |
| 7 | `stone_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Piedra | Piedra | Experto |
| 8 | `stone_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Piedra | Piedra | Profesional |
| 9 | `copper_workhand_pickaxe.png` | Pico de Obrero de Cobre | Cobre | Base |
| 10 | `copper_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Cobre | Cobre | Avanzado |
| 11 | `copper_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Cobre | Cobre | Experto |
| 12 | `copper_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Cobre | Cobre | Profesional |
| 13 | `deepslate_workhand_pickaxe.png` | Pico de Obrero de Pizarra | Pizarra | Base |
| 14 | `deepslate_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Pizarra | Pizarra | Avanzado |
| 15 | `deepslate_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Pizarra | Pizarra | Experto |
| 16 | `deepslate_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Pizarra | Pizarra | Profesional |
| 17 | `iron_workhand_pickaxe.png` | Pico de Obrero de Hierro | Hierro | Base |
| 18 | `iron_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Hierro | Hierro | Avanzado |
| 19 | `iron_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Hierro | Hierro | Experto |
| 20 | `iron_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Hierro | Hierro | Profesional |
| 21 | `blackstone_workhand_pickaxe.png` | Pico de Obrero de Roca negra | Roca negra | Base |
| 22 | `blackstone_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Roca negra | Roca negra | Avanzado |
| 23 | `blackstone_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Roca negra | Roca negra | Experto |
| 24 | `blackstone_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Roca negra | Roca negra | Profesional |
| 25 | `golden_workhand_pickaxe.png` | Pico de Obrero de Oro | Oro | Base |
| 26 | `golden_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Oro | Oro | Avanzado |
| 27 | `golden_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Oro | Oro | Experto |
| 28 | `golden_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Oro | Oro | Profesional |
| 29 | `diamond_workhand_pickaxe.png` | Pico de Obrero de Diamante | Diamante | Base |
| 30 | `diamond_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Diamante | Diamante | Avanzado |
| 31 | `diamond_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Diamante | Diamante | Experto |
| 32 | `diamond_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Diamante | Diamante | Profesional |
| 33 | `obsidian_workhand_pickaxe.png` | Pico de Obrero de Obsidiana | Obsidiana | Base |
| 34 | `obsidian_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Obsidiana | Obsidiana | Avanzado |
| 35 | `obsidian_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Obsidiana | Obsidiana | Experto |
| 36 | `obsidian_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Obsidiana | Obsidiana | Profesional |
| 37 | `netherite_workhand_pickaxe.png` | Pico de Obrero de Netherita | Netherita | Base |
| 38 | `netherite_workhand_advanced_pickaxe.png` | Pico de Obrero Avanzado de Netherita | Netherita | Avanzado |
| 39 | `netherite_workhand_expert_pickaxe.png` | Pico de Obrero Experto de Netherita | Netherita | Experto |
| 40 | `netherite_workhand_professional_pickaxe.png` | Pico de Obrero Profesional de Netherita | Netherita | Profesional |
| 41 | `wooden_workhand_shovel.png` | Pala de Obrero de Madera | Madera | Base |
| 42 | `wooden_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Madera | Madera | Avanzado |
| 43 | `wooden_workhand_expert_shovel.png` | Pala de Obrero Experto de Madera | Madera | Experto |
| 44 | `wooden_workhand_professional_shovel.png` | Pala de Obrero Profesional de Madera | Madera | Profesional |
| 45 | `stone_workhand_shovel.png` | Pala de Obrero de Piedra | Piedra | Base |
| 46 | `stone_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Piedra | Piedra | Avanzado |
| 47 | `stone_workhand_expert_shovel.png` | Pala de Obrero Experto de Piedra | Piedra | Experto |
| 48 | `stone_workhand_professional_shovel.png` | Pala de Obrero Profesional de Piedra | Piedra | Profesional |
| 49 | `copper_workhand_shovel.png` | Pala de Obrero de Cobre | Cobre | Base |
| 50 | `copper_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Cobre | Cobre | Avanzado |
| 51 | `copper_workhand_expert_shovel.png` | Pala de Obrero Experto de Cobre | Cobre | Experto |
| 52 | `copper_workhand_professional_shovel.png` | Pala de Obrero Profesional de Cobre | Cobre | Profesional |
| 53 | `deepslate_workhand_shovel.png` | Pala de Obrero de Pizarra | Pizarra | Base |
| 54 | `deepslate_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Pizarra | Pizarra | Avanzado |
| 55 | `deepslate_workhand_expert_shovel.png` | Pala de Obrero Experto de Pizarra | Pizarra | Experto |
| 56 | `deepslate_workhand_professional_shovel.png` | Pala de Obrero Profesional de Pizarra | Pizarra | Profesional |
| 57 | `iron_workhand_shovel.png` | Pala de Obrero de Hierro | Hierro | Base |
| 58 | `iron_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Hierro | Hierro | Avanzado |
| 59 | `iron_workhand_expert_shovel.png` | Pala de Obrero Experto de Hierro | Hierro | Experto |
| 60 | `iron_workhand_professional_shovel.png` | Pala de Obrero Profesional de Hierro | Hierro | Profesional |
| 61 | `blackstone_workhand_shovel.png` | Pala de Obrero de Roca negra | Roca negra | Base |
| 62 | `blackstone_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Roca negra | Roca negra | Avanzado |
| 63 | `blackstone_workhand_expert_shovel.png` | Pala de Obrero Experto de Roca negra | Roca negra | Experto |
| 64 | `blackstone_workhand_professional_shovel.png` | Pala de Obrero Profesional de Roca negra | Roca negra | Profesional |
| 65 | `golden_workhand_shovel.png` | Pala de Obrero de Oro | Oro | Base |
| 66 | `golden_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Oro | Oro | Avanzado |
| 67 | `golden_workhand_expert_shovel.png` | Pala de Obrero Experto de Oro | Oro | Experto |
| 68 | `golden_workhand_professional_shovel.png` | Pala de Obrero Profesional de Oro | Oro | Profesional |
| 69 | `diamond_workhand_shovel.png` | Pala de Obrero de Diamante | Diamante | Base |
| 70 | `diamond_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Diamante | Diamante | Avanzado |
| 71 | `diamond_workhand_expert_shovel.png` | Pala de Obrero Experto de Diamante | Diamante | Experto |
| 72 | `diamond_workhand_professional_shovel.png` | Pala de Obrero Profesional de Diamante | Diamante | Profesional |
| 73 | `obsidian_workhand_shovel.png` | Pala de Obrero de Obsidiana | Obsidiana | Base |
| 74 | `obsidian_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Obsidiana | Obsidiana | Avanzado |
| 75 | `obsidian_workhand_expert_shovel.png` | Pala de Obrero Experto de Obsidiana | Obsidiana | Experto |
| 76 | `obsidian_workhand_professional_shovel.png` | Pala de Obrero Profesional de Obsidiana | Obsidiana | Profesional |
| 77 | `netherite_workhand_shovel.png` | Pala de Obrero de Netherita | Netherita | Base |
| 78 | `netherite_workhand_advanced_shovel.png` | Pala de Obrero Avanzado de Netherita | Netherita | Avanzado |
| 79 | `netherite_workhand_expert_shovel.png` | Pala de Obrero Experto de Netherita | Netherita | Experto |
| 80 | `netherite_workhand_professional_shovel.png` | Pala de Obrero Profesional de Netherita | Netherita | Profesional |

</details>

<br>

## 🌐 Tabla de traducciones (castellano → inglés)

Los nombres de archivo y los nombres que se ven en el juego están siempre en inglés (convención del proyecto). El nombre en castellano de la tabla de arriba es una traducción de referencia, no el texto real del juego. Esta tabla traduce cada término a su equivalente real en el juego/código:

| Castellano | Inglés (real, en código/juego) |
|---|---|
| **Nombre base de la línea** | |
| de Obrero | Workhand |
| **Herramientas** | |
| Pico | Pickaxe |
| Pala | Shovel |
| **Materiales** | |
| Madera | Wood / `wooden` |
| Piedra | Stone / `stone` |
| Cobre | Copper / `copper` |
| Pizarra | Deepslate / `deepslate` |
| Hierro | Iron / `iron` |
| Roca negra | Blackstone / `blackstone` |
| Oro | Gold / `golden` |
| Diamante | Diamond / `diamond` |
| Obsidiana | Obsidian / `obsidian` |
| Netherita | Netherite / `netherite` |
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
- [ ] 80 texturas de herramienta
- [ ] Textura de Palo Robusto
