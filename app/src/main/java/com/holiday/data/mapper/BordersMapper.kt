package com.holiday.data.mapper

import com.holiday.data.local.entity.BordersEntity
import com.holiday.data.remote.dto.BordersDto

fun BordersDto.mapToBordersEntity(): BordersEntity {
    return BordersEntity(
        commonName = commonName,
        officialName = officialName,
        countryCode = countryCode,
        region = region
    )
}
