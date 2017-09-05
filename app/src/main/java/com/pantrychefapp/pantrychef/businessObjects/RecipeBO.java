package com.pantrychefapp.pantrychef.businessObjects;

import android.content.Context;
import android.database.Cursor;

import com.pantrychefapp.pantrychef.dao.DAOFactory;
import com.pantrychefapp.pantrychef.dao.RecipeDAO;
import com.pantrychefapp.pantrychef.objectResources.Recipe;

import java.util.ArrayList;
import java.util.Map;

public class RecipeBO {
	private RecipeDAO recipeDAO = DAOFactory.getDAOFactory().getRecipeDAO();

	public long addRecipe(Context context, String recipeName) {
		return recipeDAO.addRecipe(context, recipeName);
	}

	public void addIngredient(Context context, long recipeId, String ingredientName, double quantity, String unit) {
		recipeDAO.addIngredient(context, recipeId, ingredientName, quantity, unit);
	}

	public ArrayList<Recipe> getRecipes(Context context) {
		return recipeDAO.getRecipes(context);
	}

	public Recipe getRecipe(Context context, long id) {
		return recipeDAO.getRecipe(context, id);
	}
}
