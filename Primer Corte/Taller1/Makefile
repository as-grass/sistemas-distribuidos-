GCC = gcc-15
CFLAGS = -lm
FOPENMP = -fopenmp -O3
POSIX = -lpthread

modulo = inter.c

PROGRAMAS = mmClasicaOpenMP 

ALL: $(PROGRAMAS)


mmClasicaOpenMP: mmClasicaOpenMP.c
	$(GCC) $(CFLAGS) $@.c -o $@ $(FOPENMP)

clean:
	$(RM) $(PROGRAMAS) 
