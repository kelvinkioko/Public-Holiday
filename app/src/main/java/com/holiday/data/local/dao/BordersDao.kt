package com.holiday.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.holiday.data.local.entity.BordersEntity

@Dao
interface BordersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBorder(bordersEntity: BordersEntity)

    @Query("SELECT * FROM borders")
    suspend fun loadBorders(): List<BordersEntity>

    @Delete
    suspend fun deleteBorder(bordersEntity: BordersEntity)
}
