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
class DaypartAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mDayPartArray = {"日", "月", "火", "水","木", "金", "土"};

    private static class DayPartViewHolder {
        public TextView titleTextView;
    }
    private static class DaypartViewHolder {
        public TextView  title;
    }

    DaypartAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return 7;
    }

    public Object getItem(int position) {
        return mDayPartArray[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

      DaypartViewHolder holder;
      if (convertView == null) {
          convertView = mLayoutInflater.inflate(R.layout.daypart_layout, null);
          holder = new DaypartViewHolder();
          convertView.setTag(holder);
      } else {
          holder = (DaypartViewHolder)convertView.getTag();
      }

      holder.title.setText((String)getItem(position));

      return convertView;
    }
}
