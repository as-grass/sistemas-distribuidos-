/*#######################################################################################
 #* Fecha: 29/09/2025
 #* Autor: Ana Sofía Grass Giraldo
 #* Tema: 
 #* 	- MPI HELLO WORLD
######################################################################################*/

#include <mpi.h>
#include <stdio.h>

int main(int argc, char** argv) {
    // Inicializar el entorno MPI
    MPI_Init(NULL, NULL);

    // Obtener el número de procesos
    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    // Obtener el rango del proceso
    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    // Obtener el nombre del procesador
    char processor_name[MPI_MAX_PROCESSOR_NAME];
    int name_len;
    MPI_Get_processor_name(processor_name, &name_len);

    // Imprimir mensaje "Hola Mundo"
    printf("Hello world from processor %s, rank %d out of %d processors\n",
           processor_name, world_rank, world_size);

    // Finalizar el entorno MPI
    MPI_Finalize();
}


