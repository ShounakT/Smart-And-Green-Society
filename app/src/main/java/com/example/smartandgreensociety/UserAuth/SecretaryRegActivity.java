package com.example.smartandgreensociety.UserAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartandgreensociety.Database.Db;
import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class SecretaryRegActivity extends AppCompatActivity {

    EditText userPhone,etSocietyName;
    Button btnRegister;
    Db db = new  Db();

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary_reg);

        userPhone = findViewById(R.id.userPhone);
        etSocietyName = findViewById(R.id.etsocietyName);
        btnRegister = findViewById(R.id.btnSaveSecretary);

        firebaseUser = firebaseAuth.getCurrentUser();
        Globals.society = new Society();
        Globals.user = new User();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etSocietyName.getText().toString()) || TextUtils.isEmpty(userPhone.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
                }else {


                    String societyName = etSocietyName.getText().toString().trim();
                    Globals.society.setSocietyName(societyName);
                    Globals.user.setName(firebaseUser.getDisplayName());
                    Globals.user.setDesignation("Secretary");
                    Globals.user.setEmail(firebaseUser.getEmail());
                    Globals.user.setUid(firebaseUser.getUid());
                    Globals.user.setPhone(userPhone.getText().toString().trim());

                    Map userMap = Globals.user.toMapSecretary();
                    db.createNewUser(userMap, getApplicationContext());

                    Map societyMap = Globals.society.toMapSociety();
                    db.createNewSociety(societyMap, getApplicationContext(), new Db.societyCreated() {
                        @Override
                        public void societyCreated() {
                            Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            SecretaryRegActivity.this.finish();
                        }
                    });
                }



            }
        });

    }
}