### Lab2 - Cliente/Servidor TCP Multihilo

Universidad Pontificia Javeriana  
Autora: Sofia Grass  
Fecha: 29/08/2025

Este laboratorio implementa un servidor TCP multihilo y un cliente TCP. Cada conexion de cliente es atendida por un hilo independiente en el servidor.

### Estructura
- MultithreadedSocketServer.java: servidor en el puerto 8888; crea un ServerClientThread por cliente.
- ServerClientThread.java: maneja el dialogo; recibe un numero y responde su cuadrado; termina con "bye".
- TCPClient.java: cliente interactivo que lee desde consola, envia y muestra la respuesta.

### Requisitos
- JDK 8 o superior
- PowerShell/CMD o shell Linux

### Compilacion
```powershell
cd "C:\Users\anaso\OneDrive\Escritorio\sistemas-distribuidos-\Primer Corte\Lab2\lab02_clienteServidor"
javac *.java
```

### Ejecucion local (dos terminales)
1) Servidor
```powershell
java MultithreadedSocketServer
```
2) Cliente
```powershell
java TCPClient
```
3) En el cliente escribe un numero (por ejemplo 9) y luego "bye".

### Ejecucion remota (cliente en otra maquina)
- Mantener el servidor ejecutandose y abrir el puerto 8888 en firewall.
- Editar TCPClient.java y reemplazar "127.0.0.1" por la IP del servidor (p.ej. "192.168.1.50"), recompilar y ejecutar `java TCPClient` desde la maquina cliente.

### Desde la raiz del repo (usando classpath)
```powershell
# Servidor
cd "C:\Users\anaso\OneDrive\Escritorio\sistemas-distribuidos-"
javac ".\Primer Corte\Lab2\lab02_clienteServidor\*.java"
java -cp ".\Primer Corte\Lab2\lab02_clienteServidor" MultithreadedSocketServer

# Cliente
java -cp ".\Primer Corte\Lab2\lab02_clienteServidor" TCPClient
```

### Solucion de problemas
- Could not find or load main class: ejecuta `java` desde la carpeta de los .class o usa `-cp`.
- Puerto 8888 ocupado: cierra instancias previas del servidor o cambia el puerto en el codigo.
- Firewall: permitir el puerto 8888 para clientes remotos.
- NumberFormatException: evita espacios al ingresar el numero. Alternativa en servidor: recortar con `clientMessage = clientMessage.trim();` antes de parsear.

### Creditos
Desarrollado por Sofia Grass para la asignatura de Introduccion a los Sistemas Distribuidos, Universidad Pontificia Javeriana.


