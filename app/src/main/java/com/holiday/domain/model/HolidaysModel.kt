package com.holiday.domain.model

import com.holiday.util.DateResource

data class HolidaysModel(
    val date: DateResource? = null,
    val localName: String = "",
    val name: String = "",
    val countryCode: String = "",
    val fixed: Boolean,
    val global: Boolean,
    val counties: List<String> = emptyList(),
    val launchYear: Int,
    val types: List<String> = emptyList()
)
