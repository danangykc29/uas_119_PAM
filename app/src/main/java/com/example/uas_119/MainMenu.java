package com.example.uas_119;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.database.Cursor;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class MainMenu extends AppCompatActivity {
    Dbase db;
    Button tombolSimpan;
    EditText editCatatan, editTanggal, editId;
    CheckBox cekYa;
    ListView listViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu_utama );
        // Buat objek database
        db = new Dbase( this );
        // Objek komponen
        editId = (EditText) findViewById( R.id.editText3 );
        editCatatan = (EditText) findViewById( R.id.editText1 );
        editTanggal = (EditText) findViewById( R.id.editText2 );
        cekYa = (CheckBox) findViewById( R.id.checkBoxya );
        listViewData = (ListView) findViewById( R.id.listViewDb );
        tombolSimpan = (Button) findViewById( R.id.buttonSimpan );


        tombolSimpan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                db.open();
                db.insertData( editCatatan.getText().toString(),
                        cekYa.isChecked(),
                        editTanggal.getText().toString() );

                Toast.makeText( getBaseContext(), "Data telah disimpan",
                        Toast.LENGTH_SHORT ).show();

                // Kosongkan data
                editCatatan.setText( "" );
                cekYa.setChecked( false );
                editTanggal.setText( "" );


                // Tampilkan data di database
                tampilkanData();
            }

        } );
        tampilkanData();
    }

    public void tampilkanData() {
        db.open();
        Cursor c = db.getAllData();

// Buat array dinamis
        ArrayList<String> larik = new ArrayList<String>();

        if (c.moveToFirst()) {
            do {
                int idKARYAWAN = c.getInt( 0 );
                String namaCatatan = c.getString( 1 );
                int Ya = c.getInt( 2 );
                String tgLahir = c.getString( 3 );

                String diterima;
                if (Ya == 1)
                    diterima = "SudahMembayar";
                else
                    diterima = "hutang";

                larik.add( Integer.toString( idKARYAWAN ) + ": " +
                        namaCatatan + " (" + diterima + ") - " +
                        tgLahir );
            } while (c.moveToNext());
        }

        db.close();

        // Taruh larik ke Listview

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1,
                larik );

        listViewData.setAdapter( adapter );

    }

}



