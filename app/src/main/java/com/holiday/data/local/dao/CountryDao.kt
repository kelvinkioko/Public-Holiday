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
    suspend fun insertCountry(CountryEntity: CountryEntity)

    @Query("SELECT * FROM country")
    suspend fun loadCountries(): List<CountryEntity>

    @Query("SELECT COUNT(countryCode) FROM country")
    suspend fun areThereCountries(): Int

    @Delete
    suspend fun deleteCountry(CountryEntity: CountryEntity)
}
