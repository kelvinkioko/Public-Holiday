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

                insertHolidayToDB(holidaysDto = holidaysDto)
            } catch (httpException: HttpException) {
                return Response.Error(errorMessage = "Could not load countries")
            }
        }

        val holidaysModel = loadHolidaysFromDB(year = year, countryCode = countryCode)
        return Response.Success(responseData = holidaysModel)
    }

    override suspend fun checkIfTodayIsPublicHoliday(
        countryCode: String
    ): Response<List<HolidaysModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchNextPublicHolidays(countryCode: String):
        Response<List<HolidaysModel>> {
        val numberOfHolidays = holidaysDao.countHolidaysByCountryCode(
            countryCode = countryCode
        )
        if (numberOfHolidays == 0) {
            try {
                val holidaysDto = holidayApi.getNextPublicHolidayCountryCode(
                    countryCode = countryCode
                )

                insertHolidayToDB(holidaysDto = holidaysDto)
            } catch (httpException: HttpException) {
                return Response.Error(errorMessage = "Could not load countries")
            }
        }

        val holidaysModel = loadHolidaysFromDB(countryCode = countryCode)
        return Response.Success(responseData = holidaysModel)
    }

    override suspend fun fetchNextPublicHolidaysWorldWide(countryCode: String):
        Response<List<HolidaysModel>> {
        val numberOfHolidays = holidaysDao.countHolidaysByCountryCode(
            countryCode = countryCode
        )
        if (numberOfHolidays == 0) {
            try {
                val holidaysDto = holidayApi.getNextPublicHolidayWorldwide()

                insertHolidayToDB(holidaysDto = holidaysDto)
            } catch (httpException: HttpException) {
                return Response.Error(errorMessage = "Could not load countries")
            }
        }

        val holidaysModel = loadHolidaysFromDB(countryCode = countryCode)
        return Response.Success(responseData = holidaysModel)
    }

    private suspend fun loadHolidaysFromDB(year: Int? = null, countryCode: String): List<HolidaysModel> {
        val holidays = year?.let {
            holidaysDao.loadHolidaysByYearAndCountryCode(year = year, countryCode = countryCode)
        } ?: kotlin.run {
            holidaysDao.loadHolidaysByCountryCode(countryCode = countryCode)
        }

        val holidaysModel = mutableListOf<HolidaysModel>()
        holidays.map { holidayEntity ->
            val holidayModel = holidayEntity.mapToHolidaysModel()
            holidaysModel.add(holidayModel)
        }

        return holidaysModel
    }

    private suspend fun insertHolidayToDB(holidaysDto: List<HolidaysDto>) {
        holidaysDto.map { holidayDto ->
            val holidayEntity = holidayDto.mapToHolidaysEntity()
            holidaysDao.insertHolidaysEntity(holidaysEntity = holidayEntity)
        }
    }
}
