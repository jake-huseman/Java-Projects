package com.project.gamedr;

import android.app.Activity;
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
public class matchsetTest {
    @Rule
    public ActivityScenarioRule<MatchsetActivity> activityRule = new ActivityScenarioRule<>(MatchsetActivity.class);
    @Test
    public void reportUser() {
        onView(withId(R.id.user1Txt)).perform(clearText(), typeText("1"));
        onView(withId(R.id.DescriptionTxt)).perform(clearText(), typeText("Test description"));
        onView(withId(R.id.user2Txt)).perform(clearText(), typeText("8"), closeSoftKeyboard());
        onView(withId(R.id.SetMatchBtn)).perform(click());
        onView(withId(R.id.successText)).check(matches(withText(endsWith("success"))));
    }
}

