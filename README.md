# TV dan Video Streaming untuk Android TV
APK
```
echo "# TV-dan-Video-Streaming" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/ryanbekabe/TV-dan-Video-Streaming.git
git push -u origin main
```

Aplikasi ini adalah peramban web sederhana untuk Android TV yang dirancang untuk menampilkan konten dari URL yang dapat dikonfigurasi dari jarak jauh. Aplikasi ini secara otomatis mengambil URL dari file `url.txt` yang di-host di repositori GitHub, menjadikannya solusi yang fleksibel untuk menampilkan berbagai konten web di TV Anda.

## Fitur Utama

- **URL Dinamis**: Konten yang ditampilkan di aplikasi dapat diubah kapan saja dengan memperbarui file `url.txt` di repositori GitHub. Aplikasi akan secara otomatis mengambil URL terbaru saat dimulai.
- **Pengalaman Layar Penuh**: Aplikasi berjalan dalam mode layar penuh yang imersif, menyembunyikan bilah status dan navigasi untuk pengalaman menonton yang bebas gangguan.
- **Dukungan Navigasi TV**: Navigasi dasar menggunakan D-pad remote TV didukung, memungkinkan interaksi sederhana dengan konten web.
- **URL Cadangan (Fallback)**: Jika aplikasi gagal mengambil URL dari GitHub (misalnya, karena masalah jaringan), aplikasi akan memuat URL cadangan yang telah ditentukan sebelumnya untuk memastikan aplikasi tetap berfungsi.
- **Splash Screen**: Layar pembuka yang profesional ditampilkan saat aplikasi dimulai.

## Cara Kerja

1.  **Pengambilan URL**: Saat aplikasi dimulai, ia mengirimkan permintaan jaringan ke URL file `url.txt` mentah di repositori GitHub yang ditentukan.
2.  **Pemuatan Konten**: URL yang diambil dari file teks tersebut kemudian dimuat ke dalam komponen `WebView` layar penuh.
3.  **Mode Cadangan**: Jika pengambilan URL gagal, aplikasi akan memuat URL cadangan yang telah ditentukan dalam kode sumber.

## Konfigurasi

Untuk mengubah URL yang ditampilkan di aplikasi, cukup edit file `url.txt` di repositori GitHub `ryanbekabe/TV-dan-Video-Streaming`.

https://raw.githubusercontent.com/ryanbekabe/TV-dan-Video-Streaming/main/url.txt

https://raw.githubusercontent.com/ryanbekabe/TV-dan-Video-Streaming/refs/heads/main/url.txt


Pastikan file tersebut hanya berisi satu baris URL yang valid.
