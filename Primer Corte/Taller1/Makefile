GCC = gcc
CFLAGS = -lm
FOPENMP = -fopenmp -O3
POSIX = -lpthread

modulo = inter.c

PROGRAMAS = mmClasicaOpenMP 

ALL: $(PROGRAMAS)


mmClasicaOpenMP: mmClasicaOpenMP.c
	$(GCC) $(CFLAGS) $@.c -o $@ $(FOPENMP)

clean:
	del /Q $(PROGRAMAS)
