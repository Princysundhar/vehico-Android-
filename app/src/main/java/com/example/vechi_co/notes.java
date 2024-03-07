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

public class notes extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    Button bt1;
    SharedPreferences sh;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        et1 = findViewById(R.id.editTextTextPersonName5);
        et2 = findViewById(R.id.editTextTextPersonName7);
        et3 = findViewById(R.id.editTextTextPersonName8);
        et4 = findViewById(R.id.editTextTextPersonName9);
        bt1 = findViewById(R.id.button13);
        et2.setText(gpstracker.lati);
        et3.setText(gpstracker.longi);
        et2.setEnabled(false);
        et3.setEnabled(false);
        bt1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String note=et1.getText().toString();
               String amount = et4.getText().toString();
               sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

               String url = sh.getString("url", "") + "and_book_service";
               RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
               StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String s) {
                               try {
                                   JSONObject jsonObject = new JSONObject(s);
                                   if (jsonObject.getString("status").equalsIgnoreCase("ok")) {
                                       Toast.makeText(notes.this, "Request sent sucessfully", Toast.LENGTH_SHORT).show();
                                       Intent i = new Intent(getApplicationContext(),Home.class);
                                       startActivity(i);
                                   }

                               }
                               catch (Exception e){
                                   Toast.makeText(notes.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                               }

                           }
                       },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError volleyError) {

                           }
                       }){
                   @Override
                   protected Map<String, String> getParams() {
                       SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                       Map<String, String> params = new HashMap<String, String>();
                       params.put("note",note);
                       params.put("amount",amount);
                       params.put("lid",sh.getString("lid",""));
                       params.put("sid",sh.getString("sid",""));
                       params.put("latitude",gpstracker.lati);
                       params.put("longitude",gpstracker.longi);


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