#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

int main(int argc, char* argv[]) {
  char* pipeNombre = NULL;
  char* archivoEntrada = NULL;

  for (int i = 1; i < argc; i++) {
    if (strcmp(argv[i], "-p") == 0 && i + 1 < argc) {
      pipeNombre = argv[++i];
    } else if (strcmp(argv[i], "-i") == 0 && i + 1 < argc) {
      archivoEntrada = argv[++i];
    } else if (argv[i][0] == '-') {
      fprintf(stderr, "Error: Opción desconocida %s\n", argv[i]);
      fprintf(stderr, "Uso: %s [-i archivo.txt] -p pipeReceptor\n", argv[0]);
      return EXIT_FAILURE;
    }
  }

  if (pipeNombre == NULL) {
    fprintf(stderr, "Error: El parámetro -p es obligatorio.\n");
    return EXIT_FAILURE;
  }

  FILE* pipe = fopen(pipeNombre, "w");
  if (!pipe) {
    perror("No se pudo abrir el pipe de escritura");
    return EXIT_FAILURE;
  }

  // Crear pipe de respuesta
  char pipeRespuesta[50];
  sprintf(pipeRespuesta, "respuesta_%d", getpid());
  mkfifo(pipeRespuesta, 0666);

  // Enviar mensaje de inicio
  char buffer[256];
  sprintf(buffer, "I, _, 0,%s", pipeRespuesta);
  fprintf(pipe, "%s\n", buffer);
  fflush(pipe);

  if (archivoEntrada != NULL) {
    // === Modo automático desde archivo ===
    FILE* archivo = fopen(archivoEntrada, "r");
    if (!archivo) {
      perror("No se pudo abrir el archivo de entrada");
      return EXIT_FAILURE;
    }

    char linea[256];
    while (fgets(linea, sizeof(linea), archivo)) {
      linea[strcspn(linea, "\n")] = '\0';  // quitar salto de línea

      // Enviar al receptor
      snprintf(buffer, sizeof(buffer), "%s,%s", linea, pipeRespuesta);
      fprintf(pipe, "%s\n", buffer);
      fflush(pipe);

      // No espera respuesta si es "Q"
      if (linea[0] == 'Q') break;

      FILE* resp = fopen(pipeRespuesta, "r");
      if (resp) {
        char respuesta[256];
        if (fgets(respuesta, sizeof(respuesta), resp)) {
          printf("Respuesta del receptor: %s", respuesta);
        }
        fclose(resp);
      }
    }

    fclose(archivo);
  } else {
    // === Modo interactivo (menú) ===
    int opcion;
    char nombreLibro[100];
    int isbn;

    do {
      printf("\n--- Menú ---\n");
      printf("1. Solicitar préstamo\n");
      printf("2. Devolver libro\n");
      printf("3. Renovar libro\n");
      printf("4. Salir\n");
      printf("Seleccione una opción: ");
      scanf("%d", &opcion);
      getchar();  // limpiar salto de línea

      switch (opcion) {
        case 1:
          printf("Nombre del libro: ");
          fgets(nombreLibro, sizeof(nombreLibro), stdin);
          nombreLibro[strcspn(nombreLibro, "\n")] = '\0';
          printf("ISBN: ");
          scanf("%d", &isbn);
          getchar();
          sprintf(buffer, "P, %s, %d,%s", nombreLibro, isbn, pipeRespuesta);
          break;

        case 2:
          printf("Nombre del libro: ");
          fgets(nombreLibro, sizeof(nombreLibro), stdin);
          nombreLibro[strcspn(nombreLibro, "\n")] = '\0';
          printf("ISBN: ");
          scanf("%d", &isbn);
          getchar();
          sprintf(buffer, "D, %s, %d,%s", nombreLibro, isbn, pipeRespuesta);
          break;

        case 3:
          printf("Nombre del libro: ");
          fgets(nombreLibro, sizeof(nombreLibro), stdin);
          nombreLibro[strcspn(nombreLibro, "\n")] = '\0';
          printf("ISBN: ");
          scanf("%d", &isbn);
          getchar();
          sprintf(buffer, "R, %s, %d,%s", nombreLibro, isbn, pipeRespuesta);
          break;

        case 4:
          sprintf(buffer, "Q, Salir, 0,%s", pipeRespuesta);
          break;

        default:
          printf("Opción inválida.\n");
          continue;
      }

      fprintf(pipe, "%s\n", buffer);
      fflush(pipe);

      if (opcion == 4) break;

      FILE* resp = fopen(pipeRespuesta, "r");
      if (resp) {
        char respuesta[256];
        if (fgets(respuesta, sizeof(respuesta), resp)) {
          printf("Respuesta del receptor: %s", respuesta);
        }
        fclose(resp);
      }

    } while (opcion != 4);
  }

  fclose(pipe);
  unlink(pipeRespuesta);  // eliminar pipe respuesta
  printf("Proceso solicitante finalizado.\n");
  return EXIT_SUCCESS;
}
