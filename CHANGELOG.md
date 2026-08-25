# Changelog — Workhand Tools


## [1.22.4] - 2026-08-25

### Fix

- **El Reinforced Deepslate Pickaxe aún podía minar otros bloques (lento y sin drop)**: la velocidad de minado contra bloques que no son su objetivo era 1.0 (velocidad por defecto), lo que significaba que los jugadores en Supervivencia técnicamente podían romper piedra y otros bloques con él, muy lentamente y sin recibir el material. Ahora devuelve velocidad 0 contra todos los bloques salvo Reinforced Deepslate, así que el progreso de minado nunca avanza — el pico es realmente incapaz de romper cualquier otra cosa en Supervivencia.
- **El Reinforced Deepslate Pickaxe no dropeaba el bloque al minar Reinforced Deepslate consigo mismo**: la sobrescritura de la loot table solo reconocía el Diamond Workhand Professional Improved Pickaxe (necesario para romper la circularidad de fabricar el primero), así que usar el Reinforced Deepslate Pickaxe ya terminado sobre su propio bloque objetivo no dropeaba nada. Ahora también es una herramienta válida en la condición de la loot table.

## [1.22.3] - 2026-08-25

### Fix

- **La receta del Reinforced Deepslate Pickaxe usaba el item de Netherite incorrecto**: la receta estaba implementada con 7 Netherite Ingots en vez de 7 Netherite Blocks como se pretendía originalmente. Corregido — la receta ahora requiere Netherite Blocks.

## [1.22.2] - 2026-08-25

### Fix

- **Kennestroyer Pickaxe y Shovel no tenían textura de item**: ambos items se añadieron en una release anterior sin textura, modelo de item ni fichero de definición de item, así que se renderizaban con el tablero de "textura ausente" de Minecraft en el inventario y en el guide book del juego. Arte final recibido y aplicado.

## [1.22.1] - 2026-08-25

### Fix

- **Las recetas Kennestroyer fallaban al cargar**: `kennestroyer_pickaxe.json` y `kennestroyer_shovel.json` usaban patrones de receta shaped con espacios entre las letras clave (p. ej. `"n d n"`) por legibilidad, pero en el formato de receta shaped de Minecraft cada carácter —incluidos los espacios— cuenta como una celda de la rejilla, así que una fila de 5 caracteres como `"n d n"` se parseaba como 5 columnas contra una mesa de crafteo de 3 de ancho. Ambas recetas fallaban al cargar (`Invalid pattern: too many columns, 3 is maximum`), haciendo el Kennestroyer Pickaxe y Shovel incraftables. Corregido eliminando los espacios (`"ndn"`, `"oBo"`, `"nDn"`) — el layout 3x3 previsto no cambia.

## [1.22.0] - 2026-08-25

### Feature

- **Reinforced Deepslate Pickaxe**: un pico independiente que mina Reinforced Deepslate a muy alta velocidad (equivalente a lo rápido que un Diamond Pickaxe rompe piedra) pero que por lo demás es un pico no funcional incapaz de minar eficientemente cualquier otro bloque. Fabricado con Netherite Ingots, un Diamond Workhand Professional Improved Pickaxe y un bloque de Reinforced Deepslate.
- **Sobrescritura de la loot table de Reinforced Deepslate**: Reinforced Deepslate ahora se dropea a sí mismo al romperse específicamente con un Diamond Workhand Professional Improved Pickaxe (no cualquier otra herramienta), habilitando el bucle de crafteo del nuevo pico.

### Fix

- **El ciclo de modos del Kennestroyer pickaxe/shovel parecía incompleto**: un Kennestroyer recién fabricado arrancaba por defecto en un valor residual `CUBIC` de dos estados en vez de `DISABLED`, así que el primer clic derecho aterrizaba silenciosamente de vuelta en el modo 5x5x5 en vez de iniciar el ciclo, y el mensaje de action-bar/tooltip solo decía un genérico "Mode: Cubic"/"Mode: Flat" sin indicar tamaño — haciendo que los 5 modos reales (Disabled, 3x3x1, 3x3x3, 5x5x1, 5x5x5) parecieran menos de 5. El defecto ahora es `DISABLED`, y el feedback de modo muestra el área exacta (p. ej. "Mode: 3x3x1") para cada uno de los 5 estados.

## [1.21.2] - 2026-08-25

### Fix

