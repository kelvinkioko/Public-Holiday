package com.holiday.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "borders")
data class BordersEntity(
    @ColumnInfo(name = "referenceCountryCode") var referenceCountryCode: String = "",
    @ColumnInfo(name = "commonName") var commonName: String = "",
    @ColumnInfo(name = "officialName") var officialName: String = "",
    @ColumnInfo(name = "countryCode") var countryCode: String = "",
    @ColumnInfo(name = "region") var region: String = ""
)
