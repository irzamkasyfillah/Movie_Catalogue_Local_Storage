package com.example.android.mybotnav.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String SQL_CREATE_TABLE_FAV = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s REAL NOT NULL)",
            DatabaseContract.TABLE_FAV,
            DatabaseContract.NoteColumns._ID,
            DatabaseContract.NoteColumns.TITLE,
            DatabaseContract.NoteColumns.IMAGE,
            DatabaseContract.NoteColumns.DATE,
            DatabaseContract.NoteColumns.IMAGE2,
            DatabaseContract.NoteColumns.OVERVIEW,
            DatabaseContract.NoteColumns.RATING
    );
    private static final String SQL_CREATE_TABLE_FAV2 = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s REAL NOT NULL)",
            DatabaseContract.TABLE_FAV2,
            DatabaseContract.NoteColumns._ID,
            DatabaseContract.NoteColumns.TITLE,
            DatabaseContract.NoteColumns.IMAGE,
            DatabaseContract.NoteColumns.DATE,
            DatabaseContract.NoteColumns.IMAGE2,
            DatabaseContract.NoteColumns.OVERVIEW,
            DatabaseContract.NoteColumns.RATING
    );
    public static String DATABASE_NAME = "favorite";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAV);
        db.execSQL(SQL_CREATE_TABLE_FAV2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAV);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAV2);
        onCreate(db);
    }
}
