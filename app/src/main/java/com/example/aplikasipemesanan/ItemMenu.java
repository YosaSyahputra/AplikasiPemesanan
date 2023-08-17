package com.example.aplikasipemesanan;

public class ItemMenu {

    // Variabel-variabel untuk menyimpan informasi menu
    private String menuNama;
    private String menuDeskripsi;
    private int menuHarga;
    private int menuGambar;

    // Getter untuk mendapatkan nama menu
    public String getMenuNama() {
        return menuNama;
    }

    // Setter untuk mengatur nama menu
    public void setMenuNama(String menuNama) {
        this.menuNama = menuNama;
    }

    // Getter untuk mendapatkan deskripsi menu
    public String getMenuDeskripsi() {
        return menuDeskripsi;
    }

    // Setter untuk mengatur deskripsi menu
    public void setMenuDeskripsi(String menuDeskripsi) {
        this.menuDeskripsi = menuDeskripsi;
    }

    // Getter untuk mendapatkan harga menu
    public int getMenuHarga() {
        return menuHarga;
    }

    // Setter untuk mengatur harga menu
    public void setMenuHarga(int menuHarga) {
        this.menuHarga = menuHarga;
    }

    // Getter untuk mendapatkan ID gambar menu
    public int getMenuGambar() {
        return menuGambar;
    }

    // Setter untuk mengatur ID gambar menu
    public void setMenuGambar(int menuGambar) {
        this.menuGambar = menuGambar;
    }

    // Constructor untuk membuat objek ItemMenu dengan informasi yang diberikan
    public ItemMenu(String namanya, String deskripsinya, int harganya, int gambarnya) {
        this.menuNama = namanya;
        this.menuDeskripsi = deskripsinya;
        this.menuHarga = harganya;
        this.menuGambar = gambarnya;
    }
}
