package com.t.quakealert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QuestionsManager";
    //table names
    private static final String TABLE_EASY_QUESTIONS = "Easy";

    private static final String TABLE_EarthQuake_QUESTIONS = "EarthQuake";

    private static final String KEY_SERIAL_NUMBER = "SerialNumber";
    private static final String KEY_QUESTION = "Question";
    private static final String KEY_OPTION_A = "option_a";
    private static final String KEY_OPTION_B = "option_b";
    private static final String KEY_OPTION_C = "option_c";
    private static final String KEY_OPTION_D = "option_d";
    private static final String KEY_TRUE_ANSWER = "true_answer";

    //create table queries
    private static final String CREATE_TABLE_EASY_QUESTIONS = "CREATE TABLE '" + TABLE_EASY_QUESTIONS + "'" +
            "(" + KEY_SERIAL_NUMBER + " INTEGER,"
            + KEY_QUESTION + " TEXT,"
            + KEY_OPTION_A + " TEXT,"
            + KEY_OPTION_B + " TEXT,"
            + KEY_OPTION_C + " TEXT,"
            + KEY_OPTION_D + " TEXT,"
            + KEY_TRUE_ANSWER + " TEXT,"
            + "PRIMARY KEY(" + KEY_SERIAL_NUMBER + "))";


    private static final String CREATE_TABLE_EarthQuake_QUESTIONS = "CREATE TABLE '" + TABLE_EarthQuake_QUESTIONS + "'" +
            "(" + KEY_SERIAL_NUMBER + " INTEGER,"
            + KEY_QUESTION + " TEXT,"
            + KEY_OPTION_A + " TEXT,"
            + KEY_OPTION_B + " TEXT,"
            + KEY_OPTION_C + " TEXT,"
            + KEY_OPTION_D + " TEXT,"
            + KEY_TRUE_ANSWER + " TEXT,"
            + "PRIMARY KEY(" + KEY_SERIAL_NUMBER + "))";


    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EASY_QUESTIONS);
        //TODO only create the tables selected topics

        db.execSQL(CREATE_TABLE_EarthQuake_QUESTIONS);

    }

    void insert(String tableName, int SerialNumber, String Question, String option_a, String option_b, String option_c, String option_d, String true_answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERIAL_NUMBER, SerialNumber);
        contentValues.put(KEY_QUESTION, Question);
        contentValues.put(KEY_OPTION_A, option_a);
        contentValues.put(KEY_OPTION_B, option_b);
        contentValues.put(KEY_OPTION_C, option_c);
        contentValues.put(KEY_OPTION_D, option_d);
        contentValues.put(KEY_TRUE_ANSWER, true_answer);

        try {
            db.insert("'" + tableName + "'", null, contentValues);
        } catch (Exception E) {
            Log.d("insert", "Data Not Inserted in table name=" + tableName);
        }
    }

    void getQuestions(String tableName, int serial_number) {
        String countQuery = "SELECT * FROM '" + tableName + "' WHERE " + KEY_SERIAL_NUMBER + " =" + serial_number;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        while (cursor != null && cursor.moveToNext()) {
            PlayGame.QUESTION = cursor.getString(1);
            PlayGame.OPTION_A = cursor.getString(2);
            PlayGame.OPTION_B = cursor.getString(3);
            PlayGame.OPTION_C = cursor.getString(4);
            PlayGame.OPTION_D = cursor.getString(5);
            PlayGame.RIGHT_ANSWER = cursor.getString(6);
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    int getQuestionsCount(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM '" + tableName + "'";

        Cursor cursor = db.rawQuery(countQuery, null);
        int size = cursor.getCount();
        cursor.close();
        Log.d("DH", "size of " + tableName + " is " + size);
        return size;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DH", "on upgrade called");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_EASY_QUESTIONS + "'");

        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_EarthQuake_QUESTIONS + "'");


        //create new table
        onCreate(db);
    }

    // closing database
    void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
