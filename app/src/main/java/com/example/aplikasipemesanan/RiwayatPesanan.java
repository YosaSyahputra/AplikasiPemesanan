package com.example.aplikasipemesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RiwayatPesanan extends AppCompatActivity {

    private RecyclerView recyclerViewRiwayat;
    private RiwayatAdapter riwayatAdapter;
    private SharedPreferences dbSP;
    private List<RiwayatItem> dataRiwayat;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pesanan);

        // Inisialisasi tombol kembali
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiwayatPesanan.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Inisialisasi RecyclerView untuk menampilkan riwayat pesanan
        recyclerViewRiwayat = findViewById(R.id.rycRiwayat);
        dataRiwayat = new ArrayList<>();
        dbSP = getSharedPreferences("riwayat", MODE_PRIVATE);

        // Mendapatkan jumlah riwayat dari Shared Preferences
        int hitungRiwayat = dbSP.getInt("riwayat_count", 0);

        // Membaca data riwayat dari Shared Preferences dan menambahkannya ke daftar dataRiwayat
        for (int i = 0; i < hitungRiwayat; i++) {
            String riwayatKey = "riwayat_" + i;
            String pesananJson = dbSP.getString(riwayatKey, "");
            String metodePembayaran = dbSP.getString(riwayatKey + "saveMetodePembayaran", "");
            String tanggalFormatted = dbSP.getString(riwayatKey + "saveTanggal", ""); // Ambil tanggal yang sudah diformat

            // Mengubah JSON ke objek Pesanan menggunakan Gson
            Pesanan pesanan = jsonToPesanan(pesananJson);

            // Membuat data riwayat yang akan ditampilkan dalam RecyclerView
            String namaPesanan = "" + pesanan.getItemName() + " (x" + pesanan.getQuantity() + ")";
            String totalHargaPesanan = "Rp. " + pesanan.getTotalPrice();
            String tanggalOrder = "" + tanggalFormatted;
            String metodePembayaranOrder = "Pembayaran: " + metodePembayaran;
            RiwayatItem riwayatItem = new RiwayatItem(namaPesanan, totalHargaPesanan, tanggalOrder, metodePembayaranOrder);
            dataRiwayat.add(riwayatItem);
        }

        // Inisialisasi adapter dan layout manager untuk RecyclerView
        riwayatAdapter = new RiwayatAdapter(dataRiwayat);
        recyclerViewRiwayat.setAdapter(riwayatAdapter);
        recyclerViewRiwayat.setLayoutManager(new LinearLayoutManager(this));
    }

    // Metode untuk mengonversi JSON menjadi objek Pesanan menggunakan Gson
    private Pesanan jsonToPesanan(String pesananJson) {
        Gson gson = new Gson();
        return gson.fromJson(pesananJson, Pesanan.class);
    }
}
