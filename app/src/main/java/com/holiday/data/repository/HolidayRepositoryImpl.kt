package com.holiday.data.repository

import com.holiday.data.local.dao.HolidaysDao
import com.holiday.data.mapper.mapToHolidaysEntity
import com.holiday.data.mapper.mapToHolidaysModel
import com.holiday.data.remote.PublicHolidayApi
import com.holiday.data.remote.dto.HolidaysDto
import com.holiday.domain.model.HolidaysModel
import com.holiday.domain.repository.HolidaysRepository
import com.holiday.util.Response
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HolidayRepositoryImpl @Inject constructor(
    private val holidayApi: PublicHolidayApi,
    private val holidaysDao: HolidaysDao
) : HolidaysRepository {
    override suspend fun fetchAllPublicHolidays(
        year: Int,
        countryCode: String
    ): Response<List<HolidaysModel>> {
        val numberOfHolidays = holidaysDao.countHolidaysByYearAndCountryCode(
            year = year,
            countryCode = countryCode
        )
        if (numberOfHolidays == 0) {
            try {
                val holidaysDto = holidayApi.getPublicHolidaysByYearCountryCode(
                    year = year,
                    countryCode = countryCode
                )

                insertHolidayToDB(year = year, holidaysDto = holidaysDto)
            } catch (httpException: HttpException) {
                return Response.Error(errorMessage = "Could not load countries")
            }
        }

        val holidaysModel = loadHolidaysFromDB(year = year, countryCode = countryCode)
        return Response.Success(responseData = holidaysModel)
    }

    override suspend fun fetchNextPublicHolidaysWorldWide():
        Response<List<HolidaysModel>> {
        val numberOfHolidays = holidaysDao.countWorldWideHolidays()
        if (numberOfHolidays == 0) {
            try {
                val holidaysDto = holidayApi.getNextPublicHolidayWorldwide()

                insertHolidayToDB(holidaysDto = holidaysDto, worldWide = true)
            } catch (httpException: HttpException) {
                return Response.Error(errorMessage = "Could not load public holidays")
            }
        }

        val holidaysModel = loadHolidaysFromDB(worldWide = true)
        return Response.Success(responseData = holidaysModel)
    }

    private suspend fun loadHolidaysFromDB(
        year: Int? = null,
        countryCode: String? = null,
        worldWide: Boolean = false
    ): List<HolidaysModel> {
        val holidays = if (worldWide) {
            holidaysDao.loadWorldWideHolidays()
        } else {
            holidaysDao.loadHolidaysByYearAndCountryCode(
                year = year ?: 0,
                countryCode = countryCode ?: ""
            )
        }

        val holidaysModel = mutableListOf<HolidaysModel>()
        holidays.map { holidayEntity ->
            println("@@@ holiday entity $holidayEntity")
            val holidayModel = holidayEntity.mapToHolidaysModel()
            holidaysModel.add(holidayModel)
        }

        return holidaysModel
    }

    private suspend fun insertHolidayToDB(
        year: Int = 0,
        holidaysDto: List<HolidaysDto>,
        worldWide: Boolean = false
    ) {
        holidaysDto.map { holidayDto ->
            val holidayEntity = holidayDto.mapToHolidaysEntity(
                holidayYear = year,
                worldWide = worldWide
            )
            holidaysDao.insertHolidaysEntity(holidaysEntity = holidayEntity)
        }
    }
}
