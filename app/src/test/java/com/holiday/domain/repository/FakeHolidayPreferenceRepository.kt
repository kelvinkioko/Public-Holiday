package com.holiday.domain.repository

class FakeHolidayPreferenceRepository : HolidayPreferenceRepository {
    override suspend fun setCountry(countryCode: String) {}

    override suspend fun getCountry(): Result<String> {
        return Result.runCatching { "US" }
    }

    override suspend fun setYear(year: Int) {}

    override suspend fun getYear(): Result<Int> {
        return Result.runCatching { 2023 }
    }
}
