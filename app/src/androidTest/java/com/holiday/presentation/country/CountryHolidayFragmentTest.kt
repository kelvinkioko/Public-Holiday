package com.holiday.presentation.country

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.holiday.R
import com.holiday.launchFragmentInHiltContainer
import com.holiday.util.EspressoUtils
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
class CountryHolidayFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun verifyFragmentLaunchesSuccessfully() {
        launchFragmentInHiltContainer<CountryHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)
    }

    @Test
    fun verifyWorldWideHolidaysCopyIsAccurate() {
        launchFragmentInHiltContainer<CountryHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)

        onView(withId(R.id.homeHeader)).check(
            matches(withText(R.string.public_holiday))
        )

        onView(withId(R.id.holidaysHeader)).check(
            matches(withText(R.string.holidays))
        )

        onView(withId(R.id.searchCountryHolidayInputValue))
            .check(matches(withHint(R.string.search_holiday)))

        onView(withId(R.id.loaderGroup)).perform(ViewUtils.setVisibility(visible = true))

        onView(withId(R.id.loaderMessage)).check(
            matches(withText(R.string.loading_country_holidays))
        )
    }

    @Test
    fun verifyCountryDialogShows() {
        launchFragmentInHiltContainer<CountryHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)

        onView(withId(R.id.selectCountry)).perform(click())

        onView(withId(R.id.countryParent)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyCountryDialogShowsCountries() {
        launchFragmentInHiltContainer<CountryHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)

        onView(withId(R.id.selectCountry)).perform(click())

        onView(withId(R.id.countryParent)).check(matches(isDisplayed()))

        onView(EspressoUtils.withRecyclerView(R.id.countriesList).atPosition(0)).check(
            matches(
                hasMinimumChildCount(
                    1
                )
            )
        )
    }

    @Test
    fun verifyYearDialogShows() {
        launchFragmentInHiltContainer<CountryHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)

        onView(withId(R.id.selectYear)).perform(click())

        onView(withId(R.id.yearParent)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyLongWeekendDialogShows() {
        launchFragmentInHiltContainer<CountryHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)

        onView(withId(R.id.longWeekendButton)).perform(click())

        onView(withId(R.id.longHolidayParent)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyWorldWideHolidaysShowSuccessfully() {
        launchFragmentInHiltContainer<CountryHolidayFragment>(themeResId = R.style.Theme_PublicHoliday)

        onView(EspressoUtils.withRecyclerView(R.id.holidaysList).atPosition(0)).check(
            matches(
                hasMinimumChildCount(
                    1
                )
            )
        )
    }

    @Test
    fun verifyWorldWideHolidaysFilterSuccessfully() {
    }

    @Test
    fun verifyWorldWideHolidaysEmptyStateShowsSuccessfully() {
    }
}
