package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;

public class AddResidentsActivity extends AppCompatActivity {

    EditText etResidentEmail;
    Button btnAdd;
    Db db = new Db();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_residents);

        etResidentEmail = findViewById(R.id.etResidentEmail);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String residentEmail = etResidentEmail.getText().toString().trim();
                db.addResident(residentEmail);
                startActivity(new Intent(AddResidentsActivity.this,HomeActivity.class));
                AddResidentsActivity.this.finish();

            }
        });
    }
}