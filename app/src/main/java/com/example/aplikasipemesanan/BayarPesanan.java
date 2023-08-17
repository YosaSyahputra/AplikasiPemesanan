package com.example.aplikasipemesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BayarPesanan extends AppCompatActivity {

    private ListView listViewPesanan;
    private List<String> pesananList;
    private TextView txtTotBayar;
    private SharedPreferences sharedPreferences;
    private RadioGroup radioGroup;
    RadioButton radioTunai, radioOVO, radioGOPAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_pesanan);

        // Inisialisasi tampilan dan komponen
        radioGroup = findViewById(R.id.radioGroup);
        radioTunai = findViewById(R.id.radioTunai);
        radioOVO = findViewById(R.id.radioOvo);
        radioGOPAY = findViewById(R.id.radioGopay);
        txtTotBayar = findViewById(R.id.txtTotBayar);
        listViewPesanan = findViewById(R.id.listViewPesanan);

        // Tampilkan detail orderan
        munculkanDetailOrderan();
    }

    // Method untuk menampilkan detail orderan pada ListView
    private void munculkanDetailOrderan() {
        pesananList = new ArrayList<>();

        sharedPreferences = getSharedPreferences("pembelian", MODE_PRIVATE);

        int jumlahOrderan = sharedPreferences.getInt("order_count", 0);
        int totalBayarOrderan = 0;

        for (int i = 0; i < jumlahOrderan; i++) {
            String orderKey = "order_" + i;
            String itemName = sharedPreferences.getString(orderKey + "saveNamaMenu", "");
            int quantity = sharedPreferences.getInt(orderKey + "saveJumlahPesanan", 0);
            int totalPrice = sharedPreferences.getInt(orderKey + "saveTotalBayar", 0);

            String pesananText = itemName + " (x" + quantity + ") Harga: Rp " + totalPrice;
            pesananList.add(pesananText);

            totalBayarOrderan += totalPrice;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pesananList);
        listViewPesanan.setAdapter(adapter);

        txtTotBayar.setText("Rp " + totalBayarOrderan);
    }

    // Method untuk menyimpan pesanan dan riwayat pembelian
    private void simpanOrderan() {
        List<ItemMenu> items = new ArrayList<>();

        int orderCount = sharedPreferences.getInt("order_count", 0);

        SharedPreferences riwayatSharedPreferences = getSharedPreferences("riwayat", MODE_PRIVATE);
        SharedPreferences.Editor riwayatEditor = riwayatSharedPreferences.edit();
        int riwayatCount = riwayatSharedPreferences.getInt("riwayat_count", 0);

        int selectedId = radioGroup.getCheckedRadioButtonId();
        String metodePembayaran = "";

        // Menentukan metode pembayaran terpilih
        if (selectedId == R.id.radioOvo) {
            metodePembayaran = "OVO";
        } else if (selectedId == R.id.radioGopay) {
            metodePembayaran = "GoPay";
        } else if (selectedId == R.id.radioTunai) {
            metodePembayaran = "Tunai";
        }

        // Iterasi melalui pesanan dan menambahkannya ke daftar item
        for (int i = 0; i < orderCount; i++) {
            String orderKey = "order_" + i;
            String itemName = sharedPreferences.getString(orderKey + "saveNamaMenu", "");
            int quantity = sharedPreferences.getInt(orderKey + "saveJumlahPesanan", 0);
            int totalPrice = sharedPreferences.getInt(orderKey + "saveTotalBayar", 0);
            ItemMenu item = new ItemMenu(itemName, "", (int) totalPrice, 0);
            items.add(item);

            // Membuat objek Pesanan dan mengisi data
            Date tanggalPesanan = new Date();
            Pesanan pesanan = new Pesanan(itemName, totalPrice, quantity, totalPrice, tanggalPesanan);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String tanggalFormatted = dateFormat.format(tanggalPesanan);
            String pesananJson = convertPesananToJson(pesanan);

            // Menyimpan data pesanan dalam riwayat
            String riwayatKey = "riwayat_" + riwayatCount;
            riwayatEditor.putString(riwayatKey, pesananJson);
            riwayatEditor.putString(riwayatKey + "saveMetodePembayaran", metodePembayaran);
            riwayatEditor.putString(riwayatKey + "saveTanggal", tanggalFormatted);
            riwayatCount++;
        }

        // Menyimpan perubahan dalam riwayat
        riwayatEditor.putInt("riwayat_count", riwayatCount);
        riwayatEditor.apply();

        // Menghapus pesanan dari Shared Preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Beralih ke aktivitas RiwayatPesanan
        Intent intent = new Intent(this, RiwayatPesanan.class);
        startActivity(intent);
    }

    // Konversi objek Pesanan ke format JSON menggunakan Gson
    private String convertPesananToJson(Pesanan pesanan) {
        Gson gson = new Gson();
        return gson.toJson(pesanan);
    }

    // Handler tombol bayar pesanan
    public void bayarPesanan(View view) {
        simpanOrderan();
    }
}
