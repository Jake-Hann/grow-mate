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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class VegetablePageTest {

    VegetablePage vegetablePage = null;

    Instrumentation.ActivityMonitor myPlantsMonitor = getInstrumentation().addMonitor(
            MyPlants.class.getName(), null, false);

    @Rule
    public ActivityTestRule<VegetablePage> activityTestRule
            = new ActivityTestRule<>(VegetablePage.class);


    @Before
    public void setUp() throws Exception {
        vegetablePage = activityTestRule.getActivity();
    }

    @Test
    public void testExpandFragment() {

        boolean fragIsClosed;
        boolean no = false;

        onView(withId(R.id.btnDisplayPlantDateFragment)).perform(click());
        fragIsClosed = vegetablePage.plantDatesFragmentCollapsed;
        assertEquals(fragIsClosed,no);

        onView(withId(R.id.btnDisplayGrowingInfoFragment)).perform(click());
        fragIsClosed = vegetablePage.plantDatesFragmentCollapsed;
        assertEquals(fragIsClosed,no);
    }

    @Test
    public void testCollapseFragment() {

        boolean fragIsClosed;
        boolean yes = true;

        onView(withId(R.id.btnDisplayPlantDateFragment)).perform(click());
        onView(withId(R.id.btnDisplayPlantDateFragment)).perform(click());
        fragIsClosed = vegetablePage.plantDatesFragmentCollapsed;
        assertEquals(fragIsClosed,yes);

        onView(withId(R.id.btnDisplayGrowingInfoFragment)).perform(click());
        onView(withId(R.id.btnDisplayGrowingInfoFragment)).perform(click());
        fragIsClosed = vegetablePage.plantDatesFragmentCollapsed;
        assertEquals(fragIsClosed,yes);
    }

    @Test
    public void testAccessMyPlants() {

        onView(withId(R.id.btnAccessMyPlants)).perform(click());

        Activity myPlants = getInstrumentation().waitForMonitorWithTimeout(
                myPlantsMonitor, 5000);
        assertNotNull(myPlants);
        myPlants.finish();
    }

    @After
    public void tearDown() throws Exception {
        vegetablePage = null;
    }
}