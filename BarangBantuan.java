
package Responsi1;
public class BarangBantuan {
    // Atribut sesuai spesifikasi[cite: 3]
    private String kodeBarang;
    private String namaBarang;
    private String kategori; // Contoh: "Elektronik", "Buku"[cite: 3]
    private double beratKg;

    // Konstruktor untuk menginisialisasi seluruh atribut[cite: 3]
    public BarangBantuan(String kodeBarang, String namaBarang, String kategori, double beratKg) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.beratKg = beratKg;
    }

    // Getter untuk mengakses atribut
    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public double getBeratKg() {
        return beratKg;
    }

    // Setter (opsional jika ingin mengubah nilai atribut)
    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setBeratKg(double beratKg) {
        this.beratKg = beratKg;
    }
}
