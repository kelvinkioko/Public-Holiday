package com.holiday.data.mapper

import com.holiday.data.local.entity.HolidaysEntity
import com.holiday.data.remote.dto.HolidaysDto
import com.holiday.domain.model.HolidaysModel

fun HolidaysDto.mapToHolidaysEntity(): HolidaysEntity {
    return HolidaysEntity(
        date = date,
        localName = localName,
        name = name,
        countryCode = countryCode,
        fixed = fixed,
        global = global,
        counties = counties,
        launchYear = launchYear,
        types = types
    )
}

fun HolidaysEntity.mapToHolidaysModel(): HolidaysModel {
    return HolidaysModel(
        date = date,
        localName = localName,
        name = name,
        countryCode = countryCode,
        fixed = fixed,
        global = global,
        counties = counties,
        launchYear = launchYear,
        types = types
    )
}
