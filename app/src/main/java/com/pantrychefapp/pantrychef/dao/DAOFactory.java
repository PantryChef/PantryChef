package com.pantrychefapp.pantrychef.dao;

import com.pantrychefapp.pantrychef.dao.sqlite.SQLiteDAOFactory;

public abstract class DAOFactory {
	private static DAOFactory daoFactory = null;

	public abstract RecipeDAO getRecipeDAO();

	public static DAOFactory getDAOFactory() {
		if (daoFactory == null) {
			daoFactory = new SQLiteDAOFactory();
		}
		return daoFactory;
	}
}
