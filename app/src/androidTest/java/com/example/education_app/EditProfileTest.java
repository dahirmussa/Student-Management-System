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

import de.hdodenhof.circleimageview.CircleImageView;


@RunWith(AndroidJUnit4.class)
public class EditProfileTest {


    @Rule
    public ActivityTestRule<EditProfile> mActivityTestRule = new ActivityTestRule<EditProfile>(EditProfile.class);
    public EditProfile editProfile;


    @Before
    public void setUp() throws Exception {
        editProfile = mActivityTestRule.getActivity();
    }



    @Test
    public void Testing(){
        getInstrumentation().runOnMainSync(new Runnable(){
            @Override
            public void run() {

                CircleImageView Profile_images =  editProfile.findViewById(R.id.Profile_image);
                EditText username = editProfile.findViewById(R.id.email);
                EditText password = editProfile.findViewById(R.id.password);
                TextView role = editProfile.findViewById(R.id.role);
                Button saves = editProfile.findViewById(R.id.save);



                // setting Error
                Profile_images.performClick();

                username.setError("username error");
                password.setError("password error");
                role.setError("forgot role error");
                saves.setError("save error");



                // performing on click able
                saves.performClick();

            }
        });
    }

    @After
    public void tearDown() throws Exception {
        editProfile = null;
    }
}