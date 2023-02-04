package com.holiday.data.remote.dto

import com.squareup.moshi.Json

data class HolidaysDto(
    @field:Json(name = "date") val date: String,
    @field:Json(name = "localName") val localName: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "countryCode") val countryCode: String,
    @field:Json(name = "fixed") val fixed: Boolean,
    @field:Json(name = "global") val global: Boolean,
    @field:Json(name = "counties") val counties: List<String>,
    @field:Json(name = "launchYear") val launchYear: Int? = 0,
    @field:Json(name = "types") val types: List<String>
)
