package com.example.education_app;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.widget.Button;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityTestRule<Register> mActivityTestRule = new ActivityTestRule<Register>(Register.class);

    public Register registerActivity;

    @Before
    public void setUp() throws Exception {
        registerActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testregister() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText username = registerActivity.findViewById(R.id.email);
                EditText password = registerActivity.findViewById(R.id.password);
                EditText roles = registerActivity.findViewById(R.id.roles);


                username.setText("username");
                password.setText("password");
                roles.setText("roles");

                Button Signup = registerActivity.findViewById(R.id.create);

                Signup.performClick();

                // add assert if its need it.
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        registerActivity = null;
    }
}