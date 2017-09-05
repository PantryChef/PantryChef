package com.pantrychefapp.pantrychef;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.pantrychefapp.pantrychef.helper.BOHelper;
import com.pantrychefapp.pantrychef.objectResources.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Recipes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeListActivity extends AppCompatActivity {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_list);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle(getTitle());

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), AddRecipeActivity.class);
				startActivity(intent);
			}
		});

		View recyclerView = findViewById(R.id.recipe_list);
		assert recyclerView != null;
		setupRecyclerView((RecyclerView) recyclerView);

//		Cursor cursor = BOHelper.recipeBO().getRecipes(this);
//		String[] from = {"name", "id"};
//		int[] to = {android.R.id.text1, android.R.id.text2};
//		CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, 0);
//		ListView list = findViewById(R.id.recipe_list);
//		list.setAdapter(cursorAdapter);

		if (findViewById(R.id.recipe_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-w900dp).
			// If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;
		}
	}

	private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
		ArrayList<Recipe> recipes = BOHelper.recipeBO().getRecipes(recyclerView.getContext());
		recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(recipes));
	}

	public class SimpleItemRecyclerViewAdapter
			extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

		private final List<Recipe> mValues;

		public SimpleItemRecyclerViewAdapter(List<Recipe> items) {
			mValues = items;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.recipe_list_content, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			holder.mItem = mValues.get(position);
			holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
			holder.mContentView.setText(mValues.get(position).getName());

			holder.mView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mTwoPane) {
						Bundle arguments = new Bundle();
						arguments.putString(RecipeDetailFragment.ARG_ITEM_ID, String.valueOf(holder.mItem.getId()));
						RecipeDetailFragment fragment = new RecipeDetailFragment();
						fragment.setArguments(arguments);
						getSupportFragmentManager().beginTransaction()
								.replace(R.id.recipe_detail_container, fragment)
								.commit();
					} else {
						Context context = v.getContext();
						Intent intent = new Intent(context, RecipeDetailActivity.class);
						intent.putExtra(RecipeDetailFragment.ARG_ITEM_ID, String.valueOf(holder.mItem.getId()));

						context.startActivity(intent);
					}
				}
			});
		}

		@Override
		public int getItemCount() {
			return mValues.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder {
			public final View mView;
			public final TextView mIdView;
			public final TextView mContentView;
			public Recipe mItem;

			public ViewHolder(View view) {
				super(view);
				mView = view;
				mIdView = (TextView) view.findViewById(R.id.id);
				mContentView = (TextView) view.findViewById(R.id.content);
			}

			@Override
			public String toString() {
				return super.toString() + " '" + mContentView.getText() + "'";
			}
		}
	}

	public void addRecipe(View view) {
		Intent intent = new Intent(this, AddRecipeActivity.class);
		startActivity(intent);
	}
}
