package com.pantrychefapp.pantrychef;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pantrychefapp.pantrychef.helper.DBHelper;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);
    }
}
