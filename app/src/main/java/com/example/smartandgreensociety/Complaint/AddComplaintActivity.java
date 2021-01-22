package com.example.smartandgreensociety.Complaint;

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

import java.util.Map;

public class AddComplaintActivity extends AppCompatActivity {

    EditText etComplaintTitle, etComplaintContent;
    Button btnSubmitComplaint;
    SocietyComplaint societyComplaint = new SocietyComplaint();
    Db db = new Db();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        AddComplaintActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etComplaintTitle = findViewById(R.id.etComplaintTitle);
        etComplaintContent = findViewById(R.id.etComplaintContent);
        btnSubmitComplaint = findViewById(R.id.btnSubmitComplaint);

        btnSubmitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etComplaintContent.getText().toString()) ||
                        TextUtils.isEmpty(etComplaintTitle.getText().toString())){

                    Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();

                }else {
                    String complaintTitle = etComplaintTitle.getText().toString().trim();
                    String complaintContent = etComplaintContent.getText().toString().trim();
                    societyComplaint.setComplaintHeading(complaintTitle);
                    societyComplaint.setComplaintContent(complaintContent);
                    Map societyComplaintMap = societyComplaint.toComplaintMap();
                    db.addComplaint(societyComplaintMap);
                    startActivity(new Intent(AddComplaintActivity.this, HomeActivity.class));
                    AddComplaintActivity.this.finish();
                }

            }
        });
    }
}