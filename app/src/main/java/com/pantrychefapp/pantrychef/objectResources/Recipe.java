package com.pantrychefapp.pantrychef.objectResources;

import java.util.ArrayList;

public class Recipe {
	private long id;
	private String name;
	private ArrayList<Ingredient> ingredients;

	public Recipe(long id, String name, ArrayList<Ingredient> ingredients) {
		setId(id);
		setName(name);
		setIngredients(ingredients);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getDetails() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ingredients: ");
		if (ingredients != null) {
			for (Ingredient ingredient : ingredients) {
				builder.append("\n" + ingredient.getQuantity() + " " + ingredient.getUnit() + " " + ingredient.getIngredientName());
			}
		}
		return builder.toString();
	}
}