- **Crítico: el mod crasheaba al arrancar (error "Bootstrap") para todos los jugadores**, fallando con `IllegalArgumentException: Method ... onLoadComplete ... has @SubscribeEvent annotation, but takes an argument that is not valid for this bus`. `onLoadComplete` escucha `FMLLoadCompleteEvent`, un evento de ciclo de vida de mod solo válido en el event bus del mod, pero estaba anotado con `@SubscribeEvent` y arrastrado por `NeoForge.EVENT_BUS.register(this)` junto a `onServerStarting`, que solo acepta eventos de juego. Corregido registrando `onLoadComplete` explícitamente en el event bus del mod (`modEventBus.addListener(...)`) en vez de vía el escaneo `@SubscribeEvent` en `NeoForge.EVENT_BUS`.

## [1.21.1] - 2026-08-24

### Fix

- **La tala compuesta derribaba árboles adyacentes**: el modo Compound del hacha usaba un flood-fill que podía cruzar huecos de hojas sin límite (`maxLeafDistanceFromLog` por defecto `-1`, ilimitado), permitiéndole saltar del dosel de un árbol a un árbol vecino del mismo tipo de madera y talarlo también. El defecto ahora es `2` bloques de hojas consecutivos — suficiente para cruzar una rama diagonal o un dosel desplazado dentro del mismo árbol, insuficiente para alcanzar un árbol plantado separado cerca.
- **El borde del Chunk Anchor era visible desde distancia ilimitada**: en el modo de borde `ALWAYS` por defecto, el renderizador de contorno de esquinas usaba una distancia máxima sin acotar en vez de la distancia de render real del cliente, así que las líneas de esquina de 384 bloques de alto seguían visibles mucho más allá de donde cualquier otra cosa se renderizaría. Ahora limitado a la distancia de render efectiva del jugador (respetando también los topes de distancia de render del lado del servidor).
- **El guide book tenía contenido corrupto/inválido**: `entries/pickaxes.json` y `entries/shovels.json` (tanto en en_us como es_es) contenían cada uno dos objetos JSON concatenados sin separador (la entrada principal de la categoría seguida directamente por la entrada Kennestroyer, sin coma ni corchetes) — JSON inválido imposible de parsear. Divididos en ficheros válidos separados (`kennestroyer_pickaxe.json`, `kennestroyer_shovel.json`).
- **Las etiquetas de grado de las recetas del guide book estaban intercambiadas**: las páginas de crafteo de picos/palas Advanced (Grade 2) y Expert (Grade 3) tenían los números de grado intercambiados (p. ej. "Advanced Pickaxe (Grade 3)" en vez de Grade 2); las páginas del Vein-Mining Pickaxe tenían Advanced/Professional completamente invertidos (Grade 2 mostrado como 4 y viceversa). Corregido en en_us y es_es.

## [1.21.0] - 2026-08-23

### Feature

- **Kennestroyer Ultimate Tools**: añadidos Kennestroyer Pickaxe y Kennestroyer Shovel — las herramientas definitivas con 5 modos de minado/excavación ciclados con clic derecho:
  - Disabled: sin minado de área
  - Range 3 Flat: 3x3x1 (capa única)
  - Range 3 Cubic: 3x3x3
  - Range 5 Flat: 5x5x1 (capa única)
  - Range 5 Cubic: 5x5x5
- **El Kennestroyer Pickaxe incluye Vein Mining**: rompe automáticamente hasta 128 minerales conectados del mismo tipo
- **Nuevo material de herramienta**: KENNESTROYER (50.000 de durabilidad, velocidad 12.0, +4 de ataque, 15 de encantabilidad, reparado con Netherite Ingots)
- **Recetas de crafteo**: ambas herramientas se fabrican con Netherite Ingots, Diamond Blocks, Obsidian y las herramientas Workhand de primer nivel (Advanced/Professional Improved Pickaxe para el pico, Advanced/Professional Shovel para la pala)

### Content

- **Guide Book**: añadidas entradas del Kennestroyer Pickaxe y Shovel con descripciones completas de modos, controles, estadísticas y recetas en inglés y español

### Technical

- Extendido el enum `AoEMode` con los modos `FLAT_3`, `CUBIC_3`, `FLAT_5`, `CUBIC_5`
- Extendido el enum `Grade` con el grado `KENNESTROYER`, que soporta los 5 modos con cálculo dinámico lateral/altura/profundidad
- Actualizados `AoEMiningHandler` y `AoEPatterns` para manejar el ciclo de 5 modos y el cálculo de patrones del grado Kennestroyer

## [1.20.0] - 2026-08-23

### Feature

- **Vellumli es ahora dependencia obligatoria**: el mod fallará al cargar con un mensaje de error claro si Vellumli no está instalado. Esto garantiza que el guide book del juego esté siempre disponible.
- **Integración completa de recetas en el guide book**: cada herramienta y item de utilidad tiene ahora su receta mostrada directamente en el guide book de Vellumli usando el tipo de página nativo `vellumli:crafting`. No hace falta JEI para ver las recetas.

