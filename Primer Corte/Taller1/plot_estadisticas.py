import csv
from collections import defaultdict
import os

try:
    import matplotlib.pyplot as plt
except Exception as e:
    print("Matplotlib no disponible:", e)
    raise SystemExit(1)

CSV_PATH = os.path.join(os.path.dirname(__file__), 'estadisticas.csv')
OUT_DIR = os.path.dirname(__file__)

def load_data(csv_path):
    data = defaultdict(dict)  # data[N][T] = row dict
    with open(csv_path, newline='') as f:
        r = csv.DictReader(f)
        for row in r:
            N = int(row['size'])
            T = int(row['threads'])
            data[N][T] = {
                'mean_us': float(row['mean_us']) if row['mean_us'] else 0.0,
                'std_us': float(row['std_us']) if row['std_us'] else 0.0,
                'cv': float(row['cv']) if row['cv'] else 0.0,
                'speedup': float(row['speedup']) if row['speedup'] else None,
                'efficiency': float(row['efficiency']) if row['efficiency'] else None,
            }
    return data

def plot_for_size(N, rows):
    threads = sorted(rows.keys())
    mean_us = [rows[t]['mean_us'] for t in threads]
    std_us = [rows[t]['std_us'] for t in threads]
    speedup = [rows[t]['speedup'] for t in threads]
    efficiency = [rows[t]['efficiency'] for t in threads]

    fig, axes = plt.subplots(1, 3, figsize=(14, 4))
    # Tiempo
    axes[0].errorbar(threads, mean_us, yerr=std_us, fmt='-o')
    axes[0].set_title(f'Tiempo (us) N={N}')
    axes[0].set_xlabel('Hilos')
    axes[0].set_ylabel('Tiempo medio (us)')
    axes[0].grid(True, alpha=0.3)

    # Speedup
    if any(s is not None for s in speedup):
        axes[1].plot(threads, [s if s is not None else 0 for s in speedup], '-o')
        axes[1].plot(threads, threads, '--', label='Ideal')
        axes[1].set_title(f'Speedup N={N}')
        axes[1].set_xlabel('Hilos')
        axes[1].set_ylabel('Speedup')
        axes[1].legend()
        axes[1].grid(True, alpha=0.3)
    else:
        axes[1].axis('off')

    # Eficiencia
    if any(e is not None for e in efficiency):
        axes[2].plot(threads, [e if e is not None else 0 for e in efficiency], '-o')
        axes[2].set_title(f'Eficiencia N={N}')
        axes[2].set_xlabel('Hilos')
        axes[2].set_ylabel('Eficiencia (=speedup/hilos)')
        axes[2].grid(True, alpha=0.3)
    else:
        axes[2].axis('off')

    plt.tight_layout()
    out_file = os.path.join(OUT_DIR, f'graficas_N{N}.png')
    plt.savefig(out_file, dpi=140)
    plt.close(fig)
    print('Guardado', out_file)

def main():
    if not os.path.exists(CSV_PATH):
        print('No se encontró', CSV_PATH)
        return 1
    data = load_data(CSV_PATH)
    for N in sorted(data.keys()):
        plot_for_size(N, data[N])
    print('Listo.')
    return 0

if __name__ == '__main__':
    raise SystemExit(main())


