static final String DB = "shiftCal.db";
static final int DB_VERSION = 1;
static final String CREATE_TABLE_CAL = "create table mytable (_id integer primary key autoincrement, date numeric not null, shift integer not null);";
static final String CREATE_TABLE_SHIFT = "create table mytable (_id integer primary key autoincrement, shift integer not null, title text not null, color text not null, order integer not null);";
static final String DROP_TABLE = "drop table mytable;";

private static class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context c) {
        super(c, DB, null, DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CAL);
        db.execSQL(CREATE_TABLE_SHIFT);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
