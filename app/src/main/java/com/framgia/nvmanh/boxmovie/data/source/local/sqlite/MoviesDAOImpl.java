package com.framgia.nvmanh.boxmovie.data.source.local.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.framgia.nvmanh.boxmovie.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesDAOImpl implements MoviesDAO {

    private static final int ERROR_ADD = -1;
    private static final String STRING_EQUALS = " = ?";
    private DBHelper mDBHelper;

    public MoviesDAOImpl(DBHelper dbHelper){
        mDBHelper = dbHelper;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.TABLE_NAME;
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Movie movie = new Movie();
            movie.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TITLE)));
            movie.setVote(cursor.getFloat(cursor.getColumnIndex(DBHelper.COLUMN_VOTE)));
            movie.setPoster(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_POSTER)));
            movie.setBackdrop(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_BACKDROP)));
            movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_RELEASE_DATE)));
            movie.setOverview(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_OVERVIEW)));
            movies.add(movie);
        }
        cursor.close();
        db.close();
        return movies;
    }

    @Override
    public boolean addMovie(Movie movie) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, movie.getId());
        values.put(DBHelper.COLUMN_TITLE, movie.getTitle());
        values.put(DBHelper.COLUMN_VOTE, movie.getVote());
        values.put(DBHelper.COLUMN_POSTER, movie.getPoster());
        values.put(DBHelper.COLUMN_BACKDROP, movie.getBackdrop());
        values.put(DBHelper.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(DBHelper.COLUMN_OVERVIEW, movie.getOverview());
        long result = db.insert(DBHelper.TABLE_NAME, null, values);
        db.close();
        if (result != ERROR_ADD) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeMovie(int movieId) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int result = db.delete(DBHelper.TABLE_NAME,
                DBHelper.COLUMN_ID + STRING_EQUALS,
                new String[]{String.valueOf(movieId)});
        db.close();
        if(result != 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isFavorite(int movieId) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME,
                null,
                DBHelper.COLUMN_ID + STRING_EQUALS,
                new String[]{String.valueOf(movieId)},
                null,
                null,
                null);
        boolean result = false;
        if(cursor.moveToNext()){
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }
}
