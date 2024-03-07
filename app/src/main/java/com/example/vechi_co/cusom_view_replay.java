package com.example.vechi_co;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class cusom_view_replay extends BaseAdapter {
    String[] cid,com,comd,rpy,rpyd;
    private Context context;
    public cusom_view_replay(Context context, String[] cid, String[] com , String[] comd,String[] rpy,String[] rpyd){
        this.cid = cid;
        this.com = com;
        this.comd = comd;
        this.rpy = rpy;
        this.rpyd = rpyd;
        this.context = context;

    }
    @Override
    public int getCount() {
        return com.length;
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
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.activity_cusom_view_replay, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView24);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView25);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView26);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView27);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(com[i]);
        tv2.setText(comd[i]);
        tv3.setText(rpy[i]);
        tv4.setText(rpyd[i]);


        return gridView;
    }
}