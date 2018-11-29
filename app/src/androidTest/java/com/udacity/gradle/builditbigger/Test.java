package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class Test {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityAndroidTestRule = new ActivityTestRule<>(MainActivity.class);

    @org.junit.Test
    public void testAsyncTask(){
        onView(withId(R.id.fragment_button)).perform(click());
        onView(withId(R.id.fragment_main_tv))
                .check(matches(not(withText(""))));
    }
}