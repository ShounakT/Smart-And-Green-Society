package com.example.smartandgreensociety.SocietyInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.google.firebase.firestore.DocumentSnapshot;

public class SocietyInfoActivity extends AppCompatActivity {

    Db db = new Db();
    TextView tvSocietyName,tvSocietyContact,tvSocietyAddress,tvSocietySecretaryName,tvSocietySecretaryContact,
                tvSocietyChairmanName,tvSocietyChairmanContact,tvSocietyTreasurerName,tvSocietyTreasurerContact;
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

        tvSocietyName = findViewById(R.id.tvSocietyName);
        tvSocietyContact = findViewById(R.id.tvSocietyContact);
        tvSocietyAddress = findViewById(R.id.tvSocietyAddress);
        tvSocietySecretaryName = findViewById(R.id.tvSocietySecretaryName);
        tvSocietySecretaryContact = findViewById(R.id.tvSocietySecretaryContact);
        tvSocietyChairmanName = findViewById(R.id.tvSocietyChairmanName);
        tvSocietyChairmanContact = findViewById(R.id.tvSocietyChairmanContact);
        tvSocietyTreasurerName = findViewById(R.id.tvSocietyTreasurerName);
        tvSocietyTreasurerContact = findViewById(R.id.tvSocietyTreasurerContact);


        DocumentSnapshot societyInfoDoc = db.getSocietyInfo();
        String societyName = societyInfoDoc.getString("societyName");
        String societyContact = societyInfoDoc.getString("societyContact");
        String societySecretaryName = societyInfoDoc.getString("societySecretaryName");
        String societySecretaryContact = societyInfoDoc.getString("societySecretaryContact");
        String societyChairmanName = societyInfoDoc.getString("societyChairmanName");
        String societyChairmanContact = societyInfoDoc.getString("societyChairmanContact");
        String societyTreasurerName = societyInfoDoc.getString("societyTreasurerName");
        String societyTreasurerContact = societyInfoDoc.getString("societyTreasurerContact");
        String societyAddress = societyInfoDoc.getString("societyAddress");

        tvSocietyName.setText(societyName);
        tvSocietyContact.setText(societyContact);
        tvSocietyAddress.setText(societyAddress);
        tvSocietySecretaryName.setText(societySecretaryName);
        tvSocietySecretaryContact.setText(societySecretaryContact);
        tvSocietyChairmanName.setText(societyChairmanName);
        tvSocietyChairmanContact.setText(societyChairmanContact);
        tvSocietyTreasurerName.setText(societyTreasurerName);
        tvSocietyTreasurerContact.setText(societyTreasurerContact);

    }
}