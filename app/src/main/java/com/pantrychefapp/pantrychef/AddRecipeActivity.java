package com.pantrychefapp.pantrychef;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.pantrychefapp.pantrychef.helper.BOHelper;
import com.pantrychefapp.pantrychef.helper.DBHelper;

public class AddRecipeActivity extends AppCompatActivity {

	int ingredientId = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_recipe);
	}

	public void addIngredientField(View view) {
		TableRow tr = new TableRow(this);
		tr.setId(ingredientId++);
		tr.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
		TableRow.LayoutParams layoutParamsIngredients = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2);
		layoutParamsIngredients.column=1;

		TableRow.LayoutParams layoutParamsQuantity = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1);
		layoutParamsQuantity.column=2;

		TableRow.LayoutParams layoutParamsUnits = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1);
		layoutParamsQuantity.column=3;

		EditText ingredientField = new EditText(this);
		ingredientField.setHint(R.string.addRecipeHeaderIngredient);
		ingredientField.setLayoutParams(layoutParamsIngredients);
		ingredientField.setTag(getText(R.string.tag_ingredientField));
		ingredientField.setInputType(InputType.TYPE_CLASS_TEXT);
		tr.addView(ingredientField);

		EditText quantityField = new EditText(this);
		quantityField.setHint(R.string.addRecipeHeaderQuantity);
		quantityField.setLayoutParams(layoutParamsQuantity);
		quantityField.setTag(getText(R.string.tag_quantityField));
		quantityField.setInputType(InputType.TYPE_CLASS_NUMBER);
		tr.addView(quantityField);

		EditText unitField = new EditText(this);
		unitField.setHint(R.string.addRecipeHeaderUnit);
		unitField.setLayoutParams(layoutParamsUnits);
		unitField.setTag(getText(R.string.tag_unitField));
		unitField.setInputType(InputType.TYPE_CLASS_TEXT);
		tr.addView(unitField);

		TableLayout tableLayout = (TableLayout) findViewById(R.id.ingredientListLayout);
		tableLayout.addView(tr);
	}

	public void submitRecipe(View view) {
		TableLayout tableLayout = (TableLayout) findViewById(R.id.ingredientListLayout);

		// Write recipe to DB
		String recipeName = ((EditText) findViewById(R.id.recipeName)).getText().toString();
		long recipeId = BOHelper.recipeBO().addRecipe(this, recipeName);

		int rowCount = tableLayout.getChildCount();
		// Skipping row 0 (header row)
		for (int i = 1; i < rowCount; i++) {
			TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

			EditText ingredientField = tableRow.findViewWithTag(getText(R.string.tag_ingredientField));
			String ingredientName = ingredientField.getText().toString();

			EditText quantityField = tableRow.findViewWithTag(getText(R.string.tag_quantityField));
			String quantityStr = quantityField.getText().toString();
			double quantity = Double.parseDouble(quantityStr);

			EditText unitField = tableRow.findViewWithTag(getText(R.string.tag_unitField));
			String unit = unitField.getText().toString();

			// Write ingredient to DB
			BOHelper.recipeBO().addIngredient(this, recipeId, ingredientName, quantity, unit);
		}
	}
}
