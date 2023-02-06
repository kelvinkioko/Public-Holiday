package com.holiday.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.holiday.data.local.database.HolidayDatabase
import com.holiday.util.countryWideHolidays
import com.holiday.util.worldWideHolidays
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
class HolidaysDaoTest {

    private lateinit var holidayDatabase: HolidayDatabase
    private lateinit var holidayDao: HolidaysDao

    private val years = intArrayOf(2022, 2023)
    private val countryCode = arrayListOf("GD", "SI", "MT", "JP", "VA")

    @Before
    fun setup() {
        holidayDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            HolidayDatabase::class.java
        ).allowMainThreadQueries().build()

        holidayDao = holidayDatabase.holidayDao()
    }

    @Test
    fun insertWorldWideHolidays() = runBlocking {
        worldWideHolidays.forEach { holidaysEntity ->
            holidayDao.insertHolidaysEntity(holidaysEntity = holidaysEntity)
        }

        val holidays = holidayDao.loadWorldWideHolidays()

        Truth.assertThat(holidays).isEqualTo(worldWideHolidays)
    }

    @Test
    fun insertCountryHolidays() = runBlocking {
        countryWideHolidays.forEach { holidaysEntity ->
            holidayDao.insertHolidaysEntity(holidaysEntity = holidaysEntity)
        }

        val countryCode = countryCode.random()

        val holiday = holidayDao.loadHolidaysByYearAndCountryCode(
            year = years[1],
            countryCode = countryCode
        )

        Truth
            .assertThat(holiday)
            .isEqualTo(
                countryWideHolidays.filter {
                    it.countryCode == countryCode && it.holidayYear == years[1]
                }
            )
    }

    @Test
    fun deleteHolidayRecord() = runBlocking {
        worldWideHolidays.forEach { holidaysEntity ->
            holidayDao.insertHolidaysEntity(holidaysEntity = holidaysEntity)
        }

        val holidaysEntity = worldWideHolidays.random()

        holidayDao.deleteHolidaysEntity(holidaysEntity = holidaysEntity)

        val holidays = holidayDao.loadWorldWideHolidays()

        Truth.assertThat(holidays).doesNotContain(holidaysEntity)
    }

    @After
    fun teardown() {
        if (::holidayDatabase.isInitialized)
            holidayDatabase.close()
    }
}
