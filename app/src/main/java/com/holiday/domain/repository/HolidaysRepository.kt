package com.holiday.domain.repository

import com.holiday.domain.model.HolidaysModel
import com.holiday.util.Response

interface HolidaysRepository {
    suspend fun fetchAllPublicHolidays(year: Int, countryCode: String):
        Response<List<HolidaysModel>>

    suspend fun fetchNextPublicHolidaysWorldWide():
        Response<List<HolidaysModel>>
}
