package com.project.gamedr;

import android.content.Context;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class unitTest {
    @Rule
    public ActivityScenarioRule<reportUser> activityRule = new ActivityScenarioRule<>(reportUser.class);
    @Test
    public void reportUser() {
       onView(withId(R.id.username_report)).perform(clearText(), typeText("usernameDavid3"));
       onView(withId(R.id.description_report)).perform(clearText(), typeText("Unit test reason"), closeSoftKeyboard());
       onView(withId(R.id.reportuserBtn)).perform(click());
       onView(withId(R.id.report_success)).check(matches(withText(endsWith("com.android.volley.ServerError"))));
    }
}
