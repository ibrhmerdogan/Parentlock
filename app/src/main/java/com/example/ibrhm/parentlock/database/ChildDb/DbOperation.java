package com.example.ibrhm.parentlock.database.ChildDb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ibrhm on 6.03.2017.
 */

public class DbOperation {


    public void register(String email, ChildDB childDB) {
        SQLiteDatabase db = childDB.getWritableDatabase();

        ContentValues veriler = new ContentValues();

        veriler.put("email", email);
        db.insertOrThrow("user", null, veriler);

    }

    public Cursor getRegister(ChildDB childDb) {
        SQLiteDatabase db = childDb.getReadableDatabase();
        Cursor cursor = null;
        cursor = db.query("user", new String[]{"id", "email"}, null, null, null, null, null);
        return cursor;

    }
}