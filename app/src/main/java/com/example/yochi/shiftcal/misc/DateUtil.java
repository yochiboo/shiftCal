package com.example.yochi.shiftcal;

import java.sql.Timestamp;
import java.util.Calendar;

class DateUtil {

  static final int MONTHLY_CALENDAR_DAY_NUM = 42;


  // カレンダーTOP日付取得
  public Timestamp getMonthlyCalendarTop(Timestamp month){
      Calendar top = Calendar.getInstance();
      top.setTime(month);

      // 注．カレンダーは日曜日(daypart=1)はじまり
      int daypart = top.get(Calendar.DAY_OF_WEEK);
      top.add(Calendar.DAY_OF_MONTH, -(daypart-1));
      return (Timestamp) top.getTime();
  }

  // カレンダーLAST日付取得
  public Timestamp getMonthlyCalendarLast(Timestamp month){

      Timestamp top = getMonthlyCalendarTop(month);
      Calendar last = Calendar.getInstance();
      last.setTime(top);

      // 注．カレンダーTOP日付の42日後
      last.add(Calendar.DAY_OF_MONTH, MONTHLY_CALENDAR_DAY_NUM);
      return (Timestamp) last.getTime();
  }
  // 祝祭日リスト取得
  // 祝祭日リスト取得（振替対応）

}
