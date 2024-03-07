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
import android.widget.TextView;

public class custom_view_bookings extends BaseAdapter {
    String[] id, ser,dat,pay,lat,lon,sts,amount;
    private Context context;

    public custom_view_bookings(Context applicationContext, String[] id, String[] ser, String[] dat, String[] pay, String[] lat, String[] lon,String[] sts,String[] amount) {
        this.context = applicationContext;
        this.id = id;
        this.ser = ser;
        this.dat = dat;
        this.pay = pay;
        this.lat=lat;
        this.lon=lon;
        this.sts=sts;
        this.amount = amount;

    }

    @Override
    public int getCount() {
        return amount.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_bookings,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView14);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView15);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView19);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView29);
        Button bt1=(Button) gridView.findViewById(R.id.button8);
        Button bt2=(Button) gridView.findViewById(R.id.button9);
        bt1.setTag(i);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ik=(int)view.getTag();
                String url = "http://maps.google.com/?q=" + lat[ik] + "," + lon[ik];
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        bt2.setTag(i);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("bid",id[pos]);
                ed.putString("amount",amount[pos]);
                ed.commit();
                Intent i = new Intent(context,Make_payment.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });



        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);

        tv1.setText(ser[i]);
        tv2.setText(dat[i]);
        tv3.setText(pay[i]);
        tv4.setText(sts[i]);


        return gridView;
    }
}

