package com.holiday.presentation.world

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.holiday.domain.repository.FakeHolidayRepository
import com.holiday.util.MainDispatcherRule
import com.holiday.util.worldWideHolidaysModel
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
class WorldHolidayViewModelTest {

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var repository = FakeHolidayRepository()

    private lateinit var viewModel: WorldHolidayViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = WorldHolidayViewModel(repository)
    }

    @Test
    fun `load world wide holidays`() = runTest {
        viewModel.loadWorldHolidays()

        viewModel.uiState.test {
            Truth.assertThat(WorldHolidayUIState.Loading(isLoading = false)).isEqualTo(awaitItem())
            Truth.assertThat(WorldHolidayUIState.Loading(isLoading = true)).isEqualTo(awaitItem())
            Truth.assertThat(WorldHolidayUIState.HolidaysLoaded(holidays = worldWideHolidaysModel)).isEqualTo(awaitItem())
            cancel()
        }
    }
}
