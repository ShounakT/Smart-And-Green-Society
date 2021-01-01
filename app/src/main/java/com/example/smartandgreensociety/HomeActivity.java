package com.example.smartandgreensociety;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.UserAuth.User;
import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Button btnLogOut;
    private static final int AuthUI_Req_Code = 47312;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;
    Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        firebaseUser = firebaseAuth.getCurrentUser();
        btnLogOut = findViewById(R.id.btnLogOut);
        List<AuthUI.IdpConfig> provider = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder()
                .build());

        if(firebaseUser == null) {
            // User Is Not Registered OR Registered, But Not Signed In
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(provider).setIsSmartLockEnabled(false)
                    .build(), AuthUI_Req_Code);
        }else{
            //User Is Registered & Opened app
            Toast.makeText(getApplicationContext(),"Welcome!",Toast.LENGTH_SHORT).show();
        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                HomeActivity.this.finish();

                Toast.makeText(getApplicationContext(),"Successfully Logged Out!",
                        Toast.LENGTH_SHORT).show();

            }
        });

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
                        if (Globals.newUser) {

                            Globals.newUser = false;
                            HomeActivity.this.startActivity(new Intent(HomeActivity.this, ResSecActivity.class));
                            HomeActivity.this.finish();
                            Toast.makeText(HomeActivity.this.getApplicationContext(), "Please Complete Profile...",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            HomeActivity.this.startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                            HomeActivity.this.finish();
                            Toast.makeText(HomeActivity.this.getApplicationContext(), "Welcome Back!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }


                });
            } else {
                //Error Due Cancellation
                IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
                idpResponse.getError();
                Toast.makeText(getApplicationContext(), "Something went wrong... Try again " +
                        "later!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}