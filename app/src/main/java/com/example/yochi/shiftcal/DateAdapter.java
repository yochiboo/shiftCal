package com.example.yochi.shiftcal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by yochi on 2017/01/17.
 */
class DateAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private SQLiteDatabase mydb;
    private Timestamp top = null;

    private static class DateViewHolder {
        public TextView  title;
        public TextView  contents;
    }

    DateAdapter(Context context) {
      mContext = context;
      mLayoutInflater = LayoutInflater.from(context);

      if(top == null){
        top = new Timestamp(new Date().getTime());

      }

      MySQLiteOpenHelper hlpr = new MySQLiteOpenHelper(mContext);
      mydb = hlpr.getWritableDatabase();
      Cursor cursor = mydb.query("cal", new String[] {"_id", "data"}, null, null, null, null, "_id DESC");

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
        convertView = mLayoutInflater.inflate(R.layout.date_layout, null);
        holder = new DateViewHolder();
        convertView.setTag(holder);
      } else {
          holder = (DateViewHolder)convertView.getTag();
      }

      holder.title.setText((String)getItem(position));

      return convertView;
    }
}
