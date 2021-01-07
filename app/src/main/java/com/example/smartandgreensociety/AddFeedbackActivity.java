package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;

import java.util.Map;

public class AddFeedbackActivity extends AppCompatActivity {

    EditText etFeedbackTitle, etFeedbackContent;
    Button btnAddFeedback;
    SocietyFeedback societyFeedback = new SocietyFeedback();
    Db db = new Db();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        etFeedbackTitle = findViewById(R.id.etFeedbackTitle);
        etFeedbackContent = findViewById(R.id.etFeedbackContent);
        btnAddFeedback = findViewById(R.id.btnAddFeedback);

        btnAddFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String feedbackTitle = etFeedbackTitle.getText().toString().trim();
                String feedbackContent = etFeedbackContent.getText().toString().trim();
                societyFeedback.setFeedbackHeading(feedbackTitle);
                societyFeedback.setFeedbackContent(feedbackContent);
                Map societyFeedbackMap = societyFeedback.toFeedbackMap();
                db.addFeedback(societyFeedbackMap);
                startActivity(new Intent(AddFeedbackActivity.this,HomeActivity.class));
                AddFeedbackActivity.this.finish();
            }
        });
    }
}