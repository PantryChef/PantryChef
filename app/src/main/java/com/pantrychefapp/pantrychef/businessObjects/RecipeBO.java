package com.pantrychefapp.pantrychef.businessObjects;

import android.content.Context;

import com.pantrychefapp.pantrychef.dao.DAOFactory;
import com.pantrychefapp.pantrychef.dao.RecipeDAO;

public class RecipeBO {
	private RecipeDAO recipeDAO = DAOFactory.getDAOFactory().getRecipeDAO();

	public long addRecipe(Context context, String recipeName) {
		return recipeDAO.addRecipe(context, recipeName);
	}

	public void addIngredient(Context context, long recipeId, String ingredientName, double quantity, String unit) {
		recipeDAO.addIngredient(context, recipeId, ingredientName, quantity, unit);
	}
}
