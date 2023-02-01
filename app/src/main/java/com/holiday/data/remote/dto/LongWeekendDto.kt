package com.holiday.data.remote.dto

import com.squareup.moshi.Json

data class LongWeekendDto(
    @field:Json(name = "startDate") val startDate: String,
    @field:Json(name = "endDate") val endDate: String,
    @field:Json(name = "dayCount") val dayCount: String,
    @field:Json(name = "needBridgeDay") val needBridgeDay: String
)
