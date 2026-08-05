# Changelog — Workhand Tools

## 0.0.0-beta.1

- Scaffold inicial desde el esqueleto `codex-docs/mod_template/neoforge/26.2-26.2.0.32-beta` (NeoForge 26.2 / NeoForge 26.2.0.32-beta).
- Repo creado en `stalking-dragons/minecraft/workhand-tools`.
- Diseño técnico documentado en `docs/DESIGN_WORKHAND_TOOLS.md`: pala y pico de obrero para los 6 tiers vanilla de material (madera, piedra, hierro, oro, diamante, netherite), con durabilidad/eficiencia/encantabilidad calcadas de las herramientas base.
- Prompts de imagen para logo y texturas documentados en `docs/ASSET_PROMPTS_WORKHAND_TOOLS.md`.
- Ampliado el diseño a 10 materiales (+ Copper, Deepslate, Blackstone, Obsidian, stats confirmados) y 4 grados por material (obrero/avanzado/experto/profesional) con minado en área (3×3×1, 3×3×3, 5×5×1, 5×5×5), doble función clic izq./der. en grados 2 y 4, override al agacharse y anclaje vertical dependiente del pitch para los patrones de 5 de alto. Total de items: 12 → 80.
- Cerradas las recetas de los 4 grados (pico y pala, formas distintas) + nuevo item `robust_stick` (Robust Stick) usado en grados 3-4 + tabla `m`/`a` de ingredientes por material. JEI se integra gratis vía recetas vanilla estándar, sin plugin propio.
- Corregido `archivesName` en `build.gradle` para seguir la convención `<mod_id>-<mc>-neoforge`.
- Primera subida a CurseForge para validar el proyecto mientras se desarrollan los 80 items y la mecánica de minado en área.
