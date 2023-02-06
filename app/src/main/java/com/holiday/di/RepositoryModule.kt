package com.holiday.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.holiday.data.repository.CountryRepositoryImpl
import com.holiday.data.repository.HolidayPreferenceRepositoryImpl
import com.holiday.data.repository.HolidayRepositoryImpl
import com.holiday.data.repository.LongWeekendRepositoryImpl
import com.holiday.domain.repository.CountryRepository
import com.holiday.domain.repository.HolidayPreferenceRepository
import com.holiday.domain.repository.HolidaysRepository
import com.holiday.domain.repository.LongWeekendRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCountryRepository(
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository

    @Binds
    @Singleton
    fun bindHolidaysRepository(
        holidayRepositoryImpl: HolidayRepositoryImpl
    ): HolidaysRepository

    @Binds
    @Singleton
    fun bindHolidayPreferenceRepository(
        holidayPreferenceRepositoryImpl: HolidayPreferenceRepositoryImpl
    ): HolidayPreferenceRepository

    @Binds
    @Singleton
    fun bindLongWeekendRepository(
        longWeekendRepositoryImpl: LongWeekendRepositoryImpl
    ): LongWeekendRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.holidayDataStore
        }
    }
}
