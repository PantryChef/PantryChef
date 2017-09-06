package com.pantrychefapp.pantrychef.dao;

import android.content.Context;
import android.database.Cursor;

import com.pantrychefapp.pantrychef.objectResources.Recipe;

import java.util.ArrayList;
import java.util.Map;

public interface RecipeDAO {
	public long addRecipe(Context context, String recipeName);
	public void addIngredient(Context context, long recipeId, String ingredientName, double quantity, String unit);
	public ArrayList<Recipe> getRecipes(Context context);
	public Recipe getRecipe(Context context, long id);
}
