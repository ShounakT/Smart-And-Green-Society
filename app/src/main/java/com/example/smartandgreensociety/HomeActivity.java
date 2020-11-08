package com.example.smartandgreensociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartandgreensociety.Database.DbOperations;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.smartandgreensociety.Authentication.LoginRegisterActivity;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseUser firebaseUser;


    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private DbOperations db = new DbOperations();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        dl = (DrawerLayout) findViewById(R.id.act_home);
        t = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        fAuth = FirebaseAuth.getInstance();
        firebaseUser = fAuth.getCurrentUser();
        nv = findViewById(R.id.nv);
        View headerview = nv.getHeaderView(0);
        TextView navUserName = (TextView)headerview.findViewById(R.id.navUserName);
        TextView navUserEmail = (TextView)headerview.findViewById(R.id.navUserEmail);

        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        checkUserLogin();
        if(firebaseUser != null){
            db.setExistingUser(firebaseUser.getUid());
        }

        //Log.e("Credentials",firebaseUser.getDisplayName() + firebaseUser.getEmail());
        navUserName.setText(firebaseUser.getDisplayName());
        navUserEmail.setText(firebaseUser.getEmail());


        nv.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();
            switch(id){
                case R.id.profile:
                    Toast.makeText(HomeActivity.this,"Welcome To Your Profile!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
                    break;

                case R.id.noticeBoard:
                    Toast.makeText(HomeActivity.this,"Welcome To Notice Board!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),NoticeBoardActivity.class));
                    break;
            }
            return false;
        });


    }

    private void checkUserLogin(){
        if(fAuth.getCurrentUser() == null){
            //User not logged in
            Toast.makeText(getApplicationContext(),"Please Login First!",Toast.LENGTH_SHORT)
                    .show();
            startActivity(new Intent(HomeActivity.this, LoginRegisterActivity.class));
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(t.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}