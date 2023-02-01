package com.holiday.data.remote

import com.holiday.data.remote.dto.CountryDto
import com.holiday.data.remote.dto.CountryInfoDto
import com.holiday.data.remote.dto.HolidaysDto
import com.holiday.data.remote.dto.LongWeekendDto
import retrofit2.http.GET

interface PublicHolidayApi {

    // region: Country
    @GET("AvailableCountries")
    suspend fun getAvailableCountries(): List<CountryDto>

    @GET("CountryInfo/")
    suspend fun getCountryInfo(): CountryInfoDto
    // endregion

    // region: Long holiday
    @GET("LongWeekend/")
    suspend fun getLongWeekendByYearCountryCode(): List<LongWeekendDto>
    // endregion

    // region: Public holiday
    @GET("PublicHolidays/")
    suspend fun getPublicHolidaysByYearCountryCode(): List<HolidaysDto>

    @GET("IsTodayPublicHoliday/")
    suspend fun getIsTodayPublicHolidayCountryCode(): HolidaysDto

    @GET("NextPublicHolidays/")
    suspend fun getNextPublicHolidayCountryCode(): List<HolidaysDto>

    @GET("NextPublicHolidaysWorldwide/")
    suspend fun getNextPublicHolidayWorldwide(): List<HolidaysDto>
    // endregion
}
