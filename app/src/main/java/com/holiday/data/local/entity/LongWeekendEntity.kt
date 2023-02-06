package com.holiday.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "long_weekend", primaryKeys = ["startDate", "endDate"])
data class LongWeekendEntity(
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "endDate") val endDate: String,
    @ColumnInfo(name = "dayCount") val dayCount: String,
    @ColumnInfo(name = "needBridgeDay") val needBridgeDay: Boolean,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "countryCode") val countryCode: String
)
