## Sistemas Distribuidos 🚀
Repositorio de prácticas y proyectos del curso, orientado a diseñar, programar y medir sistemas que corren en múltiples nodos. Verás comunicación, consistencia y replicación, tolerancia a fallos, coordinación y consenso, computación en la nube y observabilidad. ¡Con mucho ❤️ por el performance! 

### Spotlight del repo: `Primer Corte/Taller1` 🧮💥
Implementación de multiplicación clásica de matrices paralelizada con OpenMP (`mmClasicaOpenMP.c`). Incluye un `Makefile` para compilar y un script en Perl (`auto.txt`) para ejecutar lotes y recolectar métricas en `.dat`.

---

### Requisitos 📦
- **Compilador C con OpenMP**: GCC (recomendado) o Clang con soporte `-fopenmp`.
- **make** para construir.
- **Perl** (opcional) para el script de automatización.
- Windows: se recomienda **WSL** o **MSYS2/MinGW** con `gcc` y `libgomp`. También puedes usar Docker.

Nota: El `Makefile` usa `GCC = gcc-15`. Si no lo tienes, compila así:
```bash
cd "Primer Corte/Taller1"
make GCC=gcc
```

---

### Compilación ⚙️
```bash
cd "Primer Corte/Taller1"
make
```
Genera el ejecutable `mmClasicaOpenMP`.

En Windows (PowerShell con MSYS2/MinGW) o WSL, los comandos son los mismos. Si usas PowerShell nativo, compila dentro del entorno de MSYS2/WSL.

---

### Ejecución rápida 🏁
Uso:
```bash
./mmClasicaOpenMP SIZE HILOS
```
Ejemplo:
```bash
./mmClasicaOpenMP 120 4
```
Donde:
- **SIZE**: dimensión de la matriz cuadrada (p. ej., 80, 120, 160, ...).
- **HILOS**: número de hilos OpenMP (1, 2, 4, 8, 16, 20, ...).

Tip: Para tamaños grandes, la impresión de matrices puede ralentizar. Úsalo para validar con tamaños pequeños y medir con grandes.

---

### Automatización de mediciones 🧪📈
El script `auto.txt` ejecuta múltiples combinaciones de tamaños e hilos y guarda tiempos en `archivos_dat/`.

1) Edita la ruta de salida en `auto.txt` cambiando `$Path_Datos` a tu carpeta `archivos_dat` del repo.
   - En WSL: algo como `/mnt/c/Users/tuUsuario/.../sistemas-distribuidos-/Primer Corte/Taller1/archivos_dat`
2) Corre el script desde `Primer Corte/Taller1`:
```bash
perl auto.txt
```
Se crearán archivos `.dat` con nombres tipo `mmClasicaOpenMP-200-Hilos-8.dat`.

---

### Estructura 🗂️
- `Primer Corte/Taller1/mmClasicaOpenMP.c`: multiplicación clásica paralela con OpenMP.
- `Primer Corte/Taller1/Makefile`: reglas de compilación (`-fopenmp -O3`).
- `Primer Corte/Taller1/auto.txt`: script Perl para ejecuciones por lote.
- `Primer Corte/Taller1/archivos_dat/`: resultados de tiempos en microsegundos.

---

### Créditos 👩‍💻
Autoría: Ana Sofía Grass Giraldo. Curso de Sistemas Distribuidos.

