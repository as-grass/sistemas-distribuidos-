## 🧩 Pontificia Universidad Javeriana — Sistemas Distribuidos

Bienvenid@: en esta asignatura estudiamos cómo diseñar, construir y evaluar sistemas que operan sobre múltiples procesos y nodos, manteniendo la coherencia, el desempeño y la resiliencia ante fallos. La meta: ofrecer servicios confiables a escala, como si un conjunto de máquinas fuese una sola. ✨

### 🧠 ¿Qué es un sistema distribuido?
Un conjunto de computadores (o procesos) independientes que se presentan al usuario como un sistema único y coherente. Implica retos de comunicación, sincronización, tiempo, consistencia y fallos que no aparecen (o aparecen distinto) en sistemas centralizados. 🌐

### 🎯 Objetivos de aprendizaje
- Comprender modelos de concurrencia y comunicación entre procesos y nodos.
- Analizar y diseñar protocolos con propiedades de seguridad y vivacidad.
- Evaluar desempeño y escalabilidad con métricas rigurosas (speedup, eficiencia, IC95%).
- Aplicar técnicas de replicación y tolerancia a fallos para alta disponibilidad.
- Promover reproducibilidad, trazabilidad y buenas prácticas de ingeniería. ✅

### 📚 Temas principales
- Concurrencia y sincronización: procesos, hilos, regiones críticas, bloqueos, barreras.
- Comunicación: paso de mensajes, RPC, RMI, MPI, sockets; patrones request/reply y pub/sub.
- Tiempo y orden: relojes lógicos (Lamport, vectoriales), causalidad y total order.
- Consistencia: modelos (estricta, linealizable, causal, eventual) y su trade-off.
- Replicación y consenso: quorum, primario/secundario, Paxos/Raft (nociones), leader election.
- Particionado y escalado: partición de datos, sharding, balanceo de carga.
- Tolerancia a fallos: detección, reintentos, timeouts, idempotencia, circuit breakers.
- Observabilidad: métricas, logs estructurados, trazas distribuidas.
- Desempeño: Amdahl/Gustafson, speedup, eficiencia, throughput/latencia; diseño de experimentos.

### 🛠️ Metodología y herramientas
- Enfoque práctico guiado por teoría: leer, diseñar, implementar y medir. 🔬
- Lenguajes y frameworks ilustrativos (según unidad): C/C++ (paralelismo), Java (RPC/RMI), MPI, sockets.
- Automatización de pruebas y recolección de evidencia empírica.
- Control de versiones y documentación reproducible.

### 📏 Evaluación y ética
- Énfasis en claridad técnica, corrección funcional y sustento experimental.
- Integridad académica: trabajo propio, citas adecuadas y transparencia en los resultados. 🤝

### ♻️ Reproducibilidad y buenas prácticas
- Código claro, modular y documentado.
- Experimentos parametrizables y repetibles; scripts de ejecución y análisis.
- Reportes breves con metodología, resultados y conclusiones accionables.

### 📖 Recursos recomendados
- Tanenbaum & van Steen — Modern Operating Systems / Distributed Systems. 📘
- Coulouris — Distributed Systems: Concepts and Design. 📗
- Papers clásicos: Lamport (Time, Clocks…), Brewer (CAP), Vogels (Eventual Consistency).

### 🚀 Cómo empezar
- Clona el repositorio, instala dependencias indicadas por cada unidad y ejecuta los ejemplos.
- Sigue las instrucciones de compilación/ejecución incluidas en cada carpeta de contenido.
- Revisa los scripts de automatización y los reportes para entender la metodología.

— Bienvenid@ a pensar en grande, medir mejor y construir sistemas que no se caen. 💪✨
