package com.example.education_app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    // create a monitor for the second activity, whenever the second activity is launch, this monitor will monitor it
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Register.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitor1 = getInstrumentation().addMonitor(Login.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void TestMainActivity(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                // test the two buttons

                //this button should not be null because we should be able to perform click
                assertNotNull(mainActivity.findViewById(R.id.Login));
                // the second activity should be lauched
                onView(withId(R.id.Login)).perform(click());
                // the monitor return the second activity
                Activity loginactivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
                assertNotNull(loginactivity);
                loginactivity.finish();

                Activity registeractivity = getInstrumentation().waitForMonitorWithTimeout(monitor1,5000);
                assertNotNull(registeractivity);
                registeractivity.finish();
                assertNotNull(mainActivity.findViewById(R.id.Register));
                onView(withId(R.id.Register)).perform(click());
            }
        });

    }


    @After
    public void tearDown() throws Exception {
    }
}