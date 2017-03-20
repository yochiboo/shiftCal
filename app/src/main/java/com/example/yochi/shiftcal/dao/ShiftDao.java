package com.example.yochi.shiftcal.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yochi.shiftcal.model.CalendarData;
import com.example.yochi.shiftcal.model.ShiftData;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ShiftDao {

    private Context context = null;
    private SQLiteDatabase mydb = null;

    public ShiftDao(Context c) {
        context = c;
    }

    // 月間カレンダーデータ取得
    public ArrayList<CalendarData> getMonthlyShift(Timestamp from, Timestamp to){
        ArrayList<CalendarData> result = new ArrayList<CalendarData>();
        String sql = "select c.date, c.shift s.title s.color from cal c, shift s where c.date between ? and ? and c.shift = s.shift";
        Cursor c = openDb().rawQuery(sql, new String[]{Long.toString(from.getTime()), Long.toString(to.getTime())});
        if(c.moveToFirst()){
          do{
            CalendarData cal = new CalendarData();
            cal.date = c.getLong(c.getColumnIndex("date"));
            cal.shift = c.getInt(c.getColumnIndex("shift"));
            cal.title = c.getString(c.getColumnIndex("title"));
            cal.color = c.getString(c.getColumnIndex("color"));
            result.add(cal);
          }while(c.moveToNext());
        }
        c.close();
        return result;
    }

    // カレンダーデータ挿入
    public void insertCalendarDate(CalendarData cal){
      ArrayList<CalendarData> result = new ArrayList<CalendarData>();
      String sqlFormat = "insert into cal (date, shift) values (%d, %d)";
      Cursor c = openDb().rawQuery(String.format(sqlFormat, cal.date, cal.shift), null);
      c.moveToFirst();
      c.close();
    }

    // カレンダーデータ更新
    public void updateCalendarDate(CalendarData cal){
        ArrayList<CalendarData> result = new ArrayList<CalendarData>();
        String sqlFormat = "update cal set shift=%d, title='%s', color='%s' where date=%d";
        Cursor c = openDb().rawQuery(String.format(sqlFormat, cal.shift, cal.title, cal.color, cal.date), null);
        c.moveToFirst();
        c.close();
    }

    // カレンダーデータ削除
    public void deleteCalendarDate(Long date){
      ArrayList<CalendarData> result = new ArrayList<CalendarData>();
      String sqlFormat = "delete from cal where date=%d";
      Cursor c = openDb().rawQuery(String.format(sqlFormat, date), null);
      c.moveToFirst();
      c.close();
    }

    // Shiftテーブルデータ取得
    public ArrayList<ShiftData> getShiftConfig(){
        ArrayList<ShiftData> result = new ArrayList<ShiftData>();
        String sql = "select shift, title, color, order from shift order by order";
        Cursor c = openDb().rawQuery(sql, null);
        if(c.moveToFirst()){
          do{
            ShiftData s = new ShiftData();
            s.shift = c.getInt(c.getColumnIndex("shift"));
            s.title = c.getString(c.getColumnIndex("title"));
            s.color = c.getString(c.getColumnIndex("color"));
            s.order = c.getInt(c.getColumnIndex("order"));
            result.add(s);
          }while(c.moveToNext());
        }
        c.close();
        return result;
    }

    private SQLiteDatabase openDb(){
        if(mydb == null){
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
            mydb = helper.getWritableDatabase();
        }
        return mydb;
    }

}
