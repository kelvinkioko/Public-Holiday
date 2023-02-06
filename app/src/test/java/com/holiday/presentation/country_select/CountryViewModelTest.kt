package com.holiday.presentation.country_select

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.holiday.domain.repository.FakeCountryRepository
import com.holiday.util.MainDispatcherRule
import com.holiday.util.countries
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
class CountryViewModelTest {

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var repository = FakeCountryRepository()

    private lateinit var viewModel: CountryViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = CountryViewModel(repository)
    }

    @Test
    fun `load Countries return a list of all available countries`() = runTest {
        viewModel.loadAllCountries()

        viewModel.uiState.test {
            assertThat(CountryUIState.Loading(isLoading = false)).isEqualTo(awaitItem())
            assertThat(CountryUIState.Loading(isLoading = true)).isEqualTo(awaitItem())
            assertThat(CountryUIState.Country(countries = countries)).isEqualTo(awaitItem())
            cancel()
        }
    }
}
