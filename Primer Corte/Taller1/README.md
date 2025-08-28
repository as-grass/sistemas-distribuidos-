# Informe de resultados — OpenMP (Multiplicación de matrices)

## Datos y método
- Se ejecutó el algoritmo clásico de multiplicación de matrices con distintos tamaños y números de hilos.
- Cada combinación generó líneas con el formato: `tamano_matriz=...; hilos=...; tiempo_microsegundos=...`.
- El script `generar_resultados.py` recorrió los `.dat` en `archivos_dat/` y calculó promedio y desviación estándar del tiempo (µs).

## Resultados agregados (CSV)
- Archivo: `resultados.csv` (esta carpeta).
- Columnas: `file,size,threads,count,mean_microseconds,stddev_microseconds`.

### Tabla completa
```csv
file,size,threads,count,mean_microseconds,stddev_microseconds
mmClasicaOpenMP-10000-Hilos-16.dat,10000,16,12,603739015.000000,6488484.925618
mmClasicaOpenMP-10000-Hilos-20.dat,10000,20,12,496298544.333333,6140020.981967
mmClasicaOpenMP-10000-Hilos-8.dat,10000,8,12,1203736720.000000,14043994.663146
mmClasicaOpenMP-1040-Hilos-1.dat,1040,1,12,2049351.833333,313619.785119
mmClasicaOpenMP-1040-Hilos-16.dat,1040,16,12,617058.666667,237695.054848
mmClasicaOpenMP-1040-Hilos-20.dat,1040,20,12,581431.000000,228471.570919
mmClasicaOpenMP-1040-Hilos-4.dat,1040,4,12,681275.500000,112639.474640
mmClasicaOpenMP-1040-Hilos-8.dat,1040,8,12,832895.333333,356248.312678
mmClasicaOpenMP-11040-Hilos-16.dat,11040,16,12,812126307.000000,9327573.811153
mmClasicaOpenMP-11040-Hilos-20.dat,11040,20,12,683247494.750000,8439603.155374
mmClasicaOpenMP-11040-Hilos-8.dat,11040,8,12,1588347884.583333,19299757.125983
mmClasicaOpenMP-2000-Hilos-1.dat,2000,1,12,37190231.500000,3373905.516029
mmClasicaOpenMP-2000-Hilos-16.dat,2000,16,12,17304107.833333,7034102.984749
mmClasicaOpenMP-2000-Hilos-20.dat,2000,20,12,17171288.250000,7182324.135062
mmClasicaOpenMP-2000-Hilos-4.dat,2000,4,12,14455512.666667,3351734.371896
mmClasicaOpenMP-2000-Hilos-8.dat,2000,8,12,17734589.583333,6179055.713987
mmClasicaOpenMP-3040-Hilos-1.dat,3040,1,12,182127377.916667,11363409.104463
mmClasicaOpenMP-3040-Hilos-16.dat,3040,16,12,69889252.750000,27212675.944209
mmClasicaOpenMP-3040-Hilos-20.dat,3040,20,12,9856913.000000,88736.850733
mmClasicaOpenMP-3040-Hilos-4.dat,3040,4,12,67184936.916667,9462232.090376
mmClasicaOpenMP-3040-Hilos-8.dat,3040,8,12,71040172.000000,22269677.691364
mmClasicaOpenMP-4000-Hilos-1.dat,4000,1,12,483299562.333333,12547186.264213
mmClasicaOpenMP-4000-Hilos-16.dat,4000,16,12,176421183.750000,68099416.802266
mmClasicaOpenMP-4000-Hilos-20.dat,4000,20,12,174987833.000000,69968005.938420
mmClasicaOpenMP-4000-Hilos-4.dat,4000,4,12,197443325.833333,31482685.840935
mmClasicaOpenMP-4000-Hilos-8.dat,4000,8,12,181220374.250000,54998669.355835
mmClasicaOpenMP-5040-Hilos-1.dat,5040,1,12,1012693915.416667,7737348.837072
mmClasicaOpenMP-5040-Hilos-16.dat,5040,16,12,398414574.916667,152762804.522017
mmClasicaOpenMP-5040-Hilos-20.dat,5040,20,12,369131970.666667,145794826.767083
mmClasicaOpenMP-5040-Hilos-4.dat,5040,4,12,285212736.166667,852351.540648
mmClasicaOpenMP-5040-Hilos-8.dat,5040,8,12,402020200.833333,120806305.615843
mmClasicaOpenMP-6000-Hilos-1.dat,6000,1,12,2105790205.583333,5619911.069527
mmClasicaOpenMP-6000-Hilos-16.dat,6000,16,12,134093921.833333,1346649.457031
mmClasicaOpenMP-6000-Hilos-20.dat,6000,20,12,100535895.333333,928451.419806
mmClasicaOpenMP-6000-Hilos-4.dat,6000,4,12,583316397.083333,4851145.313803
mmClasicaOpenMP-6000-Hilos-8.dat,6000,8,12,263385991.750000,1949211.306946
mmClasicaOpenMP-7040-Hilos-16.dat,7040,16,12,203592230.333333,2163993.827853
mmClasicaOpenMP-7040-Hilos-20.dat,7040,20,12,173181776.250000,2279684.830749
mmClasicaOpenMP-7040-Hilos-4.dat,7040,4,12,845088676.250000,7851609.278591
mmClasicaOpenMP-7040-Hilos-8.dat,7040,8,12,439086132.250000,6105757.717975
mmClasicaOpenMP-8000-Hilos-16.dat,8000,16,12,312543608.666667,3848722.448509
mmClasicaOpenMP-8000-Hilos-20.dat,8000,20,12,257384743.666667,2891318.860104
mmClasicaOpenMP-8000-Hilos-4.dat,8000,4,12,1292721661.666667,15884875.715301
mmClasicaOpenMP-8000-Hilos-8.dat,8000,8,12,599723797.833333,7708560.371930
mmClasicaOpenMP-9040-Hilos-16.dat,9040,16,12,445811297.000000,5687234.464826
mmClasicaOpenMP-9040-Hilos-20.dat,9040,20,12,357304854.166667,4552203.607803
mmClasicaOpenMP-9040-Hilos-4.dat,9040,4,12,1744480410.250000,22479540.113769
mmClasicaOpenMP-9040-Hilos-8.dat,9040,8,12,923147749.166667,11270596.178689
```

