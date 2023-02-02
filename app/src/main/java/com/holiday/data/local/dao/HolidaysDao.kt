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

    @Query("SELECT * FROM holidays")
    suspend fun loadHolidaysEntities(): List<HolidaysEntity>

    @Delete
    suspend fun deleteHolidaysEntity(holidaysEntity: HolidaysEntity)
}
