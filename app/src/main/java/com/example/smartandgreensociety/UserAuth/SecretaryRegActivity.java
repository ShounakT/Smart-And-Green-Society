package com.example.smartandgreensociety.UserAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.example.smartandgreensociety.SocietyInformation.UpdateSocietyInfoActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class SecretaryRegActivity extends AppCompatActivity {

    EditText userPhone,etSocietyName;
    Button btnRegister;
    Db db = new  Db();

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary_reg);

        userPhone = findViewById(R.id.userPhone);
        etSocietyName = findViewById(R.id.etsocietyName);
        btnRegister = findViewById(R.id.btnSaveSecretary);

        firebaseUser = firebaseAuth.getCurrentUser();
        Globals.society = new Society();
        Globals.user = new User();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String societyName = etSocietyName.getText().toString().trim();
                Globals.society.setSocietyName(societyName);
                Globals.user.setName(firebaseUser.getDisplayName());
                Globals.user.setDesignation("Secretary");
                Globals.user.setEmail(firebaseUser.getEmail());
                Globals.user.setUid(firebaseUser.getUid());
                Globals.user.setPhone(userPhone.getText().toString().trim());

                Map userMap = Globals.user.toMapSecretary();
                db.createNewUser(userMap, getApplicationContext());

                Map societyMap = Globals.society.toMapSociety();
                db.createNewSociety(societyMap,getApplicationContext());

                startActivity(new Intent(getApplicationContext(), UpdateSocietyInfoActivity.class));
                SecretaryRegActivity.this.finish();

            }
        });

    }
}