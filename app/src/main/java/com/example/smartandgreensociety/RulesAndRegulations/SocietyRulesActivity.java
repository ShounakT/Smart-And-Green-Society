package com.example.smartandgreensociety.RulesAndRegulations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;

public class SocietyRulesActivity extends AppCompatActivity {

    TextView etSocietyRulesReg;
    Db db = new Db();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        SocietyRulesActivity.this.finish();
        return super.onSupportNavigateUp();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_rules);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etSocietyRulesReg = findViewById(R.id.etSocietyRulesReg);
        db.getSocietyRules(new Db.societyRulesFetch() {
            @Override
            public void fetchedRules(String rules) {
                etSocietyRulesReg.setText(rules);
            }
        });

    }
}