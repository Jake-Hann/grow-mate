package com.example.sd6501_assignment1;

import android.app.Activity;
import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class IntegrationTests {

    MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor mainActivityMonitor = getInstrumentation().addMonitor(
            MainActivity.class.getName(), null, false);

    Instrumentation.ActivityMonitor myPlantsMonitor = getInstrumentation().addMonitor(
            MainMenu.class.getName(), null, false);

    Instrumentation.ActivityMonitor vegetablePageMonitor = getInstrumentation().addMonitor(
            VegetablePage.class.getName(), null, false);

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mainActivity = activityTestRule.getActivity();
    }

    @Test
    public void userLoginSelectCucumberViewInfoAddToDb() {

        String username = "admin";
        String password = "0000";

        onView(withId(R.id.loginUsername)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginPassword)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.btnAccessVegetableMenu)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.vegetableGrid)).atPosition(1).perform(click());

        onView(withId(R.id.btnDisplayGrowingInfoFragment)).perform(click());

        onView(withId(R.id.btnDisplayPlantDateFragment)).perform(click());
        onView(withId(R.id.plantTodayBtnLayout)).perform(click());
        onView(withId(R.id.addToMyPlantsLayout)).perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Log Out")).perform(click());

        Activity mainActivity = getInstrumentation().waitForMonitorWithTimeout(
                mainActivityMonitor, 5000);
        assertNotNull(mainActivity);
        mainActivity.finish();
    }

    @Test
    public void guestLoginSelectMyPlantsSelectEntryUpdateDates() {

        onView(withId(R.id.btnGuestLogin)).perform(click());

        onView(withId(R.id.btnAccessMyPlants)).perform(click());

        onView(withId(R.id.plantList)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.plantList)).atPosition(0).perform(click());

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
    public void guestLoginSelectCabbageAddDeleteRecord() {

        onView(withId(R.id.btnGuestLogin)).perform(click());

        onView(withId(R.id.btnAccessVegetableMenu)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.vegetableGrid)).atPosition(2).perform(click());

        onView(withId(R.id.btnDisplayPlantDateFragment)).perform(click());
        onView(withId(R.id.plantTodayBtnLayout)).perform(click());
        onView(withId(R.id.addToMyPlantsLayout)).perform(click());

        onView(withId(R.id.btnAccessMyPlants)).perform(click());

        onView(withId(R.id.plantList)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.plantList)).atPosition(0).perform(click());

        onView(withId(R.id.btnDeletePlant)).perform(click());
        onView(withId(R.id.btnConfirmDelete)).perform(click());

        Activity myPlants = getInstrumentation().waitForMonitorWithTimeout(
                myPlantsMonitor, 5000);
        assertNotNull(myPlants);
        myPlants.finish();
    }

    @Test
    public void testVegetableMenu() {

        onView(withId(R.id.btnGuestLogin)).perform(click());

        onView(withId(R.id.btnAccessVegetableMenu)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.vegetableGrid)).atPosition(0).perform(click());
        onView(withText("Spring Onion")).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Vegetable Menu")).perform(click());

        onData(anything()).inAdapterView(withId(R.id.vegetableGrid)).atPosition(1).perform(click());
        onView(withText("Cucumber")).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Vegetable Menu")).perform(click());

        onData(anything()).inAdapterView(withId(R.id.vegetableGrid)).atPosition(2).perform(click());
        onView(withText("Cabbage")).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Vegetable Menu")).perform(click());

        onData(anything()).inAdapterView(withId(R.id.vegetableGrid)).atPosition(3).perform(click());
        onView(withText("Tomato")).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Vegetable Menu")).perform(click());

        onData(anything()).inAdapterView(withId(R.id.vegetableGrid)).atPosition(4).perform(click());
        onView(withText("Lettuce")).check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Vegetable Menu")).perform(click());

        onData(anything()).inAdapterView(withId(R.id.vegetableGrid)).atPosition(5).perform(click());
        onView(withText("Violet Cauliflower")).check(matches(isDisplayed()));

        Activity cauliflowerPage = getInstrumentation().waitForMonitorWithTimeout(
                vegetablePageMonitor, 5000);
        assertNotNull(cauliflowerPage);
        cauliflowerPage.finish();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}