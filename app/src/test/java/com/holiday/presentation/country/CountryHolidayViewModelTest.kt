package com.holiday.presentation.country

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.holiday.domain.repository.FakeCountryRepository
import com.holiday.domain.repository.FakeHolidayPreferenceRepository
import com.holiday.domain.repository.FakeHolidayRepository
import com.holiday.util.MainDispatcherRule
import com.holiday.util.country
import com.holiday.util.countryWideHolidaysModel
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class CountryHolidayViewModelTest {

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var countryRepository = FakeCountryRepository()

    private var holidayRepository = FakeHolidayRepository()

    private var preferenceRepository = FakeHolidayPreferenceRepository()

    private lateinit var viewModel: CountryHolidayViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = CountryHolidayViewModel(
            countryRepository = countryRepository,
            holidaysRepository = holidayRepository,
            holidayPreferenceRepository = preferenceRepository
        )
    }

    @Test
    fun `load country wide holidays`() = runTest {
        viewModel.loadHolidays(year = 2023, countryCode = "GD")

        viewModel.uiState.test {
            assertThat(CountryHolidayUIState.Loading(isLoading = false)).isEqualTo(awaitItem())
            assertThat(CountryHolidayUIState.Loading(isLoading = false)).isEqualTo(awaitItem())
            assertThat(CountryHolidayUIState.Holidays(holidays = countryWideHolidaysModel)).isEqualTo(awaitItem())
            assertThat(CountryHolidayUIState.Country(countryModel = country)).isEqualTo(awaitItem())
            cancel()
        }
    }
}
