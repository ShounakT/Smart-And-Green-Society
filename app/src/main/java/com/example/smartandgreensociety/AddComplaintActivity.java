package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddComplaintActivity extends AppCompatActivity {

    EditText etComplaintTitle, etComplaintContent;
    Button btnSubmitComplaint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);

        etComplaintTitle = findViewById(R.id.etComplaintName);
        etComplaintContent = findViewById(R.id.etComplaintContent);
        btnSubmitComplaint = findViewById(R.id.btnSubmitComplaint);

        btnSubmitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db
            }
        });
    }
}