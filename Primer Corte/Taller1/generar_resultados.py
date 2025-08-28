import csv
import re
from pathlib import Path
from statistics import mean, stdev


def parse_time_us_from_line(line: str) -> float | None:
    parts = [p.strip() for p in line.split(';')]
    for p in parts:
        if p.startswith('tiempo_microsegundos='):
            try:
                return float(p.split('=', 1)[1])
            except ValueError:
                return None
    return None


def main() -> None:
    here = Path(__file__).resolve().parent
    datos_dir = here / 'archivos_dat'
    salida_csv = here / 'resultados.csv'

    patron = re.compile(r"mmClasicaOpenMP-(?P<size>\d+)-Hilos-(?P<threads>\d+)\.dat$")

    rows = []
    for dat_path in sorted(datos_dir.glob('*.dat')):
        m = patron.search(dat_path.name)
        if not m:
            continue
        size = int(m.group('size'))
        threads = int(m.group('threads'))

        times = []
        with dat_path.open('r', encoding='utf-8', errors='ignore') as f:
            for line in f:
                line = line.strip()
                if not line:
                    continue
                t = parse_time_us_from_line(line)
                if t is not None:
                    times.append(t)

        if len(times) == 0:
            avg = ''
            sd = ''
            count = 0
        elif len(times) == 1:
            avg = times[0]
            sd = 0.0
            count = 1
        else:
            avg = mean(times)
            sd = stdev(times)
            count = len(times)

        rows.append({
            'file': dat_path.name,
            'size': size,
            'threads': threads,
            'count': count,
            'mean_microseconds': f"{avg:.6f}" if avg != '' else '',
            'stddev_microseconds': f"{sd:.6f}" if sd != '' else ''
        })

    fieldnames = ['file', 'size', 'threads', 'count', 'mean_microseconds', 'stddev_microseconds']
    with salida_csv.open('w', newline='', encoding='utf-8') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for r in rows:
            writer.writerow(r)

    # Mensaje final
    print(f"Escrito CSV: {salida_csv}")


if __name__ == '__main__':
    main()


