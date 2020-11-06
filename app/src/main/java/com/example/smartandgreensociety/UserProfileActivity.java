package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Map;

import Authentication.LoginRegisterActivity;
import Authentication.User;
import Database.DbOperations;

public class UserProfileActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    Switch switchIsSecretary;
    Button btnLogout,btnSave,btnEdit;
    TextView tvUserWelcome,tvSocietyId;
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
        etUserEmail = findViewById(R.id.etUserEmail);
        etUserPhone = findViewById(R.id.etUserPhone);
        etUserName = findViewById(R.id.etUserName);
        etUserDesignation = findViewById(R.id.etUserDesignation);
        etUserSocietyId = findViewById(R.id.etUserSocietyId);
        switchIsSecretary = findViewById(R.id.switchIsSecretary);


        //checkUserLogin();
        if(fAuth.getCurrentUser().getMetadata().getCreationTimestamp() ==
                fAuth.getCurrentUser().getMetadata().getLastSignInTimestamp()){
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
            Log.e("SecretaryAdded","in onclick!");
            if(TextUtils.isEmpty(etUserName.getText()) || TextUtils.isEmpty(etUserEmail.getText())
                    || TextUtils.isEmpty(etUserPhone.getText()) || TextUtils.isEmpty(etUserDesignation.getText())){
                if(isSecretary){
                    if(TextUtils.isEmpty(etUserSocietyId.getText())){
                        Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
            }else{
                Log.e("SecretaryAdded","in else");
                user.setName(etUserName.getText().toString().trim());
                user.setEmail(etUserEmail.getText().toString().trim());
                user.setPhone(Integer.parseInt(etUserPhone.getText().toString().trim()));
                user.setDesignation(etUserDesignation.getText().toString().trim());
                if(isSecretary){
                    Log.e("SecretaryAdded","in if");
                    user.setSocietyId(etUserSocietyId.getText().toString().trim());
                    Map mapOfSecretary = user.toMapSecretary(user.getName(),user.getEmail(),user.getDesignation(),user.getPhone(),user.getSocietyId());
                    dbOperations.addNewUserAsSecretary(mapOfSecretary);
                }
                //dboperations.addNewUser()
            }
        });
    }
    /*private void checkUserLogin(){
        if(fAuth.getCurrentUser() == null){
            Toast.makeText(getApplicationContext(),"Please Login First!",Toast.LENGTH_SHORT)
                    .show();
            startActivity(new Intent(getApplicationContext(), LoginRegisterActivity.class));
            finish();
        }
    }*/

    private void signOutUser(){
        fAuth.signOut();
        Toast.makeText(getApplicationContext(),"Successfully Logged Out!",Toast.LENGTH_SHORT)
                .show();
        startActivity(new Intent(getApplicationContext(),LoginRegisterActivity.class));
        this.finish();
    }
}