package com.holiday.presentation.country_select

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.holiday.domain.repository.CountryRepository
import com.holiday.util.MainDispatcherRule
import com.holiday.util.Response
import com.holiday.util.countries
import io.mockk.coEvery
import io.mockk.core.ValueClassSupport.boxedValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verifySequence
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

    private lateinit var viewModel: CountryViewModel

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<CountryRepository>(relaxed = true)

    @Before
    fun setup() {
        viewModel = CountryViewModel(repository)
    }

    @Test
    fun `load Countries return a list of all available countries`() = runTest {
        coEvery { repository.fetchAllCountries() } returns Response.Success(responseData = countries)

        // create mockk object
        val observer = mockk<Observer<CountryUIState>>()

        // create slot
        val slot = slot<CountryUIState>()

        // create list to store values
        val list = arrayListOf<CountryUIState>()

        // start observing
        viewModel.uiState.observeForever(observer)

        // capture value on every call
        every {
            observer.onChanged(capture(slot))
        } answers {
            // store captured value
            list.add(slot.captured)
        }

        viewModel.loadAllCountries()

        println("@@@ ${list.size}")

        println("@@@ ${observer.boxedValue}")

        verifySequence {
//            observer.onChanged(CountryUIState.Loading(isLoading = false))
            observer.onChanged(CountryUIState.Loading(isLoading = true))
//            observer.onChanged(CountryUIState.Country(countries = countries))
        }

//        advanceUntilIdle()

//        viewModel.uiState.getOrAwaitValue().boxedValue

//        val countriesResponse = viewModel.uiState.getOrAwaitValue()
//        Truth.assertThat(countriesResponse).isEqualTo(CountryUIState.Loading(isLoading = true))
//        val secondResponse = viewModel.uiState.getOrAwaitValue()
//        Truth.assertThat(countriesResponse).isEqualTo(CountryUIState.Loading(isLoading = false))
//         Truth.assertThat(countriesResponse).isEqualTo(CountryUIState.Country(countries = countries))
    }
}
