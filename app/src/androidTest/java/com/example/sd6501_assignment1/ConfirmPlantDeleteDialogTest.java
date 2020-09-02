package com.example.sd6501_assignment1;

import android.app.Activity;
import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;

public class ConfirmPlantDeleteDialogTest {

    ViewPlantRecord viewPlantRecord = null;

    Instrumentation.ActivityMonitor myPlantsMonitor = getInstrumentation().addMonitor(
            MyPlants.class.getName(), null, false);

    Instrumentation.ActivityMonitor viewPlantDetailsMonitor = getInstrumentation().addMonitor(
            ViewPlantRecord.class.getName(), null, false);

    @Rule
    public ActivityTestRule<ViewPlantRecord> activityTestRule
            = new ActivityTestRule<>(ViewPlantRecord.class);

    @Before
    public void setUp() throws Exception {

        viewPlantRecord = activityTestRule.getActivity();
    }

    // Will only work if there is a plant to delete
    @Test
    public void testConfirmPlantDeleteButton() {

        onView(withId(R.id.btnDeletePlant)).perform(click());

        onView(withText("Are you sure you want to delete this plant record?"))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow()
                        .getDecorView()))).check(matches(isDisplayed()));

        onView(withId(R.id.btnConfirmDelete)).perform(click());

        Activity myPlants = getInstrumentation().waitForMonitorWithTimeout(
                myPlantsMonitor, 5000);
        assertNotNull(myPlants);
        myPlants.finish();
    }

    @Test
    public void testCancelPlantDeleteButton() {

        onView(withId(R.id.btnDeletePlant)).perform(click());

        onView(withText("Are you sure you want to delete this plant record?"))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow()
                        .getDecorView()))).check(matches(isDisplayed()));

        onView(withId(R.id.btnConfirmDelete)).perform(click());

        Activity viewPlantDetails = getInstrumentation().waitForMonitorWithTimeout(
                viewPlantDetailsMonitor, 5000);
        assertNotNull(viewPlantDetails);
        viewPlantDetails.finish();
    }

    @After
    public void tearDown() throws Exception {
        viewPlantRecord = null;
    }
}