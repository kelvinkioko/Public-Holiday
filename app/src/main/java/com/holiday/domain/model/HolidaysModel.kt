package com.holiday.domain.model

data class HolidaysModel(
    val date: String = "",
    val localName: String = "",
    val name: String = "",
    val countryCode: String = "",
    val fixed: Boolean,
    val global: Boolean,
    val counties: List<String> = emptyList(),
    val launchYear: Int,
    val types: List<String> = emptyList()
)
