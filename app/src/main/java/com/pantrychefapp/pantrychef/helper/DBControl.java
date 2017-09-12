package com.pantrychefapp.pantrychef.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DBControl {
	private Context context;
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	private StringBuilder bufferedSql;
	private ArrayList<Object> parameters;
	private String ps;
	private ResultSet rs;
	private Connection con = null;

	public DBControl(Context context) {
		this.context = context;
	}

	public DBControl open() throws SQLiteException {
		dbHelper = new DBHelper(context);
		db = dbHelper.getWritableDatabase();
		init();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	private void init() {
		bufferedSql = new StringBuilder(1000);
		parameters = new ArrayList<>();
		ps = null;
	}

	public void addSQL(String sql, Object... args) {
		bufferedSql.append(sql).append("\n");
		parameters.addAll(Arrays.asList(args));
	}

	private void buildStatement() {
		if (ps != null) {
			return;
		}
		ps = bufferedSql.toString();
		for (Object param : parameters) {
			setParam(param);
		}
	}

	private void setParam(Object param) {
		if (param instanceof Integer || param instanceof Long || param instanceof Double || param instanceof Boolean) {
			ps = ps.replaceFirst("(?<!\\\\)\\?", String.valueOf(param));
		} else {
			ps = ps.replaceFirst("(?<!\\\\)\\?", "'" + param + "'");
		}
	}

	public void reset() {
		ps = null;
		parameters.clear();
		bufferedSql.setLength(0);
	}

	public ArrayList<Map<String, String>> querySQL() {
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		buildStatement();
		Cursor cursor = db.rawQuery(ps, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < cursor.getColumnCount(); i++) {
					map.put(cursor.getColumnName(i), cursor.getString(i));
				}
				result.add(map);
			} while (cursor.moveToNext());
		}
		return result;
	}

	public long insert(String tableName, HashMap<String, String> queryValues) {
		ContentValues values = new ContentValues();
		for (Map.Entry<String, String> o : queryValues.entrySet()) {
			Map.Entry<String, String> pair = (Map.Entry) o;
			values.put(pair.getKey(), pair.getValue());
		}
		return db.insert(tableName, null, values);
	}

	public void executeSql(String sql) {
		db.execSQL(sql);
	}
}
