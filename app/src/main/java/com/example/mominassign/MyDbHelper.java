package com.example.mominassign;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QA";
    private static final int DATABASE_ID = 1;
    private static final String TABLE_NAME = "answer";
    private static final String KEY_ID = "Id";
    private static final String KEY_QUESTION = "Question";
    private static final String KEY_ANSWER = "Answer";



    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_ID);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" + KEY_QUESTION + "TEXT NOT NULL" + KEY_ANSWER + "TEXT NOT NULL" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
