package UAS;

public class TransaksiRental {
    private static int kodeTransaksiCounter = 1;
    private int kodeTransaksi;
    private String namaPeminjam;
    private int lamaPinjam;
    private double totalBiaya;
    private BarangRental barangRental;

    public TransaksiRental(String namaPeminjam, int lamaPinjam, BarangRental barangRental) {
        this.kodeTransaksi = kodeTransaksiCounter++;
        this.namaPeminjam = namaPeminjam;
        this.lamaPinjam = lamaPinjam;
        this.barangRental = barangRental;
    }

    public int getKodeTransaksi() {
        return kodeTransaksi;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public int getLamaPinjam() {
        return lamaPinjam;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(double totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public BarangRental getBarangRental() {
        return barangRental;
    }
}
