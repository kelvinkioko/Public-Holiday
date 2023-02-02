package com.holiday.data.mapper

import com.holiday.data.local.entity.CountryEntity
import com.holiday.data.remote.dto.CountryDto
import com.holiday.data.remote.dto.CountryInfoDto

fun CountryDto.mapToCountryEntity(): CountryEntity {
    return CountryEntity(
        commonName = name,
        countryCode = countryCode
    )
}

fun CountryInfoDto.mapToCountryEntity(): CountryEntity {
    return CountryEntity(
        commonName = commonName,
        officialName = officialName,
        countryCode = countryCode,
        region = region
    )
}
