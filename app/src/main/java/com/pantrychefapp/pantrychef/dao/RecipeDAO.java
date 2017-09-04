package com.pantrychefapp.pantrychef.dao;

import android.content.Context;

public interface RecipeDAO {
	public long addRecipe(Context context, String recipeName);
	public void addIngredient(Context context, long recipeId, String ingredientName, double quantity, String unit);
}
