package com.example.yochi.shiftcal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Timestamp;
import java.util.ArrayList;

class ShiftDao {

    public Long date = null;
    public Integer shift = null;

    private Context context = null;
    private SQLiteDatabase mydb = null;

    public ShiftDao() {
    }
    public ShiftDao(Context c) {
        context = c;
    }

    public ArrayList<ShiftDao> getMonthlyShift(Timestamp from, Timestamp to){
        ArrayList<ShiftDao> result = new ArrayList<ShiftDao>();
        String sql = "select date, shift from cal where date bitween ? and ?";
        Cursor c = openDb().rawQuery(sql, new String[]{Long.toString(from.getTime()), Long.toString(to.getTime())});
        if(c.moveToFirst()){
          do{
            ShiftDao s = new ShiftDao();
            s.date = c.getLong(c.getColumnIndex("date"));
            s.shift = c.getInt(c.getColumnIndex("shift"));
            result.add(s);
          }while(c.moveToNext());
        }
        return reslt;
    }

    private SQLiteDatabase openDb(){
        if(mydb == null){
            MySQLiteOpenHelper hlpr = new MySQLiteOpenHelper(context);
            mydb = hlpr.getWritableDatabase();
        }
        return mydb;
    }

}
