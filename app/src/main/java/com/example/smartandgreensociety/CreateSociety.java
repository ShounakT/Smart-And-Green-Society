package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartandgreensociety.Database.DbOperations;

import java.util.Map;

public class CreateSociety extends AppCompatActivity {

    Button btnCreateSociety;
    EditText etSocietyCode, etSocietyName;
    DbOperations dbOperations = new DbOperations();
    Society society = new Society();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_society);

        etSocietyCode = findViewById(R.id.etSocietyCode);
        etSocietyName = findViewById(R.id.etSocietyName);
        btnCreateSociety = findViewById(R.id.btnCreateSociety);

        btnCreateSociety.setOnClickListener(v -> {
            if(TextUtils.isEmpty(etSocietyName.getText().toString().trim()) || TextUtils
                    .isEmpty(etSocietyCode.getText().toString().trim())){

                Toast.makeText(getApplicationContext(),"Please enter all fields!",
                        Toast.LENGTH_SHORT).show();

            }else{

                String societyName = etSocietyName.getText().toString().trim();
                String societyCode = etSocietyCode.getText().toString().trim();
                Map societyMap = society.toMap(societyName,societyCode);
                dbOperations.createNewSociety(societyMap);

            }
        });

    }
}