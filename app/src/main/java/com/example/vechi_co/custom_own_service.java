package com.example.vechi_co;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class custom_own_service extends BaseAdapter {

    String[]sid,service_name;
    private Context context;

    public custom_own_service(Context applicationContext, String[] sid, String[] service_name) {
        this.context = applicationContext;
        this.sid = sid;
        this.service_name = service_name;

    }

    @Override
    public int getCount() {
        return service_name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_own_service,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView12);
        Button b1 =(Button) gridView.findViewById(R.id.button5);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {          // request
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed = sh.edit();
                ed.putString("sid",sid[pos]);

                ed.commit();

                Intent i = new Intent(context,notes.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        Button b2=(Button) gridView.findViewById(R.id.button10);
        b2.setTag(i);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed = sh.edit();
                ed.putString("sid",sid[pos]);

                ed.commit();

                Intent i = new Intent(context,send_rating.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        tv1.setTextColor(Color.BLACK);


        tv1.setText(service_name[i]);




        return gridView;
    }
}