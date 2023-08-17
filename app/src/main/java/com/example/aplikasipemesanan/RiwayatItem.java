package com.example.aplikasipemesanan;

// Kelas ini merepresentasikan item pada riwayat pemesanan
public class RiwayatItem {

    private String namaPesanan;             // Nama item pesanan
    private String totalHargaPesanan;       // Total harga pesanan
    private String tanggalOrder;            // Tanggal pemesanan
    private String metodePembayaranOrder;   // Metode pembayaran pemesanan

    // Konstruktor untuk menginisialisasi data pada item riwayat pemesanan
    public RiwayatItem(String itemPesanan, String totalHarga, String tanggal, String metodePembayaran) {
        this.namaPesanan = itemPesanan;
        this.totalHargaPesanan = totalHarga;
        this.tanggalOrder = tanggal;
        this.metodePembayaranOrder = metodePembayaran;
    }

    // Method untuk mengembalikan nama item pesanan
    public String getItemPesanan() {
        return namaPesanan;
    }

    // Method untuk mengembalikan total harga pesanan
    public String getTotalHarga() {
        return totalHargaPesanan;
    }

    // Method untuk mengembalikan tanggal pemesanan
    public String getTanggal() {
        return tanggalOrder;
    }

    // Method untuk mengembalikan metode pembayaran pemesanan
    public String getMetodePembayaran() {
        return metodePembayaranOrder;
    }
}
