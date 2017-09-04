package com.pantrychefapp.pantrychef.helper;

import com.pantrychefapp.pantrychef.businessObjects.RecipeBO;

public class BOHelper {
	private static RecipeBO recipeBO;

	public static RecipeBO recipeBO() {
		if (recipeBO == null) {
			recipeBO = new RecipeBO();
		}
		return recipeBO;
	}
}
