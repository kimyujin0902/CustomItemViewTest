package com.example.customitemviewtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    final static String TAG = "SQLiteDBTest";

    public DBHelper(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, getClass().getName() + ".onCreate()");
        db.execSQL(UserContract.Users.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG, getClass().getName() + ".onUpgrade()");
        db.execSQL(UserContract.Users.DELETE_TABLE);
        onCreate(db);
    }

    public void insertUserBySQL(String title, String place, String memo, String date) {
        try {
            String sql = String.format(
                    "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (NULL, '%s', '%s', '%s', '%s')",
                    UserContract.Users.TABLE_NAME,
                    UserContract.Users._ID,
                    UserContract.Users.KEY_TITLE,
                    UserContract.Users.KEY_PLACE,
                    UserContract.Users.KEY_MEMO,
                    UserContract.Users.KEY_DATE,
                    title,
                    place,
                    memo,
                    date);

            getWritableDatabase().execSQL(sql);
            Log.i(TAG, "insert("+title+", "+place+", "+memo+", "+date+")");
        } catch (SQLException e) {
            Log.e(TAG, "Error in inserting recodes");
        }
    }

    public Cursor getAllUsersBySQL() {
        String sql = "Select * FROM " + UserContract.Users.TABLE_NAME;
        return getReadableDatabase().rawQuery(sql, null);
    }

    public String getUsersMySQL(String what, String key, String value) {
        String sql = String.format(
                "Select %s FROM %s WHERE %s = '%s'",
                what,
                UserContract.Users.TABLE_NAME,
                key,
                value);
        StringBuffer buffer = new StringBuffer();
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToNext();
        Log.i("hey", "cursor.getString(1): " + cursor.getString(1));
        return cursor.getString(1);
    }


    public void deleteUserBySQL(String date) {
        try {
            String sql = String.format(
                    "DELETE FROM %s WHERE %s = '%s'",
                    UserContract.Users.TABLE_NAME,
                    UserContract.Users.KEY_DATE,
                    date);
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG, "Error in deleting recodes");
        }
    }
}

