package com.example.sd6501_assignment1;

import android.view.View;

import android.widget.RelativeLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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

public class VegetableMenuTest {

    VegetableMenu vegetableMenu = null;

    @Rule
    public ActivityTestRule<VegetableMenu> activityTestRule
            = new ActivityTestRule<>(VegetableMenu.class);

    @Before
    public void setUp() throws Exception {
        vegetableMenu = activityTestRule.getActivity();
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    @Test
    public void testVegetableGrid() {

        RelativeLayout vegetableMenuLayout = vegetableMenu.findViewById(R.id.vegetableMenuLayout);
        assertNotNull(vegetableMenuLayout);

        VegetableMenuFragment vegetableMenuFragment = new VegetableMenuFragment();

        vegetableMenu.getSupportFragmentManager().beginTransaction().add(
                vegetableMenuLayout.getId(), vegetableMenuFragment).commitAllowingStateLoss();

        getInstrumentation().waitForIdleSync();

        View view = vegetableMenuFragment.getView().findViewById(R.id.vegetableGridLayout);
        assertNotNull(view);

        onView(withIndex(withId(R.id.vegetableGridLayout), 0)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        vegetableMenu = null;
    }
}