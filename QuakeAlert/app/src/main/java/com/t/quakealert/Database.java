package com.t.quakealert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Database extends SQLiteOpenHelper {

    public static final String DATABASENAME ="hospital.db";
    public static final String TABLE_NAME ="hospital_table";
    public static final String COL1 ="hospital_name";
    public static final String COL2 ="address";
    public static final String COL3 ="no_of_bed";
    public static final String COL4 ="no_of_doctor";
    public static final String COL5 ="payment_mode";
    public static final String COL6 ="rating";
    public static final String COL7 ="phone";


    public Database(Context context) {
        super(context, DATABASENAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(hospital_name TEXT PRIMARY KEY,address TEXT,no_of_bed TEXT,no_of_doctor TEXT,payment_mode TEXT,rating TEXT,phone TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);


    }

    public boolean insertData(String hospital_name, String address , String no_of_bed, String no_of_doctor, String payment_mode, String rating, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVales = new ContentValues();

        contentVales.put(COL1,hospital_name);
        contentVales.put(COL2,address);
        contentVales.put(COL3,no_of_bed);
        contentVales.put(COL4,no_of_doctor);
        contentVales.put(COL5,payment_mode);
        contentVales.put(COL6,rating);
        contentVales.put(COL7,phone);

        long result = db.insert(TABLE_NAME,null,contentVales);

        return result != -1;



    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;


    }












}
