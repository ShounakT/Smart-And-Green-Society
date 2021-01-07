package com.example.smartandgreensociety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;

import java.util.Map;

public class AddComplaintActivity extends AppCompatActivity {

    EditText etComplaintTitle, etComplaintContent;
    Button btnSubmitComplaint;
    SocietyComplaint societyComplaint = new SocietyComplaint();
    Db db = new Db();
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

                String complaintTitle = etComplaintTitle.getText().toString().trim();
                String complaintContent = etComplaintContent.getText().toString().trim();
                societyComplaint.setComplaintHeading(complaintTitle);
                societyComplaint.setComplaintContent(complaintContent);
                Map societyComplaintMap = societyComplaint.toCComplaintMap();
                db.addComplaint(societyComplaintMap);
            }
        });
    }
}