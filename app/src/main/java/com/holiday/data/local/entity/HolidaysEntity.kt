package com.holiday.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "holidays", primaryKeys = ["countryCode", "date"])
data class HolidaysEntity(
    @ColumnInfo(name = "year") val year: String = "",
    @ColumnInfo(name = "date") val date: String = "",
    @ColumnInfo(name = "localName") val localName: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "countryCode") val countryCode: String = "",
    @ColumnInfo(name = "fixed") val fixed: Boolean,
    @ColumnInfo(name = "global") val global: Boolean,
    @ColumnInfo(name = "counties") val counties: List<String>? = null,
    @ColumnInfo(name = "launchYear") val launchYear: Int,
    @ColumnInfo(name = "types") val types: List<String>? = null,
    @ColumnInfo(name = "worldWide") val worldWide: Boolean = false
)
