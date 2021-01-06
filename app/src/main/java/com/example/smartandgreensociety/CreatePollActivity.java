package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;

import java.util.ArrayList;
import java.util.List;

public class CreatePollActivity extends AppCompatActivity {

    EditText etPollQuestion;
    Button btnCreate;
    Db db = new Db();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        etPollQuestion = findViewById(R.id.etPollQuestion);
        btnCreate = findViewById(R.id.btnConfirmPoll);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ques = etPollQuestion.getText().toString().trim();
                List<String> options = new ArrayList<>();

                options.add("Yes");
                options.add("No");

                db.createPoll(ques,options);
                startActivity(new Intent(CreatePollActivity.this, HomeActivity.class));
                CreatePollActivity.this.finish();
            }
        });
    }
}