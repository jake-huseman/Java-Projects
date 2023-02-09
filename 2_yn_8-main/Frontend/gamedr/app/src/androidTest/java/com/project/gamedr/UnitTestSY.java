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
public class UnitTestSY {

    @Rule
    public ActivityScenarioRule<UserProfile> activityRule = new ActivityScenarioRule<>(UserProfile.class);

    @Test
    public void addFpsTag() {

        onView(withId(R.id.fpsTag)).perform(click());
        onView(withId(R.id.profile_update_tag_button)).perform(click());
        onView(withId(R.id.user_profile_tag_display)).check(matches(withText(endsWith("FPS"))));

    }

    @Test
    public void addSportsTag() {

        onView(withId(R.id.sportsTag)).perform(click());
        onView(withId(R.id.profile_update_tag_button)).perform(click());
        onView(withId(R.id.user_profile_tag_display)).check(matches(withText(endsWith("SPORTS"))));


    }


    @Test
    public void addMmoTag() {

        onView(withId(R.id.mmoTag)).perform(click());
        onView(withId(R.id.profile_update_tag_button)).perform(click());
        onView(withId(R.id.user_profile_tag_display)).check(matches(withText(endsWith("MMO"))));


    }



    @Test
    public void deleteTag() {

        onView(withId(R.id.delete_tag_image)).perform(click());
        onView(withId(R.id.user_profile_tag_display)).check(matches(withText(endsWith(""))));


    }




}
