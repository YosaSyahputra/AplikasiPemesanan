package com.example.aplikasipemesanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SuksesOrder extends AppCompatActivity {

    private TextView txtPesanNama, txtPesanHarga, txtJumlah, txtTotalHarga;
    private Button btnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_order);

        // Inisialisasi elemen tampilan yang akan ditampilkan dalam aktivitas
        txtPesanNama = findViewById(R.id.txtPesanNama);
        txtPesanHarga = findViewById(R.id.txtPesanHarga);
        txtJumlah = findViewById(R.id.txtJumlah);
        txtTotalHarga = findViewById(R.id.txtTotalHarga);
        btnToMenu = findViewById(R.id.btnBack);

        // Mendapatkan data dari Intent yang dipassing dari aktivitas sebelumnya
        Intent intent = getIntent();
        String namaPesan = intent.getStringExtra("namaMakanan");
        int hargaPesan = intent.getIntExtra("hargaMakanan", 0);
        int jumlahPesan = intent.getIntExtra("jumlahPesan", 0);
        int totalharga = hargaPesan * jumlahPesan;

        // Menampilkan data pesanan yang berhasil dalam elemen tampilan
        txtPesanNama.setText(namaPesan);
        txtPesanHarga.setText(String.valueOf(hargaPesan));
        txtJumlah.setText(String.valueOf(jumlahPesan));
        txtTotalHarga.setText("Rp. " + (String.valueOf(totalharga)));

        // Memberikan tindakan pada tombol untuk kembali ke halaman utama
        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SuksesOrder.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
