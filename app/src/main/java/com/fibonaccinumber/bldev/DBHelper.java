package com.fibonaccinumber.bldev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.HashMap;

/**
 * Created by bldev on 2017. 12. 9..
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TestDB.db";
    private static final String FIBONACCI_TABLE_NAME = "Fibonacci";
    private static final String FIBONACCI_COLUMN_IN = "InputNumber";
    private static final String FIBONACCI_COLUMN_ON = "OutputNumber";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE Fibonacci" + "(InputNumber integer PRIMARY KEY, OutputNumber integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Fibonacci");
        onCreate(db);
    }

    public void insertResult(int inputNum, int outputNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS Fibonacci" + "(InputNumber integer PRIMARY KEY, OutputNumber integer)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("InputNumber", inputNum);
        contentValues.put("OutputNumber", outputNum);
        db.insert("Fibonacci", null, contentValues);
    }

    public Cursor getData(int inputNum) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Fibonacci WHERE InputNumber =" + inputNum + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FIBONACCI_TABLE_NAME);
        return numRows;
    }

    public boolean updateResult(Integer inputNum, Integer outputNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("InputNumber", inputNum);
        contentValues.put("OutputNumber", outputNum);
        db.update("Fibonacci", contentValues, " InputNumber = ? ", new String[]{Integer.toString(inputNum)});
        return true;
    }

    public Integer deleteResult(Integer inputNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Fibonacci",
                "InputNumber = ? ",
                new String[]{Integer.toString(inputNum)});
    }

    public void deleteAllRows() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE From Fibonacci");
    }

    public HashMap<Integer, Integer> getAllResults() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Fibonacci", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            hm.put(Integer.parseInt(res.getString(res.getColumnIndex(FIBONACCI_COLUMN_IN))), Integer.parseInt(res.getString(res.getColumnIndex(FIBONACCI_COLUMN_ON))));
            res.moveToNext();
        }
        return hm;
    }
}

