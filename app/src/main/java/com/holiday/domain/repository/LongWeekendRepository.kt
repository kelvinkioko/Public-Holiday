package com.holiday.domain.repository

import com.holiday.domain.model.LongWeekendModel
import com.holiday.util.Response

interface LongWeekendRepository {
    suspend fun fetchAllLongWeekends(year: Int, countryCode: String):
        Response<List<LongWeekendModel>>
}
