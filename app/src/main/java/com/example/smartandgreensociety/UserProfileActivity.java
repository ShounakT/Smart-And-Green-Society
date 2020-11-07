package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

import com.example.smartandgreensociety.Authentication.LoginRegisterActivity;
import com.example.smartandgreensociety.Authentication.User;
import com.example.smartandgreensociety.Database.DbOperations;

public class UserProfileActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    Switch switchIsSecretary;
    Button btnLogout,btnSave,btnEdit;
    TextView tvUserWelcome,tvSocietyId,tvIsUserSecretary;
    EditText etUserName,etUserEmail,etUserPhone,etUserDesignation,etUserSocietyId;
    User user = new User();
    DbOperations dbOperations = new DbOperations();
    Boolean isSecretary = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fAuth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btnLogout);
        btnSave = findViewById(R.id.btnSave);
        btnEdit = findViewById(R.id.btnUserProfileEdit);
        tvUserWelcome = findViewById(R.id.tvUserWelcome);
        tvSocietyId = findViewById(R.id.tvSocietyId);
        tvIsUserSecretary = findViewById(R.id.tvIsUserSercretary);
        etUserEmail = findViewById(R.id.etUserEmail);
        etUserPhone = findViewById(R.id.etUserPhone);
        etUserName = findViewById(R.id.etUserName);
        etUserDesignation = findViewById(R.id.etUserDesignation);
        etUserSocietyId = findViewById(R.id.etUserSocietyId);
        switchIsSecretary = findViewById(R.id.switchIsSecretary);


        //checkUserLogin();
        if(Globals.newUser){
            setNewUserProfile();
        }
        btnEdit.setOnClickListener(v -> {
            updateExistingUserProfile();
        });
        btnLogout.setOnClickListener(v -> {
            signOutUser();
        });
    }

    private void updateExistingUserProfile(){

        btnSave.setOnClickListener(v -> {
            //dboperations.updateExistingUser()
        });

    }

    private void setNewUserProfile(){

        Toast.makeText(getApplicationContext(),"Please fill details properly!",Toast.LENGTH_SHORT).show();
        btnEdit.setVisibility(View.GONE);
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
                    || TextUtils.isEmpty(etUserPhone.getText()) || TextUtils.isEmpty(etUserDesignation.getText())){
                if(isSecretary){
                    if(TextUtils.isEmpty(etUserSocietyId.getText())){
                        Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
            }else{
                user.setName(etUserName.getText().toString().trim());
                user.setEmail(etUserEmail.getText().toString().trim());
                user.setPhone(etUserPhone.getText().toString().trim());
                user.setDesignation(etUserDesignation.getText().toString().trim());
                String Uid = getIntent().getStringExtra("Uid");
                if(isSecretary){
                    user.setSocietyId(etUserSocietyId.getText().toString().trim());
                    Map mapOfSecretary = user.toMapSecretary(user.getName(),user.getEmail(),user.getDesignation(),user.getPhone(),user.getSocietyId());
                    dbOperations.addNewUserAsSecretary(mapOfSecretary,Uid);
                    Toast.makeText(getApplicationContext(),"Profile Creation Successful!",Toast.LENGTH_SHORT).show();
                }else{
                    Map mapOfResident = user.toMapResident(user.getName(),user.getEmail(),user.getDesignation(),user.getPhone());
                    dbOperations.addNewUserAsResident(mapOfResident,Uid);
                    Toast.makeText(getApplicationContext(),"Profile Creation Successful!",Toast.LENGTH_SHORT).show();
                }
            }
            etUserName.setEnabled(false);
            etUserEmail.setEnabled(false);
            etUserPhone.setEnabled(false);
            etUserDesignation.setEnabled(false);
            etUserSocietyId.setEnabled(false);
            tvIsUserSecretary.setVisibility(View.GONE);
            switchIsSecretary.setVisibility(View.GONE);
            btnEdit.setVisibility(View.VISIBLE);
            startActivity(new Intent(UserProfileActivity.this,HomeActivity.class));
            UserProfileActivity.this.finish();
        });
    }


    private void signOutUser(){

        fAuth.signOut();
        Toast.makeText(getApplicationContext(),"Successfully Logged Out!",Toast.LENGTH_SHORT)
                .show();
        startActivity(new Intent(getApplicationContext(),LoginRegisterActivity.class));
        this.finish();
    }
}