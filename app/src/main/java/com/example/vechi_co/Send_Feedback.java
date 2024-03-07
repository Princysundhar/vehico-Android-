package com.example.vechi_co;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Send_Feedback extends AppCompatActivity {
    EditText et1;
    Button bt1;
    RatingBar rb1;
    SharedPreferences sh;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        et1 = findViewById(R.id.editTextTextMultiLine);
        bt1 = findViewById(R.id.button7);
//        rb1 = findViewById(R.id.ratingBar);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = et1.getText().toString();
                int flag=0;
                if(feedback.equalsIgnoreCase("")){
                    et1.setError("Null");
                    flag++;
                }
                if(flag==0) {


                    sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String url = sh.getString("url", "") + "and_send_feedback";

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        if (jsonObject.getString("status").equalsIgnoreCase("ok")) {
                                            Toast.makeText(Send_Feedback.this, "feedback sent sucessfully", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), Home.class);
                                            startActivity(i);
                                        }

                                    } catch (Exception e) {
                                        Toast.makeText(Send_Feedback.this, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("lid", sh.getString("lid", ""));
                            params.put("feedback", feedback);
//                        params.put("rating", rating.toString());

                            return params;
                        }
                    };

                    int MY_SOCKET_TIMEOUT_MS = 100000;

                    postRequest.setRetryPolicy(new DefaultRetryPolicy(
                            MY_SOCKET_TIMEOUT_MS,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(postRequest);
                }
            }
        });



    }
}