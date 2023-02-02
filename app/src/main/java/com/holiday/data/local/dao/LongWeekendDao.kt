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

    @Query("SELECT * FROM long_weekend")
    suspend fun loadLongWeekends(): List<LongWeekendEntity>

    @Delete
    suspend fun deleteLongWeekend(longWeekendEntity: LongWeekendEntity)
}
