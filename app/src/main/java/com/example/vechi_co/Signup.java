package com.example.vechi_co;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Signup extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    Button bt1;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        et1= findViewById(R.id.editTextTextPersonName3);
        et2= findViewById(R.id.editTextPhone);
        et3= findViewById(R.id.editTextTextEmailAddress);
        et4= findViewById(R.id.editTextTextPassword2);
        bt1 = findViewById(R.id.button3);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nme = et1.getText().toString();
                String con_no=et2.getText().toString();
                String e_mail=et3.getText().toString();
                String pass=et4.getText().toString();
                if (nme.equalsIgnoreCase("")){
                    et1.setError("enter name");
                    return;
                }
                if (con_no.equalsIgnoreCase("")) {
                    et2.setError("contact no");
                    return;
                }
                if (e_mail.equalsIgnoreCase("")) {
                    et3.setError("E mail");
                    return;
                }
                if (pass.equalsIgnoreCase("")) {
                    et4.setError("password");
                    return;
                }
                String url = sh.getString("url", "") + "and_signup";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    if (jsonObject.getString("status").equalsIgnoreCase("ok")){
                                        Toast.makeText(Signup.this, "Signup success", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), Login.class);
                                        startActivity(i);
                                    }
                                    else {
                                        Toast.makeText(Signup.this, "Signup failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    Toast.makeText(Signup.this, "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(Signup.this, "Response"+volleyError, Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nme", nme);
                        params.put("con_no", con_no);
                        params.put("e_mail", e_mail);
                        params.put("pass", pass);

                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS=100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);
            }
        });
    }
}