### Content

- **Pickaxes**: 18 recetas (Stone/Iron/Diamond × todos los grados 1-4 + variantes improved)
- **Shovels**: 12 recetas (Stone/Iron/Diamond × todos los grados 1-4)
- **Axes**: 2 recetas (Iron, Diamond)
- **Hoes**: 2 recetas (Iron, Diamond)
- **Vein-Mining Pickaxes**: 4 recetas (Improved Iron/Diamond Professional + Advanced)
- **Utility Items**: 5 recetas (Tape Measure, Robust Stick, Chunk Anchor, Anchor Tome, Guide Book)

### Change

- Dependencia de Vellumli cambiada de opcional a obligatoria en `neoforge.mods.toml`
- Añadida validación de arranque: el mod lanza `IllegalStateException` si falta Vellumli

### Translation

- Todas las páginas de recetas nuevas añadidas en inglés (en_us) y español (es_es)

## [1.19.0] - 2026-08-21

### Feature

- **Traducción española del guide book**: los 12 ficheros de categoría/entrada del guide book del juego están ahora disponibles en español (es_es), junto al contenido inglés existente.

### Fix

- **La descripción de la categoría Utility Items era inexacta**: afirmaba que el Chunk Anchor establece el punto de spawn del mundo y que el Anchor Tome teletransporta hasta él — nada de eso es real. El comportamiento real es forzar la carga del chunk donde está el ancla; no hay teletransporte.

## [1.18.0] - 2026-08-21

### Feature

- **Guide book entregado en la primera conexión**: cada jugador recibe automáticamente el guide book de Workhand Tools la primera vez que entra (seguimiento por jugador, sobrevive a muerte/respawn). Si se pierde después, hay que fabricarlo de nuevo (libro + lingote de hierro + diamante) — es un regalo único, no un gratis repetible.

### Fix

- **El guide book no aparecía en la pestaña creativa / JEI**: el campo `creative_tab` de `book.json` era la cadena pelada `workhand_tab`, que Vellumli parsea como Identifier namespaced resolviéndose silenciosamente a `minecraft:workhand_tab` (una pestaña que no existe) — el libro nunca llegaba a nuestra propia pestaña creativa. Corregido a `workhand_tools:workhand_tab`.
- **El nombre/subtítulo del libro eran demasiado largos para el tooltip**: acortados `book.name` y `book.subtitle` a 2 palabras como máximo en los 10 idiomas; la redacción más larga vive en `book.landing`.

### Change

- Actualizada la dependencia de Vellumli incluida a la 1.2.0 — añade soporte de búsqueda JEI para el guide book y elimina el demo book incluido de Vellumli.

## [1.17.0] - 2026-08-20

### Feature

- **Workhand Hoes: modo Till/Harvest**: sustituido el toggle de activar/desactivar cosecha por un ciclo de modo Till/Harvest (clic derecho, independientemente de si hay un bloque apuntado). El clic izquierdo realiza ahora la acción: el modo Till ara un único bloque arable (nunca un área, sin importar el tier); el modo Harvest cosecha y replanta un área cuadrada de cultivos maduros/cacao/nether wart (radio 1 la azada de hierro, radio 2 la de diamante), con vista previa del área mientras apuntas en modo Harvest. Clic izquierdo en cualquier otra cosa cae al comportamiento normal de rotura.
- **Workhand Axes: modos de tala Simple/Compound**: sustituido el toggle booleano de tala por un ciclo de 3 estados (Disabled → Simple → Compound → Disabled, clic derecho). Simple tala solo los troncos directamente conectados (sin cruzar huecos de hojas); Compound mantiene el comportamiento anterior de cruzar huecos de hojas para alcanzar troncos desplazados o diagonales. El requisito existente de "debe estar apoyado en suelo sólido" para que la tala cascade tiene ahora un fallback de árbol huérfano: si no se encuentra ningún tronco apoyado dentro de 4 saltos de conectividad tronco-a-tronco, el fragmento se trata como flotante (p. ej. tras destruirse la base del árbol) y se tala igualmente.
- **Guide book del juego**: añadido un guide book fabricable (libro + lingote de hierro + diamante) vía Vellumli, un mod propio de libros de documentación. Documenta cada categoría de herramienta — Pickaxes, Shovels, Vein-Mining Pickaxes, Axes, Hoes e items Utility — con controles y mecánicas. Solo inglés por ahora; Vellumli es dependencia opcional, la receta no está disponible si no está instalado.

## [1.16.1] - 2026-08-20

### Change

