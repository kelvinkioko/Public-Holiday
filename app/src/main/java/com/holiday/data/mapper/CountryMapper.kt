package com.holiday.data.mapper

import com.holiday.data.local.entity.CountryEntity
import com.holiday.data.remote.dto.CountryDto
import com.holiday.data.remote.dto.CountryInfoDto
import com.holiday.domain.model.CountryModel

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

fun CountryEntity.mapToCountryModel(): CountryModel {
    return CountryModel(
        commonName = commonName,
        officialName = officialName,
        countryCode = countryCode,
        region = region
    )
}
