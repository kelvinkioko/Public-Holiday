package com.holiday.presentation.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holiday.domain.model.HolidaysModel
import com.holiday.domain.repository.HolidaysRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WorldHolidayViewModel @Inject constructor(
    private val holidaysRepository: HolidaysRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<WorldHolidayUIState>()
    val uiState: LiveData<WorldHolidayUIState> = _uiState

    fun loadWorldHolidays() {
        viewModelScope.launch {
            val holidays = withContext(Dispatchers.IO) {
                holidaysRepository.fetchNextPublicHolidaysWorldWide()
            }

            println("@@@ holidays resp -> ${holidays.responseData}")

            if (holidays.responseData != null) {
                _uiState.value = WorldHolidayUIState.Holidays(
                    holidays = holidays.responseData
                )
            } else {
                _uiState.value = WorldHolidayUIState.Error(
                    message = holidays.errorMessage ?: ""
                )
            }
        }
    }
}

sealed class WorldHolidayUIState {
    data class Loading(
        val isLoading: Boolean = false
    ) : WorldHolidayUIState()

    data class Holidays(
        val holidays: List<HolidaysModel> = emptyList()
    ) : WorldHolidayUIState()

    data class Error(
        val message: String = ""
    ) : WorldHolidayUIState()
}
