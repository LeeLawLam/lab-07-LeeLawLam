package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    private void addCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(name));
        onView(withId(R.id.button_confirm)).perform(click());
    }

    //Check whether the activity correctly switched
    @Test
    public void testSwitchesToShowActivity() {
        addCity("Edmonton");
        onData(anything()).inAdapterView(withId(R.id.city_list))
                .atPosition(0).perform(click());
        onView(withId(R.id.text_city_name)).check(matches(isDisplayed()));
    }

    //Test whether the city name is consistent
    @Test
    public void testCityNameIsConsistent() {
        addCity("Edmonton");
        onData(anything()).inAdapterView(withId(R.id.city_list))
                .atPosition(0).perform(click());
        onView(withId(R.id.text_city_name)).check(matches(withText("Edmonton")));
    }

    //Test the "back" button
    @Test
    public void testBackButton() {
        addCity("Edmonton");
        onData(anything()).inAdapterView(withId(R.id.city_list))
                .atPosition(0).perform(click());
        onView(withId(R.id.button_back)).perform(click());
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}
