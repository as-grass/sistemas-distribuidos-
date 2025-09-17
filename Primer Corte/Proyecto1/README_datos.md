# Datos y ejecución de pruebas

## Base de datos (`datos/BaseDatos.txt`)
- Formato: línea de cabecera por libro `Nombre, ISBN, Cantidad` seguida de `Cantidad` líneas `numero, estado, fecha`.
- Contenido: 25 libros (`Operating Systems N`, ISBN 3001..3025), cada uno con 50 ejemplares. Estados alternan `D`/`P`, fechas `dd-10-2021`.
- Total de entradas (ejemplares): 25 × 50 = 1250 (≥1000 requerido).

## Pruebas de solicitudes (`input/pruebasSolicitudes.txt`)
- Formato por línea: `OP, NombreLibro, ISBN` donde `OP ∈ {P, D, R, Q}`.
- Archivo incluye ≥100 líneas con mezcla realista de operaciones:
  - Préstamos `P`, Devoluciones `D` y Renovaciones `R` usando ISBN válidos (3001..3025).
  - Cierre: `Q, Salir, 0` (última línea).
  - Nota: la línea de inicio `I, _, 0` la envía el solicitante automáticamente; no debe estar en el archivo.

## Cambios mínimos en el receptor
- Diferenciación de `R`: no cambia a `D`; mantiene `P` y actualiza fecha en `hilo_consumidor`.
- Bucle sobre `total_libros`: uso de `total_libros_global` accesible al hilo consumidor.
- Verbose: se usa `verbose` global con `log_event` y `log.txt`.

## Cómo correr
```bash
make
make run-receptor      # inicia receptor (-v activa logs)
make run-solicitante   # envía las solicitudes del archivo
```
- Logs: `log.txt`
- Estado final de la base de datos: `salida.txt`

