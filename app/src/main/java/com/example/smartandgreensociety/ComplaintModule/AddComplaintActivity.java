package com.example.smartandgreensociety.ComplaintModule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartandgreensociety.DatabaseOperations.Db;
import com.example.smartandgreensociety.HomeActivity;
import com.example.smartandgreensociety.R;

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

        etComplaintTitle = findViewById(R.id.etComplaintTitle);
        etComplaintContent = findViewById(R.id.etComplaintContent);
        btnSubmitComplaint = findViewById(R.id.btnSubmitComplaint);

        btnSubmitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String complaintTitle = etComplaintTitle.getText().toString().trim();
                String complaintContent = etComplaintContent.getText().toString().trim();
                societyComplaint.setComplaintHeading(complaintTitle);
                societyComplaint.setComplaintContent(complaintContent);
                Map societyComplaintMap = societyComplaint.toComplaintMap();
                db.addComplaint(societyComplaintMap);
                startActivity(new Intent(AddComplaintActivity.this, HomeActivity.class));
                AddComplaintActivity.this.finish();
            }
        });
    }
}