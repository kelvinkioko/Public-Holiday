package com.holiday.di

import android.app.Application
import androidx.room.Room
import com.holiday.data.local.dao.BordersDao
import com.holiday.data.local.dao.CountryDao
import com.holiday.data.local.dao.HolidaysDao
import com.holiday.data.local.dao.LongWeekendDao
import com.holiday.data.local.database.HolidayDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesHolidayDatabase(application: Application): HolidayDatabase {
        return Room.databaseBuilder(
            application,
            HolidayDatabase::class.java,
            "publicHolidays"
        ).build()
    }

    @Provides
    @Singleton
    fun providesBordersDao(holidayDatabase: HolidayDatabase): BordersDao {
        return holidayDatabase.bordersDao()
    }

    @Provides
    @Singleton
    fun providesCountryDao(holidayDatabase: HolidayDatabase): CountryDao {
        return holidayDatabase.countryDao()
    }

    @Provides
    @Singleton
    fun providesHolidaysDao(holidayDatabase: HolidayDatabase): HolidaysDao {
        return holidayDatabase.holidayDao()
    }

    @Provides
    @Singleton
    fun providesLongWeekendDao(holidayDatabase: HolidayDatabase): LongWeekendDao {
        return holidayDatabase.longWeekendDao()
    }
}
