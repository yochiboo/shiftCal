package com.example.yochi.shiftcal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class ShiftDao {

    private SQLiteDatabase mydb = null;

    public MySQLiteOpenHelper(Context c) {
    }

    public ArrayList<ShiftDao> getMonthlyShift(Timestamp from, Timestamp to){
        String format = "select date, shift from cal where date bitween %s and %s";
        String sql = String.format(format, top, bottom);
        Cursor c = openDb().rawQuery(sql);
        f(c.moveToFirst()){
          do{
            long date = c.getLong(c.getColumnIndex("date"));
            long shift = c.getLong(c.getColumnIndex("shift"));
          }while(c.moveToNext());
        }

    }

    private SQLiteDatabase openDb(){
        if(mydb == null){
            MySQLiteOpenHelper hlpr = new MySQLiteOpenHelper(mContext);
            mydb = hlpr.getWritableDatabase();
        }
        return mydb;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CAL);
        db.execSQL(CREATE_TABLE_SHIFT);

        // shiftテーブル初期データ投入
        ContentValues values = new ContentValues();
        values.put("shift", 1);
        values.put("title", "早番");
        values.put("color", "#ff0000ff");
        values.put("order", 1);
        db.insert("shift", null, values);
        values.put("shift", 2);
        values.put("title", "日勤");
        values.put("color", "#ff00ff00");
        values.put("order", 2);
        db.insert("shift", null, values);
        values.put("shift", 3);
        values.put("title", "遅番");
        values.put("color", "#ffff0000");
        values.put("order", 3);
        db.insert("shift", null, values);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(DROP_TABLE);
        //onCreate(db);
    }
}
