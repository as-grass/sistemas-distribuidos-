#!/usr/bin/perl
use strict;
use warnings;

sub read_times {
  my ($file) = @_;
  my @t = ();
  open my $fh, "<", $file or return \@t;
  while (my $line = <$fh>) {
    if ($line =~ /tiempo_microsegundos=([0-9]+(?:\.[0-9]+)?)/) {
      push @t, $1 + 0.0;
    }
  }
  close $fh;
  return \@t;
}

sub percentile {
  my ($aref, $p) = @_;
  my @s = sort { $a <=> $b } @$aref;
  my $n = scalar @s;
  return $n ? $s[0] : 0 if $n <= 1;
  my $pos = ($p / 100) * ($n - 1);
  my $lo = int($pos);
  my $hi = $lo < $n - 1 ? $lo + 1 : $lo;
  my $h  = $pos - $lo;
  return $s[$lo] + ($s[$hi] - $s[$lo]) * $h;
}

sub iqr_filter {
  my ($aref) = @_;
  my @a = @$aref;
  return \@a if @a < 4;
  my $q1 = percentile(\@a, 25);
  my $q3 = percentile(\@a, 75);
  my $iqr = $q3 - $q1;
  my $low = $q1 - 1.5 * $iqr;
  my $high = $q3 + 1.5 * $iqr;
  my @f = grep { $_ >= $low && $_ <= $high } @a;
  return \@f if @f >= 2;
  return \@a;
}

sub mean_std {
  my ($aref) = @_;
  my $n = scalar @$aref;
  return (0, 0, $n) if $n == 0;
  my $sum = 0.0; $sum += $_ for @$aref;
  my $mean = $sum / $n;
  return ($mean, 0.0, $n) if $n < 2;
  my $s2 = 0.0; $s2 += ($_ - $mean) * ($_ - $mean) for @$aref;
  my $std = sqrt($s2 / ($n - 1));
  return ($mean, $std, $n);
}

sub t_factor_95 {
  my ($n) = @_;
  my $df = $n - 1;
  return 1.96 if $df >= 30;
  my %t = (
    1=>12.706, 2=>4.303, 3=>3.182, 4=>2.776, 5=>2.571, 6=>2.447, 7=>2.365,
    8=>2.306, 9=>2.262, 10=>2.228, 12=>2.179, 15=>2.131, 20=>2.086, 25=>2.060, 30=>2.042
  );
  for my $k (sort { $a <=> $b } keys %t) { return $t{$k} if $df <= $k; }
  return 2.042;
}

my $path = ".";
my $data_dir = "$path/archivos_dat";
opendir(my $dh, $data_dir) or die "No se pudo abrir $data_dir\n";
my %data; # $data{$size}{$threads} = { raw=>[], filt=>[] }
while (my $f = readdir($dh)) {
  next unless $f =~ /^mmClasicaOpenMP\-(\d+)\-Hilos\-(\d+)\.dat$/;
  my ($size,$th) = ($1+0, $2+0);
  my $times = read_times("$data_dir/$f");
  next unless @$times;
  $data{$size}{$th}{raw}  = $times;
  $data{$size}{$th}{filt} = iqr_filter($times);
}
closedir($dh);

my %baseline = ();
for my $size (keys %data) {
  if (exists $data{$size}{1}) {
    my ($m1, $sd1, $n1) = mean_std($data{$size}{1}{filt});
    $baseline{$size} = $m1 if $n1 > 0;
  }
}

open my $out, ">", "$path/summary.csv" or die "No se pudo crear summary.csv\n";
print $out "size,threads,n_raw,n,mean_us,std_us,cv,median_us,p90_us,min_us,max_us,ci95_low_us,ci95_high_us,speedup,efficiency,gflops\n";

for my $size (sort { $a <=> $b } keys %data) {
  for my $th (sort { $a <=> $b } keys %{ $data{$size} }) {
    my @raw  = @{ $data{$size}{$th}{raw}  // [] };
    my @filt = @{ $data{$size}{$th}{filt} // [] };
    next unless @filt;

    my ($mean, $std, $n) = mean_std(\@filt);
    my $cv   = $mean > 0 ? $std / $mean : 0;
    my $med  = percentile(\@filt, 50);
    my $p90  = percentile(\@filt, 90);
    my $minv = percentile(\@filt, 0);
    my $maxv = percentile(\@filt, 100);

    my $t = t_factor_95($n);
    my $half = ($n > 0) ? $t * ($std / sqrt($n)) : 0.0;
    my ($ci_lo, $ci_hi) = ($mean - $half, $mean + $half);

    my $speed = (exists $baseline{$size} && $mean > 0) ? ($baseline{$size} / $mean) : "";
    my $eff   = ($speed ne "" && $th > 0) ? ($speed / $th) : "";
    my $gflops = $mean > 0 ? (2.0*$size*$size*$size)/($mean*1000.0) : ""; # mean en us

    printf $out ("%d,%d,%d,%d,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%.3f,%s,%s,%s\n",
      $size, $th, scalar(@raw), $n, $mean, $std, $cv, $med, $p90, $minv, $maxv, $ci_lo, $ci_hi,
      ($speed eq "" ? "" : sprintf("%.3f", $speed)),
      ($eff   eq "" ? "" : sprintf("%.3f", $eff)),
      ($gflops eq "" ? "" : sprintf("%.3f", $gflops))
    );
  }
}
close $out;
print "Resumen escrito en summary.csv\n";