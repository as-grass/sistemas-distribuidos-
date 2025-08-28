## 🚀 Taller 1 — Multiplicación de Matrices con OpenMP (Ubuntu)

### TL;DR
1) Compila y prueba: `gcc -Wall -std=c99 -fopenmp -O3 mmClasicaOpenMP.c -o mmClasicaOpenMP -lm && ./mmClasicaOpenMP 200 2`
2) Ejecuta plan: `export OMP_PROC_BIND=TRUE OMP_DYNAMIC=FALSE OMP_PLACES=cores && perl ./auto.txt`
3) Revisa resultados: `head -n 10 ./estadisticas.csv` y lee `INFORME.md`

### 🗂️ Archivos clave (aquí)
- `mmClasicaOpenMP.c` — Algoritmo MM con OpenMP
- `Makefile` — Compilación automatizada (GCC + OpenMP)
- `auto.txt` — Genera `.dat` en `./archivos_dat/`
- `INFORME.md` — Metodología y sustento estadístico
- `archivos_dat/` — Datos crudos `.dat`
- `estadisticas.csv` — Resumen estadístico final

### 🧰 Requisitos
- Ubuntu 20.04/22.04+
- GCC con OpenMP: `sudo apt install -y build-essential`
- Perl: `sudo apt install -y perl`
- (Opcional) Python 3 si deseas graficar por tu cuenta

### 🛠️ Compilación
```bash
gcc -Wall -std=c99 -fopenmp -O3 mmClasicaOpenMP.c -o mmClasicaOpenMP -lm
./mmClasicaOpenMP 200 2
# o con Makefile
make
```

### 🧪 Plan de pruebas (12 tamaños × hilos {1,4,8,16,20})
Sugerencias anti‑ruido (opcional):
```bash
export OMP_PROC_BIND=TRUE
export OMP_DYNAMIC=FALSE
export OMP_PLACES=cores
```
Ejecuta el plan (genera `./archivos_dat/mmClasicaOpenMP-<N>-Hilos-<T>.dat`):
```bash
perl ./auto.txt
```

### 📊 Resultados
`estadisticas.csv` incluye: media, desviación estándar, CV, mediana, p90, min/max, IC95%, speedup, eficiencia y GFLOPS por (N,T).
```bash
head -n 10 ./estadisticas.csv
```

### 🎛️ Personalización
- Si `auto.txt` apunta a binario con `.exe`, cámbialo a: `$Bin = "mmClasicaOpenMP";`
- Ajusta `@Size_Matriz` (múltiplos de 80) y `$Repeticiones` según recursos.

### 📦 Entrega
```bash
tar -czf ../entrega_taller_paralelismo.tar.gz \
  mmClasicaOpenMP.c Makefile auto.txt INFORME.md \
  estadisticas.csv archivos_dat
```


