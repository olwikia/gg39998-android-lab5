package com.example.gg39998_android_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DodajWpis extends AppCompatActivity {

    private int modyfi_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);
        ArrayAdapter gatunki = new ArrayAdapter( this, android.R.layout.simple_spinner_dropdown_item, new String[] {"Pies", "Kot", "Rybki"});
        Spinner gatunek = (Spinner) findViewById (R.id.gatunek);
        gatunek.setAdapter(gatunki);

        Bundle extras = getIntent().getExtras();
        try {
            if(extras.getSerializable("element") != null) {
                Animal zwierz = (Animal) extras.getSerializable("element");
                EditText kolor = (EditText) findViewById(R.id.kolor);
                EditText wielkosc = (EditText) findViewById(R.id.wielkosc);
                EditText opis = (EditText) findViewById(R.id.opis);

                kolor.setText(zwierz.getKolor());
                wielkosc.setText( Float.toString(zwierz.getWielkosc()));
                opis.setText(zwierz.getOpis());
                this.modyfi_id=zwierz.getId();
                //Toast.makeText(getApplicationContext(), "test "+ modyfi_id, Toast.LENGTH_SHORT).show();
            } }
        catch(Exception ex) {
            this.modyfi_id=0;
        }

    }
    public void wyslij(View view){
       // EditText kolor = (EditText) findViewById (R.id.kolor);
        //EditText wielkosc = (EditText) findViewById (R.id.wielkosc);
       // EditText opis = (EditText) findViewById (R.id.opis);
       // Spinner gatunek = (Spinner) findViewById (R.id.gatunek);
       // Animal zwierze = new Animal( gatunek.getSelectedItem().toString(), kolor.getText().toString(), Float.valueOf(wielkosc.getText().toString()), opis.getText().toString() );

        try {
            EditText kolor = (EditText) findViewById (R.id.kolor);
            EditText wielkosc = (EditText) findViewById (R.id.wielkosc);
            EditText opis = (EditText) findViewById (R.id.opis);
            Spinner gatunek = (Spinner) findViewById (R.id.gatunek);
            Animal zwierze = new Animal( gatunek.getSelectedItem().toString(), kolor.getText().toString(), Float.valueOf(wielkosc.getText().toString()), opis.getText().toString() );
            if(kolor.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "musisz podać kolor", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(opis.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "musisz wprowadzić opis", Toast.LENGTH_SHORT).show();
                return;
            }

            zwierze.setId(this.modyfi_id);
            //Toast.makeText(getApplicationContext(), "test "+ this.modyfi_id, Toast.LENGTH_SHORT).show();
            Intent intencja = new Intent(); intencja.putExtra("nowy", zwierze);
            setResult(RESULT_OK, intencja); finish();
        }
        catch(NumberFormatException ex) {
            Toast.makeText(getApplicationContext(), "musisz podać wielkość", Toast.LENGTH_SHORT).show();
            return;
        }
        //zwierze.setId(this.modyfi_id);
        //Toast.makeText(getApplicationContext(), "test "+ this.modyfi_id, Toast.LENGTH_SHORT).show();
       // Intent intencja = new Intent(); intencja.putExtra("nowy", zwierze);
       // setResult(RESULT_OK, intencja); finish();
    }
}
