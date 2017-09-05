package com.pantrychefapp.pantrychef;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.MonitoringInstrumentation;

import com.pantrychefapp.pantrychef.helper.DBControl;
import com.pantrychefapp.pantrychef.helper.DBHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeListActivityTests {

	@Rule
	public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

	private DBHelper dbHelper;

	@Before
	public void setup() {
		getTargetContext().deleteDatabase(DBHelper.DATABASE_NAME);
		dbHelper = new DBHelper(getTargetContext());
	}

	@After
	public void tearDown() {
		dbHelper.close();
	}

	@Test
	public void testViewRecipes() {
		DBControl sql = new DBControl(getTargetContext());
		sql.open();
		HashMap<String, String> values = new HashMap<>();
		values.put("name", "X");
		sql.insert("recipe", values);
		values = new HashMap<>();
		values.put("name", "Pancakes");
		sql.insert("recipe", values);
		values = new HashMap<>();
		values.put("recipeId", "2");
		values.put("ingredientName", "Flour");
		values.put("quantity", "4");
		values.put("unit", "cup");
		sql.insert("ingredient", values);
		values = new HashMap<>();
		values.put("recipeId", "2");
		values.put("ingredientName", "Water");
		values.put("quantity", "1");
		values.put("unit", "pint");
		sql.insert("ingredient", values);
		sql.close();
		onView(withText(R.string.my_recipes)).perform(click());
		onView(allOf(withId(R.id.content), withText("X")));
		onView(allOf(withId(R.id.content), withText("Pancakes"))).perform(click());
		onView(withId(R.id.recipe_detail)).check(matches(withText("Ingredients: \n4.0 cup Flour\n1.0 pint Water")));
	}
}