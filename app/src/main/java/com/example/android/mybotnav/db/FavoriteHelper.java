package com.example.android.mybotnav.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.mybotnav.Item.Movie;
import com.example.android.mybotnav.Item.TVShow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.android.mybotnav.db.DatabaseContract.NoteColumns.DATE;
import static com.example.android.mybotnav.db.DatabaseContract.NoteColumns.IMAGE;
import static com.example.android.mybotnav.db.DatabaseContract.NoteColumns.IMAGE2;
import static com.example.android.mybotnav.db.DatabaseContract.NoteColumns.OVERVIEW;
import static com.example.android.mybotnav.db.DatabaseContract.NoteColumns.RATING;
import static com.example.android.mybotnav.db.DatabaseContract.NoteColumns.TITLE;
import static com.example.android.mybotnav.db.DatabaseContract.TABLE_FAV;
import static com.example.android.mybotnav.db.DatabaseContract.TABLE_FAV2;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_FAV;
    private static final String DATABASE_TABLE2 = TABLE_FAV2;
    private static DatabaseHelper dataBaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {

        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<Movie> getAllFavorites() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movie.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow(RATING)));
                movie.setPhoto1(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                movie.setPhoto2(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE2)));
                movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                arrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
//        String sql = "SELECT * FROM " + TABLE_FAV;
//        database = dataBaseHelper.getReadableDatabase();
//        Cursor cursor = database.rawQuery(sql, null);
//        if (cursor.moveToFirst()) {
//            do {
//                Movie movie = new Movie();
//                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
//                movie.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
//                movie.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow(RATING)));
//                movie.setPhoto1(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
//                movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
//                movie.setPhoto2(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE2)));
//                movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
//                arrayList.add(movie);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
        return arrayList;
    }

    public boolean isExist(Movie movie) {
        database = dataBaseHelper.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_FAV + " WHERE " + _ID + "=" + movie.getId();

        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insertFavorite(Movie movie) {
        ContentValues args = new ContentValues();
        args.put(_ID, movie.getId());
        args.put(TITLE, movie.getName());
        args.put(RATING, movie.getRating());
        args.put(DATE, movie.getDate());
        args.put(IMAGE, movie.getPhoto1());
        args.put(IMAGE2, movie.getPhoto2());
        args.put(OVERVIEW, movie.getDescription());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteFavorite(int id) {
        return database.delete(TABLE_FAV, _ID + " = '" + id + "'", null);
    }

    public ArrayList<TVShow> getAllFavorites2() {
        ArrayList<TVShow> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE2, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        TVShow tvShow;
        if (cursor.getCount() > 0) {
            do {
                tvShow = new TVShow();
                tvShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                tvShow.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                tvShow.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow(RATING)));
                tvShow.setPhoto1(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                tvShow.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                tvShow.setPhoto2(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE2)));
                tvShow.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                arrayList.add(tvShow);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
//        String sql = "SELECT * FROM " + TABLE_FAV;
//        database = dataBaseHelper.getReadableDatabase();
//        Cursor cursor = database.rawQuery(sql, null);
//        if (cursor.moveToFirst()) {
//            do {
//                Movie movie = new Movie();
//                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
//                movie.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
//                movie.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow(RATING)));
//                movie.setPhoto1(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
//                movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
//                movie.setPhoto2(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE2)));
//                movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
//                arrayList.add(movie);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
        return arrayList;
    }

    public boolean isExist2(TVShow tvShow) {
        database = dataBaseHelper.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_FAV2 + " WHERE " + _ID + "=" + tvShow.getId();

        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insertFavorite2(TVShow tvShow) {
        ContentValues args = new ContentValues();
        args.put(_ID, tvShow.getId());
        args.put(TITLE, tvShow.getName());
        args.put(RATING, tvShow.getRating());
        args.put(DATE, tvShow.getDate());
        args.put(IMAGE, tvShow.getPhoto1());
        args.put(IMAGE2, tvShow.getPhoto2());
        args.put(OVERVIEW, tvShow.getDescription());
        return database.insert(DATABASE_TABLE2, null, args);
    }

    public int deleteFavorite2(int id) {
        return database.delete(TABLE_FAV2, _ID + " = '" + id + "'", null);
    }
}