- **Actualización de NeoForge**: actualizado de 26.2.0.45-beta a 26.2.0.57.
- **Nombre de JAR con versión del cargador**: el artefacto ahora se compila como `workhand_tools-26.2-neoforge-26.2.0.57-1.16.1.jar`.
- **Documentación del workflow**: actualizada `docs/WORKFLOW_WORKHAND_TOOLS_26-2.md` para reflejar la nueva rama de trabajo.

## [1.16.0] - 2026-08-20

### Fix

- **La cosecha de beetroot podía duplicarse con las Workhand Hoes**: el reset de edad del cultivo tras cosechar hacía hardcode de `CropBlock.AGE`, pero `BeetrootBlock` usa su propia propiedad de edad (rango 0-3) en vez de la de `CropBlock` (rango 0-7). Establecer la propiedad incorrecta lanzaba excepción en runtime *después* de haber concedido los drops, así que la beetroot seguía madura y podía cosecharse otra vez gratis. La propiedad de edad ahora se busca dinámicamente en la definición de estado del propio bloque, arreglando por igual wheat, carrots, potatoes, beetroot, nether wart y cocoa.
- **Los toggles de modo/efecto con clic derecho no reproducían animación de swing**: alternar el efecto de cosecha de la azada, la tala del hacha o el modo AoE del pico/pala cancelaba la interacción sin disparar el swing del brazo, así que la herramienta parecía estática. Los tres toggles ahora hacen swing de la herramienta.
- **La tala ignoraba en qué punto del tronco se rompía el bloque**: cualquier rotura de tronco con la tala activada cascadeaba el árbol completo, incluso a la altura de los ojos a mitad de tronco. La cascada ahora solo se dispara cuando el tronco roto está apoyado en suelo sólido (la base del tronco); romper un tronco en otro punto del árbol rompe solo ese bloque.

### Feature

- **Vista previa de área para las Workhand Hoes**: apuntar con una azada a un cultivo maduro, cacao o nether wart dibuja ahora el contorno del área cuadrada exacta que se cosechará, igualando la preview que ya tenían picos/hachas/palas.
- **Toggle de cosecha activada para las Workhand Hoes**: clic derecho sin objetivo para activar/desactivar el efecto de cosecha de la azada (activada por defecto). Desactivada, las azadas se comportan como una azada vanilla (p. ej. arar tierra).

## [1.15.0] - 2026-08-19

### Feature

- **Arte final para las Workhand Hoes**: las texturas placeholder de `iron_workhand_hoe` y `diamond_workhand_hoe` (copias de las texturas vanilla de azada, añadidas en 1.14.1) han sido sustituidas por el arte final del diseñador. Cada item del mod muestra ahora arte custom final — no quedan texturas vanilla ni placeholder.

## [1.14.2] - 2026-08-19

### Change

- **Los ficheros de config viven ahora en `config/workhand_tools/`**: tanto la config común (`workhand_tools-common.toml`) como la de cliente (`workhand_tools-client.toml`) se registran con una subcarpeta explícita `workhand_tools/`, en vez de estar directamente en `config/` junto a los ficheros de todos los demás mods.

## [1.14.1] - 2026-08-19

### Fix

- **Las Workhand Hoes usaban directamente la textura vanilla de azada en vez del placeholder propio del mod**: los modelos de item `iron_workhand_hoe.json`/`diamond_workhand_hoe.json` referenciaban directamente `minecraft:item/iron_hoe`/`diamond_hoe`, rompiendo la convención que sigue toda otra herramienta de este mod (su propio PNG placeholder local bajo `workhand_tools:item/`, listo para cambiarse por el arte final sin cambios de código). Añadidos `iron_workhand_hoe.png`/`diamond_workhand_hoe.png` (copias de las texturas vanilla) y los modelos apuntan a ellos. Sin cambio visual en el juego.
- **README.md no incluía las Workhand Hoes**: añadida la sección "Workhand Hoes — crop harvesting" y un bullet de Features; se introdujeron en 1.14.0 pero se omitieron de la docs en su momento.

## [1.14.0] - 2026-08-19

### Feature

- **Workhand Hoes — cosecha de cultivos con clic derecho**: dos herramientas nuevas, `iron_workhand_hoe` y `diamond_workhand_hoe` (materiales Improved Iron/Diamond, igualando la durabilidad de las Workhand Axes). Clic derecho en un cultivo maduro, cacao o nether wart para cosecharlo y replantarlo al instante en su sitio — sin necesidad de semillas; los drops respetan Fortune y otros encantamientos de minado. La cosecha se extiende a todo otro cultivo maduro del mismo nivel en un área cuadrada centrada en el bloque apuntado: 3×3 la azada de hierro, 5×5 la de diamante (las plantas que crecen apilando múltiples bloques, como sugar cane o cactus, quedan fuera de alcance). Cada bloque cosechado consume 1 punto de durabilidad.

