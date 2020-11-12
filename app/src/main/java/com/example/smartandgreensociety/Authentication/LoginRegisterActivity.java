package com.example.smartandgreensociety.Authentication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartandgreensociety.Database.DbOperations;
import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.example.smartandgreensociety.SP;
import com.example.smartandgreensociety.UserProfileActivity;
import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginRegisterActivity extends AppCompatActivity {

    private static final int AuthUI_Req_Code = 47312;
    Button btnLoginRegister;
    FirebaseAuth fAuth;
    FirebaseUser firebaseUser;
    DbOperations dbOperations = new DbOperations();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);


        btnLoginRegister = findViewById(R.id.btnLoginRegister);
        fAuth = FirebaseAuth.getInstance();
        List<AuthUI.IdpConfig> provider = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder()
                .build());
        FirebaseUser firebaseUser = fAuth.getCurrentUser();
        if(firebaseUser == null) {
            btnLoginRegister.setOnClickListener(v -> {

                AuthMethodPickerLayout authMethodPickerLayout = new AuthMethodPickerLayout
                        .Builder(R.layout.activity_login_register)
                        .setEmailButtonId(R.id.btnLoginRegister)
                        .build();
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                        .setAuthMethodPickerLayout(authMethodPickerLayout)
                        .setAvailableProviders(provider).setIsSmartLockEnabled(false)
                        .build(), AuthUI_Req_Code);
            });
        }else{
            Globals.USER = new User();
            Globals.USER.setUid(firebaseUser.getUid());
            Globals.USER.setDesignation(SP.getSP(LoginRegisterActivity.this,"designation"));
            dbOperations.setGlobalsUserFromCache(firebaseUser.getUid());
            dbOperations.setExistingUser(firebaseUser.getUid());
            if(Globals.USER == null){
                Log.e("Globals Not SEt","inside loginreg else part");
            }
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            LoginRegisterActivity.this.finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if(requestCode == AuthUI_Req_Code){
            if(resultCode == RESULT_OK){
                Globals.USER = new User();
                firebaseUser = fAuth.getCurrentUser();
                dbOperations.userExists(firebaseUser.getUid(), userExists -> {
                    Globals.newUser = !userExists;
                    if (!userExists) {

                        Globals.USER.setUid(firebaseUser.getUid());
                        Toast.makeText(getApplicationContext(),"Welcome New User! Please complete profile.",Toast.LENGTH_LONG)
                                .show();
                        Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                        intent.putExtra("Uid",firebaseUser.getUid());
                        dbOperations.setExistingUser(firebaseUser.getUid());
                        if(Globals.USER == null){
                            Log.e("Globals Set","Inside LoginRegNewUser"+Globals.USER.toString());
                        }
                        //Log.d("Uid ","Inside LoginRegister");
                        startActivity(intent);
                        LoginRegisterActivity.this.finish();
                    }else {
                        dbOperations.setExistingUser(firebaseUser.getUid());
                        if(Globals.USER == null){
                            Log.e("Globals Set","Inside LoginRegRegUser"+Globals.USER.toString());
                        }
                        Toast.makeText(getApplicationContext(),"Welcome Back!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("Uid",firebaseUser.getUid());
                        startActivity(intent);
                        LoginRegisterActivity.this.finish();

                    }
                    if(Globals.USER == null){
                        Log.e("Globals Set","Inside HomeActivity");
                    }
                });
            }else{
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if(response == null){
                    Log.d("Login Error","User Cancelled");
                    Toast.makeText(getApplicationContext(),"Please Sign In!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,LoginRegisterActivity.class));
                }else{
                    Log.d("Server Error",String.valueOf(response.getError()));
                    Toast.makeText(getApplicationContext(),"Server Error Occurred! Try Again Later!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}