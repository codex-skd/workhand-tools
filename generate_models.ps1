# Genera automaticamente los modelos JSON en src/generated/resources

$modelsDir = "src/generated/resources/assets/workhand_tools/models/item"
New-Item -ItemType Directory -Force -Path $modelsDir | Out-Null

$materials = @{
    "wooden" = "wooden"
    "stone" = "stone"
    "copper" = "iron"
    "deepslate" = "stone"
    "iron" = "iron"
    "blackstone" = "netherite"
    "golden" = "golden"
    "diamond" = "diamond"
    "obsidian" = "diamond"
    "netherite" = "netherite"
}

$grades = @("", "advanced", "expert", "professional")

# Pickaxes
foreach ($material in $materials.Keys) {
    foreach ($grade in $grades) {
        $gradeSuffix = if ($grade -eq "") { "" } else { "_$grade" }
        $filename = "${material}_workhand${gradeSuffix}_pickaxe.json"
        $texture = $materials[$material] + "_pickaxe"

        @"
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "minecraft:item/$texture"
  }
}
"@ | Out-File -FilePath "$modelsDir/$filename" -Encoding UTF8
    }
}

# Shovels
foreach ($material in $materials.Keys) {
    foreach ($grade in $grades) {
        $gradeSuffix = if ($grade -eq "") { "" } else { "_$grade" }
        $filename = "${material}_workhand${gradeSuffix}_shovel.json"
        $texture = $materials[$material] + "_shovel"

        @"
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "minecraft:item/$texture"
  }
}
"@ | Out-File -FilePath "$modelsDir/$filename" -Encoding UTF8
    }
}

# Robust stick
@"
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "minecraft:item/stick"
  }
}
"@ | Out-File -FilePath "$modelsDir/robust_stick.json" -Encoding UTF8

Write-Host "Modelos generados en $modelsDir"