## [1.13.3] - 2026-08-19

### Fix

- **El contorno AoE podía resaltar bloques que la herramienta realmente no podía romper**: `AreaHighlighter` solo comprobaba `isCorrectToolForDrops` una vez, contra el bloque inicialmente apuntado, y luego dibujaba el contorno para cada posición no-aérea del patrón AoE. `AoEMiningHandler`, que ejecuta la rotura real, comprueba `isCorrectToolForDrops` por bloque — así que un bloque que la herramienta no puede minar podía seguir resaltado aunque nunca llegara a romperse. El bucle por posición aplica ahora la misma comprobación por bloque que usa el handler de minado, de modo que el contorno siempre coincide con el conjunto de bloques que realmente se romperán.

## [1.13.2] - 2026-08-19

### Fix

- **El Chunk Anchor era casi indestructible**: el bloque copiaba las `Properties` completas de Obsidian (`Block.Properties.ofFullCopy(Blocks.OBSIDIAN)`), lo que arrastraba su tiempo de rotura base de 50 segundos, pero nunca se añadió el bloque al tag `minecraft:mineable/pickaxe` — así que ningún pico, ni siquiera de diamante o netherita, recibía su bonus de velocidad de minado. En la práctica, el bloque tardaba el tiempo completo de rotura sin cortar al romperlo a mano. Ahora pertenece a `mineable/pickaxe` manteniendo la dureza original de Obsidian, así que cualquier pico lo mina a la velocidad esperada.

## [1.13.1] - 2026-08-18

### Change

- **Actualización de NeoForge**: actualizado de 26.2.0.37-beta a 26.2.0.45-beta.
- **Nombre de JAR con versión del cargador**: el artefacto ahora se compila como `workhand_tools-26.2-neoforge-26.2.0.45-beta-1.13.1.jar`.
- **Documentación del workflow**: actualizada `docs/WORKFLOW_WORKHAND_TOOLS_26-2.md` para reflejar la nueva rama de trabajo.


## [1.13.0] - 2026-08-17

### Feature

- **Chunk Anchor + Anchor Tome — carga forzada de chunks**: nueva pareja bloque/item, sin relación con la línea de herramientas. Coloca un pedestal Chunk Anchor en el suelo y haz clic derecho con un Anchor Tome para forzar la carga permanente de su chunk, sin necesidad de jugadores cerca — el mismo mecanismo tras `/forceload` de vanilla. Clic derecho con la mano vacía (o agachado) para extraer el tome y liberar el chunk; romper el pedestal con un tome insertado dropea ambos items y libera el ticket inmediatamente. El ticket de chunk forzado se reconcilia en la carga de mundo/chunk, así que sobrevive a reinicios del servidor.
- **Indicador de borde de chunk**: con un tome insertado, las 4 aristas verticales de las esquinas del chunk cargado se dibujan en blanco. La visibilidad es configurable vía `chunkAnchorBorderMode` en la config común: `ALWAYS` (defecto, visible dentro de la distancia de render), `SNEAK_LOOKING` (solo agachado y mirando al ancla, dentro de 20 bloques) o `NEARBY` (dentro de 20 bloques sin importar la postura).
- **Aspecto del Anchor Tome**: el tome insertado flota abierto sobre el pedestal, girando continuamente y pasando páginas adelante y atrás — la misma técnica de animación que el libro de la mesa de encantamientos vanilla — con partículas verdes `enchant` orbitando a su alrededor.
- **Assets reutilizados de Occultism (con permiso)**: el modelo/textura de bloque del Chunk Anchor y la textura de item del Anchor Tome están adaptados, con permiso, del mod Occultism de klikli-dev (`otherstone_pedestal` y `book_of_binding_djinni` respectivamente) — ver `README.md` → Credits.

## [1.12.1] - 2026-08-14

### Fix

- **La vista previa de área ignoraba el estado Disabled del AoE**: `AreaHighlighter` (el contorno en cliente mostrado mientras apuntas a un bloque) seguía usando la lógica de modos previa a la 1.11.0 y dibujaba siempre el contorno de área plana/cúbica, incluso con el AoE de la herramienta en Disabled. Ahora lee el mismo defecto consciente del grado que la lógica de minado y tooltips y, cuando el modo es Disabled, resalta solo el bloque apuntado en vez del patrón completo.

## [1.12.0] - 2026-08-14

### Feature

