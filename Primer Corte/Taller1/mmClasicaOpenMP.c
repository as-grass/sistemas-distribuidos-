/*#######################################################################################
 #* Fecha: 15/08/2025
 #* Autor: Ana Sofía Grass Giraldo
 #* Tema: 
 #* 	- Programa Multiplicación de Matrices algoritmo clásico
 #* 	- Paralelismo con OpenMP
######################################################################################*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <sys/time.h>
#include <omp.h>

struct timeval inicio, fin; // variables para medir tiempo

void InicioMuestra(){
	/* marca inicio (microsegundos) */
	gettimeofday(&inicio, (void *)0);
}

void FinMuestra(int N, int TH){
	/* marca fin y reporta duración en microsegundos */
	gettimeofday(&fin, (void *)0);
	long tiempo_usec = (fin.tv_sec - inicio.tv_sec) * 1000000 + (fin.tv_usec - inicio.tv_usec);
	double tiempo = (double) tiempo_usec;
	printf("tamano_matriz=%d; hilos=%d; tiempo_microsegundos=%.0f\n", N, TH, tiempo);
}

void impMatrix(double *matrix, int D){
	printf("\n");
	if(D < 9){
		for(int i=0; i<D*D; i++){
			if(i%D==0) printf("\n");
			printf("%f ", matrix[i]);
		}
		printf("\n**-----------------------------**\n");
	}
}

void iniMatrix(double *m1, double *m2, int D){
	for(int i=0; i<D*D; i++){
		m1[i] = (double)(rand() % 100);	// Números entre 0 y 99
		m2[i] = (double)(rand() % 100);	// Números entre 0 y 99
	}
}

void multiMatrix(double *mA, double *mB, double *mC, int D){
	/* algoritmo clásico i-j-k; paraleliza i y j */
	#pragma omp parallel for collapse(2)
	for(int i=0; i<D; i++){
		for(int j=0; j<D; j++){
			double sum = 0.0;
			for(int k=0; k<D; k++){
				sum += mA[i*D + k] * mB[k*D + j];
			}
			mC[i*D + j] = sum;
		}
	}
}

int main(int argc, char *argv[]){
	if(argc < 3){
		printf("\n Use: $./clasicaOpenMP SIZE Hilos \n\n");
		exit(0);
	}

	int N = atoi(argv[1]);
	int TH = atoi(argv[2]);
	double *matrixA  = (double *)calloc(N*N, sizeof(double));
	double *matrixB  = (double *)calloc(N*N, sizeof(double));
	double *matrixC  = (double *)calloc(N*N, sizeof(double));
	srand(time(NULL));

	omp_set_num_threads(TH);

	iniMatrix(matrixA, matrixB, N);

	impMatrix(matrixA, N);
	impMatrix(matrixB, N);

	InicioMuestra();
	multiMatrix(matrixA, matrixB, matrixC, N);
	FinMuestra(N, TH);

	impMatrix(matrixC, N);

	/*Liberación de Memoria*/
	free(matrixA);
	free(matrixB);
	free(matrixC);
	
	return 0;
}
