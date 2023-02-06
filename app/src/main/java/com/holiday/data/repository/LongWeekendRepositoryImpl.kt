package com.holiday.data.repository

import com.holiday.R
import com.holiday.data.local.dao.LongWeekendDao
import com.holiday.data.mapper.LongWeekEndMapper.Mapper.mapToLongWeekEndEntity
import com.holiday.data.mapper.LongWeekEndMapper.Mapper.mapToLongWeekEndModel
import com.holiday.data.remote.PublicHolidayApi
import com.holiday.data.remote.dto.LongWeekendDto
import com.holiday.domain.model.LongWeekendModel
import com.holiday.domain.repository.LongWeekendRepository
import com.holiday.util.Response
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LongWeekendRepositoryImpl @Inject constructor(
    private val holidayApi: PublicHolidayApi,
    private val longWeekendDao: LongWeekendDao
) : LongWeekendRepository {
    override suspend fun fetchAllLongWeekends(
        year: Int,
        countryCode: String
    ): Response<List<LongWeekendModel>> {
        val numberOfWeekends = longWeekendDao.countLongWeekends(
            year = year,
            countryCode = countryCode
        )
        if (numberOfWeekends == 0) {
            try {
                val weekendsDto = holidayApi.getLongWeekendByYearCountryCode(
                    year = year,
                    countryCode = countryCode
                )

                insertWeekendsToDB(year = year, countryCode = countryCode, weekendsDto = weekendsDto)
            } catch (httpException: HttpException) {
                return Response.Error(errorMessage = R.string.could_not_load_long_weekends)
            }
        }

        val holidaysModel = loadHolidaysFromDB(year = year, countryCode = countryCode)
        return Response.Success(responseData = holidaysModel)
    }

    private suspend fun insertWeekendsToDB(
        year: Int = 0,
        countryCode: String,
        weekendsDto: List<LongWeekendDto>
    ) {
        weekendsDto.map { weekendDto ->
            val weekendEntity = weekendDto
                .mapToLongWeekEndEntity(year = year, countryCode = countryCode)
            longWeekendDao.insertLongWeekend(longWeekendEntity = weekendEntity)
        }
    }

    private suspend fun loadHolidaysFromDB(
        year: Int,
        countryCode: String
    ): List<LongWeekendModel> {
        val weekends = longWeekendDao.loadLongWeekends(year = year, countryCode = countryCode)

        val longWeekendModel = mutableListOf<LongWeekendModel>()
        weekends.map { weekendEntity ->
            val weekendModel = weekendEntity.mapToLongWeekEndModel()
            longWeekendModel.add(weekendModel)
        }

        return longWeekendModel
    }
}
