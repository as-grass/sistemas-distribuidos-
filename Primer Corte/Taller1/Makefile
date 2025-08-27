# Compilador
GCC = gcc

# Flags de compilación
CFLAGS = -Wall -std=c99
FOPENMP = -fopenmp -O3
LIBS = -lm

# Nombre del programa
PROGRAM = mmClasicaOpenMP

# Compilar el programa
all: $(PROGRAM)

# Regla para compilar
$(PROGRAM): $(PROGRAM).c
	$(GCC) $(CFLAGS) $(FOPENMP) $< -o $@ $(LIBS)

# Limpiar archivos compilados
clean:
ifeq ($(OS),Windows_NT)
	del /Q $(PROGRAM).exe 2>nul || echo "No hay archivos para eliminar"
else
	rm -f $(PROGRAM)
endif
