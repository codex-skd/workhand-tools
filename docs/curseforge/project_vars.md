# CurseForge — Variables del proyecto

## Proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | *(pendiente — se rellena al crear el proyecto en CurseForge)* |
| `mod_id` | `workhand_tools` |
| `display_name` | `Workhand Tools` |

## Tokens

| API | Token | Uso |
|-----|-------|-----|
| Upload | *(mismo token de cuenta que el resto de mods — copiar de `ascendant_equipment/neoforge/26.2/docs/curseforge/project_vars.md` u otro mod)* | Subir archivos JAR |
| Core (GET) | *(ídem)* | Consultar datos del mod |

Autenticación Upload: cabecera `X-Api-Token`
Autenticación Core: cabecera `x-api-key`

> Token de cuenta (mismo para todos los mods).

## Variables para script (lectura automática)

project_id =
api_token =
release_type = release
game_versions =
relations =

El script lee `project_id`, `api_token` y `game_versions` de este archivo, y `mod_id`, `mod_name`, `minecraft_version`, `mod_version` de `gradle.properties`. Sube automáticamente el JAR desde `build/libs/` con el changelog de `docs/curseforge/versions/<version>.md`.

## Nota

La **primera subida a CurseForge se hace manual** (proyecto recién creado, sin archivos previos que verificar por API). A partir de la segunda subida se puede usar el script `codex-docs/scripts/curseforge-upload.ps1`.

## Rama

```
minecraft/26.2/neoforge-26.2.0.32-beta/production
```

## Tag

Formato: `<mc-version>-<framework>-<version>`
Ejemplo: `26.2-neoforge-0.0.0-beta.1`
