package com.example.sd6501_assignment1;

import android.app.Activity;
import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;

public class MyPlantsTest {

    MyPlants myPlants = null;

    Instrumentation.ActivityMonitor plantDetailsMonitor = getInstrumentation().addMonitor(
            ViewPlantRecord.class.getName(), null, false);

    Instrumentation.ActivityMonitor mainMenuMonitor = getInstrumentation().addMonitor(
            MainMenu.class.getName(), null, false);

    @Rule
    public ActivityTestRule<MyPlants> activityTestRule
            = new ActivityTestRule<>(MyPlants.class);

    @Before
    public void setUp() throws Exception {
        myPlants = activityTestRule.getActivity();
    }

    //Requires at least one plant in list to pass
    @Test
    public void testAccessViewPlantDetails() {

        onView(withId(R.id.plantList)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.plantList)).atPosition(0).perform(click());

        Activity plantDetails = getInstrumentation().waitForMonitorWithTimeout(
                plantDetailsMonitor, 5000);
        assertNotNull(plantDetails);
        plantDetails.finish();
    }

    @Test
    public void testAccessMainMenu() {

        onView(withId(R.id.btnAccessMainMenu)).perform(click());

        Activity mainMenu = getInstrumentation().waitForMonitorWithTimeout(
                mainMenuMonitor, 5000);
        assertNotNull(mainMenu);
        mainMenu.finish();
    }

    @After
    public void tearDown() throws Exception {
        myPlants = null;
    }
}