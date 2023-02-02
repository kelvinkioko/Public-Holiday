package com.holiday.domain.repository

import com.holiday.domain.model.CountryModel
import com.holiday.util.Response

interface CountryRepository {
    suspend fun fetchAllCountries(): Response<List<CountryModel>>

    suspend fun fetchCountryInformation(countryCode: String): Response<CountryModel>
}
