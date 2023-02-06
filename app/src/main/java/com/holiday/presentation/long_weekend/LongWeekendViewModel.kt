package com.holiday.presentation.long_weekend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holiday.domain.model.LongWeekendModel
import com.holiday.domain.repository.LongWeekendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LongWeekendViewModel @Inject constructor(
    private val longWeekendRepository: LongWeekendRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LongWeekendUIState>(LongWeekendUIState.Loading())
    val uiState = _uiState.asStateFlow()

    private val weekends = mutableListOf<LongWeekendModel>()

    fun loadLongWeekends(year: Int, countryCode: String) {
        viewModelScope.launch {
            _uiState.value = LongWeekendUIState.Loading(isLoading = true)
            val countriesResponse = withContext(Dispatchers.IO) {
                longWeekendRepository.fetchAllLongWeekends(
                    year = year,
                    countryCode = countryCode
                )
            }

            _uiState.value = LongWeekendUIState.Loading(isLoading = false)
            if (countriesResponse.responseData != null) {
                weekends.clear()
                weekends.addAll(countriesResponse.responseData)
                _uiState.value = LongWeekendUIState.LongWeekend(weekends = weekends)
            } else {
                _uiState.value = LongWeekendUIState.Error(
                    message = countriesResponse.errorMessage ?: ""
                )
            }
        }
    }
}

sealed class LongWeekendUIState {
    data class Loading(
        val isLoading: Boolean = false
    ) : LongWeekendUIState()

    data class LongWeekend(
        val weekends: List<LongWeekendModel> = emptyList()
    ) : LongWeekendUIState()

    data class Error(
        val message: String = ""
    ) : LongWeekendUIState()
}