- **Tala completa de árboles, incluidos troncos de ramas sueltos**: el flood-fill de tala de las Workhand Axes busca ahora los 26 bloques vecinos (incluidas diagonales) en vez de solo las 6 caras, y puede atravesar bloques de hojas para alcanzar troncos separados por un hueco — los troncos sueltos incrustados en el dosel que suelen tener los árboles vanilla y moddeados, que la anterior búsqueda solo-troncos-adyacentes-por-cara dejaba en pie. Las hojas nunca se rompen directamente; dependen del decaimiento acelerado existente cuando desaparecen sus troncos de soporte. El máximo de bloques de hojas consecutivos cruzables en una sola cadena es configurable (`maxLeafDistanceFromLog`, ilimitado por defecto).
- **Detección de troncos cross-mod**: la tala y su identificación de troncos ya no dependen exclusivamente del tag de bloques vanilla `minecraft:logs`. Cualquier bloque cuyo id de registro contenga `_log` o `log_` (excluyendo variantes stripped) se reconoce también como tronco, así que los árboles de mods que no taguean bien sus troncos se talan igualmente como un todo.

## [1.11.0] - 2026-08-14

### Feature

- **El minado AoE puede desactivarse por completo por herramienta**: el clic derecho ya no solo alterna entre formas de área — cada grado de pico cicla ahora por un estado "Disabled" también. Los grados 1 y 3 (antes un área fija plana sin acción de clic derecho) alternan **Disabled → Flat → Disabled**. Los grados 2 y 4 (antes Cubic/Flat solamente, siempre activo) ciclan **Disabled → Flat → Cubic → Disabled**. Los picos existentes siguen minando en modo área por defecto — nada cambia salvo que el jugador haga clic derecho. Tooltips actualizados para mostrar el estado desactivado y la pista de clic derecho en todos los grados.

## [1.10.2] - 2026-08-13

### Fix

- **Crash de servidor (`ConcurrentModificationException`) en el decaimiento de hojas**: `LeafDecayHandler.onServerTick` iteraba su mapa de programación mientras tickeaba hojas; si una hoja decaía durante ese tick, el `NeighborNotifyEvent` resultante se disparaba síncronamente y reentraba en `onNeighborNotify`, mutando el mismo mapa a mitad de iteración. Las entradas listas se eliminan ahora del mapa primero y se tickean después en una pasada separada.

## [1.10.1] - 2026-08-12

### Change

- **Nombre de JAR con versión del cargador**: el artefacto ahora se compila como `workhand_tools-26.2-neoforge-26.2.0.37-beta-1.10.1.jar` (se añade la versión de cargador/NeoForge al nombre del archivo). Empaquetado y documentación; sin cambios de funcionalidad.

## [1.10.0] - 2026-08-12

### Balance

- **Durabilidad aumentada x5 en todas las herramientas**: el coste de crafteo era lo bastante alto como para que la durabilidad de nivel vanilla hiciera estas herramientas desechables. Stone 131 → 655, Iron 250 → 1250, Diamond 1561 → 7805, Improved Iron (picos improved + hachas) 750 → 3750, Improved Diamond 4683 → 23415. El resto de estadísticas (velocidad, bonus de ataque, encantabilidad, material de reparación) queda idéntico a vanilla.

### Documentation

- Catálogo completo de items añadido al README.md, a la descripción del proyecto en CurseForge y a `docs/ASSET_LIST_WORKHAND_TOOLS.md`: los 32 items listados con foto, material, durabilidad y descripción de propósito en una línea.

## [1.9.0] - 2026-08-12

### Feature

- **Decaimiento de hojas más rápido**: las hojas desconectadas de madera decaen ahora notablemente más rápido. Cuando se elimina un bloque adyacente a hojas (a mano, con hacha, por explosión, etc.), se fuerza una comprobación temprana de decaimiento unos ticks después en lugar de esperar el intervalo natural de random-tick de vanilla. Efecto global, no ligado a una herramienta concreta.

## [1.8.0] - 2026-08-11

### Feature

- **Arte final de herramientas completado**: las texturas placeholder de las 24 herramientas restantes (Stone/Iron/Diamond × Base/Advanced/Expert/Professional × Pickaxe/Shovel) sustituidas por el arte final del diseñador. Combinado con los picos improved, las workhand axes y el Robust Stick finalizados antes, cada item del mod muestra arte custom final — no quedan texturas vanilla ni placeholder.

## [1.7.0] - 2026-08-11

### Feature

- **Tape Measure**: nuevo item para medir el área entre dos bloques. Clic derecho en un bloque para fijar el punto inicial, clic derecho otra vez para fijar el punto final y dibujar una caja wireframe con etiquetas de longitud X/Y/Z; shift+clic derecho deshace la última caja. El punto final de la caja pendiente se actualiza en vivo desde la mira antes de confirmarse. Color y tamaño de línea/texto configurables en una nueva pantalla de config de cliente. Adaptado del mod "Measurements" de Mrbysco (Licencia MIT), ver Credits en README.md.

