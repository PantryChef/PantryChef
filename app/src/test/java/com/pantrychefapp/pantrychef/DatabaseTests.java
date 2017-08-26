package com.pantrychefapp.pantrychef;

import com.pantrychefapp.pantrychef.helper.DBControl;
import com.pantrychefapp.pantrychef.helper.DBHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19, packageName = "com.pantrychefapp.pantrychef")
public class DatabaseTests {

    private DBHelper dbHelper;

    @Before
    public void setup() {
        dbHelper = new DBHelper(RuntimeEnvironment.application);
    }

    @After
    public void tearDown() {
        dbHelper.close();
    }

    @Test
    public void insertAndSelect() {
        DBControl sql = new DBControl(RuntimeEnvironment.application);
        sql.open();
        HashMap<String, String> valuesMap = new HashMap<>();
        valuesMap.put("name", "Chicken");
        valuesMap.put("amount", "245.8");
        sql.insert("pantry", valuesMap);

        valuesMap.clear();
        valuesMap.put("name", "Beef");
        valuesMap.put("amount", "189.649");
        sql.insert("pantry", valuesMap);

        ArrayList<Map<String, String>> item = sql.select("select * from pantry where name='Chicken'");
        assertEquals(1, item.size());
        assertEquals("Chicken", item.get(0).get("name"));
        assertEquals("245.8", item.get(0).get("amount"));

        item = sql.select("select * from pantry where amount=189.649");
        assertEquals(1, item.size());
        assertEquals("Beef", item.get(0).get("name"));
        assertEquals("189.649", item.get(0).get("amount"));
    }
}
