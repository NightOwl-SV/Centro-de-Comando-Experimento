# Centro de Mando - Mod para Mindustry

## Descripción
Este mod restaura el Centro de Mando original con 3 modos de control de unidades:
- **Ataque**: Las unidades buscan y atacan objetivos enemigos, cruzando obstáculos cuando es posible
- **Defensa**: Las unidades se mueven a la estructura aliada más cercana, priorizando muros y torres defensivas
- **Retirada**: Las unidades regresan al núcleo más cercano

## Instalación

### Versión JSON (Sin compilación)
1. Copia la carpeta `centro-de-mando` a `%appdata%/Mindustry/mods/`
2. Reinicia Mindustry
3. El mod aparecerá en la lista de mods

### Versión Java (Con compilación)
Para usar las funciones avanzadas con IA personalizada:

1. Instala JDK 17
2. Clona la plantilla: `https://github.com/Anuken/MindustryJavaModTemplate`
3. Copia los archivos Java de `src/centrodemando/` al proyecto
4. Ejecuta `gradlew jar`
5. Copia el JAR generado a `%appdata%/Mindustry/mods/`

## Contenido
- Bloque: Centro de Mando (2x2)
- Costo: 200 Cobre, 250 Plomo, 100 Grafito, 250 Silicio
- Categoría: Efectos

## Compatible con
- Mindustry v8 Build 159.2 (itch.io)
- Serpulo y Erekir

## Notas
- El sprite actual es un placeholder. Reemplázalo con el sprite original del Command Center para mejores resultados.
- Los modos de IA están implementados en Java y requieren compilación.
