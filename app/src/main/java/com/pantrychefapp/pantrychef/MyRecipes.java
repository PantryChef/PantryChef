package com.pantrychefapp.pantrychef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MyRecipes extends AppCompatActivity {

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_recipes);
	}

	public void addRecipe(View view) {
		Intent intent = new Intent(this, AddRecipeActivity.class);
		startActivity(intent);
	}
}
