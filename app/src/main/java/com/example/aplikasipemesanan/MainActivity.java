package com.example.aplikasipemesanan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterMenu menuAdapter;
    private List<ItemMenu> listMenu;
    private Button btnTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi tombol dan RecyclerView dari layout
        btnTotal = findViewById(R.id.btnTotalBayar);
        recyclerView = findViewById(R.id.rycView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Memanggil fungsi untuk menampilkan daftar menu dan total bayar
        munculkanMenu();
        munculkanTotal();
    }

    // Method untuk pindah ke halaman riwayat pesanan
    public void toRiwayat(View view) {
        Intent intent = new Intent(this, RiwayatPesanan.class);
        startActivity(intent);
    }

    // Method untuk pindah ke halaman pembayaran pesanan
    public void formBayarPesanan(View view) {
        Intent intent = new Intent(this, BayarPesanan.class);
        startActivity(intent);
    }

    // Method untuk menampilkan daftar menu dalam RecyclerView
    private void munculkanMenu(){
        listMenu = new ArrayList<>();
        // ... Inisialisasi gambar-gambar menu dan tambahkan ke listMenu

        // Inisialisasi AdapterMenu dan mengatur adapter untuk RecyclerView
        menuAdapter = new AdapterMenu(listMenu);
        recyclerView.setAdapter(menuAdapter);
    }

    // Method untuk menghitung dan menampilkan total bayar pesanan
    private void munculkanTotal(){
        SharedPreferences sharedPreferences = getSharedPreferences("pembelian", MODE_PRIVATE);
        int jumlahOrderan = sharedPreferences.getInt("order_count", 0);
        int totalBayarOrderan = 0;

        // Iterasi melalui pesanan-pesanan dan menghitung total bayar
        for (int i = 0; i < jumlahOrderan; i++) {
            String orderKey = "order_" + i;
            int totalPrice = (int) sharedPreferences.getInt(orderKey + "saveTotalBayar", 0);
            totalBayarOrderan += totalPrice;
        }

        // Menampilkan total bayar jika ada pesanan, atau menghilangkan tombol jika tidak ada pesanan
        if (totalBayarOrderan > 0) {
            btnTotal.setVisibility(View.VISIBLE);
            btnTotal.setText("Total: Rp. " + totalBayarOrderan + "");
        } else {
            btnTotal.setVisibility(View.GONE);
        }
    }

    // Method untuk menghapus semua data pesanan dari SharedPreferences
    private void clearDb(){
        SharedPreferences cleardb = getSharedPreferences("pembelian", MODE_PRIVATE);
        SharedPreferences.Editor edit = cleardb.edit();
        edit.clear();
        edit.apply();
    }
}
