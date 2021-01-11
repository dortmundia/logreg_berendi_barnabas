package com.example.logreg_berendi_barnabas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LogActivity extends AppCompatActivity {
    Button btnLogOut;
    SharedPreferences sharedPref;
    TextView txtUdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        init();

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masikActivityre = new Intent(LogActivity.this, MainActivity.class);
                startActivity(masikActivityre);
                finish();
            }
        });

    }

    private void init() {
        btnLogOut = findViewById(R.id.log_button_out);
        txtUdv = findViewById(R.id.log_txt_udv);

    }
}
