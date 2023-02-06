package com.holiday.presentation.long_weekend

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.holiday.domain.repository.FakeLongWeekendRepository
import com.holiday.util.MainDispatcherRule
import com.holiday.util.longWeekendModel
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
class LongWeekendViewModelTest {

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var repository = FakeLongWeekendRepository()

    private lateinit var viewModel: LongWeekendViewModel

    private val year = 2015

    private val countryCode = "AU"

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = LongWeekendViewModel(repository)
    }

    @Test
    fun `load Countries return a list of all available countries`() = runTest {
        viewModel.loadLongWeekends(year = year, countryCode = countryCode)

        viewModel.uiState.test {
            Truth.assertThat(LongWeekendUIState.Loading(isLoading = false)).isEqualTo(awaitItem())
            Truth.assertThat(LongWeekendUIState.Loading(isLoading = true)).isEqualTo(awaitItem())
            Truth.assertThat(LongWeekendUIState.LongWeekend(weekends = longWeekendModel)).isEqualTo(awaitItem())
            cancel()
        }
    }
}