package com.example.smartandgreensociety.RulesAndRegulations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartandgreensociety.Database.Db;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;

public class UpdateRulRegActivity extends AppCompatActivity {

    EditText etRulReg;
    Button btnUpdateRulReg;
    Db db = new Db();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        UpdateRulRegActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rul_reg);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etRulReg = findViewById(R.id.etRulReg);
        btnUpdateRulReg = findViewById(R.id.btnUpdateSocietyRules);

        btnUpdateRulReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etRulReg.getText().toString())){

                    Toast.makeText(getApplicationContext(),"Please Enter All Fields",Toast.LENGTH_SHORT).show();
                }else {
                    String societyRules = etRulReg.getText().toString().trim();
                    db.addSocietyRules(societyRules);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    UpdateRulRegActivity.this.finish();
                }
            }
        });
    }
}