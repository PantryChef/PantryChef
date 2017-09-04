package com.pantrychefapp.pantrychef.dao.sqlite;

import android.content.Context;

import com.pantrychefapp.pantrychef.dao.RecipeDAO;
import com.pantrychefapp.pantrychef.helper.DBControl;

import java.util.HashMap;

public class SQLiteRecipeDAO implements RecipeDAO {
	@Override
	public long addRecipe(Context context, String recipeName) {
		DBControl sql = new DBControl(context);
		sql.open();
		try {
			HashMap<String, String> values = new HashMap<>();
			values.put("name", recipeName);
			return sql.insert("recipe", values);
		} finally {
			sql.close();
		}
	}

	@Override
	public void addIngredient(Context context, long recipeId, String ingredientName, double quantity, String unit) {
		DBControl sql = new DBControl(context);
		sql.open();
		try {
			HashMap<String, String> values = new HashMap<>();
			values.put("recipeId", String.valueOf(recipeId));
			values.put("ingredientName", ingredientName);
			values.put("quantity", String.valueOf(quantity));
			values.put("unit", unit);
			sql.insert("ingredient", values);
		} finally {
			sql.close();
		}
	}
}
