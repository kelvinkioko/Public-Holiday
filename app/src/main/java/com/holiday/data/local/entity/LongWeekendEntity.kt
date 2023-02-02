package com.holiday.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "long_weekend")
data class LongWeekendEntity(
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "endDate") val endDate: String,
    @ColumnInfo(name = "dayCount") val dayCount: String,
    @ColumnInfo(name = "needBridgeDay") val needBridgeDay: String
)
