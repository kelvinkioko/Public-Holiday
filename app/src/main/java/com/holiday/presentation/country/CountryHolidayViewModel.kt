package com.holiday.presentation.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holiday.domain.model.CountryModel
import com.holiday.domain.model.HolidaysModel
import com.holiday.domain.repository.CountryRepository
import com.holiday.domain.repository.HolidayPreferenceRepository
import com.holiday.domain.repository.HolidaysRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryHolidayViewModel @Inject constructor(
    private val countryRepository: CountryRepository,
    private val holidaysRepository: HolidaysRepository,
    private val holidayPreferenceRepository: HolidayPreferenceRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<CountryHolidayUIState>()
    val uiState: LiveData<CountryHolidayUIState> = _uiState

    init {
        loadCountryModel()
    }

    private fun loadCountryModel() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val countryCode = holidayPreferenceRepository.getCountry().getOrNull()

                countryCode?.let {
                    val response = countryRepository.fetchCountryInformation(countryCode = it)

                    if (response.responseData != null) {
                        withContext(Dispatchers.Main) {
                            _uiState.value = CountryHolidayUIState.Country(
                                countryModel = response.responseData
                            )
                        }
                    }
                }
            }
        }
    }

    fun loadHolidays(year: Int, countryCode: String) {
        viewModelScope.launch {
            _uiState.value = CountryHolidayUIState.Loading(isLoading = true)
            val holidays = withContext(Dispatchers.IO) {
                holidaysRepository.fetchAllPublicHolidays(year = year, countryCode = countryCode)
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

    data class Country(
        val countryModel: CountryModel
    ) : CountryHolidayUIState()

    data class Holidays(
        val holidays: List<HolidaysModel> = emptyList()
    ) : CountryHolidayUIState()

    data class Error(
        val message: String = ""
    ) : CountryHolidayUIState()
}
