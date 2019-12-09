package com.unvin.simpleaccount.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unvin.simpleaccount.R;
import com.unvin.simpleaccount.models.checkList;

import java.util.ArrayList;

public class CheckListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<checkList> sample;

    public CheckListAdapter(Context context, ArrayList<checkList> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public checkList getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.adapter_checklist, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.img_money);
        TextView txt_category = (TextView)view.findViewById(R.id.txt_category);
        TextView txt_cost = (TextView)view.findViewById(R.id.txt_per);
        TextView txt_what = (TextView)view.findViewById(R.id.txt_what);
        TextView txt_datetime = (TextView)view.findViewById(R.id.txt_datetime);

        imageView.setImageResource(sample.get(position).getMoney());
        txt_category.setText(sample.get(position).getWho());
        txt_cost.setText(sample.get(position).getCost());
        txt_what.setText(sample.get(position).getWhat());
        txt_datetime.setText(sample.get(position).getDatetime());

        return view;
    }
}
