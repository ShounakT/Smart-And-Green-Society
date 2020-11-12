package com.example.smartandgreensociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

import com.example.smartandgreensociety.Authentication.LoginRegisterActivity;
import com.example.smartandgreensociety.Authentication.User;
import com.example.smartandgreensociety.Database.DbOperations;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseUser firebaseUser;
    Switch switchIsSecretary;
    Button btnLogout,btnSave;
    TextView tvUserWelcome,tvSocietyId,tvIsUserSecretary;
    Boolean isSecretary = false;
    EditText etUserName,etUserEmail,etUserPhone,etUserDesignation,etUserSocietyId;

    User user = new User();
    DbOperations dbOperations = new DbOperations();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        UserProfileActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fAuth = FirebaseAuth.getInstance();
        firebaseUser = fAuth.getCurrentUser();

        btnLogout = findViewById(R.id.btnLogout);
        btnSave = findViewById(R.id.btnSave);
        tvUserWelcome = findViewById(R.id.tvUserWelcome);
        tvSocietyId = findViewById(R.id.tvSocietyId);
        tvIsUserSecretary = findViewById(R.id.tvIsUserSercretary);
        etUserEmail = findViewById(R.id.etUserEmail);
        etUserPhone = findViewById(R.id.etUserPhone);
        etUserName = findViewById(R.id.etUserName);
        etUserDesignation = findViewById(R.id.etUserDesignation);
        etUserSocietyId = findViewById(R.id.etUserSocietyId);
        switchIsSecretary = findViewById(R.id.switchIsSecretary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //checkNewUserLogin();
        if(Globals.newUser){
            setNewUserProfile();
        }else{

            tvUserWelcome.setText(firebaseUser.getDisplayName());
            etUserName.setText(Globals.USER.getName());
            etUserEmail.setText(Globals.USER.getEmail());
            etUserPhone.setText(Globals.USER.getPhone());
            etUserDesignation.setText(Globals.USER.getDesignation());
            tvIsUserSecretary.setVisibility(View.GONE);
            switchIsSecretary.setVisibility(View.GONE);
            if(Globals.USER.getDesignation().equals("Secretary")){
                tvSocietyId.setVisibility(View.VISIBLE);
                etUserSocietyId.setVisibility(View.VISIBLE);
                etUserSocietyId.setText(Globals.USER.getSocietyId());
            }
        }

        btnLogout.setOnClickListener(v -> {
            signOutUser();
        });
    }



    private void setNewUserProfile() {

        tvUserWelcome.setText(firebaseUser.getDisplayName());
        Toast.makeText(getApplicationContext(),"Please fill details properly!",
                Toast.LENGTH_SHORT).show();
        btnSave.setVisibility(View.VISIBLE);
        etUserName.setEnabled(true);
        etUserEmail.setEnabled(true);
        etUserPhone.setEnabled(true);
        etUserDesignation.setEnabled(true);
        etUserSocietyId.setEnabled(true);
        switchIsSecretary.setOnCheckedChangeListener((buttonView, isChecked) -> {
             if(switchIsSecretary.isChecked()){
                 isSecretary = true;
                 tvSocietyId.setVisibility(View.VISIBLE);
                 etUserSocietyId.setVisibility(View.VISIBLE);
             }else{
                 isSecretary = false;
                 tvSocietyId.setVisibility(View.GONE);
                 etUserSocietyId.setVisibility(View.GONE);
             }
        });
        btnSave.setOnClickListener(v -> {
            if(TextUtils.isEmpty(etUserName.getText()) || TextUtils.isEmpty(etUserEmail.getText())
                    || TextUtils.isEmpty(etUserPhone.getText()) ||
                    TextUtils.isEmpty(etUserDesignation.getText())) {
                if(isSecretary) {
                    if(TextUtils.isEmpty(etUserSocietyId.getText())) {
                        Toast.makeText(getApplicationContext(),"Please Enter All Fields!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(getApplicationContext(),"Please Enter All Fields!",
                        Toast.LENGTH_SHORT).show();
            }else{
                user.setName(etUserName.getText().toString().trim());
                user.setEmail(etUserEmail.getText().toString().trim());
                user.setPhone(etUserPhone.getText().toString().trim());
                user.setDesignation(etUserDesignation.getText().toString().trim());
                String Uid = getIntent().getStringExtra("Uid");
                if(isSecretary){
                    user.setSocietyId(etUserSocietyId.getText().toString().trim());
                    Map mapOfSecretary = user.toMapSecretary(user.getName(),user.getEmail(),
                            user.getDesignation(),user.getPhone(),user.getSocietyId());
                    dbOperations.addNewUserAsSecretary(mapOfSecretary,Uid);
                    Toast.makeText(getApplicationContext(),"Profile Creation Successful!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Map mapOfResident = user.toMapResident(user.getName(),user.getEmail(),
                            user.getDesignation(),user.getPhone());
                    dbOperations.addNewUserAsResident(mapOfResident,Uid);
                    Toast.makeText(getApplicationContext(),"Profile Creation Successful!",
                            Toast.LENGTH_SHORT).show();
                }
                Globals.newUser = false;
            }
            etUserName.setEnabled(false);
            etUserEmail.setEnabled(false);
            etUserPhone.setEnabled(false);
            etUserDesignation.setEnabled(false);
            etUserSocietyId.setEnabled(false);
            btnSave.setVisibility(View.GONE);
            tvIsUserSecretary.setVisibility(View.GONE);
            switchIsSecretary.setVisibility(View.GONE);
            dbOperations.setExistingUser(firebaseUser.getUid());
            startActivity(new Intent(UserProfileActivity.this,HomeActivity.class));
            UserProfileActivity.this.finish();
        });
    }

    private void signOutUser(){

        fAuth.signOut();
        Toast.makeText(getApplicationContext(),"Successfully Logged Out!",Toast.LENGTH_SHORT)
                .show();
        Globals.USER = null;
        startActivity(new Intent(UserProfileActivity.this,LoginRegisterActivity.class));
        UserProfileActivity.this.finish();

    }
}