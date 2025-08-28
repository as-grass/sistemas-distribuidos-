# Compilador
GCC = gcc

# Flags de compilación
CFLAGS = -Wall -std=c99
FOPENMP = -fopenmp -O3
LIBS = -lm

# Nombre del programa (sin extensión)
PROGRAM = mmClasicaOpenMP

# Objetivo por defecto: compilar
all: $(PROGRAM)

# Regla para compilar
# $< = archivo fuente (mmClasicaOpenMP.c)
# $@ = nombre del ejecutable de salida
$(PROGRAM): $(PROGRAM).c
	$(GCC) $(CFLAGS) $(FOPENMP) $< -o $@ $(LIBS)

# Limpiar ejecutable generado (Windows o Linux)
clean:
ifeq ($(OS),Windows_NT)
	del /Q $(PROGRAM).exe 2>nul || echo "No hay archivos para eliminar"
else
	rm -f $(PROGRAM)
endif
