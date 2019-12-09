package com.unvin.simpleaccount.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unvin.simpleaccount.R;
import com.unvin.simpleaccount.models.chartList;

import java.util.ArrayList;

public class ChartListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<chartList> sample;

    public ChartListAdapter(Context context, ArrayList<chartList> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public chartList getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.adapter_chartlist, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.img_category);
        TextView txt_category = (TextView)view.findViewById(R.id.txt_category);
        TextView txt_per = (TextView)view.findViewById(R.id.txt_per);
        TextView txt_cost = (TextView)view.findViewById(R.id.txt_cost);

        imageView.setImageResource(sample.get(position).getCategory());
        txt_category.setText(sample.get(position).getTxt_cat());
        txt_per.setText(sample.get(position).getPercent());
        txt_cost.setText(sample.get(position).getCost());

        return view;
    }
}
