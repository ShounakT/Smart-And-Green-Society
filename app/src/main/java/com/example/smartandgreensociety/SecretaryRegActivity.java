package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.UserAuth.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class SecretaryRegActivity extends AppCompatActivity {

    EditText userPhone,societyName;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary_reg);

        userPhone = findViewById(R.id.userPhone);
        societyName = findViewById(R.id.societyName);
        btnRegister = findViewById(R.id.btnSaveSecretary);
        Db db = new  Db();
        User user = new User();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setName(firebaseUser.getDisplayName());
                user.setDesignation("Secretary");
                user.setEmail(firebaseUser.getEmail());
                user.setUid(firebaseUser.getUid());
                user.setPhone(userPhone.getText().toString().trim());

                Map userMap = user.toMapSecretary();
                db.createNewUser(userMap, getApplicationContext());
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                SecretaryRegActivity.this.finish();

            }
        });

    }
}