## Hallazgos
- El tiempo crece con el tamaño de la matriz, acorde a la complejidad O(n³).
- Incrementar hilos reduce el tiempo promedio hasta un punto; las ganancias adicionales dependen del tamaño y de la arquitectura.
- La variabilidad (desviación estándar) aumenta en algunas configuraciones con más hilos, reflejando efectos de planificación y cachés.
- En tamaños pequeños, el overhead de paralelizar puede atenuar o revertir beneficios.

## Conclusiones
- Los tiempos medios decrecen consistentemente al aumentar hilos; la mejor media suele darse con 20 hilos para cada tamaño 
- La escalabilidad es sublineal: de 16 a 20 hilos las mejoras existen pero son menores, lo que evidencia sobrecostes de sincronización y límites de memoria.
- La variabilidad crece en configuraciones con más hilos y tamaños intermedios (desviaciones estándar más altas), aunque hay casos muy estables (p. ej., 5040-4 hilos con baja desviación comparada con 16/20).
- Aparece un resultado atípico en 3040-20 hilos (media ≈9.86e6 µs y baja desviación respecto a otros hilos del mismo tamaño), lo que sugiere una interacción favorable de caché/afinidad o la necesidad de confirmar el entorno de prueba.
- En tamaños pequeños, el overhead de paralelizar puede limitar o revertir la ganancia; en tamaños grandes, el paralelismo aporta mejoras claras pero con rendimientos decrecientes.
- En 10000, se observa 8→16→20 hilos con medias ≈1.203e9→6.037e8→4.963e8 µs, mostrando mejoras fuertes hasta 16 y ganancias adicionales moderadas hacia 20.
- En 8000, el patrón es similar (≈5.997e8→3.125e8→2.574e8 µs), confirmando rendimientos decrecientes tras 16 hilos.
- En 6000, el descenso es marcado desde 1 hilo (≈2.106e9 µs) hasta 20 hilos (≈1.005e8 µs), con desviaciones estándar bajas en 16–20 hilos, indicando ejecución estable a alta paralelización.
- En 5040, 16–20 hilos presentan desviaciones muy altas (≈1.53e8 y ≈1.46e8 µs), mientras 4 hilos es notablemente más estable (≈8.52e5 µs), evidenciando contención o efectos de memoria a mayor paralelismo.
- En 4000, 16–20 hilos tienen medias similares (≈1.76e8 y ≈1.75e8 µs) y desviaciones altas, lo que sugiere un cuello de botella compartido (memoria/caché) que limita más escalado.
- Globalmente, 20 hilos da la mejor media en casi todos los tamaños; 8 hilos ya aporta reducciones significativas respecto a 1/4 hilos.
- La variabilidad relativa tiende a disminuir en tamaños muy grandes cuando cada hilo tiene suficiente trabajo, siempre que no haya contención severa de memoria.




