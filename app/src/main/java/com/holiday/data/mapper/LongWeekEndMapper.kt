package com.holiday.data.mapper

import com.holiday.data.local.entity.LongWeekendEntity
import com.holiday.data.remote.dto.LongWeekendDto

fun LongWeekendDto.mapToLongWeekEndEntity(): LongWeekendEntity {
    return LongWeekendEntity(
        startDate = startDate,
        endDate = endDate,
        dayCount = dayCount,
        needBridgeDay = needBridgeDay
    )
}
