package com.holiday.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "borders")
data class BordersEntity(
    @ColumnInfo(name = "commonName") val commonName: String,
    @ColumnInfo(name = "officialName") val officialName: String,
    @ColumnInfo(name = "countryCode") val countryCode: String,
    @ColumnInfo(name = "region") val region: String
)
