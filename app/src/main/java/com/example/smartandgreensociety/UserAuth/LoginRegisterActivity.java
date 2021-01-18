package com.example.smartandgreensociety.UserAuth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginRegisterActivity extends AppCompatActivity {

    private static final int AuthUI_Req_Code = 47312;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;
    Db db = new Db();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);


        firebaseUser = firebaseAuth.getCurrentUser();

        List<AuthUI.IdpConfig> provider = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder()
                .build());

        if(firebaseUser == null) {
            // User Is Not Registered OR Registered, But Not Signed In
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(provider)
                    .setTheme(R.style.Theme_SmartAndGreenSociety).setIsSmartLockEnabled(false)
                    .build(), AuthUI_Req_Code);
        }else{
            //User Is Registered & Opened app
            db.setUserDetailsGloballyWaitForCallback(firebaseUser.getUid(), getApplicationContext(), new Db.globalUserSet() {
                @Override
                public void detailsSet() {
                    startActivity(new Intent(LoginRegisterActivity.this, HomeActivity.class));
                    LoginRegisterActivity.this.finish();
                }
            });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == AuthUI_Req_Code) {
            if (resultCode == Activity.RESULT_OK) {
                firebaseAuth = firebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                db = new Db();
                db.isNewUserCallback(firebaseUser.getUid(), new Db.NewUser() {
                    @Override
                    public void isNewUserCallback(boolean isNewUser) {

                        Globals.newUser = isNewUser;
                        //User is Not Registered
                        if (Globals.newUser) {

                            Globals.newUser = false;
                            startActivity(new Intent(LoginRegisterActivity.this, ResSecActivity.class));
                            LoginRegisterActivity.this.finish();
                            Toast.makeText(getApplicationContext(), "Register As A...",
                                    Toast.LENGTH_SHORT).show();
                        //User is Registered
                        } else {

                            db.setUserDetailsGloballyWaitForCallback(firebaseUser.getUid(), getApplicationContext(), new Db.globalUserSet() {
                                @Override
                                public void detailsSet() {
                                    startActivity(new Intent(LoginRegisterActivity.this, HomeActivity.class));
                                    LoginRegisterActivity.this.finish();
                                    Toast.makeText(getApplicationContext(), "Welcome Back!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }

                });
            }else {
                //Error Due Cancellation
                IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
                idpResponse.getError();
                Toast.makeText(getApplicationContext(), "Something went wrong... Try again " +
                        "later!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}