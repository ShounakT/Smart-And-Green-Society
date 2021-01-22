package com.example.smartandgreensociety.SocietyInformation;

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

import java.util.Map;

public class UpdateSocietyInfoActivity extends AppCompatActivity {

    EditText etSocietysName,etSocietysContact,etSecretarysName,etSecretarysContact,etChairmansName,
                etChairmansContact,etTreasurersName,etTreasurersContact,etSocietysAddress;
    Button btnUpdateSocInfo;
    SocietyInformation societyInformation = new SocietyInformation();
    Db db = new Db();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        UpdateSocietyInfoActivity.this.finish();
        return super.onSupportNavigateUp();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_society_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etSocietysName = findViewById(R.id.etSocietysName);
        etSocietysContact = findViewById(R.id.etSocietysContact);
        etSecretarysName = findViewById(R.id.etSecretarysName);
        etSecretarysContact = findViewById(R.id.etSecretarysContact);
        etChairmansName = findViewById(R.id.etChairmansName);
        etChairmansContact = findViewById(R.id.etChairmansContact);
        etTreasurersName = findViewById(R.id.etTreasurersName);
        etTreasurersContact = findViewById(R.id.etTreasurersContact);
        etSocietysAddress = findViewById(R.id.etSocietysAddress);
        btnUpdateSocInfo = findViewById(R.id.btnUpdateSocInfo);

        btnUpdateSocInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etSocietysName.getText().toString()) || TextUtils.isEmpty(etSocietysContact.getText().toString()) ||
                        TextUtils.isEmpty(etSecretarysName.getText().toString()) || TextUtils.isEmpty(etSecretarysContact.getText().toString()) ||
                        TextUtils.isEmpty(etChairmansName.getText().toString()) || TextUtils.isEmpty(etChairmansContact.getText().toString())   ||
                        TextUtils.isEmpty(etTreasurersName.getText().toString()) || TextUtils.isEmpty(etTreasurersContact.getText().toString()) ||
                        TextUtils.isEmpty(etSocietysAddress.getText().toString())){

                    Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();

                }else {
                    String societyName = etSocietysName.getText().toString().trim();
                    String societyContact = etSocietysContact.getText().toString().trim();
                    String secretaryName = etSecretarysName.getText().toString().trim();
                    String secretaryContact = etSecretarysContact.getText().toString().trim();
                    String chairmanName = etChairmansName.getText().toString().trim();
                    String chairmanContact = etChairmansContact.getText().toString().trim();
                    String treasurerName = etTreasurersName.getText().toString().trim();
                    String treasurerContact = etTreasurersContact.getText().toString().trim();
                    String societyAddress = etSocietysAddress.getText().toString().trim();

                    societyInformation.setSocietyName(societyName);
                    societyInformation.setSocietyContact(societyContact);
                    societyInformation.setSocietySecretaryName(secretaryName);
                    societyInformation.setSocietySecretaryContact(secretaryContact);
                    societyInformation.setSocietyChairmanName(chairmanName);
                    societyInformation.setSocietyChairmanContact(chairmanContact);
                    societyInformation.setSocietyTreasurerName(treasurerName);
                    societyInformation.setSocietyTreasurerContact(treasurerContact);
                    societyInformation.setSocietyAddress(societyAddress);

                    Map societyInfoMap = societyInformation.toSocietyInfoMap();
                    db.addSocietyInformation(societyInfoMap);

                    startActivity(new Intent(UpdateSocietyInfoActivity.this, HomeActivity.class));
                    UpdateSocietyInfoActivity.this.finish();
                }

            }
        });
    }
}