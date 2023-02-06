package com.holiday.presentation.world

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holiday.domain.model.HolidaysModel
import com.holiday.domain.repository.HolidaysRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WorldHolidayViewModel @Inject constructor(
    private val holidaysRepository: HolidaysRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WorldHolidayUIState>(WorldHolidayUIState.Loading())
    val uiState = _uiState.asStateFlow()

    private val holidays = mutableListOf<HolidaysModel>()

    fun loadWorldHolidays() {
        viewModelScope.launch {
            _uiState.value = WorldHolidayUIState.Loading(isLoading = true)
            val holidaysResponse = withContext(Dispatchers.IO) {
                holidaysRepository.fetchNextPublicHolidaysWorldWide()
            }

            _uiState.value = WorldHolidayUIState.Loading(isLoading = false)

            if (holidaysResponse.responseData != null) {
                holidays.clear()
                holidays.addAll(holidaysResponse.responseData)
                _uiState.value = WorldHolidayUIState.HolidaysLoaded(
                    holidays = holidays
                )
            } else {
                _uiState.value = WorldHolidayUIState.Error(
                    message = holidaysResponse.errorMessage ?: ""
                )
            }
        }
    }

    fun filterHolidays(searchQuery: String) {
        val filteredHolidays = holidays.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                it.localName.contains(searchQuery, ignoreCase = true)
        }

        _uiState.value = WorldHolidayUIState.HolidaysLoaded(
            holidays = filteredHolidays
        )
    }
}

sealed class WorldHolidayUIState {
    data class Loading(
        val isLoading: Boolean = false
    ) : WorldHolidayUIState()

    data class HolidaysLoaded(
        val holidays: List<HolidaysModel> = emptyList()
    ) : WorldHolidayUIState()

    data class Error(
        val message: Any? = null
    ) : WorldHolidayUIState()
}
