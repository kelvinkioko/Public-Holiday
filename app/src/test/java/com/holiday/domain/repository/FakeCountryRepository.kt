package com.holiday.domain.repository

import com.holiday.domain.model.CountryModel
import com.holiday.util.Response
import com.holiday.util.countries
import com.holiday.util.country

class FakeCountryRepository : CountryRepository {

    override suspend fun fetchAllCountries(): Response<List<CountryModel>> {
        return Response.Success(responseData = countries)
    }

    override suspend fun fetchCountryInformation(countryCode: String): Response<CountryModel> {
        return Response.Success(responseData = country)
    }
}
