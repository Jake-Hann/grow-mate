package com.example.sd6501_assignment1;

import android.app.Activity;
import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class ToolbarMenuTest {

    MainMenu mainMenu = null;

    Instrumentation.ActivityMonitor mainMenuMonitor = getInstrumentation().addMonitor(
            MainMenu.class.getName(), null, false);

    Instrumentation.ActivityMonitor vegetableMenuMonitor = getInstrumentation().addMonitor(
            VegetableMenu.class.getName(), null, false);

    Instrumentation.ActivityMonitor myPlantsMonitor = getInstrumentation().addMonitor(
            MyPlants.class.getName(), null, false);

    Instrumentation.ActivityMonitor logOutMonitor = getInstrumentation().addMonitor(
            MainActivity.class.getName(), null, false);

    Instrumentation.ActivityMonitor settingsMonitor = getInstrumentation().addMonitor(
            SettingsActivity.class.getName(), null, false);

    @Rule
    public ActivityTestRule<MainMenu> activityTestRule
            = new ActivityTestRule<>(MainMenu.class);

    @Before
    public void setUp() throws Exception {
        mainMenu = activityTestRule.getActivity();
    }

    @Test
    public void testOnCreateOptionsMenu() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
    }

    @Test
    public void testOnOptionsItemSelectedMainMenu() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Main Menu")).perform(click());

        Activity mainMenu = getInstrumentation().waitForMonitorWithTimeout(
                mainMenuMonitor, 5000);
        assertNotNull(mainMenu);
        mainMenu.finish();
    }

    @Test
    public void testOnOptionsItemSelectedVegetableMenu() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText("Vegetable Menu")).perform(click());

        Activity vegetableMenu = getInstrumentation().waitForMonitorWithTimeout(
                vegetableMenuMonitor, 5000);
        assertNotNull(vegetableMenu);
        vegetableMenu.finish();
    }

    @Test
    public void testOnOptionsItemSelectedMyPlants() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText("My Plants")).perform(click());

        Activity myPlants = getInstrumentation().waitForMonitorWithTimeout(
                myPlantsMonitor, 5000);
        assertNotNull(myPlants);
        myPlants.finish();
    }

    @Test
    public void testOnOptionsItemSelectedLogOut() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText("Log Out")).perform(click());

        Activity logOut = getInstrumentation().waitForMonitorWithTimeout(
                logOutMonitor, 5000);
        assertNotNull(logOut);
        logOut.finish();
    }

    @Test
    public void testOnOptionsItemSelectedSettings() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText("Settings")).perform(click());

        Activity settings = getInstrumentation().waitForMonitorWithTimeout(
                settingsMonitor, 5000);
        assertNotNull(settings);
        settings.finish();
    }

    @After
    public void tearDown() throws Exception {
        mainMenu = null;
    }
}