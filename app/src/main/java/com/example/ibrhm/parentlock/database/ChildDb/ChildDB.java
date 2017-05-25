package com.example.ibrhm.parentlock.database.ChildDb;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by ibrhm on 6.03.2017.
 */
public class ChildDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME   = "ChildDB";
    // Contacts table name
    private static final String TABLE_COUNTRIES = "user";
    public ChildDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE If EXIST user");
        onCreate(db);


    }
}
