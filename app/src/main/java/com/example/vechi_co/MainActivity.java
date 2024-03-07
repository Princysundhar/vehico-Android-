package com.example.vechi_co;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button bt;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        et = findViewById(R.id.editTextTextPersonName);
        et.setText(sh.getString("ip", ""));
        bt = findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = et.getText().toString();
                String url = "http://" + ip + ":5000/";
                int flag =0;
                if(ip.equalsIgnoreCase("")){
                    et.setError("Invalid");
                    flag++;
                }
                if(flag == 0) {

                    SharedPreferences.Editor ed = sh.edit();
                    ed.putString("url", url);
                    ed.putString("ip", ip);
                    ed.commit();
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }
            }
        });
    }
}