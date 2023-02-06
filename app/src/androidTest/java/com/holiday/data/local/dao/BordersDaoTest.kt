package com.holiday.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.holiday.data.local.database.HolidayDatabase
import com.holiday.data.local.entity.BordersEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
class BordersDaoTest {

    private lateinit var holidayDatabase: HolidayDatabase
    private lateinit var bordersDao: BordersDao

    private val countryCode: String = "US"

    private val bordersEntity = BordersEntity(
        referenceCountryCode = "US",
        commonName = "Canada",
        officialName = "Canada",
        countryCode = "CA",
        region = "Americas"
    )

    @Before
    fun setup() {
        holidayDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            HolidayDatabase::class.java
        ).allowMainThreadQueries().build()

        bordersDao = holidayDatabase.bordersDao()
    }

    @Test
    fun insertBorder() = runBlocking {
        bordersDao.insertBorder(bordersEntity = bordersEntity)

        val savedBorder = bordersDao.loadBorders(countryCode = countryCode)

        assertThat(savedBorder).contains(bordersEntity)
    }

    @Test
    fun loadBorderByCountryCode() = runBlocking {
        bordersDao.insertBorder(bordersEntity = bordersEntity)

        val savedBorder = bordersDao.loadBorders(countryCode = countryCode)

        assertThat(savedBorder).isNotEmpty()
        assertThat(savedBorder).isEqualTo(mutableListOf(bordersEntity))
    }

    @Test
    fun loadEmptyBorderByCountryCode() = runBlocking {
        val savedBorder = bordersDao.loadBorders(countryCode = countryCode)

        assertThat(savedBorder).isEmpty()
    }

    @Test
    fun checkIfCountryHasBorders() = runBlocking {
        bordersDao.insertBorder(bordersEntity = bordersEntity)

        val numberOfBorders = bordersDao.countCountryBorders(
            countryCode = countryCode
        )

        assertThat(numberOfBorders).isEqualTo(1)
    }

    @Test
    fun deleteExistingBorderRecord() = runBlocking {
        bordersDao.insertBorder(bordersEntity = bordersEntity)
        bordersDao.deleteBorder(bordersEntity = bordersEntity)

        val numberOfBorders = bordersDao.countCountryBorders(
            countryCode = countryCode
        )

        assertThat(numberOfBorders).isEqualTo(0)
    }

    @After
    fun teardown() {
        if (::holidayDatabase.isInitialized)
            holidayDatabase.close()
    }
}
