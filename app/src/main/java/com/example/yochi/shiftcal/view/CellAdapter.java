package com.example.yochi.shiftcal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yochi.shiftcal.R;

/**
 *
 */
public class CellAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mDayPartArray = {"日", "月", "火", "水","木", "金", "土"};

    private static class DayPartViewHolder {
        public TextView  titleTextView;
    }
    private static class DateViewHolder {
        public TextView  titleTextView;
        public TextView  contentsTextView;
    }

    public CellAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return 7*7;
    }

    public Object getItem(int position) {
        if(position < 7){
            return mDayPartArray[position];
        }
        // 日にち計算
        return position + "日";
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

//        if (convertView == null) {
//            convertView = mLayoutInflater.inflate(R.layout.grid_item_hue, null);
//            holder = new ViewHolder();
//            holder.hueImageView = (ImageView)convertView.findViewById(R.id.hue_imageview);
//            holder.hueTextView = (TextView)convertView.findViewById(R.id.hue_textview);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder)convertView.getTag();
//        }

        convertView = mLayoutInflater.inflate(R.layout.date_layout, null);
        DateViewHolder holder = new DateViewHolder();
        holder.titleTextView.setText((String)getItem(position));
        holder.titleTextView.setText((String)getItem(position));
        convertView.setTag(holder);

        return convertView;
    }
}
