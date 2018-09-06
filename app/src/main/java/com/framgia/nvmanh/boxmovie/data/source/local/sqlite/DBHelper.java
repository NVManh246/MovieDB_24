package com.framgia.nvmanh.boxmovie.data.source.local.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.framgia.nvmanh.boxmovie.ultis.StringUltis;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies_db";
    public static final String CREATE_TABLE = "CREATE TABLE";
    public static final String INTEGER = " INTEGER";
    public static final String TEXT = " TEXT";
    public static final String COMMA = ",";
    public static final String CLOSE_BRACKET = ")";
    public static final String OPEN_BRACKET = "(";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS";

    public static final String TABLE_NAME = "movie";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VOTE = "vote";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_BACKDROP = "backdrop";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_OVERVIEW = "overview";

    private static DBHelper sInstance;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context) {
        if(sInstance == null){
            sInstance = new DBHelper(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = StringUltis.getQureryCreateTable(
                CREATE_TABLE, TABLE_NAME, OPEN_BRACKET,
                COLUMN_ID, INTEGER, COMMA,
                COLUMN_VOTE, TEXT, COMMA,
                COLUMN_TITLE, TEXT, COMMA,
                COLUMN_POSTER, TEXT, COMMA,
                COLUMN_BACKDROP, TEXT, COMMA,
                COLUMN_RELEASE_DATE, TEXT, COMMA,
                COLUMN_OVERVIEW, TEXT, CLOSE_BRACKET);
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(StringUltis.getQueryDropTable(DROP_TABLE, TABLE_NAME));
        onCreate(sqLiteDatabase);
    }
}
