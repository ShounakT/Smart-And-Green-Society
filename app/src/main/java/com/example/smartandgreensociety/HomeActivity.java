package com.example.smartandgreensociety;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.UserAuth.User;
import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseUser = firebaseAuth.getCurrentUser();
        dl = (DrawerLayout) findViewById(R.id.home_act);
        t = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        nv = findViewById(R.id.nav_view);
        View headerview = nv.getHeaderView(0);
        TextView navUserName = (TextView)headerview.findViewById(R.id.navUserName);
        TextView navUserEmail = (TextView)headerview.findViewById(R.id.navUserEmail);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();
            switch(id){
                case R.id.profile:
                    Toast.makeText(HomeActivity.this,"Welcome To Your Profile!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
                    this.finish();
                    break;

                case R.id.noticeBoard:
                    Toast.makeText(HomeActivity.this,"Welcome To Notice Board!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),NoticeBoardActivity.class));
                    this.finish();
                    break;
            }
            return false;
        });
        ////////////////////////////////////////////////////////////////////////////////////////
        navUserName.setText(firebaseUser.getDisplayName());
        navUserEmail.setText(firebaseUser.getEmail());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(t.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}