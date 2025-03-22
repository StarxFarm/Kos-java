import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MainApp {
        private static HashMap<String, List<Kos>> daftarKosPerKota = new HashMap<>();
        private static String[] kotaKos = { "Surabaya", "Jakarta", "Bandung", "Yogyakarta", "Malang", "Bali" };
        private static String[][] dataKelompok = {
                        { "Bintang Wira Akbar Aghni Habibillah", "24111814009" },
                        { "Izaz Tsany Rismawan", "24111814088" },
                        { "Keisa Aushafa Dzihni", "24111814109" },
                        { "M. Reyhan Sheva R.Q", "24111814124" },
                        { "Izha Valensy", "24111814125" },
                        { "Ismail ali Mukharom", "24118144130" },
                        { "Fearda Agnessiya Putri Dardiri", "24111814138" },
                        { "Naila Nurul Faizah", "24111814144" },
        };
        private static String[][] dataUser = {
                        { "user", "Reyhan", "12345" },
                        { "admin", "Keisa", "12345" },
        };

        // Ini adalah bebarapa contoh variabel
        private static String role = "";
        private static String nama = "";
        private static boolean isLoggedIn = false;

        static class Kos {
                Double harga;
                String nama;
                List<String> fasilitas;

                public Kos(String nama, Double harga, List<String> fasilitas) {
                        this.nama = nama;
                        this.harga = harga;
                        this.fasilitas = fasilitas;
                }

                /**
                 * Tampilkan detail dari sebuah Kos.
                 */
                public void tampilkanKos() {
                        System.out.println("\n╔════════════════════════════════════════════════════════╗");
                        System.out.printf("║ %-54s ║\n", "🏠  Nama Kos: " + nama);
                        System.out.printf("║ %-54s ║\n", "💲  Harga Kos: " + formatRupiah(harga));
                        System.out.println("╟────────────────────────────────────────────────────────╢");
                        System.out.printf("║ %-54s ║\n", "📋  Fasilitas:");
                        for (String fasilitasItem : fasilitas) {
                                System.out.printf("║    • %-49s ║\n", fasilitasItem);
                        }
                        System.out.println("╚════════════════════════════════════════════════════════╝");
                }
        }

        /**
         * Inisialisasi data kos di tiap kota.
         */
        public static void inisialisasiData() {
                for (String kota : kotaKos) {
                        daftarKosPerKota.put(kota, new ArrayList<>());
                }
                daftarKosPerKota.get("Surabaya").add(new Kos("Kos A", 1000000.0,
                                new ArrayList<>(List.of("AC", "WiFi", "Kamar Mandi Dalam"))));
                daftarKosPerKota.get("Surabaya").add(new Kos("Kos B", 1500000.0,
                                new ArrayList<>(List.of("WiFi", "Kamar Mandi Luar", "Parkir Motor"))));
                daftarKosPerKota.get("Surabaya").add(
                                new Kos("Kos C", 1200000.0, new ArrayList<>(List.of("AC", "Parkir Motor", "Laundry"))));

                daftarKosPerKota.get("Jakarta").add(new Kos("Kos B", 1200000.0,
                                new ArrayList<>(List.of("WiFi", "Kamar Mandi Luar", "Parkir Motor"))));
                daftarKosPerKota.get("Bandung").add(new Kos("Kos C", 800000.0, new ArrayList<>(List.of("AC", "WiFi"))));

                daftarKosPerKota.get("Yogyakarta").add(new Kos("Kos D", 900000.0,
                                new ArrayList<>(List.of("WiFi", "Parkir Motor", "Kamar Mandi Luar"))));
                daftarKosPerKota.get("Yogyakarta").add(new Kos("Kos E", 1100000.0,
                                new ArrayList<>(List.of("AC", "WiFi", "Kamar Mandi Dalam", "Parkir Mobil"))));

                daftarKosPerKota.get("Malang").add(new Kos("Kos F", 1000000.0,
                                new ArrayList<>(List.of("WiFi", "Kamar Mandi Dalam", "Parkir Motor"))));
                daftarKosPerKota.get("Malang").add(new Kos("Kos G", 1300000.0,
                                new ArrayList<>(List.of("AC", "WiFi", "Kamar Mandi Luar", "Laundry"))));

        }

        /**
         * Tampilkan daftar kota secara rekursif dengan indeks.
         * Jika indeks sama dengan panjang array kota, hentikan rekursi.
         * Lalu tampilkan kota dengan indeks berikutnya.
         **/
        public static void tampilkanKotaRekursif(int indeks) {
                // Jika indeks sama dengan panjang array kota, hentikan rekursi
                if (indeks >= kotaKos.length) {
                        return;
                }

                System.out.printf("  %d. ❖ %s\n", (indeks + 1), kotaKos[indeks]);

                // Panggil fungsi rekursif untuk indeks berikutnya
                tampilkanKotaRekursif(indeks + 1);
        }

        public static void tampilkanMenuLogin(Scanner scanner) {
                System.out.println("\n╔══════════════════════════════════════════════╗");
                System.out.println("║        LOGIN APLIKASI Valensy De Kost        ║");
                System.out.println("╚══════════════════════════════════════════════╝");

                System.out.print("🔑 Masukkan Username: ");
                String username = scanner.nextLine();

                System.out.print("🔒 Masukkan Password: ");
                String password = scanner.nextLine();

                for (String[] user : dataUser) {
                        if (user[0].equals(username) && user[2].equals(password)) {
                                isLoggedIn = true;
                                role = username.equals("admin") ? "admin" : "user";
                                nama = user[1];
                                break;
                        }
                }

                if (!isLoggedIn) {
                        System.out.println("\n❌ Username atau password salah. Silakan coba lagi.");
                }
        }

        /**
         * Menampilkan menu kota ke pengguna. Menu ini berisi header, subjudul,
         * daftar kota, dan footer yang meminta pengguna untuk memilih nomor kota
         * yang ingin dibooking.
         */
        public static void tampilkanMenuKota() {
                // Header menu
                System.out.println("╔════════════════════════════════════════════════╗");
                System.out.println("║             ★ Selamat Datang ★                 ║");
                System.out.println("║       Di Aplikasi Layanan Kos Kami 🏠          ║");
                System.out.println("╚════════════════════════════════════════════════╝");

                // Looping for ini di gunakan untuk melooping data kelompok
                for (int i = 0; i < dataKelompok.length; i++) {
                        System.out.printf("🔹 Nama: %-30s | NIM: %s%n", dataKelompok[i][0], dataKelompok[i][1]);
                }

                // Subjudul
                System.out.println("\n📜 Daftar Kota yang Tersedia:");

                // Daftar kota
                tampilkanKotaRekursif(0);

                // Footer dan prompt
                System.out.println("-------------------------------------------");
                System.out.print("👉 Pilih nomor kota (0 untuk keluar): ");

        }

        public static void tampilAkhir() {
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║          🙏 TERIMA KASIH 🙏                ║");
                System.out.println("║     Telah Menggunakan Aplikasi Kami        ║");
                System.out.println("╠════════════════════════════════════════════╣");
                System.out.println("║         Created by: Kelompok 1             ║");
                System.out.println("╚════════════════════════════════════════════╝");
        }

        /**
         * Menampilkan menu utama ke pengguna. Menu ini berisi header, daftar
         * menu, dan footer yang meminta pengguna untuk memilih menu yang
         * diinginkan.
         * 
         * @param kotaTerpilih nama kota yang sedang dipilih pengguna
         */
        public static void tampilkanMenuUtamaAdmin(String kotaTerpilih) {
                System.out.println("\n╔════════════════════════════════════════════════╗");
                System.out.printf("       ★ Menu Utama - Kota: %s ★          \n", kotaTerpilih);
                System.out.println("╚════════════════════════════════════════════════╝");

                // Daftar menu
                System.out.println("📋 Pilihan Menu:");
                System.out.println("  1. 🏠 Tampilkan Kos");
                System.out.println("  2. ➕ Tambah Kos");
                System.out.println("  3. 📅 Booking Kos");
                System.out.println("  4. 🔍 Manipulasi Substring");
                System.out.println("  5. ❓ Manipulasi Contains");
                System.out.println("  6. 🔡 Manipulasi ToLower");
                System.out.println("  7. 🔠 Manipulasi ToUpper");
                System.out.println("  8. ✏️ Manipulasi Replace");
                System.out.println("  9. 📏 Manipulasi Length");
                System.out.println(" 10. ⬅️ Kembali ke Menu Kota");

                // Footer prompt
                System.out.print("\n🌟 Pilih menu: ");

        }

        public static void tampilkanMenuUtamaUser(String kotaTerpilih) {
                System.out.println("\n╔════════════════════════════════════════════════╗");
                System.out.printf("       ★ Menu Utama - Kota: %s ★          \n", kotaTerpilih);
                System.out.println("╚════════════════════════════════════════════════╝");

                // Daftar menu
                System.out.println("📋 Pilihan Menu:");
                System.out.println("  1. 🏠 Tampilkan Kos");
                System.out.println("  2. 📅 Booking Kos");
                System.out.println("  3. ⬅️ Kembali ke Menu Kota");

                // Footer prompt
                System.out.print("\n🌟 Pilih menu: ");

        }

        /**
         * Menampilkan daftar kos yang tersedia di kota yang dipilih oleh
         * pengguna. Jika belum ada kos yang tersedia, maka akan ditampilkan
         * pesan bahwa belum ada kos yang tersedia di kota tersebut.
         * 
         * @param kotaTerpilih Nama kota yang dipilih oleh pengguna
         */
        public static void tampilkanDaftarKos(String kotaTerpilih) {
                List<Kos> daftarKos = daftarKosPerKota.get(kotaTerpilih);
                if (daftarKos.isEmpty()) {
                        System.out.println(
                                        "\n╔═════════════════════════════════════════════════════════════════════════════════════╗");
                        System.out.printf("║ %-84s ║\n",
                                        "  ⚠️  Belum ada kos yang tersedia di "
                                                        + kotaTerpilih + ". ");
                        System.out.println(
                                        "╚═════════════════════════════════════════════════════════════════════════════════════╝");
                } else {
                        for (Kos kos : daftarKos) {
                                kos.tampilkanKos();
                        }
                }
        }

        /**
         * Menambahkan kos baru ke daftar kos yang tersedia di kota yang
         * dipilih.
         * 
         * @param kotaTerpilih Nama kota yang dipilih pengguna
         * @param scanner      Scanner yang digunakan untuk menerima input dari
         *                     pengguna
         */
        public static void tambahKos(String kotaTerpilih, Scanner scanner) {
                System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
                System.out.printf("            ✨ Tambah Kos Baru - Kota: %s ✨              \n", kotaTerpilih);
                System.out.println("╚════════════════════════════════════════════════════════════════╝");

                // Output nama kos dan hasil input
                System.out.print("🏠 Masukkan Nama Kos: ");
                String nama = scanner.nextLine();

                // Input harga kos dengan validasi
                Double harga = null;
                while (harga == null) {
                        try {
                                System.out.print("💰 Masukkan Harga Kos: ");
                                harga = scanner.nextDouble(); // Mencoba membaca input harga
                                scanner.nextLine();
                        } catch (Exception e) {
                                System.out.println("❌ Input tidak valid. Harap masukkan harga yang sesuai.");
                                scanner.nextLine();
                        }
                }

                // Input fasilitas kos
                List<String> fasilitas = new ArrayList<>();
                System.out.println("\n🔧 Masukkan Fasilitas (ketik 'selesai' untuk selesai):");

                // Perulangan menggunakan while untuk memasukan fasilitas jika input bukan
                // "selesai" maka perulangan tidak akan berhenti
                while (true) {
                        System.out.print("➤ ");
                        String input = scanner.nextLine();
                        if (input.equalsIgnoreCase("selesai")) {
                                break;
                        }
                        fasilitas.add(input);
                }

                // Tambah kos ke daftar
                daftarKosPerKota.get(kotaTerpilih).add(new Kos(nama, harga, fasilitas));

                // Konfirmasi penambahan kos
                System.out.println("\n✅ Kos berhasil ditambahkan!");
                System.out.println("╔════════════════════════════════════════════════════════════════╗");
                System.out.println("║              🎉 Detail Kos yang Ditambahkan 🎉                 ║");
                System.out.println("╚════════════════════════════════════════════════════════════════╝");
                System.out.printf(" 🏠 Nama: %-50s \n", nama);
                System.out.printf(" 💰 Harga: %-50s \n", formatRupiah(harga));
                System.out.println(" 🛠️  Fasilitas:");
                for (String fasilitasKos : fasilitas) {
                        System.out.printf("   - %-50s \n", fasilitasKos);
                }

        }

        public static void bookingKos(String kotaTerpilih, Scanner scanner, String nama, String role) {
                List<Kos> daftarKos = daftarKosPerKota.get(kotaTerpilih);

                // Daftar kode promo yang valid
                String[] kodePromoValid = { "DISKON20", "PROMO2024", "HEMATKOS" };

                System.out.println("\n╔══════════════════════════════════════════════════════╗");
                System.out.println("                📋 Booking Kos di " + kotaTerpilih + "               ");
                System.out.println("╚══════════════════════════════════════════════════════╝");

                if (daftarKos.isEmpty()) {
                        System.out.println("⚠️  Belum ada kos yang bisa dibooking di " + kotaTerpilih + ".");
                        return;
                }

                if (role == "admin") {
                        System.out.print("👤 Masukkan nama pembooking: ");
                        nama = scanner.nextLine().trim();
                        if (nama.isBlank()) {
                                System.out.println(
                                                "❌ Nama pembooking tidak boleh kosong. Harap masukkan nama yang valid.");
                                return;
                        }
                }

                // Input alamat asli sesuai KTP
                String alamatRumah = inputAlamatRumah(scanner);

                System.out.println("\nPilih kos yang ingin Anda booking:");
                // Menampilkan daftar kos
                for (int i = 0; i < daftarKos.size(); i++) {
                        System.out.printf(" %2d. %s\n", (i + 1), daftarKos.get(i).nama);
                }

                int pilihanKos = -1; // Inisialisasi dengan nilai tidak valid
                while (true) {
                        try {
                                System.out.print("\nMasukkan nomor kos yang ingin dibooking: ");
                                pilihanKos = scanner.nextInt(); // Mencoba membaca input angka
                                scanner.nextLine();
                                // Validasi pilihan kos
                                if (pilihanKos < 1 || pilihanKos > daftarKos.size()) {
                                        System.out.println(
                                                        "❌ Pilihan tidak valid. Masukkan nomor yang sesuai dengan daftar.");
                                } else {
                                        break; // Keluar dari loop jika input valid
                                }
                        } catch (Exception e) {
                                System.out.println("❌ Input tidak valid. Harap masukkan angka.");
                                scanner.nextLine(); // Bersihkan buffer untuk menghindari infinite loop
                        }
                }

                // Booking kos setelah validasi
                Kos kosTerpilih = daftarKos.get(pilihanKos - 1);

                // Tanya pengguna apakah ada kode promo
                System.out.print("\nApakah Anda memiliki kode promo? (y/n): ");
                String memilikiKodePromo = scanner.nextLine().trim().toLowerCase();

                double hargaSetelahDiskon = kosTerpilih.harga;

                if (memilikiKodePromo.equals("y")) {
                        hargaSetelahDiskon = prosesKodePromo(scanner, kodePromoValid, hargaSetelahDiskon);
                }

                // Menambahkan menu pilihan metode pembayaran
                System.out.println("\n╔══════════════════════════════════════════════════════╗");
                System.out.println("                💳 Pilih Metode Pembayaran              ");
                System.out.println("╚══════════════════════════════════════════════════════╝");
                System.out.println("1.  Cash 💸");
                System.out.println("2️. Transfer 🏧");

                int metodePembayaran = -1;

                while (true) {
                        try {
                                System.out.print("\nMasukkan pilihan metode pembayaran (1/2): ");
                                metodePembayaran = scanner.nextInt();
                                scanner.nextLine();
                                if (metodePembayaran == 1 || metodePembayaran == 2) {
                                        // Menampilkan konfirmasi berdasarkan pilihan pengguna
                                        String metode = (metodePembayaran == 1) ? "Cash 💸" : "Transfer 🏧";
                                        System.out.println("\n✅ Anda memilih metode pembayaran: " + metode);
                                        break;
                                } else {
                                        System.out.println("❌ Pilihan tidak valid. Masukkan angka 1 atau 2.");
                                }
                        } catch (Exception e) {
                                System.out.println("❌ Input tidak valid. Harap masukkan angka.");
                                scanner.nextLine();
                        }
                }

                // Deklarasi variabel daftar bank dan nomor rekening di luar blok
                String[] daftarBank = { "Bank BCA", "Bank Mandiri", "Bank BRI", "Bank BNI" };
                String[] noRekening = { "123-456-7890", "987-654-3210", "567-890-1234", "432-109-8765" };
                int pilihanBank = -1;

                if (metodePembayaran == 2) {
                        System.out.println("\n╔══════════════════════════════════════════════════════╗");
                        System.out.println("                🏦 Pilih Bank untuk Transfer            ");
                        System.out.println("╚══════════════════════════════════════════════════════╝");

                        // Menampilkan daftar bank
                        for (int i = 0; i < daftarBank.length; i++) {
                                System.out.printf("%d️.  %s - No. Rekening: %s\n", (i + 1), daftarBank[i],
                                                noRekening[i]);
                        }

                        while (true) {
                                try {
                                        System.out.print("\nMasukkan pilihan bank (1-" + daftarBank.length + "): ");
                                        pilihanBank = scanner.nextInt();
                                        scanner.nextLine();
                                        if (pilihanBank >= 1 && pilihanBank <= daftarBank.length) {
                                                System.out.println("\n✅ Anda memilih " + daftarBank[pilihanBank - 1] +
                                                                " dengan No. Rekening: " + noRekening[pilihanBank - 1]);
                                                break;
                                        } else {
                                                System.out.println(
                                                                "❌ Pilihan tidak valid. Masukkan angka sesuai dengan daftar.");
                                        }
                                } catch (Exception e) {
                                        System.out.println("❌ Input tidak valid. Harap masukkan angka.");
                                        scanner.nextLine();
                                }
                        }
                }

                // Konfirmasi detail pembayaran
                String metodePembayaranStr = (metodePembayaran == 1) ? "Cash" : "Transfer";
                System.out.println("\n✅ Anda berhasil membooking kos berikut:");
                System.out.println("\n╔════════════════════════════════════════════╗");
                System.out.println("║             Detail Kos yang Dipesan        ║");
                System.out.println("╚════════════════════════════════════════════╝");
                System.out.printf("👤 Nama         : %s\n", nama);
                System.out.printf("🏠 Alamat Rumah : %s\n", alamatRumah);
                System.out.printf("📌 Nama Kos     : %s\n", kosTerpilih.nama);
                System.out.printf("💲 Harga Kos    : %s\n", formatRupiah(hargaSetelahDiskon));
                System.out.printf("🔹 Metode Bayar : %s\n", metodePembayaranStr);
                // Menambahkan detail rekening jika metode pembayaran adalah transfer
                if (metodePembayaran == 2) {
                        System.out.printf("🏦 Bank         : %s\n", daftarBank[pilihanBank - 1]);
                        System.out.printf("🔢 No. Rekening : %s\n", noRekening[pilihanBank - 1]);
                }
                System.out.print("🔹 Fasilitas    : ");
                System.out.println(String.join(", ", kosTerpilih.fasilitas));

                System.out.println("\n--------------------------------------------");

                // Menghapus kos dari daftar setelah dibooking
                daftarKos.remove(kosTerpilih);
                System.out.println("🙏 Terima kasih sudah menggunakan layanan kami!");
                System.out.println("   Semoga Anda mendapatkan kos yang sesuai.");
        }

        private static String inputAlamatRumah(Scanner scanner) {
                System.out.println("\nSebelum melanjutkan, masukkan alamat rumah Anda sesuai dengan KTP:");
                String alamatRumah = "";
                while (alamatRumah.isBlank()) {
                        System.out.print("📝 Alamat Rumah (sesuai KTP): ");
                        alamatRumah = scanner.nextLine().trim();
                        if (alamatRumah.isBlank()) {
                                System.out.println("❌ Alamat tidak boleh kosong. Harap masukkan alamat yang valid.");
                        }
                }
                System.out.println("\n✅ Alamat berhasil disimpan: " + alamatRumah);
                return alamatRumah;
        }

        public static double prosesKodePromo(Scanner scanner, String[] kodePromoValid, double hargaSaatIni) {
                System.out.print("Masukkan kode promo: ");
                String kodePromo = scanner.nextLine().trim();

                // Periksa apakah kode promo valid
                boolean isValidKodePromo = Arrays.asList(kodePromoValid).contains(kodePromo);

                // Jika valid, hitung diskon
                double hargaSetelahDiskon = isValidKodePromo ? hitungDiskon(hargaSaatIni, 20) : hargaSaatIni;

                // Tampilkan pesan kepada pengguna
                System.out.println(isValidKodePromo
                                ? "✅ Kode promo berhasil digunakan! Anda mendapatkan diskon 20%."
                                : "❌ Kode promo tidak valid. Lanjutkan tanpa diskon.");

                return hargaSetelahDiskon;
        }

        public static double hitungDiskon(double harga, double diskonPersen) {
                double diskon = harga * (diskonPersen / 100); // Menggunakan operator aritmatika
                harga -= diskon; // Menggunakan operator penugasan
                return harga;
        }

        /**
         * Menampilkan daftar kos di kota yang dipilih dan meminta pengguna memilih
         * nomor kos yang ingin dimanipulasi. Setelah itu, program ini akan meminta
         * input jumlah karakter yang ingin dihapus dari belakang nama kos yang
         * dipilih. Jika input valid, maka program ini akan menghapus karakter
         * tersebut dan menampilkan nama kos yang sudah diubah.
         *
         * @param scanner      Scanner yang digunakan untuk menerima input dari
         *                     pengguna.
         * @param kotaTerpilih Nama kota yang dipilih oleh pengguna.
         */
        public static void manipulasiSubstring(Scanner scanner, String kotaTerpilih) {
                List<Kos> daftarKos = daftarKosPerKota.get(kotaTerpilih);

                if (daftarKos.isEmpty()) {
                        System.out.println("🚫 Tidak ada kos di kota ini untuk dimanipulasi.");
                        return;
                }

                System.out.println("╔════════════════════════════════════════════════════╗");
                System.out.println("║           ✂️ Pilih Kos untuk Dimanipulasi ✂️         ║");
                System.out.println("╚════════════════════════════════════════════════════╝");
                for (int i = 0; i < daftarKos.size(); i++) {
                        System.out.printf(" %d. 🏠 %s\n", (i + 1), daftarKos.get(i).nama);
                }

                int pilihan = -1;
                while (true) {
                        try {
                                System.out.print("\n👉 Pilih nomor kos: ");
                                pilihan = scanner.nextInt();
                                scanner.nextLine(); // Membersihkan buffer
                                if (pilihan < 1 || pilihan > daftarKos.size()) {
                                        System.out.println("⚠️ Pilihan tidak valid. Silakan coba lagi.");
                                } else {
                                        break; // Keluar dari loop jika input valid
                                }
                        } catch (InputMismatchException e) {
                                System.out.println("⚠️ Input harus berupa angka. Silakan coba lagi.");
                                scanner.nextLine(); // Membersihkan buffer
                        }
                }

                Kos kosTerpilih = daftarKos.get(pilihan - 1);

                int jumlahKarakter = -1;
                while (true) {
                        try {
                                System.out.print("\n✍️ Masukkan jumlah karakter yang akan dihapus dari belakang: ");
                                jumlahKarakter = scanner.nextInt();
                                scanner.nextLine(); // Membersihkan buffer
                                if (jumlahKarakter >= kosTerpilih.nama.length()) {
                                        System.out.println(
                                                        "⚠️ Jumlah karakter yang akan dihapus terlalu banyak! Silakan coba lagi.");
                                } else {
                                        break; // Keluar dari loop jika input valid
                                }
                        } catch (InputMismatchException e) {
                                System.out.println("⚠️ Input harus berupa angka. Silakan coba lagi.");
                                scanner.nextLine(); // Membersihkan buffer
                        }
                }

                // Memanipulasi nama kos untuk menghapus karakter dari belakang
                kosTerpilih.nama = kosTerpilih.nama.substring(0, kosTerpilih.nama.length() - jumlahKarakter);

                System.out.println("\n✅ Nama kos berhasil dimanipulasi!");
                System.out.println("╔════════════════════════════════════════════════╗");
                System.out.printf(" Nama kos baru: %-30s \n", kosTerpilih.nama);
                System.out.println("╚════════════════════════════════════════════════╝");

                System.out.println("\n✨ Data kos berhasil diperbarui!");
        }

        /**
         * Mencari kos yang namanya mengandung substring yang diinput pengguna.
         * Program ini akan menampilkan daftar kos di kota yang dipilih, lalu
         * meminta pengguna untuk menginput nama atau sebagian nama kos yang
         * diinginkan. Program kemudian akan menampilkan semua kos yang namanya
         * mengandung input tersebut.
         *
         * @param scanner      Scanner yang digunakan untuk menerima input dari
         *                     pengguna.
         * @param kotaTerpilih Nama kota yang dipilih oleh pengguna.
         */
        public static void manipulasiContains(Scanner scanner, String kotaTerpilih) {
                List<Kos> daftarKos = daftarKosPerKota.get(kotaTerpilih);

                if (daftarKos.isEmpty()) {
                        System.out.println("🚫 Tidak ada kos di kota ini.");
                        return;
                }

                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.println("║        🔍 Cari Nama Kos yang Diinginkan      ║");
                System.out.println("╚══════════════════════════════════════════════╝");
                System.out.print("\n✍️ Masukkan nama atau sebagian nama kos: ");
                String inputKos = scanner.nextLine(); // Input dari pengguna

                boolean ditemukan = false;
                System.out.println("\n📋 Kos yang mengandung \"" + inputKos + "\":");

                System.out.println("╔══════════════════════════════════════════════╗");
                for (Kos kos : daftarKos) {
                        // Mengecek apakah nama kos mengandung inputKos
                        if (kos.nama.contains(inputKos)) {
                                ditemukan = true;
                                System.out.printf(" 🏠 %-40s \n", kos.nama); // Menampilkan kos yang mengandung input
                        }
                }
                System.out.println("╚══════════════════════════════════════════════╝");

                if (!ditemukan) {
                        System.out.println("🚫 Tidak ada kos yang mengandung nama tersebut.");
                } else {
                        System.out.println("✅ Pencarian selesai!");
                }
        }

        /**
         * Menampilkan menu untuk mengubah nama kos menjadi Lowercase atau Uppercase.
         * 
         * @param scanner      Scanner yang digunakan untuk menerima input dari
         *                     pengguna.
         * @param kotaTerpilih Nama kota yang dipilih oleh pengguna.
         * @param toLowerCase  true jika ingin mengubah ke Lowercase, false jika ingin
         *                     mengubah ke Uppercase.
         */
        public static void manipulasiLoworUp(Scanner scanner, String kotaTerpilih, boolean toLowerCase) {
                List<Kos> daftarKos = daftarKosPerKota.get(kotaTerpilih);

                if (daftarKos.isEmpty()) {
                        System.out.println("🚫 Tidak ada kos di kota ini.");
                        return;
                }

                String operasi = toLowerCase ? "Lowercase" : "Uppercase";
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.println("║        🔡 Ubah Nama Kos Menjadi " + operasi + "              ║");
                System.out.println("╚════════════════════════════════════════════════════════╝");
                System.out.println("\n📋 Pilih kos yang ingin diubah menjadi " + operasi + ":");

                // Menampilkan daftar kos
                System.out.println("╔════════════════════════════════════════════════════════╗");
                for (int i = 0; i < daftarKos.size(); i++) {
                        System.out.printf("║ %2d. %-50s ║\n", (i + 1), daftarKos.get(i).nama);
                }
                System.out.println("╚════════════════════════════════════════════════════════╝");

                int pilihan = -1;

                while (true) {
                        try {
                                System.out.print("\n✍️ Pilih nomor kos: ");
                                pilihan = scanner.nextInt();
                                scanner.nextLine(); // Membersihkan buffer

                                // Jika pilihan di luar rentang valid, kembali ke menu utama
                                if (pilihan < 1 || pilihan > daftarKos.size()) {
                                        System.out.println("🚫 Pilihan tidak valid. Kembali ke menu utama.");
                                        return;
                                }
                                break; // Keluar dari loop jika input valid
                        } catch (InputMismatchException e) {
                                System.out.println("⚠️ Input harus berupa angka. Silakan coba lagi.");
                                scanner.nextLine(); // Membersihkan buffer
                        }
                }

                Kos kosTerpilih = daftarKos.get(pilihan - 1);

                // Mengubah nama kos berdasarkan operasi yang dipilih (Lowercase atau Uppercase)
                if (toLowerCase) {
                        kosTerpilih.nama = kosTerpilih.nama.toLowerCase();
                } else {
                        kosTerpilih.nama = kosTerpilih.nama.toUpperCase();
                }

                // Menampilkan hasil perubahan
                System.out.println("\n✅ Nama kos berhasil diubah menjadi " + operasi + ":");
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.printf("║ 🏠 Nama kos sekarang: %-32s ║\n", kosTerpilih.nama);
                System.out.println("╚════════════════════════════════════════════════════════╝");
        }

        /**
         * Manipulasi nama kos dengan mengganti substring yang sesuai dengan input
         * pengguna. Program ini akan menampilkan daftar kos di kota yang dipilih,
         * lalu meminta pengguna untuk memilih nomor kos yang ingin diubah. Setelah
         * itu, program ini akan meminta input substring yang ingin diganti dan
         * penggantiannya. Jika bagian nama kos yang dimaksud ditemukan, maka
         * program ini akan mengganti bagian tersebut dengan penggantiannya dan
         * menampilkan hasilnya.
         *
         * @param scanner      Scanner yang digunakan untuk menerima input dari
         *                     pengguna.
         * @param kotaTerpilih Nama kota yang dipilih oleh pengguna.
         */
        public static void manipulasiReplace(Scanner scanner, String kotaTerpilih) {
                List<Kos> daftarKos = daftarKosPerKota.get(kotaTerpilih);

                if (daftarKos.isEmpty()) {
                        System.out.println("🚫 Tidak ada kos di kota ini.");
                        return;
                }

                // Menampilkan Header dengan Border
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.println("║   🔄 Manipulasi Nama Kos (Replace) di Kota: " + kotaTerpilih + "   ║");
                System.out.println("╚════════════════════════════════════════════════════════╝");

                // Menampilkan daftar kos
                System.out.println("\n📋 Pilih kos yang ingin dimanipulasi:");
                System.out.println("╔════════════════════════════════════════════════════════╗");
                for (int i = 0; i < daftarKos.size(); i++) {
                        System.out.printf("║ %2d. %-50s ║\n", (i + 1), daftarKos.get(i).nama);
                }
                System.out.println("╚════════════════════════════════════════════════════════╝");

                int pilihan = -1;

                // Loop untuk validasi input
                while (true) {
                        try {
                                System.out.print("\n✍️ Pilih nomor kos yang ingin diubah: ");
                                pilihan = scanner.nextInt();
                                scanner.nextLine(); // Membersihkan buffer

                                if (pilihan < 1 || pilihan > daftarKos.size()) {
                                        System.out.println("🚫 Pilihan tidak valid. Kembali ke menu utama.");
                                        return;
                                }
                                break; // Keluar dari loop jika input valid
                        } catch (InputMismatchException e) {
                                System.out.println("⚠️ Input harus berupa angka. Silakan coba lagi.");
                                scanner.nextLine(); // Membersihkan buffer
                        }
                }

                Kos kosTerpilih = daftarKos.get(pilihan - 1);

                // Meminta input substring yang ingin diganti dan penggantiannya
                System.out.print("\n🔍 Masukkan bagian nama kos yang ingin diganti: ");
                String oldSubstring = scanner.nextLine();
                System.out.print("🔄 Masukkan pengganti nama kos: ");
                String newSubstring = scanner.nextLine();

                // Mengganti bagian nama kos yang sesuai dengan input
                if (kosTerpilih.nama.contains(oldSubstring)) {
                        kosTerpilih.nama = kosTerpilih.nama.replace(oldSubstring, newSubstring);

                        // Menampilkan hasil perubahan
                        System.out.println("\n✅ Nama kos berhasil diubah menjadi:");
                        System.out.println("╔════════════════════════════════════════════════════════╗");
                        System.out.printf("║ 🏠 Nama kos sekarang: %-32s ║\n", kosTerpilih.nama);
                        System.out.println("╚════════════════════════════════════════════════════════╝");
                } else {
                        System.out.println("❌ Bagian nama kos yang dimaksud tidak ditemukan.");
                        System.out.println("Kembali ke menu utama.");
                }
        }

        /**
         * Menampilkan jumlah karakter dalam nama kos di kota yang dipilih.
         * Program ini akan menampilkan daftar kos yang ada di kota yang dipilih,
         * lalu meminta pengguna untuk memilih nomor kos yang ingin dihitung jumlah
         * karakternya. Setelah itu, program ini akan menghitung jumlah karakter nama
         * kos yang dipilih dan menampilkan hasilnya.
         *
         * @param scanner      Scanner yang digunakan untuk menerima input dari
         *                     pengguna.
         * @param kotaTerpilih Nama kota yang dipilih oleh pengguna.
         */
        public static void manipulasiLength(Scanner scanner, String kotaTerpilih) {
                List<Kos> daftarKos = daftarKosPerKota.get(kotaTerpilih);

                if (daftarKos.isEmpty()) {
                        System.out.println("🚫 Tidak ada kos di kota ini.");
                        return;
                }

                // Menampilkan Header dengan Border
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.println("║  📏 Hitung Jumlah Karakter Nama Kos di Kota: " + kotaTerpilih + "  ║");
                System.out.println("╚════════════════════════════════════════════════════════╝");

                // Menampilkan daftar kos
                System.out.println("\n📋 Pilih kos yang ingin dihitung jumlah karakternya:");
                System.out.println("╔════════════════════════════════════════════════════════╗");
                for (int i = 0; i < daftarKos.size(); i++) {
                        System.out.printf("║ %2d. %-50s ║\n", (i + 1), daftarKos.get(i).nama);
                }
                System.out.println("╚════════════════════════════════════════════════════════╝");

                int pilihan = -1;

                // Loop untuk validasi input
                while (true) {
                        try {
                                System.out.print("\n✍️ Pilih nomor kos yang ingin dihitung jumlah karakternya: ");
                                pilihan = scanner.nextInt();
                                scanner.nextLine(); // Membersihkan buffer

                                if (pilihan < 1 || pilihan > daftarKos.size()) {
                                        System.out.println("🚫 Pilihan tidak valid. Kembali ke menu utama.");
                                        return;
                                }
                                break; // Keluar dari loop jika input valid
                        } catch (InputMismatchException e) {
                                System.out.println("⚠️ Input harus berupa angka. Silakan coba lagi.");
                                scanner.nextLine(); // Membersihkan buffer
                        }
                }

                Kos kosTerpilih = daftarKos.get(pilihan - 1);

                // Menghitung jumlah karakter nama kos
                int panjangNamaKos = kosTerpilih.nama.length();

                // Menampilkan hasil perhitungan
                System.out.println("\n✅ Jumlah karakter dalam nama kos \"" + kosTerpilih.nama + "\" adalah: "
                                + panjangNamaKos);

                // Tampilan hasil dengan border
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.printf("║ 📏 Nama kos: %-41s ║\n", kosTerpilih.nama);
                System.out.printf("║ 🔢 Jumlah karakter: %-34d ║\n", panjangNamaKos);
                System.out.println("╚════════════════════════════════════════════════════════╝");
        }

        /**
         * Menampilkan header dengan border dan kembali ke menu kota.
         */
        static void KembaliMenuKota() {
                // Menampilkan header dengan border
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.println("║   🔙 Kembali ke Menu Kota                              ║");
                System.out.println("╚════════════════════════════════════════════════════════╝");
        }

        /**
         * Mengembalikan string yang terformat sebagai nilai mata uang ruiah.
         * 
         * Contoh: 1000000.5 -> "Rp. 1.000.000,50"
         * 
         * @param harga nilai yang ingin di-format
         * @return string yang sudah terformat
         */
        public static String formatRupiah(double harga) {
                String currency = "Rp.";
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                DecimalFormat formatter = new DecimalFormat("#,##0.00", symbols);
                return currency + " " + formatter.format(harga);

        }

        // Ini function untuk menuUtama
        private static boolean menuUtama(Scanner scanner, String kotaTerpilih, String role, String nama) {
                // Ini percabangan switch case di gunakan untuk mengecek dia sebagai role admin
                // atau user
                switch (role) {
                        case "admin":
                                while (true) {
                                        tampilkanMenuUtamaAdmin(kotaTerpilih);
                                        int pilihanMenu = bacaPilihan(scanner, "Masukkan nomor menu: ");

                                        if (pilihanMenu == 10) { // Pilihan untuk kembali ke menu kota
                                                System.out.println("\n🔙 Kembali ke menu kota...");
                                                return true; // Kembali ke menu kota
                                        }

                                        prosesPilihanMenuAdmin(scanner, kotaTerpilih, pilihanMenu, nama, role); // Proses
                                                                                                                // pilihan
                                                                                                                // admin
                                }
                        case "user":
                                while (true) {
                                        tampilkanMenuUtamaUser(kotaTerpilih);
                                        int pilihanMenu = bacaPilihan(scanner, "Masukkan nomor menu: ");

                                        if (pilihanMenu == 3) { // Pilihan untuk kembali ke menu kota
                                                System.out.println("\n🔙 Kembali ke menu kota...");
                                                return true; // Kembali ke menu kota
                                        }

                                        prosesPilihanMenuUser(scanner, kotaTerpilih, pilihanMenu, nama, role); // Proses
                                                                                                               // pilihan
                                                                                                               // user
                                }

                        default:
                                System.out.println("\n⚠️ Role tidak valid. Kembali ke menu utama.");
                                return true; // Tetap kembali jika role tidak dikenali
                }
        }

        private static int bacaPilihan(Scanner scanner, String prompt) {
                while (true) {
                        try {
                                System.out.print(prompt);
                                return scanner.nextInt();
                        } catch (Exception e) {
                                System.out.println("Input tidak valid. Harap masukkan angka.");
                        } finally {
                                scanner.nextLine(); // Bersihkan buffer
                        }
                }
        }

        private static void prosesPilihanMenuAdmin(Scanner scanner, String kotaTerpilih, int pilihanMenu, String nama,
                        String role) {
                switch (pilihanMenu) {
                        case 1 -> tampilkanDaftarKos(kotaTerpilih);
                        case 2 -> tambahKos(kotaTerpilih, scanner);
                        case 3 -> bookingKos(kotaTerpilih, scanner, nama, role);
                        case 4 -> manipulasiSubstring(scanner, kotaTerpilih);
                        case 5 -> manipulasiContains(scanner, kotaTerpilih);
                        case 6 -> manipulasiLoworUp(scanner, kotaTerpilih, true);
                        case 7 -> manipulasiLoworUp(scanner, kotaTerpilih, false);
                        case 8 -> manipulasiReplace(scanner, kotaTerpilih);
                        case 9 -> manipulasiLength(scanner, kotaTerpilih);
                        case 10 -> KembaliMenuKota();
                        default -> System.out.println("Pilihan menu tidak valid.");
                }
        }

        private static void prosesPilihanMenuUser(Scanner scanner, String kotaTerpilih, int pilihanMenu, String nama,
                        String role) {
                switch (pilihanMenu) {
                        case 1 -> tampilkanDaftarKos(kotaTerpilih);
                        case 2 -> bookingKos(kotaTerpilih, scanner, nama, role);
                        case 3 -> KembaliMenuKota();
                        default -> System.out.println("Pilihan menu tidak valid.");
                }
        }

        // private static void prosesPilihanMenuUser(Scanner scanner, String
        // kotaTerpilih, int pilihanMenu) {
        // switch (pilihanMenu) {
        // case 1 -> tampilkanDaftarKos(kotaTerpilih);
        // case 2 -> tambahKos(kotaTerpilih, scanner);
        // case 3 -> bookingKos(kotaTerpilih, scanner);
        // case 4 -> manipulasiSubstring(scanner, kotaTerpilih);
        // case 5 -> manipulasiContains(scanner, kotaTerpilih);
        // case 6 -> manipulasiLoworUp(scanner, kotaTerpilih, true);
        // case 7 -> manipulasiLoworUp(scanner, kotaTerpilih, false);
        // case 8 -> manipulasiReplace(scanner, kotaTerpilih);
        // case 9 -> manipulasiLength(scanner, kotaTerpilih);
        // case 10 -> KembaliMenuKota();
        // default -> System.out.println("Pilihan menu tidak valid.");
        // }
        // }

        private static int pilihKota(Scanner scanner) {
                while (true) {
                        int pilihanKota = bacaInputAngka(scanner, "Masukkan nomor kota: ");

                        if (pilihanKota == 0) {
                                return 0; // Indikasi untuk keluar dari aplikasi
                        }

                        if (pilihanKota < 1 || pilihanKota > kotaKos.length) {
                                System.out.println("Pilihan kota tidak valid. Silakan coba lagi.");
                                continue;
                        }

                        return pilihanKota; // Kembalikan pilihan kota yang valid
                }
        }

        private static int bacaInputAngka(Scanner scanner, String pesan) {
                while (true) {
                        try {
                                System.out.print(pesan);
                                int input = scanner.nextInt();
                                scanner.nextLine(); // Membersihkan buffer
                                return input;
                        } catch (Exception e) {
                                System.out.println("Input tidak valid. Harap masukkan angka.");
                                scanner.nextLine(); // Bersihkan buffer untuk menghindari infinite loop
                        }
                }
        }

        /**
         * Poin masuk program.
         * 
         * Program akan menampilkan menu kota dan setelah memilih kota akan menampilkan
         * menu utama. Setelah memilih menu utama maka program akan menampilkan hasil
         * yang sesuai dengan pilihan menu. Setelah selesai maka program akan kembali ke
         * menu kota.
         * 
         * Jika pilihan menu kota adalah 0 maka program akan berhenti.
         */
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                inisialisasiData();

                while (!isLoggedIn) {
                        tampilkanMenuLogin(scanner);
                }

                while (true) {
                        tampilkanMenuKota();
                        int pilihanKota = pilihKota(scanner);

                        if (pilihanKota == 0) {
                                tampilAkhir();
                                break;
                        }

                        String kotaTerpilih = kotaKos[pilihanKota - 1];

                        boolean kembaliKeMenuKota = menuUtama(scanner, kotaTerpilih, role, nama);

                        if (kembaliKeMenuKota) {
                                // Jika pengguna memilih untuk kembali, ulangi dari menu kota
                                continue;
                        }

                }
                scanner.close();
        }
}
