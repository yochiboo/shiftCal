package com.example.yochi.shiftcal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by yochi on 2017/01/17.
 */
class DateAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private Timestamp top = null;

    private static class DateViewHolder {
        public TextView  title;
        public TextView  contents;
    }

    DateAdapter(Context context) {
      mContext = context;
      mLayoutInflater = LayoutInflater.from(context);

      if(top == null){
        top = new Date();
        
      }
    }

    public int getCount() {
        return 7*6;
    }

    public Object getItem(int position) {
        // 日にち計算
        return position + "日";
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

      DateViewHolder holder;
      if (convertView == null) {
        convertView = mLayoutInflater.inflate(R.layout.cell_layout, null);
        holder = new DateViewHolder();
        convertView.setTag(holder);
      } else {
          holder = (ViewHolder)convertView.getTag();
      }

      holder.title.setText((String)getItem(position));

      return convertView;
    }
}
