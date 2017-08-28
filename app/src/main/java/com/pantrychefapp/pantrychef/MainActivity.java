package com.pantrychefapp.pantrychef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pantrychefapp.pantrychef.helper.DBHelper;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);


        final Button my_pantry_btn = (Button) findViewById(R.id.my_pantry_btn);
        my_pantry_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("Pantry was clicked!");
                Intent i = new Intent(MainActivity.this, MyPantry.class);
                startActivity(i);
            }
        });

        final Button my_recipe_btn = (Button) findViewById(R.id.my_recipes_btn);
        my_recipe_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.out.println("Recipe was clicked!");
                Intent i = new Intent(MainActivity.this, MyRecipes.class);
                startActivity(i);
            }
        });
    }

}
