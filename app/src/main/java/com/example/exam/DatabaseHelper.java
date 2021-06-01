package com.example.exam;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int SCHEMA = 5; // версия бд
    static final String TABLE = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts ("
                + "_id integer primary key autoincrement,"
                + "name text,"
                + "phone text" + ");");
    }

    // Вызывается в случае обновления версии бд, указанной выше. Не определить низя, ругается
    // компилятор и не билдит.
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
