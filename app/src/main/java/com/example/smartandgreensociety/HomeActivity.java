package com.example.smartandgreensociety;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.UserAuth.SP;
import com.example.smartandgreensociety.UserAuth.Society;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;
    Db db = new Db();
    Button btnAddResidents, btnCreatePoll, btnViewPolls, btnAddNotice, btnAddComplaint,
            btnGiveFeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        dl = (DrawerLayout) findViewById(R.id.home_act);
        t = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        nv = findViewById(R.id.nav_view);
        View headerview = nv.getHeaderView(0);
        TextView navUserName = headerview.findViewById(R.id.navUserName);
        TextView navUserEmail = headerview.findViewById(R.id.navUserEmail);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseUser = firebaseAuth.getCurrentUser();
        btnAddResidents = findViewById(R.id.btnAddResidents);
        btnCreatePoll = findViewById(R.id.btnCreatePoll);
        btnViewPolls = findViewById(R.id.btnViewPolls);
        btnAddNotice = findViewById(R.id.btnAddNotice);
        btnAddComplaint = findViewById(R.id.btnAddComplaint);
        btnGiveFeedback = findViewById(R.id.btnGiveFeedback);

        nv.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();
            switch(id){
                case R.id.profile:
                    Toast.makeText(HomeActivity.this,"Welcome To Your Profile!",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
                    this.finish();
                    break;

                case R.id.noticeBoard:
                    Toast.makeText(HomeActivity.this,"Welcome To Notice Board!",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),NoticeBoardActivity.class));
                    this.finish();
                    break;

                case R.id.societyInfo:
                    Toast.makeText(HomeActivity.this,"Welcome To Society Information!",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),SocietyInfoActivity.class));
                    this.finish();
                    break;

                case R.id.societyComplaints:
                    Toast.makeText(HomeActivity.this,"Welcome To Complaint Section!",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),SocietyComplaintActivity.class));
                    this.finish();
                    break;

                case R.id.societyFeedback:
                    Toast.makeText(HomeActivity.this,"Welcome To Feedback Section!",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),SocietyFeedbackActivity.class));
                    this.finish();
                    break;
            }
            return false;
        });
        ////////////////////////////////////////////////////////////////////////////////////////
        navUserName.setText(firebaseUser.getDisplayName());
        navUserEmail.setText(firebaseUser.getEmail());

        if(SP.getSP(getApplicationContext(),"designation").equals("Secretary")){
            btnAddResidents.setVisibility(View.VISIBLE);
            btnCreatePoll.setVisibility(View.VISIBLE);
            btnAddNotice.setVisibility(View.VISIBLE);
        }
        if(SP.getSP(getApplicationContext(),"designation").equals("Resident")){
            btnViewPolls.setVisibility(View.VISIBLE);
        }

        btnAddResidents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AddResidentsActivity.class));
                HomeActivity.this.finish();
            }
        });

        btnCreatePoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, CreatePollActivity.class));
                HomeActivity.this.finish();

            }
        });

        btnViewPolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,PollsActivity.class));
                HomeActivity.this.finish();
            }
        });

        btnAddNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AddNoticeActivity.class));
                HomeActivity.this.finish();
            }
        });

        btnGiveFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AddFeedbackActivity.class));
                HomeActivity.this.finish();
            }
        });



    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(t.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}