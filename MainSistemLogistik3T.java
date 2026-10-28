
package Responsi1;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainSistemLogistik3T {
    public static void main(String[] args) {
        // Koleksi dinamis ArrayList<BarangBantuan> di kelas utama (Modul 4)
        try (Scanner scanner = new Scanner(System.in)) {
            // Koleksi dinamis ArrayList<BarangBantuan> di kelas utama (Modul 4)
            ArrayList<BarangBantuan> daftarBarang = new ArrayList<>();
            boolean berjalan = true;
            
            // Perulangan (while) untuk menu interaktif di konsol (Modul 2)
            while (berjalan) {
                System.out.println("\n=========================================");
                System.out.println("   SISTEM LOGISTIK BANTUAN DAERAH 3T     ");
                System.out.println("=========================================");
                System.out.println("1. Tambah Barang Bantuan");
                System.out.println("2. Selesai dan Cetak Manifes");
                System.out.print("Pilih menu (1/2): ");
                
                String pilihan = scanner.nextLine();
                
                switch (pilihan) {
                    case "1":
                        boolean inputValid = false;
                        // Perulangan untuk memastikan user mengulangi input jika terjadi error
                        while (!inputValid) {
                            // Penerapan Exception Handling (try-catch-finally) (Modul 3)
                            try {
                                System.out.print("Masukkan Kode Barang (contoh: EDX-001): ");
                                String kodeBarang = scanner.nextLine();
                                
                                // Validasi jika kodeBarang tidak diawali teks "EDX-" menggunakan .startsWith()
                                if (!kodeBarang.startsWith("EDX-")) {
                                    throw new IllegalArgumentException("Kode barang harus diawali EDX-");
                                }
                                
                                System.out.print("Masukkan Nama Barang: ");
                                String namaBarang = scanner.nextLine();
                                
                                System.out.print("Masukkan Kategori (contoh: Elektronik, Buku): ");
                                String kategori = scanner.nextLine();
                                
                                System.out.print("Masukkan Berat Barang (kg): ");
                                double beratKg = Double.parseDouble(scanner.nextLine());
                                
                                // Validasi jika berat barang <= 0, lempar BeratInvalidException
                                if (beratKg <= 0) {
                                    throw new BeratInvalidException("Berat barang tidak boleh bernilai 0 atau negatif!");
                                }
                                
                                // Jika validasi sukses, buat objek dan masukkan ke ArrayList
                                BarangBantuan barang = new BarangBantuan(kodeBarang, namaBarang, kategori, beratKg);
                                daftarBarang.add(barang);
                                System.out.println("-> Berhasil: Barang ditambahkan ke daftar muatan!");
                                inputValid = true; // Keluar dari loop input jika berhasil
                                
                            } catch (BeratInvalidException e) {
                                System.out.println("[ERROR BERAT INVALID]: " + e.getMessage());
                                System.out.println("Silakan masukkan ulang data barang dengan benar.\n");
                            } catch (IllegalArgumentException e) {
                                System.out.println("[ERROR KODE BARANG]: " + e.getMessage());
                                System.out.println("Silakan masukkan ulang data barang dengan benar.\n");
                            } finally {
                                System.out.println("(Siklus validasi input selesai diproses).\n");
                            }
                        }   break;
                    case "2":
                        if (daftarBarang.isEmpty()) {
                            System.out.println("Daftar barang masih kosong! Harap masukkan setidaknya satu barang terlebih dahulu.");
                            continue;
                        }   // Hitung total barang dan total berat muatan dari ArrayList (Modul 4 & 5)
                        int totalJenisBarang = daftarBarang.size();
                        double totalBerat = 0;
                        for (BarangBantuan b : daftarBarang) {
                            totalBerat += b.getBeratKg();
                        }   // Percabangan (if-else) untuk menentukan label armada (Modul 2)
                        String labelArmada;
                        if (totalBerat < 50) {
                            labelArmada = "Mobil Pickup";
                        } else {
                            labelArmada = "Truk Ekspedisi";
                        }   // File I/O (BufferedWriter & FileWriter) mencetak ke manifest_pengiriman.txt (Modul 5)
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("manifest_pengiriman.txt"))) {
                            writer.write("===================================================\n");
                            writer.write("          MANIFEST PENGIRIMAN LOGISTIK 3T          \n");
                            writer.write("===================================================\n");
                            writer.write(String.format("%-12s | %-18s | %-12s | %-10s\n", "Kode", "Nama Barang", "Kategori", "Berat (kg)"));
                            writer.write("---------------------------------------------------\n");
                            
                            for (BarangBantuan b : daftarBarang) {
                                writer.write(String.format("%-12s | %-18s | %-12s | %-10.2f\n",
                                        b.getKodeBarang(), b.getNamaBarang(), b.getKategori(), b.getBeratKg()));
                            }
                            
                            writer.write("---------------------------------------------------\n");
                            writer.write("Total Jenis Barang    : " + totalJenisBarang + "\n");
                            writer.write("Total Akumulasi Berat : " + totalBerat + " kg\n");
                            writer.write("Rekomendasi Armada    : " + labelArmada + "\n");
                            writer.write("===================================================\n");
                            
                            System.out.println("Data manifest berhasil diekspor ke manifest_pengiriman.txt");
                            berjalan = false; // Menghentikan perulangan menu utama
                            
                        } catch (IOException e) {
                            System.out.println("Terjadi kesalahan saat menyimpan file: " + e.getMessage());
                        }   break;
                    default:
                        System.out.println("Pilihan menu tidak valid. Masukkan angka 1 atau 2.");
                        break;
                }
            }
        }
    }
}
