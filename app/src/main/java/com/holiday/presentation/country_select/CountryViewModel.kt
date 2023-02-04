package com.holiday.presentation.country_select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holiday.domain.model.CountryModel
import com.holiday.domain.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<CountryUIState>()
    val uiState: LiveData<CountryUIState> = _uiState

    fun loadAllCountries() {
        viewModelScope.launch {
            val holidays = withContext(Dispatchers.IO) {
                countryRepository.fetchAllCountries()
            }

            if (holidays.responseData != null) {
                _uiState.value = CountryUIState.Country(
                    countries = holidays.responseData
                )
            } else {
                _uiState.value = CountryUIState.Error(
                    message = holidays.errorMessage ?: ""
                )
            }
        }
    }
}

sealed class CountryUIState {
    data class Loading(
        val isLoading: Boolean = false
    ) : CountryUIState()

    data class Country(
        val countries: List<CountryModel> = emptyList()
    ) : CountryUIState()

    data class Error(
        val message: String = ""
    ) : CountryUIState()
}
