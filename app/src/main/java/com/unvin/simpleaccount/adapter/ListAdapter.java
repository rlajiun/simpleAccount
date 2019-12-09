package com.unvin.simpleaccount.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unvin.simpleaccount.R;
import com.unvin.simpleaccount.models.gagebu;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<gagebu> sample;

    public ListAdapter(Context context, ArrayList<gagebu> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public gagebu getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.adapter_gagebu, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.img_category);
        TextView txt_category = (TextView)view.findViewById(R.id.txt_category);
        TextView txt_cost = (TextView)view.findViewById(R.id.txt_per);
        TextView txt_datetime = (TextView)view.findViewById(R.id.txt_datetime);

        imageView.setImageResource(sample.get(position).getCategory());
        txt_category.setText(sample.get(position).getTxt_category());
        txt_cost.setText(sample.get(position).getCost());
        txt_datetime.setText(sample.get(position).getDatetime());

        return view;
    }
}
