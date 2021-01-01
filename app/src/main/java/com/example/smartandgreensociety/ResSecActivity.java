package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResSecActivity extends AppCompatActivity {

    Button btnRegisterAsResident;
    Button btnRegisterAsSecretary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_sec);

        btnRegisterAsResident = findViewById(R.id.btnRegAsResident);
        btnRegisterAsSecretary = findViewById(R.id.btnRegAsSecretary);

        btnRegisterAsSecretary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ResSecActivity.this, SecretaryRegActivity.class));
                ResSecActivity.this.finish();
                Toast.makeText(getApplicationContext(), "Please Complete Profile!",Toast.LENGTH_SHORT).show();

            }
        });

        btnRegisterAsResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ResSecActivity.this, ResidentRegActivity.class));
                ResSecActivity.this.finish();
                Toast.makeText(getApplicationContext(), "Please Complete Profile!",Toast.LENGTH_SHORT);

            }
        });

    }
}