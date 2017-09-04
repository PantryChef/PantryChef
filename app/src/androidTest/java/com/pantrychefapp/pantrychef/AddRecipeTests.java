package com.pantrychefapp.pantrychef;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.pantrychefapp.pantrychef.helper.DBControl;
import com.pantrychefapp.pantrychef.helper.DBHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Map;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddRecipeTests {
	@Rule
	public ActivityTestRule<AddRecipeActivity> mActivityRule = new ActivityTestRule<AddRecipeActivity>(AddRecipeActivity.class);

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
    public void testContent() {
	    onView(withText("Ingredient Name")).check(matches(isDisplayed()));
    }

    @Test
	public void testAddRecipe() {
	    onView(withId(R.id.recipeName)).perform(typeText("Lemon Chicken"));
	    onView(withId(R.id.ingredientField1)).perform(typeText("Chicken Breast"));
	    onView(withId(R.id.quantityField1)).perform(typeText("8"));
	    onView(withId(R.id.unitField1)).perform(typeText("oz"));
	    onView(withId(R.id.ingredientField2)).perform(typeText("Lemon Pepper"));
	    onView(withId(R.id.quantityField2)).perform(typeText("3"));
	    onView(withId(R.id.unitField2)).perform(typeText("tbsp"));

	    onView(withId(R.id.addIngredientButton)).perform(click());
	    onView(allOf(withText(""), withHint("Ingredient Name"))).perform(typeText("Olive Oil"));
	    onView(allOf(withText(""), withHint("Quantity"))).perform(typeText("1"));
	    onView(allOf(withText(""), withHint("Unit"))).perform(typeText("tsp"));
	    closeSoftKeyboard();
	    onView(withId(R.id.submitRecipeButton)).perform(click());

	    DBControl sql = new DBControl(getTargetContext());
	    sql.open();
	    ArrayList<Map<String, String>> item = sql.select("select * from recipe where name='Lemon Chicken'");
	    assertEquals(1, item.size());
	    long id = Long.parseLong(item.get(0).get("id"));

	    item = sql.select("select * from ingredient where recipeId=" + id);
	    assertEquals(3, item.size());
	    assertEquals("Chicken Breast", item.get(0).get("ingredientName"));
	    assertEquals("8", item.get(0).get("quantity"));
	    assertEquals("oz", item.get(0).get("unit"));
	    assertEquals("Lemon Pepper", item.get(1).get("ingredientName"));
	    assertEquals("3", item.get(1).get("quantity"));
	    assertEquals("tbsp", item.get(1).get("unit"));
	    assertEquals("Olive Oil", item.get(2).get("ingredientName"));
	    assertEquals("1", item.get(2).get("quantity"));
	    assertEquals("tsp", item.get(2).get("unit"));
    }
}
