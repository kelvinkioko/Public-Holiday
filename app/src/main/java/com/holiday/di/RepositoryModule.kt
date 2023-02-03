package com.holiday.di

import com.holiday.data.repository.CountryRepositoryImpl
import com.holiday.data.repository.HolidayRepositoryImpl
import com.holiday.domain.repository.CountryRepository
import com.holiday.domain.repository.HolidaysRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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
}
