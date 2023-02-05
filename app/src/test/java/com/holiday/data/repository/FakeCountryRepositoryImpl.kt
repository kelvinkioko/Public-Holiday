package com.holiday.data.repository

import com.holiday.domain.model.CountryModel
import com.holiday.domain.repository.CountryRepository
import com.holiday.util.Response
import javax.inject.Inject

@Deprecated(message = "Delete later")
class FakeCountryRepositoryImpl @Inject constructor() : CountryRepository {
    override suspend fun fetchAllCountries(): Response<List<CountryModel>> {
        val countries = mutableListOf(
            CountryModel(commonName = "Albania", officialName = "", countryCode = "AL", region = "", borders = arrayListOf()),
            CountryModel(commonName = "Andorra", officialName = "", countryCode = "AD", region = "", borders = arrayListOf()),
            CountryModel(commonName = "Argentina", officialName = "", countryCode = "AR", region = "", borders = arrayListOf()),
            CountryModel(commonName = "Australia", officialName = "", countryCode = "AU", region = "", borders = arrayListOf()),
            CountryModel(commonName = "Austria", officialName = "", countryCode = "AT", region = "", borders = arrayListOf()),
            CountryModel(commonName = "Bahamas", officialName = "", countryCode = "BS", region = "", borders = arrayListOf())
        )

        return Response.Success(responseData = countries)
    }

    override suspend fun fetchCountryInformation(countryCode: String): Response<CountryModel> {
        val country = CountryModel(
            commonName = "Albania",
            officialName = "",
            countryCode = "AL",
            region = "",
            borders = arrayListOf()
        )

        return Response.Success(responseData = country)
    }
}
