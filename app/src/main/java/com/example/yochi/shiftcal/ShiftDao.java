package com.example.yochi.shiftcal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Timestamp;
import java.util.ArrayList;

class ShiftDao {

    public Long date = null;
    public Integer shift = null;

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
        return result;
    }

    // カレンダーデータ更新
    public void saveCalendarDate(CalendarData cal){

        // 削除＆挿入
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
        return result;
    }

    // 指定日付データ取得
    public CalendarData getCalendarDate(Timestamp date){


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
        return result;
    }

    // Shiftテーブルデータ取得
    public ArrayList<ShiftData> getShiftConfig(Timestamp from, Timestamp to){
        ArrayList<ShiftData> result = new ArrayList<ShiftData>();
        String sql = "select shift title color order from shift order by order";
        Cursor c = openDb().rawQuery(sql, new String[]{});
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