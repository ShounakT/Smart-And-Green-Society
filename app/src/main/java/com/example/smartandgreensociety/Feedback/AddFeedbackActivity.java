package com.example.smartandgreensociety.Feedback;

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

import java.util.Map;

public class AddFeedbackActivity extends AppCompatActivity {

    EditText etFeedbackContent;
    Button btnAddFeedback;
    SocietyFeedback societyFeedback = new SocietyFeedback();
    Db db = new Db();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        AddFeedbackActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etFeedbackContent = findViewById(R.id.etFeedbackContent);
        btnAddFeedback = findViewById(R.id.btnAddFeedback);

        btnAddFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etFeedbackContent.getText().toString())){

                    Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();

                }else {
                    String feedbackTitle = Globals.user.getName();
                    String feedbackContent = etFeedbackContent.getText().toString().trim();
                    societyFeedback.setFeedbackHeading(feedbackTitle);
                    societyFeedback.setFeedbackContent(feedbackContent);
                    Map societyFeedbackMap = societyFeedback.toFeedbackMap();
                    db.addFeedback(societyFeedbackMap);
                    startActivity(new Intent(AddFeedbackActivity.this, HomeActivity.class));
                    AddFeedbackActivity.this.finish();
                }
            }
        });
    }
}