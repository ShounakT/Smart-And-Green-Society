package com.example.smartandgreensociety.Notice;

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

public class AddNoticeActivity extends AppCompatActivity {

    EditText etNoticeContent, etNoticeTitle;
    Button btnSubmitNotice;
    Notice notice = new Notice();
    Db db = new Db();

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        AddNoticeActivity.this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNoticeContent = findViewById(R.id.etNoticeContent);
        btnSubmitNotice = findViewById(R.id.btnSubmitNotice);
        etNoticeTitle = findViewById(R.id.etNoticeTitle);

        btnSubmitNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etNoticeTitle.getText().toString()) ||
                    TextUtils.isEmpty(etNoticeContent.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
                }else {
                    String noticeTitle = etNoticeTitle.getText().toString().trim();
                    String noticeContent = etNoticeContent.getText().toString().trim();
                    notice.setNoticeTitle(noticeTitle);
                    notice.setNoticeContent(noticeContent);
                    Map noticeMap = notice.toNoticeMap();
                    db.addNotice(noticeMap);
                    startActivity(new Intent(AddNoticeActivity.this, HomeActivity.class));
                    AddNoticeActivity.this.finish();
                }
            }
        });
    }
}