package com.unvin.simpleaccount.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unvin.simpleaccount.R;

public class CategoryAdapter extends ArrayAdapter<String> {
    String[] spinnerNames;
    int[] spinnerImages;
    Context mContext;

    public CategoryAdapter(@NonNull Context context, String[] names, int[] images) {
        super(context, R.layout.adapter_category);

        this.spinnerNames = names;
        this.spinnerImages = images;
        this.mContext = context;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return spinnerNames.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder mViewHolder = new ViewHolder();

        if (convertView == null) {

            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.adapter_category, parent, false);

            mViewHolder.mImage = (ImageView) convertView.findViewById(R.id.img_cat);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.txt_cat);
            convertView.setTag(mViewHolder);

        } else {

            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mImage.setImageResource(spinnerImages[position]);
        mViewHolder.mName.setText(spinnerNames[position]);

        return convertView;
    }

    private static class ViewHolder {

        ImageView mImage;
        TextView mName;
    }
}
