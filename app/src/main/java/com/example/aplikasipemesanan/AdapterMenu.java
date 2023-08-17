package com.example.aplikasipemesanan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolder> {

    private List<ItemMenu> menuItems; // Daftar item menu
    private int selectedPosition = RecyclerView.NO_POSITION; // Posisi item yang dipilih, awalnya tidak ada yang dipilih

    // Konstruktor untuk menginisialisasi daftar item menu
    public AdapterMenu(List<ItemMenu> menuItems) {
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate tata letak untuk setiap item dalam RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemMenu menuItem = menuItems.get(position);

        // Setel data item menu ke tampilan di ViewHolder
        holder.namaMenu.setText(menuItem.getMenuNama());
        holder.deskripsiMenu.setText(menuItem.getMenuDeskripsi());
        holder.hargaMenu.setText("Rp. " + menuItem.getMenuHarga());
        holder.gambarMenu.setImageResource(menuItem.getMenuGambar());

        // Tangani klik tombol pemesanan
        Button btnOrderMenu = holder.itemView.findViewById(R.id.btnOrderMenu);
        btnOrderMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, OrderActivity.class);

                // Kirim data item ke OrderActivity
                intent.putExtra("namaItem", menuItem.getMenuNama());
                intent.putExtra("deskripsiItem", menuItem.getMenuDeskripsi());
                intent.putExtra("hargaItem", menuItem.getMenuHarga());
                intent.putExtra("gambarItem", menuItem.getMenuGambar());

                context.startActivity(intent);
            }
        });

        // Tangani klik item untuk menyoroti item yang dipilih
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelectedPosition = getSelectedPosition();
                int currentPosition = holder.getAdapterPosition();

                // Perbarui posisi yang dipilih dan beri tahu perubahan tampilan
                if (previousSelectedPosition != currentPosition) {
                    selectedPosition = currentPosition;
                    notifyItemChanged(previousSelectedPosition);
                    notifyItemChanged(selectedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size(); // Jumlah item dalam daftar menu
    }

    // Dapatkan posisi item yang dipilih
    public int getSelectedPosition() {
        int selectedPosition = 0;
        return selectedPosition; // Mengembalikan posisi item yang dipilih
    }

    // Kelas ViewHolder untuk menyimpan tampilan setiap item dalam RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaMenu;
        TextView deskripsiMenu;
        TextView hargaMenu;
        Button btnOrder;
        ImageView gambarMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inisialisasi tampilan dari layout item_menu.xml
            namaMenu = itemView.findViewById(R.id.idNamaMenu);
            deskripsiMenu = itemView.findViewById(R.id.idDeskripsiMenu);
            hargaMenu = itemView.findViewById(R.id.idHargaMenu);
            btnOrder = itemView.findViewById(R.id.btnOrderMenu);
            gambarMenu = itemView.findViewById(R.id.menuItemImage);
        }
    }
}
