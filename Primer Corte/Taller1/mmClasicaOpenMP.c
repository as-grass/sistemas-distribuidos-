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

// Función auxiliar para obtener el mínimo de dos valores
int min(int a, int b) {
    return (a < b) ? a : b;
}


struct timeval inicio, fin; 

void InicioMuestra(){
	gettimeofday(&inicio, (void *)0);
}

void FinMuestra(int N, int TH){
	gettimeofday(&fin, (void *)0);
	fin.tv_usec -= inicio.tv_usec;
	fin.tv_sec  -= inicio.tv_sec;
	double tiempo = (double) (fin.tv_sec*1000000 + fin.tv_usec); 
	printf("tamano_matriz=%d; hilos=%d; tiempo_microsegundos=%.0f\n", N, TH, tiempo);
}

void impMatrix(double *matrix, int D){
	fprintf(stderr, "\n");
	if(D < 9){
		// Para matrices pequeñas, mostrar toda la matriz
		for(int i=0; i<D*D; i++){
			if(i%D==0) fprintf(stderr, "\n");
			fprintf(stderr, "%f ", matrix[i]);
		}
		fprintf(stderr, "\n**-----------------------------**\n");
	} else {
		// Para matrices grandes, mostrar solo los primeros elementos de las primeras filas
		fprintf(stderr, "Matriz %dx%d (mostrando primeros elementos):\n", D, D);
		for(int i=0; i<min(4, D); i++){
			fprintf(stderr, "Fila %d: ", i);
			for(int j=0; j<min(4, D); j++){
				fprintf(stderr, "%f ", matrix[i*D + j]);
			}
			if(D > 4) fprintf(stderr, "...");
			fprintf(stderr, "\n");
		}
		if(D > 4) fprintf(stderr, "... (matriz truncada para visualización)\n");
		fprintf(stderr, "**-----------------------------**\n");
	}
}

// Función para verificar la multiplicación de matrices
void verificarMultiplicacion(double *mA, double *mB, double *mC, int D) {
	fprintf(stderr, "\n=== VERIFICACIÓN DE MULTIPLICACIÓN ===\n");
	fprintf(stderr, "Dimensiones: %dx%d\n", D, D);
	
	// Verificar algunos elementos específicos
	fprintf(stderr, "\nVerificando elementos específicos:\n");
	
	// Elemento [0][0]
	double resultado_manual = 0;
	for(int k = 0; k < D; k++) {
		resultado_manual += mA[k] * mB[k*D];
	}
	fprintf(stderr, "Elemento [0][0]: Calculado=%f, Resultado=%f %s\n", 
		   resultado_manual, mC[0], (resultado_manual == mC[0]) ? "✓" : "✗");
	
	// Elemento [0][1] si D > 1
	if(D > 1) {
		resultado_manual = 0;
		for(int k = 0; k < D; k++) {
			resultado_manual += mA[k] * mB[k*D + 1];
		}
		fprintf(stderr, "Elemento [0][1]: Calculado=%f, Resultado=%f %s\n", 
			   resultado_manual, mC[1], (resultado_manual == mC[1]) ? "✓" : "✗");
	}
	
	// Elemento [1][0] si D > 1
	if(D > 1) {
		resultado_manual = 0;
		for(int k = 0; k < D; k++) {
			resultado_manual += mA[D + k] * mB[k*D];
		}
		fprintf(stderr, "Elemento [1][0]: Calculado=%f, Resultado=%f %s\n", 
			   resultado_manual, mC[D], (resultado_manual == mC[D]) ? "✓" : "✗");
	}
	
	// Verificación general de la propiedad: suma de todos los elementos
	double suma_total = 0;
	for(int i = 0; i < D*D; i++) {
		suma_total += mC[i];
	}
	fprintf(stderr, "\nSuma total de elementos en matriz resultado: %f\n", suma_total);
	
	fprintf(stderr, "=== FIN VERIFICACIÓN ===\n\n");
}

void iniMatrix(double *m1, double *m2, int D){
	for(int i=0; i<D*D; i++, m1++, m2++){
		*m1 = (double)(rand() % 100);	
		*m2 = (double)(rand() % 100);	
	}
}

void multiMatrix(double *mA, double *mB, double *mC, int D){
	#pragma omp parallel for
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
		fprintf(stderr, "\n Use: $./clasicaOpenMP SIZE Hilos \n\n");
		exit(0);
	}


	int N = atoi(argv[1]);
	int TH = atoi(argv[2]);
	
	fprintf(stderr, "\n=== MULTIPLICACIÓN DE MATRICES CON OPENMP ===\n");
	fprintf(stderr, "Tamaño de matrices: %dx%d\n", N, N);
	fprintf(stderr, "Número de hilos: %d\n", TH);
	
	double *matrixA  = (double *)calloc(N*N, sizeof(double));
	double *matrixB  = (double *)calloc(N*N, sizeof(double));
	double *matrixC  = (double *)calloc(N*N, sizeof(double));
	srand(time(NULL));

	omp_set_num_threads(TH);

	iniMatrix(matrixA, matrixB, N);

	fprintf(stderr, "\n--- MATRIZ A ---");
	impMatrix(matrixA, N);
	fprintf(stderr, "\n--- MATRIZ B ---");
	impMatrix(matrixB, N);

	InicioMuestra();
	multiMatrix(matrixA, matrixB, matrixC, N);
	FinMuestra(N, TH);

	fprintf(stderr, "\n--- MATRIZ RESULTADO C ---");
	impMatrix(matrixC, N);
	
	// Verificar la multiplicación
	verificarMultiplicacion(matrixA, matrixB, matrixC, N);

	/*Liberación de Memoria*/
	free(matrixA);
	free(matrixB);
	free(matrixC);
	
	return 0;
}
