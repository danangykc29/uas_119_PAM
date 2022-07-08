package com.example.uas_119;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Dbase {
    private SQLiteDatabase db;
    private final Context konteks;
    private final DBHelper dbhelper;

    // Konstruktor
    public Dbase(Context k) {
        konteks = k;
        dbhelper = new DBHelper( konteks, Konstanta.NAMA_DB, null, Konstanta.VERSI_DB );
    }

    // Membuka database
    public void open() throws SQLiteException {
        try {
            db = dbhelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbhelper.getReadableDatabase();
        }
    }

    // Menutup database
    public void close() {
        db.close();
    }

    // Menyisipkan data
    public long insertData(String catatan, boolean ya, String tanggal) {
        try {
            ContentValues dataBaru = new ContentValues();
            dataBaru.put( Konstanta.PEMASUKAN_CATATANP, catatan );
            dataBaru.put( Konstanta.PEMBAYARAN_DITERIMA, ya );
            dataBaru.put( Konstanta.TANGGAL_REKAP, tanggal );

            return db.insert( Konstanta.NAMA_TABEL, null, dataBaru );
        } catch (SQLiteException e) {
            return -1;
        }
    }


    // Mengambil seluruh data
    public Cursor getAllData() {
        return db.query( Konstanta.NAMA_TABEL,
                new String[]{Konstanta.ID_KARYAWAN,
                        Konstanta.PEMASUKAN_CATATANP,
                        Konstanta.PEMBAYARAN_DITERIMA,
                        Konstanta.TANGGAL_REKAP},
                null, null, null, null, null );
    }


}