## [1.6.1] - 2026-08-10

### Fix

- **Minado en modo Cubic corregido**: el minado de área en modo Cubic solo rompía una única capa `NxNx1` en vez del volumen completo `NxNxN`, y apuntar ligeramente descentrado de la cara del bloque volteaba todo el patrón al eje incorrecto. La dirección de excavación se deriva ahora de un raytrace real de bloque a lo largo de la vista del jugador en vez de una aproximación basada en la posición del jugador, y las caras laterales se invierten igual que ya hacían las caras superiores/inferiores — así la profundidad se extiende hacia dentro de la pared independientemente de pequeñas desviaciones de puntería.
- **Línea duplicada de tooltip eliminada**: los picos improved (vein-mining) mostraban "Hold Shift for normal mining" dos veces, añadida independientemente por los handlers de tooltip de AoE y vein-mining.
- **Códigos de color rotos en tooltips**: las líneas de tooltip `hold_shift` y `right_click` carecían de su dígito de formato de color, haciendo que la primera letra de la palabra siguiente se consumiera como código de formato (p. ej. rojo o tachado) en vez de renderizarse como texto. `vein_mining` tenía un código de color doblemente escapado que se renderizaba como cadena literal. Corregido en los 10 idiomas.

### Update

- **Arte final aplicado**: texturas placeholder de las 24 herramientas, los 4 picos improved y las 2 workhand axes sustituidas por el arte final del diseñador.

## [1.6.0] - 2026-08-10

### Feature

- **Workhand Axes**: hachas de hierro y diamante con tala de árboles conmutable (clic derecho para activar/desactivar; talar un tronco corta el árbol conectado entero).
- **Picos improved con vein-mining**: picos improved Advanced y Professional (hierro/diamante) que, además del minado de área, rompen en cadena bloques de mineral conectados del mismo tipo.

## [1.5.0] - 2026-08-10

### Update

- **Texturas de items renovadas**: todas las texturas de items sustituidas por nuevo artwork.

## [1.4.0] - 2026-08-09

### Feature

- **Dirección de minado basada en la cara**: la dirección del minado AoE se deriva ahora de la cara del bloque apuntado en vez del pitch de la cámara. Una cara lateral (pared) excava hacia dentro de la pared, la cara superior (suelo) excava hacia abajo, y la inferior (techo) hacia arriba. Aplica igual a picos y palas, y la vista previa usa la misma lógica para que resaltado y minado coincidan siempre.

## [1.3.0] - 2026-08-08

### Release

- **Release funcional**: todas las features core estables y funcionando como se pretende. Consolida todos los fixes de la 1.2.x en una release limpia.

### Included Features

- 12 picos + 12 palas en 3 materiales x 4 grados
- Minado de área con dirección adaptativa: horizontal, vertical abajo, vertical arriba
- Toggle modo Cubic / Flat (clic derecho en Advanced y Professional)
- Contorno blanco de preview sobre bloques sólidos mostrando el área afectada
- Traducciones a 10 idiomas con tooltips descriptivos
- Soporte completo de encantamientos igualando los tiers de material vanilla

## [1.2.3] - 2026-08-08

### Fix

- **Contorno solo en bloques sólidos**: se omiten los bloques de aire al renderizar la preview de área. Aspecto más limpio sin wireframes vacíos.

## [1.2.2] - 2026-08-08

### Fix

- **Crash del resaltado de área corregido**: sustituido el dibujado manual de líneas con `VertexConsumer` por `submitShapeOutline`. El enfoque anterior causaba el crash `Missing elements in vertex` porque `LINES_TRANSLUCENT` requiere atributos de vértice adicionales (UV) que las llamadas de dibujo custom no establecían. Usar el built-in `SubmitNodeCollector.submitShapeOutline()` maneja el formato de vértice correctamente.

## [1.2.1] - 2026-08-08

### Fix

- **Sistema de resaltado reescrito**: cambiado de `ExtractLevelRenderStateEvent` + gizmos a `SubmitCustomGeometryEvent` + `RenderTypes.LINES_TRANSLUCENT` + `submitCustomGeometry`. Esto renderiza bordes suaves de líneas blancas alrededor de los bloques afectados — la misma técnica que usa CoKTools. El enfoque anterior basado en gizmos parpadeaba o fallaba según el timing de actualización.
- **Umbral vertical reducido**: de 60° a 50° para una transición más natural entre modos de minado horizontal y vertical.

