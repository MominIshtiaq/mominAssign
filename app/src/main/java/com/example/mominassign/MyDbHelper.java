package com.example.mominassign;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FragementAssignmentDB";
    private static final String TABLE_NAME = "result";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_ANSWER_ENTERED = "answerentered";
    private static final String COLUMN_ANSWER_CORRECT = "answercorrect";

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your tables here
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUESTION + " TEXT,"
                + COLUMN_ANSWER_ENTERED + " TEXT,"
                + COLUMN_ANSWER_CORRECT + " TEXT"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades here
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean insertQuestion(String question){
        boolean chk = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, question);

        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId != -1) {
            // Insertion successful
            chk = true;
        }
        return chk;
    }

    public boolean insertUserAns(String answer){
        boolean chk = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ANSWER_ENTERED, answer);

        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId != -1) {
            // Insertion successful
            chk = true;
        }
        return chk;
    }

    public boolean insertActualAnswer(String answer){
        boolean chk = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ANSWER_CORRECT, answer);

        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId != -1) {
            // Insertion successful
            chk = true;
        }
        return chk;
    }

}
