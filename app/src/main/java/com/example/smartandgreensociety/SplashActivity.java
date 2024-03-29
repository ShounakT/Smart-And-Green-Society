package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.smartandgreensociety.UserAuth.LoginRegisterActivity;

public class SplashActivity extends AppCompatActivity {

    Handler handler;
    // Splash screen will be visible for 1.5 seconds
    private static final int SPLASH_TME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //After splash screen is shown, push user for authentication.
                startActivity(new Intent(SplashActivity.this, LoginRegisterActivity.class));
                SplashActivity.this.finish();

            }
        },SPLASH_TME);

    }
}