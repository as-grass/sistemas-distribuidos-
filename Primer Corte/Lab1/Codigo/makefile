# ----------------- CONFIGURACIÓN -----------------
# Compilador y banderas
CC = gcc
CFLAGS = -Wall -pthread

# Archivos fuente
SOLICITANTE_SRC = procesoSolicitante.c
RECEPTOR_SRC = procesoReceptor.c

# Ejecutables
SOLICITANTE_EXE = solicitante
RECEPTOR_EXE = receptor

# Nombre del pipe
PIPE_NAME = hola

# ----------------- PHONY: evita errores con nombres de archivos -----------------
.PHONY: all pipe run-receptor run-solicitante clean

# ----------------- COMPILACIÓN PRINCIPAL -----------------
all: $(SOLICITANTE_EXE) $(RECEPTOR_EXE)

# Compilar solicitante
$(SOLICITANTE_EXE): $(SOLICITANTE_SRC)
	$(CC) $(CFLAGS) -o $@ $^

# Compilar receptor
$(RECEPTOR_EXE): $(RECEPTOR_SRC)
	$(CC) $(CFLAGS) -o $@ $^

# ----------------- CREAR PIPE -----------------
pipe:
	@if [ ! -p $(PIPE_NAME) ]; then \
		echo "Creando pipe: $(PIPE_NAME)"; \
		mkfifo $(PIPE_NAME); \
		sleep 0.1; \
	else \
		echo "Pipe ya existe: $(PIPE_NAME)"; \
	fi

# ----------------- EJECUTAR PROGRAMAS -----------------

# Ejecutar receptor con argumentos (requiere pipe)
run-receptor: pipe
	./$(RECEPTOR_EXE) -p $(PIPE_NAME) -f BaseDatos.txt -s salida.txt -v

# Ejecutar solicitante (requiere pipe)
run-solicitante: pipe
	./$(SOLICITANTE_EXE) -p $(PIPE_NAME)

# ----------------- LIMPIAR -----------------
clean:
	rm -f $(SOLICITANTE_EXE) $(RECEPTOR_EXE) $(PIPE_NAME) log.txt salida.txt
