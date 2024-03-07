package com.example.vechi_co;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_view_service_provider extends BaseAdapter {
    String[] id,nme,con,plc,eml,lat,lon;
    private Context context;
    public custom_view_service_provider(Context context, String[] id, String[] nme, String[] con,String[] plc,String[] eml,String[] lat,String[] lon){
        this.context = context;
        this.id = id;
        this.nme = nme;
        this.con=con;
        this.plc=plc;
        this.eml=eml;
        this.lat=lat;
        this.lon=lon;

    }
    @Override
    public int getCount() {
        return nme.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_service_provider,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView3);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView5);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView7);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView9);
        Button bt=(Button) gridView.findViewById(R.id.button6);             //locate
        bt.setTag(i);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ik=(int)view.getTag();
                String url = "http://maps.google.com/?q=" + lat[ik] + "," + lon[ik];
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        Button bt3=(Button) gridView.findViewById(R.id.button14);
        bt3.setTag(i);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("id",id[pos]);
                ed.commit();
                Intent i = new Intent(context,view_service.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
        Button bt2=(Button) gridView.findViewById(R.id.button12);           // send complaint
        bt2.setTag(i);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("sid",id[pos]);
                ed.commit();
                Intent i = new Intent(context,view_replay.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });




        tv1.setTextColor(Color.BLACK);


        tv1.setText(nme[i]);
        tv2.setText(con[i]);
        tv3.setText(plc[i]);
        tv4.setText(eml[i]);




        return gridView;
    }
}