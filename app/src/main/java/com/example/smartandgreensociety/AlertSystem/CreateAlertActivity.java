package com.example.smartandgreensociety.AlertSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateAlertActivity extends AppCompatActivity {

    Button btnNotify;
    private String FCM_API = "http://fcm.googleapis.com/fcm/send";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);

        btnNotify = findViewById(R.id.btnNotify);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject = new JSONObject();
                JsonObjectRequest jsonObjectRequest;

                try {
                        jsonObject.put("title", "Emergency Alert");
                        jsonObject.put("message ","There's an emergency in the " +
                                "society. ");

                } catch (JSONException e) {
                    e.printStackTrace();
                }





            }
        });

    }
}