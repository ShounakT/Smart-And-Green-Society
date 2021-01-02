package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.UserAuth.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    Button btnLogOut;
    TextView tvUserName, tvUserEmail, tvUserDesignation, tvUserPhone, tvSocietyId;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;
    Db db = new Db();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseUser = firebaseAuth.getCurrentUser();



        btnLogOut = findViewById(R.id.btnLogout);
        tvUserName = findViewById(R.id.tvUserWelcome);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvUserDesignation = findViewById(R.id.tvUserDesignation);
        tvUserPhone = findViewById(R.id.tvUserPhone);
        tvSocietyId = findViewById(R.id.tvUserSocietyId);

        tvUserName.setText(Globals.user.getName());
        tvUserEmail.setText(Globals.user.getEmail());
        tvUserPhone.setText(Globals.user.getPhone());
        tvUserDesignation.setText(Globals.user.getDesignation());
        tvSocietyId.setText(Globals.user.getSocietyRef());


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), LoginRegisterActivity.class));
                UserProfileActivity.this.finish();

                Toast.makeText(getApplicationContext(),"Successfully Logged Out!",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        UserProfileActivity.this.finish();
        return super.onSupportNavigateUp();
    }
}