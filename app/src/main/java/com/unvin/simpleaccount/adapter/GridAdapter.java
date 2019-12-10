package com.unvin.simpleaccount.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unvin.simpleaccount.MainActivity;
import com.unvin.simpleaccount.R;
import com.unvin.simpleaccount.models.day;

import java.util.ArrayList;
import java.util.Calendar;

public class GridAdapter extends BaseAdapter {

    private final ArrayList<day> list;

    private final LayoutInflater inflater;
    private Calendar cal;

    /**
     * 생성자
     *  @param context
     * @param list
     */
    public GridAdapter(Context context, ArrayList<day> list) {
        this.list = list;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cal = MainActivity.mCal;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public day getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_gridview, parent, false);

            holder = new ViewHolder();

            holder.relGridView = (RelativeLayout) convertView.findViewById(R.id.rel_day);
            holder.tvItemGridView = (TextView) convertView.findViewById(R.id.txt_day);
            holder.totalItemGridView = (TextView) convertView.findViewById(R.id.txt_total);
            holder.inItemGridView = (TextView) convertView.findViewById(R.id.txt_income);
            holder.conItemGridView = (TextView) convertView.findViewById(R.id.txt_consume);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvItemGridView.setText("" + getItem(position).getDay());
//        holder.totalItemGridView.setText(list.get(position).getTotal());
//        holder.inItemGridView.setText(list.get(position).getIncome());
//        holder.conItemGridView.setText(list.get(position).getConsume());

        //해당 날짜 텍스트 컬러,배경 변경
        cal = Calendar.getInstance();
        //오늘 day 가져옴
        Integer today = cal.get(Calendar.DAY_OF_MONTH);
        String sToday = String.valueOf(today);
        if (sToday.equals(getItem(position).getDay())) { //오늘 day 텍스트 컬러 변경
            holder.tvItemGridView.setTextColor(convertView.getResources().getColor(R.color.color_ffffff));
            holder.relGridView.setBackgroundColor(convertView.getResources().getColor(R.color.income));
            holder.inItemGridView.setTextColor(convertView.getResources().getColor(R.color.color_ffffff));
        }
        return convertView;
    }

    private class ViewHolder {
        RelativeLayout relGridView;
        TextView tvItemGridView;
        TextView totalItemGridView;
        TextView inItemGridView;
        TextView conItemGridView;
    }
}