package com.example.sd6501_assignment1;

import android.app.Activity;
import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class ViewPlantRecordTest {

    ViewPlantRecord viewPlantRecord = null;

    Instrumentation.ActivityMonitor myPlantsMonitor = getInstrumentation().addMonitor(
            MyPlants.class.getName(), null, false);

    @Rule
    public ActivityTestRule<ViewPlantRecord> activityTestRule
            = new ActivityTestRule<>(ViewPlantRecord.class);

    @Before
    public void setUp() throws Exception {

        viewPlantRecord = activityTestRule.getActivity();
    }

    @Test
    public void testDeleteButton() {

        onView(withId(R.id.btnDeletePlant)).perform(click());

        onView(withText("Are you sure you want to delete this plant record?"))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow()
                .getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonUpdate() {

        onView(withId(R.id.editTxtGerminationDate)).perform(click());
        onView(withId(R.id.editTxtGerminationDate)).perform(clearText(),
                typeText("01/01/2010"));

        onView(withId(R.id.editTxtTransplantDate)).perform(click());
        onView(withId(R.id.editTxtTransplantDate)).perform(clearText(),
                typeText("01/01/2010"));

        onView(withId(R.id.editTxtHarvestDate)).perform(click());
        onView(withId(R.id.editTxtHarvestDate)).perform(clearText(),
                typeText("01/01/2010"));

        onView(withId(R.id.btnUpdatePlantDetails)).perform(click());
        onView(withText("Plant details updated!")).
                inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                .getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void testAccessMyPlants() {

        onView(withId(R.id.btnAccessMyPlants)).perform(click());

        Activity myPlants = getInstrumentation().waitForMonitorWithTimeout(
                myPlantsMonitor, 5000);
        assertNotNull(myPlants);
        myPlants.finish();
    }

    @Test
    public void testHideEditTextCursors() {

        onView(withId(R.id.editTxtGerminationDate)).perform(click());
        onView(withId(R.id.editTxtTransplantDate)).perform(click());
        onView(withId(R.id.editTxtHarvestDate)).perform(click());
        onView(withId(R.id.btnUpdatePlantDetails)).perform(click());

        onView(withId(R.id.editTxtGerminationDate)).check(matches(not(isFocusable())));
        onView(withId(R.id.editTxtTransplantDate)).check(matches(not(isFocusable())));
        onView(withId(R.id.editTxtHarvestDate)).check(matches(not(isFocusable())));
    }

    @After
    public void tearDown() throws Exception {
        viewPlantRecord = null;
    }
}