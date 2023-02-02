package com.holiday.domain.repository

import com.holiday.domain.model.HolidaysModel
import com.holiday.util.Response

interface HolidaysRepository {
    suspend fun fetchAllPublicHolidays(year: Int, countryCode: String):
        Response<List<HolidaysModel>>

    suspend fun checkIfTodayIsPublicHoliday(countryCode: String):
        Response<List<HolidaysModel>>

    suspend fun fetchNextPublicHolidays(year: Int, countryCode: String):
        Response<List<HolidaysModel>>

    suspend fun fetchNextPublicHolidaysWorldWide(year: Int, countryCode: String):
        Response<List<HolidaysModel>>
}
