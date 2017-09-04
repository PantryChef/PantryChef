package com.pantrychefapp.pantrychef.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DBControl {
    private Context context;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DBControl(Context context) {
        this.context = context;
    }

    public DBControl open() throws SQLiteException {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insert(String tableName, HashMap<String, String> queryValues) {
        ContentValues values = new ContentValues();
        Iterator it = queryValues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pair = (Map.Entry) it.next();
            values.put(pair.getKey(), pair.getValue());
        }
        return db.insert(tableName, null, values);
    }

    public void executeSql(String sql) {
        db.execSQL(sql);
    }

    public ArrayList<Map<String, String>> select(String query) {
        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }
                result.add(map);
            } while (cursor.moveToNext());
        }
        return result;
    }
}
