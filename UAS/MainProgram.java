package UAS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainProgram {
    private static List<BarangRental> listBarang = new ArrayList<>();
    private static List<TransaksiRental> listTransaksi = new ArrayList<>();
    private static int kodeTransaksi_counter = 1;

    public static void main(String[] args) {
        initializeData();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--- Menu Rental Kendaraan Bima ---");
            System.out.println("1. Daftar Kendaraan");
            System.out.println("2. Peminjaman");
            System.out.println("3. Tampilkan seluruh transaksi");
            System.out.println("4. Urutkan Transaksi urut no TNKB");
            System.out.println("5. Keluar");

            System.out.print("Pilih(1-5): ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    displayDaftarKendaraan();
                    break;
                case "2":
                    prosesPeminjaman(scanner);
                    break;
                case "3":
                    tampilkanSeluruhTransaksi();
                    break;
                case "4":
                    urutkanTransaksiUrutNoTNKB();
                    break;
                case "5":
                    System.out.println("Terima kasih telah menggunakan layanan Rental Kendaraan Bima.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }
        }
    }

    private static void initializeData() {
        listBarang.add(new BarangRental("IS 4567 YV", "Honda Beat", "Motor", 2017, 10000));
        listBarang.add(new BarangRental("IN 4511 VS", "Honda Vario", "Motor", 2018, 10000));
        listBarang.add(new BarangRental("IN 1453 AA", "Toyota Yaris", "Mobil", 2022, 30000));
        listBarang.add(new BarangRental("AB 4321 A", "Toyota Innova", "Mobil", 2019, 60000));
        listBarang.add(new BarangRental("B 1234 AG", "Toyota Avanza", "Mobil", 2021, 25000));
    }

    private static void displayDaftarKendaraan() {
        System.out.println("=====================================");
        System.out.println("Daftar Kendaraan Rental Serba Serbi");
        System.out.println("=====================================");
        System.out.printf("|%-12s| %-18s| %-10s| %-6s| %-12s|\n", "Nomor TNKB", "Nama Kendaraan", "Jenis", "Tahun", "Biaya Sewa");
        for (BarangRental barang : listBarang) {
            barang.display();
        }
    }

    private static void prosesPeminjaman(Scanner scanner) {
        displayDaftarKendaraan();
        System.out.println("=========================");
        System.out.println("Masukkan Data Peminjaman");
        System.out.println("=========================");
        
        System.out.print("Masukkan Nama Peminjam: ");
        String namaPeminjam = scanner.nextLine();
        
        System.out.print("Masukkan Nomor TNKB: ");
        String noTNKB = scanner.nextLine();
        
        System.out.print("Masukkan Lama Pinjam (jumlah hari): ");
        int lamaPinjam = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Apakah Member (ya/tidak): ");
        String memberStatus = scanner.nextLine().toLowerCase();
        boolean isMember = memberStatus.equals("ya");
        
        // Cari barang rental berdasarkan nomor TNKB
        BarangRental barangRental = null;
        for (BarangRental barang : listBarang) {
            if (barang.getNoTNKB().equalsIgnoreCase(noTNKB)) {
                barangRental = barang;
                break;
            }
        }
        
        if (barangRental != null) {
            double totalBiaya = lamaPinjam * barangRental.getBiayaSewa();
            if (isMember) {
                totalBiaya *= 0.9; // Member mendapatkan diskon 10%
            }
            
            // Tambahkan transaksi baru
            TransaksiRental transaksi = new TransaksiRental(namaPeminjam, lamaPinjam, barangRental);
            transaksi.setTotalBiaya(totalBiaya);
            listTransaksi.add(transaksi);
            
            // Output sesuai dengan format yang diminta
            System.out.println("| kode\t| Nama Peminjam\t| Lama Pinjam\t| Total Biaya\t| No TNKB\t| Nama Barang");
            System.out.printf("|  %d\t| %-15s\t| %-10d\t| %-12.0f\t| %-8s\t| %s\n",
                    transaksi.getKodeTransaksi(), transaksi.getNamaPeminjam(), transaksi.getLamaPinjam(),
                    totalBiaya, barangRental.getNoTNKB(), barangRental.getNamaKendaraan());
        } else {
            System.out.println("Nomor TNKB tidak valid.");
        }
    }

    private static void tampilkanSeluruhTransaksi() {
        System.out.println("==============================================");
        System.out.println("Daftar Transaksi Peminjaman Rental Serba Serbi");
        System.out.println("==============================================");
        if (listTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            System.out.println("| kode\t| No TNKB\t| Nama Barang\t| Nama Peminjam\t| Lama Pinjam\t| Total Biaya");
            double totalPendapatan = 0;
            for (TransaksiRental transaksi : listTransaksi) {
                System.out.printf("|  %d\t| %-10s\t| %-12s\t| %-15s\t| %-10d\t| %-12.0f\n",
                        transaksi.getKodeTransaksi(), transaksi.getBarangRental().getNoTNKB(),
                        transaksi.getBarangRental().getNamaKendaraan(), transaksi.getNamaPeminjam(),
                        transaksi.getLamaPinjam(), transaksi.getTotalBiaya());
                totalPendapatan += transaksi.getTotalBiaya();
            }
            System.out.println("==============================================");
            System.out.println("TOTAL PENDAPATAN RENTAL SERBA SERBI");
            System.out.println("==============================================");
            System.out.printf("Pendapatan hari ini :%.0f\n", totalPendapatan);
        }
    }

    private static void urutkanTransaksiUrutNoTNKB() {
        if (listTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            Collections.sort(listTransaksi, Comparator.comparing(t -> t.getBarangRental().getNoTNKB()));
            System.out.println("==============================================");
            System.out.println("Daftar Transaksi Peminjaman Rental Serba Serbi");
            System.out.println("==============================================");
            System.out.println("| kode\t| No TNKB\t| Nama Barang\t| Nama Peminjam\t| Lama Pinjam\t| Total Biaya");
            for (TransaksiRental transaksi : listTransaksi) {
                System.out.printf("|  %d\t| %-10s\t| %-12s\t| %-15s\t| %-10d\t| %-12.0f\n",
                        transaksi.getKodeTransaksi(), transaksi.getBarangRental().getNoTNKB(),
                        transaksi.getBarangRental().getNamaKendaraan(), transaksi.getNamaPeminjam(),
                        transaksi.getLamaPinjam(), transaksi.getTotalBiaya());
            }
        }
    }
}
