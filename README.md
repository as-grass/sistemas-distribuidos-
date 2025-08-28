## 🚀 Taller 1 — Multiplicación de Matrices con OpenMP (Sistemas Distribuidos)

Bienvenid@ al laboratorio de paralelismo: multiplicación clásica de matrices (O(N^3)) con OpenMP, plan de pruebas de carga y un pipeline de análisis estadístico para que tus resultados sobrevivan al ruido del SO. 😎

### TL;DR (3 pasos y a volar)
1) Compila y prueba: `gcc ... && ./mmClasicaOpenMP 200 2`
2) Ejecuta el plan: `perl ./auto.txt` (genera `.dat`)
3) Resume: usa `estadisticas.csv` (ya generado) y revisa `INFORME.md`

### 🗂️ Estructura
```
Primer Corte/
  Taller1/
    mmClasicaOpenMP.c        # Algoritmo MM con OpenMP
    Makefile                 # Compilación automatizada (GCC + OpenMP)
    auto.txt                 # Script (Perl) que ejecuta el plan de pruebas y genera .dat
    INFORME.md               # Metodología y sustento estadístico
    archivos_dat/            # Salida de datos crudos (.dat)
    estadisticas.csv         # Resumen estadístico (si ya fue generado)
```

### 🧰 Requisitos (Ubuntu)
- Ubuntu 20.04/22.04+ con bash/zsh
- GCC con OpenMP (`sudo apt install -y build-essential`)
- Perl (suele venir instalado; si no: `sudo apt install -y perl`)
- Python 3 + matplotlib (`sudo apt install -y python3-matplotlib`) o `pip install matplotlib`

### 🛠️ Compilación
```bash
cd "Primer Corte/Taller1"
gcc -Wall -std=c99 -fopenmp -O3 mmClasicaOpenMP.c -o mmClasicaOpenMP -lm
# Prueba mínima
./mmClasicaOpenMP 200 2
```

También puedes usar Makefile:
```bash
cd "Primer Corte/Taller1"
make
```

### 🧪 Plan de pruebas (12 tamaños × hilos {1,4,8,16,20})
Los tamaños están configurados como múltiplos de 80 para ser divisibles por todos los hilos.

🔇 Sugerencias anti-ruido (opcional):
```bash
export OMP_PROC_BIND=TRUE
export OMP_DYNAMIC=FALSE
export OMP_PLACES=cores
```

Ejecuta el plan (genera `./archivos_dat/mmClasicaOpenMP-<N>-Hilos-<T>.dat`):
```bash
perl ./auto.txt
```

### 📊 Resumen estadístico (CSV)
`estadisticas.csv` contiene media, desviación estándar, CV, mediana, p90, min/max, IC95%, speedup, eficiencia y GFLOPS por (N,T).
Consulta:
```bash
head -n 10 ./Primer\ Corte/Taller1/estadisticas.csv
```

### 📈 Gráficas
Opcional: si deseas gráficas, puedes generar las tuyas a partir de `estadisticas.csv` en el entorno que prefieras (p. ej., Python/Excel).

### 🎛️ Personalización
- En Linux, el ejecutable no tiene extensión: si es necesario, en `auto.txt` ajusta la variable para usar el binario sin `.exe`:
  - `$Bin = "mmClasicaOpenMP";` (en lugar de `mmClasicaOpenMP.exe`).
- Edita `auto.txt` para cambiar `@Size_Matriz` (mantener múltiplos de 80) o `$Repeticiones`.
- Para validación rápida: usar pocas repeticiones (p. ej., 5–10) y menos tamaños; luego volver a 30/12.

### 🧠 Sustento estadístico (resumen)
- Control de entorno: OMP fijas, plan de energía Alto rendimiento.
- Repeticiones por combinación: 30 (o hasta que IC95% relativo ≤ 10% y CV ≤ 10%).
- IQR para atenuar outliers antes de estimar estadísticas.
- Speedup/eficiencia respecto a 1 hilo por cada N.

### 📦 Entrega comprimida (ejemplo)
```bash
cd "Primer Corte/Taller1"
tar -czf ../entrega_taller_paralelismo.tar.gz \
  mmClasicaOpenMP.c Makefile auto.txt INFORME.md \
  estadisticas.csv archivos_dat
```

### 🩹 Solución de problemas
- «command not found: gcc»: `sudo apt install -y build-essential`
- «gcc: error: unrecognized command line option '-fopenmp'»: usa GCC (no Clang) o instala soporte OpenMP para tu compilador.
- «No aparecen .dat»: verifica que estás en `Primer Corte/Taller1` y que `auto.txt` apunta al binario correcto (`mmClasicaOpenMP`).

### 💡 Notas
- El tiempo crece ~O(N^3) y la memoria ~O(N^2). Ajusta tamaños según tus recursos.
- Los resultados pueden variar por interferencia del SO; aplica las sugerencias anti‑ruido para mayor estabilidad.


