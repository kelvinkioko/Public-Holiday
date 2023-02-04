package com.holiday.domain.repository

interface HolidayPreferenceRepository {
    suspend fun setCountry(countryCode: String)

    suspend fun getCountry(): Result<String>

    suspend fun setYear(year: Int)

    suspend fun getYear(): Result<Int>
}
