package com.holiday.presentation.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holiday.domain.model.CountryModel
import com.holiday.domain.model.HolidaysModel
import com.holiday.domain.repository.CountryRepository
import com.holiday.domain.repository.HolidayPreferenceRepository
import com.holiday.domain.repository.HolidaysRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryHolidayViewModel @Inject constructor(
    private val countryRepository: CountryRepository,
    private val holidaysRepository: HolidaysRepository,
    private val holidayPreferenceRepository: HolidayPreferenceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryHolidayUIState>(CountryHolidayUIState.Loading())
    val uiState = _uiState.asStateFlow()

    private val holidays = mutableListOf<HolidaysModel>()

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
            val holidaysResponse = withContext(Dispatchers.IO) {
                holidaysRepository.fetchAllPublicHolidays(year = year, countryCode = countryCode)
            }

            _uiState.value = CountryHolidayUIState.Loading(isLoading = false)

            _uiState.value = if (holidaysResponse.responseData != null) {
                holidays.clear()
                holidays.addAll(holidaysResponse.responseData)
                CountryHolidayUIState.Holidays(holidays = holidays)
            } else {
                CountryHolidayUIState.Error(message = holidaysResponse.errorMessage ?: "")
            }
        }
    }

    fun filterHolidays(searchQuery: String) {
        val filteredHolidays = holidays.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                it.localName.contains(searchQuery, ignoreCase = true)
        }

        _uiState.value = CountryHolidayUIState.Holidays(
            holidays = filteredHolidays
        )
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
