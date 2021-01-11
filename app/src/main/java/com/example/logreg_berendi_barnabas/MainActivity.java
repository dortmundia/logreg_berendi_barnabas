package com.example.logreg_berendi_barnabas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etnn, etpw; // nn =nickname, pw=password
    Button btnLog, btnReg;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Toast toast;
    Toast custom_Toast;
    DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listeners();
    }
    private void listeners()
    {
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nn=etnn.getText().toString().trim();;
                String pw=etpw.getText().toString().trim();;


                if (adatbazis.logWeMailEllenorzo(nn,pw) || adatbazis.logWNickEllenorzo(nn,pw))
                {
                    // Toast.makeText(this, "Sikeres bejelntkezés",Toast.LENGTH_SHORT).show();
                    Intent masikActivityre = new Intent(MainActivity.this, LogActivity.class);
                    startActivity(masikActivityre);
                    finish();
                }
                else
                {
                    toastB();
                    return;
                }



                if (nn.isEmpty())
                {
                    toastNN();
                    return;
                }

                if (pw.isEmpty())
                {
                    toastPW();
                    return;
                }


            }
        });


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String nev = etnn.getText().toString();
                //editor.putString("neved",nev);
                //editor.commit();
                Intent masikActivityre = new Intent(MainActivity.this, RegActivity.class);
                startActivity(masikActivityre);
                finish();
            }
        });



    }

    private void toastPW() {

        Toast.makeText(this, "jelszó megadása kötlező",Toast.LENGTH_SHORT).show();
    }

    private void toastNN() {
        Toast.makeText(this, "Felhasznákói név vagy email cím megadása kötlező",Toast.LENGTH_SHORT).show();
    }

    private void toastB() {
        Toast.makeText(this, "Sikertelen bejelentkezés",Toast.LENGTH_SHORT).show();
    }


    private void init()
    {
        etnn = findViewById(R.id.et_Nick);
        etpw = findViewById(R.id.et_Jelszo);
        btnLog = findViewById(R.id.btn_LogIn);
        btnReg = findViewById(R.id.btn_Reg);
        // sharedPref = getSharedPreferences("adatok", Context.MODE_PRIVATE);
        //editor = sharedPref.edit();

        adatbazis= new DBhelper(MainActivity.this);

        toast = Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        View view = getLayoutInflater().inflate(R.layout.customtoast, (ViewGroup) findViewById(R.id.customToast));
        toast.setView(view);


    }

}