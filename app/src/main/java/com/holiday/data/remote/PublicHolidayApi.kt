package com.holiday.data.remote

import com.holiday.data.remote.dto.CountryDto
import com.holiday.data.remote.dto.CountryInfoDto
import com.holiday.data.remote.dto.HolidaysDto
import com.holiday.data.remote.dto.LongWeekendDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicHolidayApi {

    // region: Country
    @GET("AvailableCountries")
    suspend fun getAvailableCountries(): List<CountryDto>

    @GET("CountryInfo/{countryCode}")
    suspend fun getCountryInfo(
        @Path("countryCode") countryCode: String
    ): CountryInfoDto
    // endregion

    // region: Long holiday
    @GET("LongWeekend/{year}/{countryCode}")
    suspend fun getLongWeekendByYearCountryCode(
        @Path("year") year: String,
        @Path("countryCode") countryCode: String
    ): List<LongWeekendDto>
    // endregion

    // region: Public holiday
    @GET("PublicHolidays/{year}/{countryCode}")
    suspend fun getPublicHolidaysByYearCountryCode(
        @Path("year") year: String,
        @Path("countryCode") countryCode: String
    ): List<HolidaysDto>

    @GET("IsTodayPublicHoliday//{countryCode}")
    suspend fun getIsTodayPublicHolidayCountryCode(
        @Path("countryCode") countryCode: String
    ): HolidaysDto

    @GET("NextPublicHolidays//{countryCode}")
    suspend fun getNextPublicHolidayCountryCode(
        @Path("countryCode") countryCode: String
    ): List<HolidaysDto>

    @GET("NextPublicHolidaysWorldwide/")
    suspend fun getNextPublicHolidayWorldwide(): List<HolidaysDto>
    // endregion
}
