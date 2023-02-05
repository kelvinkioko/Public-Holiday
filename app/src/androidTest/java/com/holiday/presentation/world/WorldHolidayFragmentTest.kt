package com.holiday.presentation.world

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.holiday.R
import com.holiday.launchFragmentInHiltContainer
import com.holiday.util.ViewUtils
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class WorldHolidayFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun verifyFragmentLaunchesSuccessfully() {
        launchFragmentInHiltContainer<WorldHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)
    }

    @Test
    fun verifyWorldWideHolidaysCopyIsAccurate() {
        launchFragmentInHiltContainer<WorldHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)

        val resources = ApplicationProvider.getApplicationContext<Context>().resources

        onView(withId(R.id.worldHeadline)).check(
            matches(ViewMatchers.withText(R.string.world_wide_holidays))
        )

        onView(withId(R.id.worldDescription)).check(
            matches(ViewMatchers.withText(R.string.world_wide_holiday_description))
        )

//        onView(withId(R.id.searchHolidayInputValue)).check(
//            matches(textInputMatcherWithHint(resources.getString(R.string.search_holiday)))
//        )

        onView(withId(R.id.loaderGroup)).perform(ViewUtils.setVisibility(visible = true))

        onView(withId(R.id.loaderMessage)).check(
            matches(ViewMatchers.withText(R.string.loading_world_holidays))
        )
    }

    @Test
    fun verifyWorldWideHolidaysShowSuccessfully() {
        launchFragmentInHiltContainer<WorldHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)
    }

    @Test
    fun verifyWorldWideHolidaysFilterSuccessfully() {
    }

    @Test
    fun verifyWorldWideHolidaysEmptyStateShowsSuccessfully() {
    }
}
