package com.holiday.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.holiday.data.local.entity.LongWeekendEntity

@Dao
interface LongWeekendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLongWeekend(longWeekendEntity: LongWeekendEntity)

    @Query("SELECT * FROM long_weekend WHERE year = :year AND countryCode = :countryCode")
    suspend fun loadLongWeekends(year: Int, countryCode: String): List<LongWeekendEntity>

    @Query(
        """
            SELECT COUNT(dayCount) 
            FROM long_weekend 
            WHERE year = :year
            AND countryCode = :countryCode
        """
    )
    suspend fun countLongWeekends(year: Int, countryCode: String): Int

    @Delete
    suspend fun deleteLongWeekend(longWeekendEntity: LongWeekendEntity)
}
