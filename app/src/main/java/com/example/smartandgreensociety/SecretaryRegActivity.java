package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecretaryRegActivity extends AppCompatActivity {

    EditText userPhone,societyName;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary_reg);

        userPhone = findViewById(R.id.userPhone);
        societyName = findViewById(R.id.societyName);
        btnRegister = findViewById(R.id.btnSaveSecretary);



    }
}