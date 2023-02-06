package com.holiday.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.holiday.data.local.database.HolidayDatabase
import com.holiday.data.local.entity.CountryEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
class CountryDaoTest {

    private lateinit var holidayDatabase: HolidayDatabase
    private lateinit var countryDao: CountryDao

    private val countryCode: String = "AD"

    private val countryOne = CountryEntity(
        commonName = "Andorra",
        officialName = "",
        countryCode = "AD",
        region = ""
    )

    private val countryTwo = CountryEntity(
        commonName = "Albania",
        officialName = "",
        countryCode = "AL",
        region = ""
    )

    private val countries = mutableListOf(countryOne, countryTwo)

    @Before
    fun setup() {
        holidayDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            HolidayDatabase::class.java
        ).allowMainThreadQueries().build()

        countryDao = holidayDatabase.countryDao()
    }

    @Test
    fun insertCountry() = runBlocking {
        countryDao.insertCountry(countryEntity = countryOne)

        val savedCountries = countryDao.loadCountries()

        Truth.assertThat(savedCountries).contains(countryOne)
    }

    @Test
    fun loadListOfCountries() = runBlocking {
        countries.forEach { country ->
            countryDao.insertCountry(countryEntity = country)
        }

        val savedCountries = countryDao.loadCountries()

        Truth.assertThat(savedCountries).isEqualTo(countries)
    }

    @Test
    fun loadListOfCountryByCountryCode() = runBlocking {
        countries.forEach { country ->
            countryDao.insertCountry(countryEntity = country)
        }

        val savedCountries = countryDao.loadCountry(countryCode = countryCode)

        Truth.assertThat(savedCountries).isEqualTo(countryOne)
    }

    @Test
    fun checkIfCountriesAreCached() = runBlocking {
        countries.forEach { country ->
            countryDao.insertCountry(countryEntity = country)
        }

        val numberOfCountries = countryDao.countCountries()

        Truth.assertThat(numberOfCountries).isEqualTo(2)
    }

    @Test
    fun checkIfCountryIsCachedByCountryCode() = runBlocking {
        countries.forEach { country ->
            countryDao.insertCountry(countryEntity = country)
        }

        val numberOfCountries = countryDao.countCountriesByCode(countryCode = countryCode)

        Truth.assertThat(numberOfCountries).isEqualTo(1)
    }

    @Test
    fun deleteExistingCountryRecord() = runBlocking {
        countries.forEach { country ->
            countryDao.insertCountry(countryEntity = country)
        }
        countryDao.deleteCountry(countryEntity = countryOne)

        val numberOfBorders = countryDao.countCountriesByCode(
            countryCode = countryCode
        )

        Truth.assertThat(numberOfBorders).isEqualTo(0)
    }

    @After
    fun teardown() {
        if (::holidayDatabase.isInitialized)
            holidayDatabase.close()
    }
}
