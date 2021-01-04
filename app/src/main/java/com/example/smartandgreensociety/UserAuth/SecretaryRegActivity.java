package com.example.smartandgreensociety.UserAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class SecretaryRegActivity extends AppCompatActivity {

    EditText userPhone,etSocietyName;
    Button btnRegister;
    Db db = new  Db();
    User user = new User();
    Society society = new Society();
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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String societyName = etSocietyName.getText().toString().trim();
                society.setSocietyName(societyName);
                user.setName(firebaseUser.getDisplayName());
                user.setDesignation("Secretary");
                user.setEmail(firebaseUser.getEmail());
                user.setUid(firebaseUser.getUid());
                user.setPhone(userPhone.getText().toString().trim());

                Map userMap = user.toMapSecretary();
                db.createNewUser(userMap, getApplicationContext());

                Map societyMap = society.toMapSociety();
                db.createNewSociety(societyMap,getApplicationContext());

                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                SecretaryRegActivity.this.finish();

            }
        });

    }
}