package UAS;

public class BarangRental {
    private String noTNKB;
    private String namaKendaraan;
    private String jenis;
    private int tahun;
    private double biayaSewa;

    public BarangRental(String noTNKB, String namaKendaraan, String jenis, int tahun, double biayaSewa) {
        this.noTNKB = noTNKB;
        this.namaKendaraan = namaKendaraan;
        this.jenis = jenis;
        this.tahun = tahun;
        this.biayaSewa = biayaSewa;
    }

    public String getNoTNKB() {
        return noTNKB;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public String getJenis() {
        return jenis;
    }

    public int getTahun() {
        return tahun;
    }

    public double getBiayaSewa() {
        return biayaSewa;
    }

    public void display() {
        System.out.printf("|%-12s| %-18s| %-10s| %-6d| %-12.0f|\n", noTNKB, namaKendaraan, jenis, tahun, biayaSewa);
    }
}
