# Taller 3 — Sistemas distribuidos

**Clase**: Sistemas distribuidos  
**Autores**: Ana Sofía Grass, Sebastián Vargas, Sergio Ortiz, Isabela Palacio

## Descripción
Ejemplos simples de concurrencia en Java:
- Procesamiento secuencial (`Main` + `Cajera`).
- Con hilos extendiendo `Thread` (`MainThread` + `CajeraThread`).
- Con interfaz `Runnable` (`MainRunnable`).

Paquete: `Taller3`.

## Requisitos
- JDK 17 o superior instalado y en el PATH (`java -version`, `javac -version`).

## Compilación y ejecución (Windows PowerShell / CMD)
Desde la raíz del repositorio:

```powershell
cd taller3
# Compilar a carpeta de salida
javac -encoding UTF-8 -d out *.java

# Ejecutar ejemplo secuencial
java -cp out Taller3.Main

# Ejecutar ejemplo con Thread
java -cp out Taller3.MainThread

# Ejecutar ejemplo con Runnable
java -cp out Taller3.MainRunnable
```

Notas:
- `-d out` genera la estructura de paquetes dentro de `out/` y coloca allí los `.class`.
- `-cp out` indica a la JVM dónde buscar las clases compiladas.

## Estructura
```
taller3/
  Cajera.java
  CajeraThread.java
  Cliente.java
  Main.java
  MainRunnable.java
  MainThread.java
```
Todas las clases declaran `package Taller3;`.

## Nota (classpath en el editor)
Si ves el aviso “is not on the classpath…”, en VS Code crea `.vscode/settings.json` en la raíz con:
```json
{
  "java.project.sourcePaths": ["taller3"]
}
```
O migra a una estructura estándar de Maven (`src/main/java`) con `pom.xml`.
