package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartandgreensociety.Database.Db;

public class AddResidentsActivity extends AppCompatActivity {

    EditText etResidentEmail;
    Button btnAdd;
    Db db = new Db();
    //hello
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        AddResidentsActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_residents);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etResidentEmail = findViewById(R.id.etResidentEmail);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etResidentEmail.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
                }else {


                    String residentEmail = etResidentEmail.getText().toString().trim();
                    db.addResident(residentEmail);
                    startActivity(new Intent(AddResidentsActivity.this, HomeActivity.class));
                    AddResidentsActivity.this.finish();
                }

            }
        });
    }
}