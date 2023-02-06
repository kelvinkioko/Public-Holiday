package com.holiday.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.holiday.data.local.database.HolidayDatabase
import com.holiday.util.longWeekendEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
class LongWeekendDaoTest {

    private lateinit var holidayDatabase: HolidayDatabase
    private lateinit var longWeekendDao: LongWeekendDao

    @Before
    fun setup() {
        holidayDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            HolidayDatabase::class.java
        ).allowMainThreadQueries().build()

        longWeekendDao = holidayDatabase.longWeekendDao()
    }

    @Test
    fun insertLongWeekend() = runBlocking {
        longWeekendEntity.forEach { longWeekend ->
            longWeekendDao.insertLongWeekend(longWeekendEntity = longWeekend)
        }

        val weekends = longWeekendDao.loadLongWeekends(
            year = 2015,
            countryCode = "AU"
        )

        Truth.assertThat(weekends).isEqualTo(longWeekendEntity)
    }

    @Test
    fun countLongWeekend() = runBlocking {
        longWeekendEntity.forEach { longWeekend ->
            longWeekendDao.insertLongWeekend(longWeekendEntity = longWeekend)
        }

        val weekends = longWeekendDao.countLongWeekends(
            year = 2015,
            countryCode = "AU"
        )

        Truth.assertThat(weekends).isEqualTo(longWeekendEntity.size)
    }

    @Test
    fun deleteLongWeekendRecord() = runBlocking {
        longWeekendEntity.forEach { longWeekend ->
            longWeekendDao.insertLongWeekend(longWeekendEntity = longWeekend)
        }

        val weekendEntity = longWeekendEntity.random()

        longWeekendDao.deleteLongWeekend(longWeekendEntity = weekendEntity)

        val holidays = longWeekendDao.loadLongWeekends(
            year = 2015,
            countryCode = "AU"
        )

        Truth.assertThat(holidays).doesNotContain(weekendEntity)
    }

    @After
    fun teardown() {
        if (::holidayDatabase.isInitialized)
            holidayDatabase.close()
    }
}
