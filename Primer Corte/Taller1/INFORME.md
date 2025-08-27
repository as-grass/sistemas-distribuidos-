## Informe de experimentación: Multiplicación de matrices con OpenMP

### Objetivo
Evaluar rendimiento y escalabilidad de la multiplicación clásica de matrices (O(N^3)) con OpenMP para tamaños múltiples de 80 y hilos {1,4,8,16,20}, con sustento estadístico frente a ruido/cargas del SO.

### Configuración experimental
- Hardware/OS: indicar CPU, núcleos/hilos, RAM, SO.
- Compilación: `gcc -Wall -std=c99 -fopenmp -O3`.
- Variables OMP: `OMP_PROC_BIND=TRUE`, `OMP_DYNAMIC=FALSE`, `OMP_PLACES=cores`.
- Energía: plan Alto rendimiento.
- Tamaños N (múltiplos de 80): `1040, 2000, 3040, 4000, 5040, 6000, 7040, 8000, 9040, 10000, 11040, 13040`.
- Hilos: `1, 4, 8, 16, 20`.
- Repeticiones por combinación: 30 (o hasta IC95% relativo ≤ 10%).

### Metodología para control de ruido
1) Precalentamiento (warm-up) antes de medir.
2) Aleatorizar orden de tamaños/hilos.
3) Fijar OMP y plan de energía.
4) Métrica: tiempo en microsegundos reportado por el programa.
5) Repetir por combinación (N,T) y filtrar outliers por IQR.

### Métricas calculadas (estadisticas.csv)
- Media y desviación estándar (tras IQR), CV = std/mean.
- Mediana, p90, min, max.
- IC95% con t-Student (n<30) o z≈1.96.
- Speedup vs 1 hilo (por N) y eficiencia = speedup/T.
- GFLOPS ≈ 2·N^3 / tiempo(µs) / 1000.

### Procedimiento
1) Ejecutar el plan de carga para generar `.dat`:
   - `perl .\auto.txt` (o vía ruta completa a perl.exe).
2) Resumir resultados:
   - `perl .\resumen.pl` → genera `estadisticas.csv`.
3) Visualizar gráficas (script Python):
   - `python .\plot_estadisticas.py` → genera PNGs.

### Criterios de aceptación estadística
- CV ≤ 10% e IC95% relativo ≤ 10% de la media.
- Si no se cumple para (N,T), añadir repeticiones a ese par y recomputar.

### Interpretación esperada
- Speedup creciente con hilos; rendimientos decrecientes al aumentar T por límites de memoria/jerarquía de caché.
- Para N pequeños, overhead paralelismo puede dominar.
- Para N grandes, posible saturación de ancho de banda y TLB: eficiencia cae.

### Reproducibilidad
Se adjuntan scripts (`auto.txt`, `resumen.pl`, `plot_estadisticas.py`) y CSV (`estadisticas.csv`).


