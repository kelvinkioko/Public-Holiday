package com.holiday.presentation.world

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holiday.domain.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WorldHolidayViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    fun loadCountries() {
        viewModelScope.launch {
            val countries = withContext(Dispatchers.IO) {
                countryRepository.fetchAllCountries()
            }

            println("@@@ ${countries.responseData}")
        }
    }
}
