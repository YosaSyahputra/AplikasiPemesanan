package com.example.aplikasipemesanan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private int jumlah = 1; // Jumlah pesanan awal
    private int hargaItem, gambarItem; // Informasi harga dan gambar item
    private Button btnOrderrr;
    private TextView txtQuantity, txtTotalHarga, namaMenu, deskripsiMenu, hargaMenu;
    private ImageView gambarMenu;
    private String namaItem, deskripsiItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Inisialisasi elemen dari layout
        txtQuantity = findViewById(R.id.txtQuantity);
        txtTotalHarga = findViewById(R.id.idTotal);
        gambarMenu = findViewById(R.id.idGambar);
        namaMenu = findViewById(R.id.idNama);
        deskripsiMenu = findViewById(R.id.idDeskripsi);
        hargaMenu = findViewById(R.id.idHarga);
        btnOrderrr = findViewById(R.id.btnPesanMenu);

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        namaItem = intent.getStringExtra("namaItem");
        deskripsiItem = intent.getStringExtra("deskripsiItem");
        hargaItem = intent.getIntExtra("hargaItem", 0);
        gambarItem = intent.getIntExtra("gambarItem", R.drawable.nasi_goreng);

        // Menampilkan data pada elemen-elemen UI
        gambarMenu.setImageResource(gambarItem);
        namaMenu.setText(namaItem);
        deskripsiMenu.setText(deskripsiItem);
        hargaMenu.setText("Harga: Rp. " + hargaItem);

        // Mengatur fungsi tombol "Pesan"
        btnOrderrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalBayar = jumlah * hargaItem;

                // Menyimpan pesanan ke SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("pembelian", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                int orderanKe = sharedPreferences.getInt("order_count", 0);
                String orderKey = "order_" + orderanKe;

                editor.putString(orderKey + "saveNamaMenu", namaItem);
                editor.putInt(orderKey + "saveJumlahPesanan", jumlah);
                editor.putInt(orderKey + "saveTotalBayar", (int) totalBayar);

                editor.putInt("order_count", orderanKe + 1);
                editor.apply();

                // Pindah ke halaman SuksesOrder
                Intent intent = new Intent(OrderActivity.this, SuksesOrder.class);
                intent.putExtra("namaMakanan", namaItem);
                intent.putExtra("hargaMakanan", hargaItem);
                intent.putExtra("jumlahPesan", jumlah);
                startActivity(intent);
                finish();
            }
        });
    }

    // Method untuk menambah jumlah pesanan
    public void tambahJumlah(View view){
        jumlah++;
        updateQuantityAndTotal();
    }

    // Method untuk mengurangi jumlah pesanan
    public void kurangJumlah(View view){
        if (jumlah > 1) {
            jumlah--;
            updateQuantityAndTotal();
        }
    }

    // Method untuk mengupdate tampilan jumlah dan total harga
    private void updateQuantityAndTotal() {
        txtQuantity.setText(String.valueOf(jumlah));
        int totalHarga = jumlah * hargaItem;
        txtTotalHarga.setText("Total Harga: Rp. " + totalHarga);
    }
}
