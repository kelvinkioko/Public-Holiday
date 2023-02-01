package com.holiday.data.remote.dto

import com.squareup.moshi.Json

data class CountryDto(
    @field:Json(name = "countryCode") val countryCode: String,
    @field:Json(name = "name") val name: String
)
