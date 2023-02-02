package com.holiday.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "country", primaryKeys = ["countryCode"])
data class CountryEntity(
    @ColumnInfo(name = "commonName") val commonName: String,
    @ColumnInfo(name = "officialName") val officialName: String,
    @ColumnInfo(name = "countryCode") val countryCode: String,
    @ColumnInfo(name = "region") val region: String
)
