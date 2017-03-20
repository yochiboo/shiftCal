package com.example.yochi.shiftcal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

import com.example.yochi.shiftcal.model.CalendarData;
import com.example.yochi.shiftcal.misc.DateUtil;
import com.example.yochi.shiftcal.R;
import com.example.yochi.shiftcal.dao.*;


/**
 * Created by yochi on 2017/01/17.
 */
class DateAdapter extends BaseAdapter {

  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private Timestamp top = null;
  ArrayList<CalendarData> calList = null;

  private static class DateViewHolder {
    public TextView  title;
    public TextView  contents;
  }

  DateAdapter(Context context) {
    mContext = context;
    mLayoutInflater = LayoutInflater.from(context);

    moveMonth((Timestamp) new Date());
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

  public void moveMonth(Timestamp month){

    // 現在月の１日の曜日からカレンダー表示の左上日付を求める
    top = DateUtil.getMonthlyCalendarTop(month);
    // カレンダー最終日（top+42日）
    Timestamp bottom = DateUtil.getMonthlyCalendarLast(month);

    // calテーブルからカレンダー表示分のデータ取得
    ShiftDao dao = new ShiftDao(mContext);
    calList = dao.getMonthlyShift(top, bottom);
  }
}
