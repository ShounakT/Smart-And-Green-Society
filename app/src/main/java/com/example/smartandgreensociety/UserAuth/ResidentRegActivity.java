package com.example.smartandgreensociety.UserAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class ResidentRegActivity extends AppCompatActivity {

    EditText userPhone;
    Button btnRegister;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_reg);

        userPhone = findViewById(R.id.userPhone);
        btnRegister = findViewById(R.id.btnSaveResident);
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        db = new Db();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Globals.user.setName(firebaseUser.getDisplayName());
                Globals.user.setDesignation("Resident");
                Globals.user.setEmail(firebaseUser.getEmail());
                Globals.user.setUid(firebaseUser.getUid());
                Globals.user.setPhone(userPhone.getText().toString().trim());

                Map userMap = Globals.user.toMapResident();

                db.createNewUser(userMap, getApplicationContext());
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                ResidentRegActivity.this.finish();
            }
        });

    }
}