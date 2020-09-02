package com.example.sd6501_assignment1;

import android.app.Activity;
import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor mainMenuMonitor = getInstrumentation().addMonitor(
            MainMenu.class.getName(), null, false);

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mainActivity = activityTestRule.getActivity();
    }

    @Test
    public void testAccessMainMenu() {

        onView(withId(R.id.btnGuestLogin)).perform(click());

        Activity mainMenu = getInstrumentation().waitForMonitorWithTimeout(
                mainMenuMonitor, 5000);
        assertNotNull(mainMenu);
        mainMenu.finish();
    }

    @Test
    public void testValidLoginCredentials() {

        String username = "admin";
        String password = "0000";

        Espresso.onView(withId(R.id.loginUsername)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.loginPassword)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btnLogin)).perform(click());

        Activity mainMenu = getInstrumentation().waitForMonitorWithTimeout(
                mainMenuMonitor, 5000);
        assertNotNull(mainMenu);
        mainMenu.finish();
    }

    @Test
    public void testInvalidLoginCredentials() {

        String username = "hello";
        String password = "incorrect";

        onView(withId(R.id.loginUsername)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginPassword)).perform(typeText(password));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.btnLogin)).check(matches(not(isEnabled())));
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}