package com.example.smartandgreensociety.Polling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartandgreensociety.Database.Db;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;

import java.util.ArrayList;
import java.util.List;

public class CreatePollActivity extends AppCompatActivity {

    EditText etPollQuestion;
    Button btnCreate;
    Db db = new Db();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        CreatePollActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPollQuestion = findViewById(R.id.etPollQuestion);
        btnCreate = findViewById(R.id.btnConfirmPoll);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etPollQuestion.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
                }else {
                    String ques = etPollQuestion.getText().toString().trim();
                    List<String> options = new ArrayList<>();

                    options.add("Yes");
                    options.add("No");

                    db.createPoll(ques, options);
                    startActivity(new Intent(CreatePollActivity.this, HomeActivity.class));
                    CreatePollActivity.this.finish();
                }
            }
        });
    }
}