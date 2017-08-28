package com.pantrychefapp.pantrychef.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.io.InputStream;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pantryChefDB";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates schema
        try {
            InputStream is = context.getAssets().open("createDB");
            String[] statements = FileHelper.parseSqlFile(is);
            for (String statement : statements) {
                db.execSQL(statement);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
