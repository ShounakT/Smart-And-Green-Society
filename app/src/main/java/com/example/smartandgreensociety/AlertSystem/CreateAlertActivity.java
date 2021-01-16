package com.example.smartandgreensociety.AlertSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartandgreensociety.R;

public class CreateAlertActivity extends AppCompatActivity {

    Button btnNotify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);

        btnNotify = findViewById(R.id.btnNotify);



        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}