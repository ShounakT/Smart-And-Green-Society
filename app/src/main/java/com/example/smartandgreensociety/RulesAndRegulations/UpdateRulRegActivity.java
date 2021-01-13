package com.example.smartandgreensociety.RulesAndRegulations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.example.smartandgreensociety.SocietyInformation.SocietyInfoActivity;

public class UpdateRulRegActivity extends AppCompatActivity {

    EditText etRulReg;
    Button btnUpdateRulReg;
    Db db = new Db();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rul_reg);

        etRulReg = findViewById(R.id.etRulReg);
        btnUpdateRulReg = findViewById(R.id.btnUpdateSocietyRules);

        btnUpdateRulReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String societyRules = etRulReg.getText().toString().trim();
                db.addSocietyRules(societyRules);
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                UpdateRulRegActivity.this.finish();
            }
        });
    }
}