package com.pantrychefapp.pantrychef.dao.sqlite;

import android.content.Context;
import android.database.Cursor;

import com.pantrychefapp.pantrychef.dao.RecipeDAO;
import com.pantrychefapp.pantrychef.helper.DBControl;
import com.pantrychefapp.pantrychef.objectResources.Ingredient;
import com.pantrychefapp.pantrychef.objectResources.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	@Override
	public ArrayList<Recipe> getRecipes(Context context) {
		ArrayList<Recipe> recipes = new ArrayList<>();
		DBControl sql = new DBControl(context);
		sql.open();
		try {
			sql.addSQL("select * from recipe");
			ArrayList<Map<String, String>> query = sql.querySQL();
			for (Map<String, String> row : query) {
				Recipe recipe = new Recipe(Long.parseLong(row.get("id")), row.get("name"), null);
				recipes.add(recipe);
			}
		} finally {
			sql.close();
		}
		return recipes;
	}

	@Override
	public Recipe getRecipe(Context context, long id) {
		Recipe recipe = null;
		DBControl sql = new DBControl(context);
		sql.open();
		try {
			sql.addSQL("select * from recipe where id=?", id);
			Map<String, String> query = sql.querySQL().get(0);
			recipe = new Recipe(Long.parseLong(query.get("id")), query.get("name"), null);
			sql.reset();
			sql.addSQL("select * from ingredient where recipeId=?", id);
			ArrayList<Map<String, String>> ingredientsQuery = sql.querySQL();
			ArrayList<Ingredient> ingredients = new ArrayList<>();
			for (Map<String, String> ingredientQuery : ingredientsQuery) {
				Ingredient ingredient = new Ingredient(
						Long.parseLong(ingredientQuery.get("id")),
						Long.parseLong(ingredientQuery.get("recipeId")),
						ingredientQuery.get("ingredientName"),
						Double.parseDouble(ingredientQuery.get("quantity")),
						ingredientQuery.get("unit"));
				ingredients.add(ingredient);
			}
			recipe.setIngredients(ingredients);
		} finally {
			sql.close();
		}
		return recipe;
	}
}
