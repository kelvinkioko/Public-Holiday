package com.holiday.domain.repository

import com.holiday.domain.model.LongWeekendModel
import com.holiday.util.Response
import com.holiday.util.longWeekendModel

class FakeLongWeekendRepository : LongWeekendRepository {

    var returnError = false

    override suspend fun fetchAllLongWeekends(
        year: Int,
        countryCode: String
    ): Response<List<LongWeekendModel>> {
        return if (returnError) {
            Response.Error(errorMessage = "Could not load long weekends")
        } else {
            Response.Success(responseData = longWeekendModel)
        }
    }
}
