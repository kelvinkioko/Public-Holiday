package com.holiday.presentation.country_select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holiday.domain.model.CountryModel
import com.holiday.domain.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryUIState>(CountryUIState.Loading())
    val uiState = _uiState.asStateFlow()

    private val countries = mutableListOf<CountryModel>()

    fun loadAllCountries() {
        viewModelScope.launch {
            _uiState.value = CountryUIState.Loading(isLoading = true)
            val countriesResponse = withContext(Dispatchers.IO) {
                countryRepository.fetchAllCountries()
            }

            _uiState.value = CountryUIState.Loading(isLoading = false)
            if (countriesResponse.responseData != null) {
                countries.clear()
                countries.addAll(countriesResponse.responseData)
                _uiState.value = CountryUIState.CountriesLoaded(countries = countries)
            } else {
                _uiState.value = CountryUIState.Error(
                    message = countriesResponse.errorMessage ?: ""
                )
            }
        }
    }

    fun filterCountries(searchQuery: String) {
        val filteredCountries = countries.filter {
            it.commonName.contains(searchQuery, ignoreCase = true) ||
                it.officialName.contains(searchQuery, ignoreCase = true) ||
                it.countryCode.contains(searchQuery, ignoreCase = true)
        }

        _uiState.value = CountryUIState.CountriesLoaded(countries = filteredCountries)
    }
}

sealed class CountryUIState {
    data class Loading(
        val isLoading: Boolean = false
    ) : CountryUIState()

    data class CountriesLoaded(
        val countries: List<CountryModel> = emptyList()
    ) : CountryUIState()

    data class Error(
        val message: Any? = null
    ) : CountryUIState()
}
