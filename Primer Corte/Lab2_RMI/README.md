# Taller RMI - Sumador

Este proyecto implementa un sistema distribuido usando Java RMI (Remote Method Invocation) que permite sumar dos números de forma remota.

## Archivos del Proyecto

- `Sumador.java` - Interfaz remota que define el método sumar
- `SumadorImp.java` - Implementación de la interfaz remota
- `SumadorServer.java` - Servidor RMI que registra el objeto remoto
- `SumadorClient.java` - Cliente RMI que se conecta al servidor

## Compilación

Para compilar todas las clases Java:

```bash
javac *.java
```

## Ejecución

### 1. Iniciar el Servidor

En una terminal, ejecutar:

```bash
java SumadorServer
```

El servidor se iniciará y mostrará:
```
Servidor RMI iniciado...
Objeto Sumador registrado en el registro RMI
Esperando conexiones de clientes...
```

### 2. Ejecutar el Cliente

En otra terminal, ejecutar:

```bash
java SumadorClient
```

El cliente se conectará al servidor y pedirá que ingreses dos números para sumar.

## Funcionamiento

1. El servidor crea una instancia de `SumadorImp` y la registra en el registro RMI
2. El cliente se conecta al registro RMI y obtiene una referencia al objeto remoto
3. El cliente llama al método `sumar()` del objeto remoto
4. El servidor ejecuta la suma y devuelve el resultado al cliente

## Notas

- Asegúrate de que el servidor esté ejecutándose antes de ejecutar el cliente
- El puerto por defecto es 1099
- El servidor debe estar en la misma máquina que el cliente (localhost)
