package com.holiday.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.holiday.data.local.entity.CountryEntity

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countryEntity: CountryEntity)

    @Query("SELECT * FROM country ORDER BY commonName ASC")
    suspend fun loadCountries(): List<CountryEntity>

    @Query(" SELECT * FROM country WHERE countryCode = :countryCode")
    suspend fun loadCountry(countryCode: String): CountryEntity

    @Query("SELECT COUNT(countryCode) FROM country")
    suspend fun countCountries(): Int

    @Query("SELECT COUNT(countryCode) FROM country WHERE countryCode = :countryCode")
    suspend fun countCountriesByCode(countryCode: String): Int

    @Delete
    suspend fun deleteCountry(countryEntity: CountryEntity)
}
