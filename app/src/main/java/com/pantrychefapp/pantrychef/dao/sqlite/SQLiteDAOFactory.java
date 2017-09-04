package com.pantrychefapp.pantrychef.dao.sqlite;

import com.pantrychefapp.pantrychef.dao.DAOFactory;
import com.pantrychefapp.pantrychef.dao.RecipeDAO;

public class SQLiteDAOFactory extends DAOFactory {
	private RecipeDAO recipeDAO = null;

	@Override
	public RecipeDAO getRecipeDAO() {
		if (recipeDAO == null) {
			recipeDAO = new SQLiteRecipeDAO();
		}
		return recipeDAO;
	}
}
