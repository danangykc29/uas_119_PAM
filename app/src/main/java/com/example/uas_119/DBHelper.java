package com.example.uas_119;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBHelper extends SQLiteOpenHelper {
 // Berisi perintah SQL untuk menciptakan tabel
    private final static String BUAT_TABEL = "create table " +
    Konstanta.NAMA_TABEL + " (" +
    Konstanta.ID_KARYAWAN + " integer primary key autoincrement, " +
    Konstanta.PEMASUKAN_CATATANP+ " text not null, " +
    Konstanta.PEMBAYARAN_DITERIMA + " boolean not null, " +
    Konstanta.TANGGAL_REKAP + " text not null);";

public DBHelper(Context konteks, String nama, CursorFactory f, int versi) { super(konteks, nama, f, versi);
}

public void onCreate(SQLiteDatabase db) {
      try {
            db.execSQL(BUAT_TABEL);
      }
      catch (SQLiteException e) {
      }
}

@Override
public void onUpgrade(SQLiteDatabase db, int versiLama,
 int versiBaru) {
     db.execSQL("drop table if exists " + Konstanta.NAMA_TABEL);
     onCreate(db);
     }


}



