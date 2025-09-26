CC=mpicc
CFLAGS=-O2
NP?=10

all: mpi01

mpi01: mpi01.c
	$(CC) $(CFLAGS) -o mpi01 mpi01.c

run: mpi01
	mpirun --oversubscribe -np $(NP) ./mpi01

clean:
	rm -f mpi01


