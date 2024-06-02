# The-Holy-Quran
Tentu! Selamat datang di aplikasi The Holy Al-Quran! Aplikasi seluler ini hadir sebagai teman setia Anda dalam menjelajahi dan memahami Al-Quran dengan lebih mendalam. Dibangun menggunakan Android Studio dengan bahasa pemrograman Java, aplikasi ini dirancang untuk membantu Anda membaca, mempelajari, dan menghafal ayat-ayat suci Al-Quran dengan lebih mudah dan menyenangkan.

Fitur-fitur utama aplikasi The Holy Al-Quran mencakup:

Baca Al-Quran: Nikmati pengalaman membaca Al-Quran yang nyaman dan mudah digunakan langsung dari perangkat seluler Anda.

Terjemahan: Akses terjemahan Al-Quran ke dalam berbagai bahasa untuk memahami maknanya dengan lebih baik.

Pencarian: Temukan ayat-ayat Al-Quran dengan cepat berdasarkan kata kunci atau tema tertentu.

Detail Proyek
MainActivity.java: Titik masuk utama aplikasi.
SurahActivity.java: Aktivitas untuk menampilkan detail Surah.
DoaDetailActivity.java: Aktivitas untuk menampilkan detail Doa.
SplashActivity.java: Aktivitas layar pembuka.
WelcomeActivity.java: Aktivitas layar selamat datang.
Kelas Adapter: Adapters untuk mengelola berbagai tampilan dalam aplikasi.

Penjelasan Dependensi Tambahan
Retrofit: Digunakan untuk membuat permintaan HTTP ke API.
implementation ("com.squareup.retrofit2:retrofit:2.9.0")
implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
OkHttp: Sebuah HTTP client yang efisien untuk Android.
implementation ("com.squareup.okhttp3:okhttp:4.9.1")
Lottie: Library untuk memutar animasi Lottie di Android.
implementation ("com.airbnb.android:lottie:6.4.0")
CircleImageView: Sebuah view untuk menampilkan gambar dengan bentuk lingkaran.
implementation ("de.hdodenhof:circleimageview:3.1.0")

Instalasi
Untuk mendapatkan salinan lokal dan menjalankannya, ikuti langkah-langkah sederhana berikut.

Prasyarat
Android Studio terinstal di komputer Anda
Java Development Kit (JDK) versi 8 atau lebih tinggi
Setup
Kloning repositori:
git clone https://github.com/maykoRa/Kings-Library.git
Buka proyek di Android Studio:
Buka Android Studio
Klik Open an existing Android Studio project
Navigasikan ke repositori yang telah dikloning dan klik OK
Bangun proyek:
Tunggu hingga build Gradle selesai
Selesaikan setiap dependensi jika diminta
Jalankan aplikasi:
Pilih perangkat atau emulator
Klik tombol Run (tombol hijau)
