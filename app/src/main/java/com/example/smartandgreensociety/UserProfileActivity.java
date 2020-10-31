package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import Authentication.LoginRegisterActivity;

public class UserProfileActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        fAuth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btnLogout);

        checkUserLogin();

        btnLogout.setOnClickListener(v -> {
            signOutUser();
        });
    }
    private void checkUserLogin(){
        if(fAuth.getCurrentUser() == null){
            Toast.makeText(getApplicationContext(),"Please Login First!",Toast.LENGTH_SHORT)
                    .show();
            startActivity(new Intent(getApplicationContext(), LoginRegisterActivity.class));
            finish();
        }
    }

    private void signOutUser(){
        fAuth.signOut();
        Toast.makeText(getApplicationContext(),"Successfully Logged Out!",Toast.LENGTH_SHORT)
                .show();
        startActivity(new Intent(getApplicationContext(),LoginRegisterActivity.class));
        this.finish();
    }
}