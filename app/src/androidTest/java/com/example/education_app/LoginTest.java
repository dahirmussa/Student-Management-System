package com.example.education_app;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.junit.Assert.*;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<Login> mActivityTestRule = new ActivityTestRule<Login>(Login.class);
    public Login LoginActivity;

    @Before
    public void setUp() throws Exception {
        LoginActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void Testing(){
        getInstrumentation().runOnMainSync(new Runnable(){
            @Override
            public void run() {

                EditText username = LoginActivity.findViewById(R.id.email);
                EditText password = LoginActivity.findViewById(R.id.password);
                TextView Forgot = LoginActivity.findViewById(R.id.Forgot);
                Button Login = LoginActivity.findViewById(R.id.aLogin);



                // setting Error
                username.setError("username error");
                password.setError("password error");
                Forgot.setError("forgot password error");
                Login.setError("Login error");



                // performing on click able
                Forgot.performClick();
                Login.performClick();

            }
        });
    }
    @After
    public void tearDown() throws Exception {
        LoginActivity = null;
    }
}