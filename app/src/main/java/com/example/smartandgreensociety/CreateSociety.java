package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    EditText etSocietyName;
    DbOperations dbOperations = new DbOperations();
    Society society = new Society();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_society);


        etSocietyName = findViewById(R.id.etSocietyName);
        btnCreateSociety = findViewById(R.id.btnCreateSociety);

        btnCreateSociety.setOnClickListener(v -> {
            if(TextUtils.isEmpty(etSocietyName.getText().toString().trim())){

                Toast.makeText(getApplicationContext(),"Please enter all fields!",
                        Toast.LENGTH_SHORT).show();

            }else{

                    Globals.SOCIETY = new Society();
                    String societyName = etSocietyName.getText().toString().trim();
                    String societyCode = Globals.USER.getSocietyId();
                    Globals.SOCIETY.setSocietyName(societyName);
                    Globals.SOCIETY.setSocietyCode(societyCode);
                    Map societyMap = society.toMap();
                    dbOperations.createNewSociety(this,societyMap);
                    startActivity(new Intent(CreateSociety.this,HomeActivity.class));
                    CreateSociety.this.finish();
            }
        });
    }
}