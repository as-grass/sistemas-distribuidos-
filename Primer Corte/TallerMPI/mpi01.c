// Programa de ejemplo con MPI: cada proceso imprime su rank y el total de procesos.
// Compilación: mpicc -O2 -o mpi01 mpi01.c
// Ejecución: mpirun --oversubscribe -np N ./mpi01
#include <mpi.h>
#include <stdio.h>

// Punto de entrada: inicializa MPI, obtiene tamaño y rank, imprime mensaje y finaliza.
int main(int argc, char** argv) {
    // Inicializa el entorno MPI (debe ejecutarse antes de otras llamadas MPI).
    MPI_Init(NULL, NULL);

    // Número total de procesos en el comunicador global (MPI_COMM_WORLD)
    int world_size;
    // Obtiene el tamaño del mundo (cantidad de procesos)
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    // Identificador (rank) del proceso actual
    int world_rank;
    // Obtiene el rank del proceso en MPI_COMM_WORLD
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    // Nombre del host donde corre este proceso
    char processor_name[MPI_MAX_PROCESSOR_NAME];
    int name_len;
    // Recupera el nombre del procesador (host) y su longitud
    MPI_Get_processor_name(processor_name, &name_len);

    // Imprime un mensaje por proceso con host, rank y total de procesos
    printf("==> Hola Mundo!! <==  Nodo: %s  |  IDnodo: %d de %d Nodos\n",
           processor_name, world_rank, world_size);

    // Cierra el entorno MPI y libera recursos
    MPI_Finalize();
    return 0;
}


