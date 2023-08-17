package com.example.aplikasipemesanan;

import java.util.Date;
import java.util.List;

public class Pesanan {

    // Variabel untuk menyimpan informasi pesanan
    private List<ItemMenu> items;
    private String menu;
    private int hargaMenu;
    private int quantity;
    private int totalHarga;
    private Date tanggal;
    private String metodePembayaran;

    // Konstruktor untuk inisialisasi pesanan dengan informasi diberikan
    public Pesanan(String itemName, int harga, int quantity, int totalHarga, Date tanggal) {
        this.menu = itemName;
        this.hargaMenu = harga;
        this.quantity = quantity;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
    }

    // Konstruktor untuk inisialisasi pesanan dengan daftar item
    public Pesanan(List<ItemMenu> items) {
        this.items = items;
        calculateTotalHarga();
    }

    // Method untuk menghitung total harga pesanan dari daftar item
    private void calculateTotalHarga() {
        totalHarga = 0;
        for (ItemMenu item : items) {
            totalHarga += item.getMenuHarga();
        }
    }

    // Getter untuk mendapatkan daftar item pesanan
    public List<ItemMenu> getItems() {
        return items;
    }

    // Getter untuk mendapatkan total harga pesanan
    public int getTotalHarga() {
        return totalHarga;
    }

    // Getter untuk mendapatkan nama menu pesanan
    public String getItemName() {
        return menu;
    }

    // Getter untuk mendapatkan total harga pesanan
    public int getTotalPrice() {
        return totalHarga;
    }

    // Getter untuk mendapatkan jumlah pesanan
    public int getQuantity() {
        return quantity;
    }

    // Getter untuk mendapatkan tanggal pesanan
    public Date getTanggal() {
        return tanggal;
    }

    // Setter untuk mengatur tanggal pesanan
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    // Getter untuk mendapatkan metode pembayaran pesanan
    public String getMetodePembayaran() {
        return metodePembayaran;
    }

}
