package com.pantrychefapp.pantrychef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getMyPantry(View view) {
        Intent intent = new Intent(this, MyPantry.class);
        startActivity(intent);
    }

    public void getMyRecipes(View view) {
        Intent intent = new Intent(this, MyRecipes.class);
        startActivity(intent);
    }
}
