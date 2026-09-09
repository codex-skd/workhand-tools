# Project Variables — Workhand Tools (1.21.1)

> **Rama 1.21.1**: `game_versions = 9638, 9639, 11779, 10150` (Client, Server, **1.21.1** id `11779`, NeoForge). `release_type = release`. JAR `workhand_tools-1.21.1-neoforge-21.1.249-<version>.jar`. Tag `1.21.1-neoforge-<version>`. Proyecto CurseForge compartido con la rama 26.2 (`1640361`).

## Proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | `1640361` |
| `mod_id` | `workhand_tools` |
| `display_name` | `Workhand Tools` |
| Summary (tagline) | *Pickaxe and shovel for every material — 4 grades each, unlocking 3×3 to 5×5 area mining. Durability, speed and enchants always match vanilla.* |

## Tokens

| API | Token | Uso |
|-----|-------|-----|
| Upload | `ee776b0a-ee95-4850-b554-06be02a8657f` | Subir archivos JAR |
| Core (GET) | `$2a$10$yGwryAfmRkS9ZJsJUDf5YOKZpOIsmHB8Fji2D8JVCKBSZEKYlwmaO` | Consultar datos del mod |

Autenticación Upload: cabecera `X-Api-Token`
Autenticación Core: cabecera `x-api-key`

> Token de cuenta (mismo para todos los mods).

## Variables para script (lectura automática)

project_id = 1640361
api_token = ee776b0a-ee95-4850-b554-06be02a8657f
release_type = release
game_versions = 9638, 9639, 11779, 10150
relations =

El script lee `project_id`, `api_token` y `game_versions` de este archivo, y `mod_id`, `mod_name`,
`minecraft_version`, `mod_version` de `gradle.properties`. Sube automáticamente el JAR desde
`build/libs/` con el changelog de `docs/curseforge/versions/<version>.md`.

**game_versions**: `9638` Client · `9639` Server · `11779` Minecraft 1.21.1 · `10150` NeoForge.

## Nota

Proyecto CurseForge **compartido** con la rama 26.2 (`1640361`). Todas las subidas de la rama
1.21.1 se hacen con el mismo script. Desde `1.0.0` el `release_type` de la línea 1.21.1 es
`release`.

## Rama

```
minecraft/1.21.1/neoforge-21.1.249/production
```

## Tag

Formato: `<mc-version>-<framework>-<version>`
Ejemplo: `1.21.1-neoforge-0.0.0-beta.1`

## Repo GitLab

https://gitlab.com/stalking-dragons/minecraft/workhand-tools.git
