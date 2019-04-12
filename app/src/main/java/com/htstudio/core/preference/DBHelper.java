package com.htstudio.core.preference;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "HTDBHelper";
    private static DBHelper DBHelper;
    private static final int VERSION = 1;

    public static final String TB_NAME = "setting";
    public static final String COL_ID = "id";
    public static final String COL_KEY = "keyhash";
    public static final String COL_DATA = "data";

    private String createTable = "CREATE TABLE " + TB_NAME + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_KEY + " VARCHAR(32), "
            + COL_DATA + " VARCHAR(32))";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DBHelper getInstance(Context context) {
        if (DBHelper == null) DBHelper = new DBHelper(context, DB_NAME, null, VERSION);
        return DBHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("HTDB", "No procress database upgrade");
    }
}
