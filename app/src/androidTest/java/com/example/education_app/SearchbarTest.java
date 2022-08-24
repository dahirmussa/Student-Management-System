package com.example.education_app;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SearchbarTest {

    @Rule
    public ActivityTestRule<Searchbar> mActivityTestRule = new ActivityTestRule<Searchbar>(Searchbar.class);

    public Searchbar Searchbar;

    @Before
    public void setUp() throws Exception
    {
        Searchbar = mActivityTestRule.getActivity();
    }

    @Test
    public void searchTesting(){

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                EditText searchField = Searchbar.findViewById(R.id.search_field);
                ImageButton searchImage = Searchbar.findViewById(R.id.search_btn);

                searchField.setText("error on searchbar");
                searchImage.performClick();
            }

        });

    }
    @After
    public void tearDown() throws Exception {
        Searchbar = null;
    }
}