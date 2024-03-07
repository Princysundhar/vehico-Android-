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

public class Login extends AppCompatActivity {
    EditText et1, et2;
    Button bt1, bt2;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        et1= findViewById(R.id.editTextTextPersonName2);
        et2= findViewById(R.id.editTextTextPassword);
        bt1 = findViewById(R.id.button2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un=et1.getText().toString();
                String pwd=et2.getText().toString();
                int flag=0;
                if(un.equalsIgnoreCase("")){
                    et1.setError("Enter your username");
                    flag++;
                }
                if(pwd.equalsIgnoreCase("")){
                    et2.setError("Enter your password");
                    flag++;
                }
                if(flag==0) {
                    String url = sh.getString("url", "") + "and_login";
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        if (jsonObject.getString("status").equalsIgnoreCase("ok")) {
                                            String lid = jsonObject.getString("lid");
                                            String type = jsonObject.getString("type");
                                            SharedPreferences.Editor ed = sh.edit();
                                            ed.putString("lid", lid);
                                            ed.commit();
                                            if (type.equalsIgnoreCase("user")) {
                                                Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(getApplicationContext(), user_home.class);
                                                startActivity(i);
                                                Intent k = new Intent(getApplicationContext(), gpstracker.class);
                                                startService(k);
                                            }
                                        }

                                    } catch (Exception e) {
                                        Toast.makeText(Login.this, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                            params.put("usn", un);
                            params.put("psw", pwd);

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
        bt2= findViewById(R.id.button4);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Signup.class);
                startActivity(i);
            }
        });


    }
}