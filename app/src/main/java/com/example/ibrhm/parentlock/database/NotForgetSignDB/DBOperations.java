package com.example.ibrhm.parentlock.database.NotForgetSignDB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ibrhm on 13.04.2017.
 */

public class DBOperations{
        public void register(String email,String password, SignDB signDB) {
            SQLiteDatabase db = signDB.getWritableDatabase();

            ContentValues veriler = new ContentValues();

            veriler.put("email", email);
            veriler.put("password", password);
            db.insertOrThrow("user", null, veriler);

        }

        public Cursor getRegister(SignDB signDB) {
            SQLiteDatabase db = signDB.getReadableDatabase();
            Cursor cursor = null;
            cursor = db.query("user", new String[]{"id", "email","password"}, null, null, null, null, null);
            return cursor;

        }
}