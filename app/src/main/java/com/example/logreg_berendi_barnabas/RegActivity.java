package com.example.logreg_berendi_barnabas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {
    EditText etNev, etEmail, etPw, etNick;
    Button btnVissza, btnReg;
    Toast toast;
    Toast custom_Toast;
    DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);



        init();
        listeners();

    }

    private void listeners() {

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatRogzites();
            }
        });

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masikActivityre = new Intent(RegActivity.this, MainActivity.class);
                startActivity(masikActivityre);
                finish();
            }
        });
    }


    private void adatRogzites() {
        String nev = etNev.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String jelszo = etPw.getText().toString().trim();
        String nick = etNick.getText().toString().trim();

        if (nev.isEmpty())
        {
            Toast.makeText(this, "teljes név megadása kötlező",Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.isEmpty())
        {
            Toast.makeText(this, "email megadása kötlező",Toast.LENGTH_SHORT).show();
            return;
        }

        if (jelszo.isEmpty())
        {
            Toast.makeText(this, "jelszó megadása kötlező",Toast.LENGTH_SHORT).show();
            return;
        }

        if (nick.isEmpty())
        {
            Toast.makeText(this, "felhasználóinév megadása kötlező",Toast.LENGTH_SHORT).show();
            return;
        }

        search();

        if (adatbazis.adatRogzites(nev,email,jelszo,nick))
        {


            Toast.makeText(this, "Sikeres regisztrálás",Toast.LENGTH_SHORT).show();
            Intent masikActivityre = new Intent(RegActivity.this, MainActivity.class);
            startActivity(masikActivityre);
            finish();
        }
        else
        {
            Toast.makeText(this, "Sikertelen regisztrálás",Toast.LENGTH_SHORT).show();
            return;
        }


    }

    private void search() {

        String s=etEmail.getText().toString().trim() ;
        int intIndex = s.indexOf("@");
        if(intIndex == - 1) {
            Toast.makeText(this, "Email mezőbe nem email cím került!",Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        btnReg = findViewById(R.id.btn_Reg);
        btnVissza = findViewById(R.id.btn_vissza);
        etEmail = findViewById(R.id.et_Email);
        etNev = findViewById(R.id.et_Nev);
        etNick = findViewById(R.id.et_Nick);
        etPw = findViewById(R.id.et_Jelszo);
        adatbazis= new DBhelper(RegActivity.this);

        toast = Toast.makeText(RegActivity.this,"",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        View view = getLayoutInflater().inflate(R.layout.customtoast, (ViewGroup) findViewById(R.id.customToast));
        toast.setView(view);
    }
}