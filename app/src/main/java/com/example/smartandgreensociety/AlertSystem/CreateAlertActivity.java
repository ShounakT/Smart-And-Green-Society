package com.example.smartandgreensociety.AlertSystem;

import androidx.appcompat.app.AppCompatActivity;

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
    private String serverKey = "key=" + "";
    private String contentType = "application/json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);

        btnNotify = findViewById(R.id.btnNotify);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JSONObject notification = new JSONObject();
                JSONObject notificationBody = new JSONObject();

                try {
                        notificationBody.put("title", "Emergency Alert");
                        notificationBody.put("message ","There's an emergency in the " +
                                "society. ");
                        notification.put("to", Globals.user.getSocietyRef());
                        notification.put("data",notificationBody);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest =
                        new JsonObjectRequest(FCM_API, notification,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

            }
        });

    }
}