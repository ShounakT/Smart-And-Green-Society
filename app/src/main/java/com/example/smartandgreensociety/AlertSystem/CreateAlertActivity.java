package com.example.smartandgreensociety.AlertSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartandgreensociety.Globals;
import com.example.smartandgreensociety.R;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateAlertActivity extends AppCompatActivity {

    Button btnNotify;
    private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    private String key = "AAAAe-cKJpw:APA91bE-laSueLCoDCNOBtFc4TpOHGszyzCSjt3yrTIPqFdC-Ht5hPjKSt6GTseI9e7cLotkiCsAKzcKZXwT2qzNAgeT97Te_GbyGobjnkvVwDepodR7-Mu7CCDFZPqugNa3cz3cCu9O";

    private String TAG = "AlertSystem";

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

                JSONObject dataObject = new JSONObject();



                try {
                        dataObject.put("title", "Emergency Alert");
                        dataObject.put("body ","There's an emergency in the " +
                                "society. ");

                        jsonObject.put("notification",dataObject);
                        Log.e(TAG, Globals.user.getSocietyRef());
                        jsonObject.put("to","/topics/" + Globals.user.getSocietyRef());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestQueue queue = Volley.newRequestQueue(CreateAlertActivity.this);

                 JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,FCM_API, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG,"Response Recd");
                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,"Error Recd");
                        Log.e(TAG, Objects.requireNonNull(error.getMessage()));
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        headers.put("Authorization", "key="+key);
                        return headers;
                    }
                };

                 queue.add(request);


            }
        });

    }
}