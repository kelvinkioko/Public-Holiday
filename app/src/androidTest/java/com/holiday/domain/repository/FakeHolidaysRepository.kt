package com.holiday.domain.repository

import com.holiday.domain.model.HolidaysModel
import com.holiday.util.Response
import com.holiday.util.countryWideHolidaysModel
import com.holiday.util.worldWideHolidaysModel

class FakeHolidaysRepository : HolidaysRepository {
    override suspend fun fetchAllPublicHolidays(
        year: Int,
        countryCode: String
    ): Response<List<HolidaysModel>> {
        return Response.Success(responseData = countryWideHolidaysModel)
    }

    override suspend fun fetchNextPublicHolidaysWorldWide(): Response<List<HolidaysModel>> {
        return Response.Success(responseData = worldWideHolidaysModel)
    }
}
