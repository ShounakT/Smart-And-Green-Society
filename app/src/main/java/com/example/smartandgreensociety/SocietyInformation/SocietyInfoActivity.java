package com.example.smartandgreensociety.SocietyInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;

public class SocietyInfoActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        SocietyInfoActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}