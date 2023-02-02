package com.holiday.data.repository

import com.holiday.data.local.dao.BordersDao
import com.holiday.data.local.dao.CountryDao
import com.holiday.data.local.entity.CountryEntity
import com.holiday.data.mapper.mapToBordersEntity
import com.holiday.data.mapper.mapToBordersModel
import com.holiday.data.mapper.mapToCountryEntity
import com.holiday.data.mapper.mapToCountryModel
import com.holiday.data.remote.PublicHolidayApi
import com.holiday.data.remote.dto.BordersDto
import com.holiday.data.remote.dto.CountryDto
import com.holiday.domain.model.BordersModel
import com.holiday.domain.model.CountryModel
import com.holiday.domain.repository.CountryRepository
import com.holiday.util.Response
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepositoryImpl @Inject constructor(
    private val holidayApi: PublicHolidayApi,
    private val countryDao: CountryDao,
    private val bordersDao: BordersDao
) : CountryRepository {

    override suspend fun fetchAllCountries(): Response<List<CountryModel>> {
        if (countryDao.areThereCountries() == 0) {
            try {
                val countriesFromApi = holidayApi.getAvailableCountries()

                insertCountriesToLocalDB(countriesDto = countriesFromApi)
            } catch (httpException: HttpException) {
                return Response.Error(errorMessage = "Could not load countries")
            }
        }

        val countriesModel = loadCountriesFromLocalDB()
        return Response.Success(responseData = countriesModel)
    }

    override suspend fun fetchCountryInformation(countryCode: String): Response<CountryModel> {
        if (bordersDao.doesCountryCodeHaveBorderInfo(countryCode = countryCode) == 0) {
            try {
                val countryInfoFromApi = holidayApi.getCountryInfo(countryCode = countryCode)
                val countryEntity = CountryEntity(
                    commonName = countryInfoFromApi.commonName,
                    officialName = countryInfoFromApi.officialName,
                    countryCode = countryInfoFromApi.countryCode,
                    region = countryInfoFromApi.region
                )
                countryDao.insertCountry(countryEntity = countryEntity)

                insertCountryBordersToDB(
                    countryCode = countryCode,
                    borders = countryInfoFromApi.borders
                )
            } catch (httpException: HttpException) {
                return Response.Error(errorMessage = "Could not load countries")
            }
        }

        val countryInformation = loadCountryInfoFromDB(countryCode = countryCode)
        return Response.Success(responseData = countryInformation)
    }

    private suspend fun loadCountriesFromLocalDB(): List<CountryModel> {
        val countriesEntity = countryDao.loadCountries()

        val countriesModel = mutableListOf<CountryModel>()

        countriesEntity.map { countryEntity ->
            countriesModel.add(countryEntity.mapToCountryModel())
        }
        return countriesModel
    }

    private suspend fun insertCountriesToLocalDB(countriesDto: List<CountryDto>) {
        countriesDto.map { countryDto ->
            val countryEntity = countryDto.mapToCountryEntity()

            countryDao.insertCountry(countryEntity = countryEntity)
        }
    }

    private suspend fun loadCountryInfoFromDB(countryCode: String): CountryModel {
        val countryModel = countryDao.loadCountry(countryCode = countryCode).mapToCountryModel()

        val bordersEntity = bordersDao.loadBorders(countryCode = countryCode)

        val bordersModel = mutableListOf<BordersModel>()

        bordersEntity.map { borderEntity ->
            bordersModel.add(borderEntity.mapToBordersModel())
        }
        return countryModel.apply { borders = bordersModel }
    }

    private suspend fun insertCountryBordersToDB(countryCode: String, borders: List<BordersDto>) {
        borders.map { borderDto ->
            val borderEntity = borderDto.mapToBordersEntity().also {
                it.referenceCountryCode = countryCode
            }
            bordersDao.insertBorder(bordersEntity = borderEntity)
        }
    }
}
