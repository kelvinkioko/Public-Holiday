package com.holiday.domain.model

import com.holiday.util.DateResource

data class LongWeekendModel(
    val startDate: DateResource? = null,
    val endDate: DateResource? = null,
    val dayCount: String,
    val needBridgeDay: Boolean
)
