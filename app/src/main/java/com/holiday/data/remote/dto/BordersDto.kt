package com.holiday.data.remote.dto

import com.squareup.moshi.Json

data class BordersDto(
    @field:Json(name = "commonName") val commonName: String,
    @field:Json(name = "officialName") val officialName: String,
    @field:Json(name = "countryCode") val countryCode: String,
    @field:Json(name = "region") val region: String
)
