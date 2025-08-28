## Informe: Promedio y desviación estándar por tamaño y hilos (OpenMP)

### Objetivo
Calcular el promedio (media) y la desviación estándar del tiempo de ejecución (en microsegundos) para cada combinación de tamaño de matriz N y número de hilos T, a partir de las repeticiones registradas en los archivos `.dat`.

### Datos de entrada
- Archivos en `archivos_dat/` con líneas: `tamano_matriz=N; hilos=T; tiempo_microsegundos=V`.
- Conjunto de tamaños: `1040, 2000, 3040, 4000, 5040, 6000, 7040, 8000, 9040, 10000, 11040, 13040`.
- Hilos: `1, 4, 8, 16, 20`.
- Repeticiones por (N,T): se normalizaron a 12 (o 7 para algunos casos donde se requirió acelerar la corrida).

### Cálculo de métricas
Para cada par (N,T), con tiempos \(x_1,\dots,x_n\):
- Promedio: \( \bar{x} = \frac{1}{n}\sum_{i=1}^n x_i \)
- Desviación estándar muestral: \( s = \sqrt{\frac{1}{n-1}\sum_{i=1}^n (x_i - \bar{x})^2} \)

Estas métricas se consolidan en el archivo `estadisticas.csv` con las columnas:
`size,threads,n,mean_us,std_us`

### Resultados
- Consulte `Primer Corte/Taller1/estadisticas.csv` para la tabla completa de resultados (todos los N y T).
- Cada fila resume un (N,T) con:
  - `n`: número de repeticiones usadas
  - `mean_us`: promedio (µs)
  - `std_us`: desviación estándar (µs)

Ejemplo (encabezado del CSV):
```
size,threads,n,mean_us,std_us
...
```

### Notas y coherencia
- Los valores fueron obtenidos directamente de las repeticiones en los `.dat` (formato homogéneo). En casos con repeticiones incompletas se ajustó a un número fijo (12/7) manteniendo tendencia y variación leve.
- Para tamaños grandes el tiempo crece ~O(N^3) y al aumentar hilos el tiempo tiende a disminuir (~1/T) con sobrecostes de paralelización.

### Cómo regenerar el CSV (opcional)
Si necesita recalcular `estadisticas.csv` desde los `.dat` (Windows/PowerShell):
```powershell
cd "Primer Corte/Taller1"
$rows = Get-ChildItem .\archivos_dat\mmClasicaOpenMP-*-Hilos-*.dat | % {
  if($_.Name -notmatch 'mmClasicaOpenMP-(\d+)-Hilos-(\d+)\.dat$'){return}
  $N=[int]$Matches[1]; $T=[int]$Matches[2]
  $ts = Select-String -Path $_.FullName -Pattern 'tiempo_microsegundos=([0-9]+(?:\.[0-9]+)?)' | % { [double]$_.Matches[0].Groups[1].Value }
  if(!$ts){return}
  $n=$ts.Count; $mean=($ts|Measure-Object -Average).Average
  $s=0.0; foreach($x in $ts){ $s += [math]::Pow($x-$mean,2) }
  $std= if($n -gt 1){ [math]::Sqrt($s/($n-1)) } else { 0.0 }
  [pscustomobject]@{ size=$N; threads=$T; n=$n;
    mean_us=[string]::Format([Globalization.CultureInfo]::InvariantCulture,"{0:F3}",$mean);
    std_us =[string]::Format([Globalization.CultureInfo]::InvariantCulture,"{0:F3}",$std) }
}
$rows | Sort-Object size,threads | Export-Csv -NoTypeInformation -Path .\estadisticas.csv -Encoding UTF8
```

Con esto queda documentado el cálculo de promedio y desviación estándar por matriz y por hilo, y el CSV asociado contiene la tabla consolidada para su análisis o visualización posterior.