## [1.2.0] - 2026-08-08

### Feature

- **Dirección de minado adaptativa**: el patrón AoE ahora se adapta a donde miras. Mirando en horizontal: el patrón se extiende hacia delante, dentro de la pared. Mirando abajo (pitch > 60°): patrón centrado extendiéndose hacia abajo (minado de suelo). Mirando arriba (pitch < -60°): patrón centrado extendiéndose hacia arriba (minado de techo). La anchura lateral siempre queda centrada en el bloque apuntado.

## [1.1.2] - 2026-08-08

### Fix

- **Resaltado de preview de área restaurado**: reimplementado usando `ExtractLevelRenderStateEvent` en lugar del abstracto `RenderLevelStageEvent`. Los gizmos se añaden ahora durante la fase extract donde el collector está activo, arreglando el crash de arranque de la v1.1.0.
- **Dirección de minado corregida**: el patrón AoE se extiende solo hacia delante desde el bloque apuntado (hacia la pared que el jugador mira), en vez de estar centrado en él. Antes la mitad del patrón se extendía detrás del jugador. Anchura lateral y anclaje vertical sin cambios.

### Technical

- **`AreaHighlighter`**: se suscribe a `ExtractLevelRenderStateEvent` (se dispara durante `LevelExtractor.extract()`). Usa `Gizmos.cuboid(pos, GizmoStyle.fill(0x40FFFF00))` para resaltados semitransparentes amarillos.
- **`AoEPatterns`**: el bucle de profundidad cambió de `[-depthHalf, +depthHalf]` a `[0, depth-1]` para que todo el patrón se extienda en la dirección de vista del jugador.

## [1.1.1] - 2026-08-08

### Fix

- **Eliminado el resaltado de preview de área**: el `AreaHighlighter` causaba un crash al arrancar (`Cannot register listeners for abstract class RenderLevelStageEvent`). En NeoForge 26.2, `RenderLevelStageEvent` es abstracta con subclases concretas (`AfterSky`, `AfterTranslucentBlocks`, etc.), y el sistema de gizmos es inaccesible desde eventos render-level (los gizmos se recogen durante la fase extract previa al render). La feature de resaltado se reimplementará usando `SubmitCustomGeometryEvent` en una versión futura.

## [1.1.0] - 2026-08-08

### Fix

- **Sistema de minado AoE restaurado**: `buildLookups()` puebla ahora correctamente los mapas internos de grado/item durante `FMLCommonSetupEvent` vía `enqueueWork`. Antes los lookups estaban comentados por un problema de inicializador estático, causando que todas las workhand tools se comportaran como herramientas vanilla sin minado de área alguno. Las 24 herramientas activan ahora sus patrones AoE al usarse sin agacharse.

### Feature

- **Traducciones a 10 idiomas**: soporte de localización completo para inglés (`en_us`), español (`es_es`), portugués brasileño (`pt_br`), francés (`fr_fr`), alemán (`de_de`), ruso (`ru_ru`), chino simplificado (`zh_cn`), japonés (`ja_jp`), italiano (`it_it`) y coreano (`ko_kr`). Todos los nombres de herramientas, mensajes de modo y descripciones de tooltip traducidos.
- **Tooltips descriptivos**: cada workhand tool muestra ahora las dimensiones de su área de minado en el tooltip (p. ej., "Area: 3x3x3" en dorado). Las herramientas con modos (grados 2 y 4) muestran adicionalmente el modo actual (Cubic/Flat) y pistas de control ("Right-click to toggle mode", "Hold Shift for normal mining").
- **Resaltado de preview de área**: sosteniendo una workhand tool y mirando a un bloque mineable, el área afectada se resalta con cubos amarillos semitransparentes usando el sistema nativo de gizmos de MC 26.2. La preview se actualiza en tiempo real y se oculta al agacharse.

### Refactor

- **Clase utilitaria `AoEPatterns`**: la lógica de cálculo de patrones extraída de `AoEMiningHandler` a una clase utilitaria compartida, permitiendo que el minado de área en servidor y el resaltado de preview en cliente usen el mismo algoritmo.

### Technical

- **`ModItems.buildLookups()`**: añadido el registro explícito de las 24 herramientas + robust_stick en los mapas `BY_ID` e `ITEM_GRADES`. Invocado de forma segura vía `FMLCommonSetupEvent.enqueueWork()` para evitar problemas de orden de inicialización estática.
- **`AreaHighlighter`**: clase de cliente que usa la API `Gizmos.cuboid()` de MC 26.2 para la preview de área. Registrada en el event bus de NeoForge desde `WorkhandToolsClient`.

## [1.0.0] - 2026-08-07
