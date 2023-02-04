package com.holiday.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.holiday.data.local.entity.HolidaysEntity

@Dao
interface HolidaysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHolidaysEntity(holidaysEntity: HolidaysEntity)

    @Query("SELECT * FROM holidays WHERE year =:year AND countryCode =:countryCode")
    suspend fun loadHolidaysByYearAndCountryCode(
        year: Int,
        countryCode: String
    ): List<HolidaysEntity>

    @Query("SELECT * FROM holidays WHERE countryCode =:countryCode")
    suspend fun loadHolidaysByCountryCode(countryCode: String): List<HolidaysEntity>

    @Query("SELECT * FROM holidays")
    suspend fun loadWorldWideHolidays(): List<HolidaysEntity>

    @Query("SELECT COUNT(name) FROM holidays WHERE year =:year AND countryCode =:countryCode")
    suspend fun countHolidaysByYearAndCountryCode(year: Int, countryCode: String): Int

    @Query("SELECT COUNT(name) FROM holidays WHERE countryCode =:countryCode")
    suspend fun countHolidaysByCountryCode(countryCode: String): Int

    @Query("SELECT COUNT(name) FROM holidays")
    suspend fun countHolidays(): Int

    @Delete
    suspend fun deleteHolidaysEntity(holidaysEntity: HolidaysEntity)
}
