package com.example.aplikasipemesanan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nama dan versi database
    private static final String DATABASE_NAME = "aplikasimenu";
    private static final int DATABASE_VERSION = 1;

    // Nama-nama kolom dan tabel untuk tabel pesanan
    public static final String TABLE_NAME_ORDER = "tblorder";
    public static final String COLUMN_ID_ORDER = "id";
    public static final String COLUMN_NAME_ORDER = "nama_menu";
    public static final String COLUMN_PRICE_ORDER = "harga_menu";
    public static final String COLUMN_QUANTITY_ORDER = "jumlah";
    public static final String COLUMN_TOTAL_PRICE_ORDER = "total_harga";
    public static final String COLUMN_ORDER_KE_ORDER = "order_ke";

    // Nama-nama kolom dan tabel untuk tabel riwayat
    public static final String TABLE_NAME_RIWAYAT = "tblriwayat";
    public static final String COLUMN_ID_RIWAYAT = "id";
    public static final String COLUMN_NAME_RIWAYAT = "nama_menu";
    public static final String COLUMN_PRICE_RIWAYAT = "harga_menu";
    public static final String COLUMN_QUANTITY_RIWAYAT = "jumlah";
    public static final String COLUMN_TOTAL_PRICE_RIWAYAT = "total_harga";
    public static final String COLUMN_ORDER_KE_RIWAYAT = "order_ke";
    public static final String COLUMN_PEMBAYARAN = "channel_pembayaran";
    public static final String COLUMN_TANGGAL = "tanggal";

    // Query pembuatan tabel pesanan
    private static final String TABLE_CREATE_ORDER =
            "CREATE TABLE " + TABLE_NAME_ORDER + " (" +
                    COLUMN_ID_ORDER + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME_ORDER + " TEXT, " +
                    COLUMN_PRICE_ORDER + " INTEGER, " +
                    COLUMN_QUANTITY_ORDER + " INTEGER, " +
                    COLUMN_TOTAL_PRICE_ORDER + " INTEGER, " +
                    COLUMN_ORDER_KE_ORDER + " INTEGER);";

    // Query pembuatan tabel riwayat
    private static final String TABLE_CREATE_RIWAYAT =
            "CREATE TABLE " + TABLE_NAME_RIWAYAT + " (" +
                    COLUMN_ID_RIWAYAT + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME_RIWAYAT + " TEXT, " +
                    COLUMN_PRICE_RIWAYAT + " INTEGER, " +
                    COLUMN_QUANTITY_RIWAYAT + " INTEGER, " +
                    COLUMN_TOTAL_PRICE_RIWAYAT + " INTEGER, " +
                    COLUMN_ORDER_KE_RIWAYAT + " INTEGER, " +
                    COLUMN_PEMBAYARAN + " TEXT, " +
                    COLUMN_TANGGAL + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method yang dipanggil ketika database dibuat
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_ORDER); // Buat tabel pesanan
        db.execSQL(TABLE_CREATE_RIWAYAT); // Buat tabel riwayat
    }

    // Method yang dipanggil ketika database di-upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ORDER); // Hapus tabel pesanan lama
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_RIWAYAT); // Hapus tabel riwayat lama
        onCreate(db); // Buat tabel baru
    }

    // Method untuk mengambil total harga dari tabel pesanan
    public int getTotalHargaFromDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        int totalHarga = 0;

        // Query untuk mengambil total harga dari tabel tblorder
        String query = "SELECT SUM(" + COLUMN_TOTAL_PRICE_ORDER + ") FROM " + TABLE_NAME_ORDER;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            totalHarga = cursor.getInt(0); // Ambil nilai total harga dari hasil query
        }

        cursor.close();
        return totalHarga;
    }
}
