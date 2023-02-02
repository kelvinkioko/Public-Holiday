package com.holiday.domain.model

data class CountryModel(
    var commonName: String = "",
    var officialName: String = "",
    var countryCode: String = "",
    var region: String = "",
    var borders: List<BordersModel> = emptyList()
)
