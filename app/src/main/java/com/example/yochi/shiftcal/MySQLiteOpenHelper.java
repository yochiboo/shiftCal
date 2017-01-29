static final String DB = "shiftCal.db";
static final int DB_VERSION = 1;
static final String CREATE_TABLE_CAL = "create table cal (_id integer primary key autoincrement, date numeric not null, shift integer not null);";
static final String CREATE_TABLE_SHIFT = "create table shift (_id integer primary key autoincrement, shift integer not null, title text not null, color text not null, order integer not null);";
static final String DROP_TABLE = "drop table mytable;";

private static class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context c) {
        super(c, DB, null, DB_VERSION);
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
        mydb.insert("mytable", null, values);
        values.put("shift", 2);
        values.put("title", "日勤");
        values.put("color", "#ff00ff00");
        values.put("order", 2);
        mydb.insert("mytable", null, values);
        values.put("shift", 3);
        values.put("title", "遅番");
        values.put("color", "#ffff0000");
        values.put("order", 3);
        mydb.insert("mytable", null, values);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(DROP_TABLE);
        //onCreate(db);
    }
}
