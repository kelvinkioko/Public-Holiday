package com.holiday.presentation.country

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
class CountryHolidayViewModel @Inject constructor(
    private val holidaysRepository: HolidaysRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<CountryHolidayUIState>()
    val uiState: LiveData<CountryHolidayUIState> = _uiState

    fun loadHolidays() {
        viewModelScope.launch {
            _uiState.value = CountryHolidayUIState.Loading(isLoading = true)
            val holidays = withContext(Dispatchers.IO) {
                holidaysRepository.fetchAllPublicHolidays(year = 2023, countryCode = "US")
            }

            _uiState.value = CountryHolidayUIState.Loading(isLoading = false)

            if (holidays.responseData != null) {
                _uiState.value = CountryHolidayUIState.Holidays(
                    holidays = holidays.responseData
                )
            } else {
                _uiState.value = CountryHolidayUIState.Error(
                    message = holidays.errorMessage ?: ""
                )
            }
        }
    }
}

sealed class CountryHolidayUIState {
    data class Loading(
        val isLoading: Boolean = false
    ) : CountryHolidayUIState()

    data class Holidays(
        val holidays: List<HolidaysModel> = emptyList()
    ) : CountryHolidayUIState()

    data class Error(
        val message: String = ""
    ) : CountryHolidayUIState()
}
