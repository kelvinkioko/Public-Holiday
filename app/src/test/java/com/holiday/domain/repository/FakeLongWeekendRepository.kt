package com.holiday.domain.repository

import com.holiday.domain.model.LongWeekendModel
import com.holiday.util.Response
import com.holiday.util.longWeekendModel

class FakeLongWeekendRepository : LongWeekendRepository {
    override suspend fun fetchAllLongWeekends(
        year: Int,
        countryCode: String
    ): Response<List<LongWeekendModel>> {
        return Response.Success(responseData = longWeekendModel)
    }
}