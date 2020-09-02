package com.example.sd6501_assignment1;

import android.view.View;
import android.widget.RelativeLayout;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class VegetableMenuFragmentTest {

    private TestActivity testActivity = null;

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule
            = new ActivityTestRule<>(TestActivity.class);

    @Before
    public void setUp() throws Exception {
        testActivity = activityTestRule.getActivity();
    }

    @Test
    public void testFragmentLaunch() {

        RelativeLayout testLayout = testActivity.findViewById(R.id.testActivityLayout);
        assertNotNull(testActivity);

        VegetableMenuFragment vegetableMenuFragment = new VegetableMenuFragment();

        testActivity.getSupportFragmentManager().beginTransaction().add(
                testLayout.getId(), vegetableMenuFragment).commitAllowingStateLoss();

        getInstrumentation().waitForIdleSync();

        View view = vegetableMenuFragment.getView().findViewById(R.id.vegetableGridLayout);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        testActivity = null;
    }
}