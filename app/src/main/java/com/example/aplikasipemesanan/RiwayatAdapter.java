package com.example.aplikasipemesanan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {

    private List<RiwayatItem> dataRiwayat;

    // Constructor untuk menginisialisasi data riwayat yang akan ditampilkan
    public RiwayatAdapter(List<RiwayatItem> riwayatItems) {
        this.dataRiwayat = riwayatItems;
    }

    // Method yang dipanggil ketika RecyclerView memerlukan ViewHolder baru
    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Meng-inflate layout item_riwayat_card ke dalam view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_card, parent, false);
        return new RiwayatViewHolder(view);
    }

    // Method yang dipanggil untuk mengisi data pada ViewHolder dengan data pada posisi tertentu
    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
        RiwayatItem riwayatItem = dataRiwayat.get(position);

        // Mengisi TextView pada ViewHolder dengan data dari RiwayatItem yang sesuai
        holder.txtNamaPesanan.setText(riwayatItem.getItemPesanan());
        holder.txtTotalHarga.setText(riwayatItem.getTotalHarga());
        holder.txtTanggalOrder.setText(riwayatItem.getTanggal());
        holder.txtMetodePembayaranOrder.setText(riwayatItem.getMetodePembayaran());
    }

    // Method yang mengembalikan jumlah item yang akan ditampilkan oleh adapter
    @Override
    public int getItemCount() {
        return dataRiwayat.size();
    }

    // Inner class yang merupakan ViewHolder yang akan digunakan oleh adapter
    public class RiwayatViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNamaPesanan;
        private TextView txtTotalHarga;
        private TextView txtTanggalOrder;
        private TextView txtMetodePembayaranOrder;
        private CardView idCardView;

        // Constructor untuk menginisialisasi elemen-elemen pada ViewHolder
        public RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaPesanan = itemView.findViewById(R.id.idRiwayatNamaMenu);
            txtTotalHarga = itemView.findViewById(R.id.idRiwayatHargaMenu);
            txtTanggalOrder = itemView.findViewById(R.id.idRiwayatTanggalOrder);
            txtMetodePembayaranOrder = itemView.findViewById(R.id.idRiwayatMetodePembayaran);
            idCardView = itemView.findViewById(R.id.cardRiwayat);
        }
    }
}
