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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartandgreensociety.Authentication.User;
import com.example.smartandgreensociety.Database.DbOperations;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.smartandgreensociety.Authentication.LoginRegisterActivity;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser  = fAuth.getCurrentUser();
    Button btn_Create_A_Society;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private DbOperations dbOperations = new DbOperations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_Create_A_Society = findViewById(R.id.btn_Create_A_Society);
        dl = (DrawerLayout) findViewById(R.id.act_home);
        t = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        nv = findViewById(R.id.nv);
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

        ///////////////////////////////////////////////////////////////



            if(Globals.USER == null){
                Log.e("Globals not Set","Inside HomeActivity");
            }
            navUserName.setText(firebaseUser.getDisplayName());
            navUserEmail.setText(firebaseUser.getEmail());
            Log.e("TAG","desigantion"+Globals.USER.getDesignation());
            if(Globals.USER.getDesignation().equals("Secretary") && Globals.SOCIETY == null){
                btn_Create_A_Society.setVisibility(View.VISIBLE);
                btn_Create_A_Society.setOnClickListener(v -> {
                    startActivity(new Intent(HomeActivity.this,CreateSociety.class));
                    this.finish();
                });
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