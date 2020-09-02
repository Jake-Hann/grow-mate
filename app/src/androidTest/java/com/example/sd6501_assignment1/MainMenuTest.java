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
import static org.junit.Assert.assertNotNull;

public class MainMenuTest {

    MainMenu mainMenu = null;

    Instrumentation.ActivityMonitor vegetableMenuMonitor = getInstrumentation().addMonitor(
            VegetableMenu.class.getName(), null, false);

    Instrumentation.ActivityMonitor myPlantsMonitor = getInstrumentation().addMonitor(
            MyPlants.class.getName(), null, false);

    @Rule
    public ActivityTestRule<MainMenu> activityTestRule
            = new ActivityTestRule<>(MainMenu.class);

    @Before
    public void setUp() throws Exception {
        mainMenu = activityTestRule.getActivity();
    }

    @Test
    public void testAccessVegetableMenu(){

        onView(withId(R.id.btnAccessVegetableMenu)).perform(click());

        Activity vegetableMenu = getInstrumentation().waitForMonitorWithTimeout(
                vegetableMenuMonitor, 5000);
        assertNotNull(vegetableMenu);
        vegetableMenu.finish();
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
        mainMenu = null;
    }
}