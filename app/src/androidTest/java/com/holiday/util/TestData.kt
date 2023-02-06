package com.holiday.util

import com.holiday.data.local.entity.HolidaysEntity
import com.holiday.domain.model.CountryModel
import com.holiday.domain.model.HolidaysModel
import com.holiday.extension.dateFormatter

val countries = mutableListOf(
    CountryModel(commonName = "Albania", officialName = "", countryCode = "AL", region = "", borders = arrayListOf()),
    CountryModel(commonName = "Andorra", officialName = "", countryCode = "AD", region = "", borders = arrayListOf()),
    CountryModel(commonName = "Argentina", officialName = "", countryCode = "AR", region = "", borders = arrayListOf()),
    CountryModel(commonName = "Australia", officialName = "", countryCode = "AU", region = "", borders = arrayListOf()),
    CountryModel(commonName = "Austria", officialName = "", countryCode = "AT", region = "", borders = arrayListOf()),
    CountryModel(commonName = "Bahamas", officialName = "", countryCode = "BS", region = "", borders = arrayListOf())
)

val country = CountryModel(
    commonName = "Albania",
    officialName = "",
    countryCode = "AL",
    region = "",
    borders = arrayListOf()
)

val worldWideHolidays = arrayListOf(
    HolidaysEntity(
        holidayYear = 0,
        date = "2023-02-05",
        localName = "Feast of Saint Agatha",
        name = "Feast of Saint Agatha",
        countryCode = "SM",
        fixed = true,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public"),
        worldWide = true
    ),
    HolidaysEntity(
        holidayYear = 0,
        date = "2023-02-06",
        localName = "Lá Fhéile Bríde",
        name = "Saint Brigid's Day",
        countryCode = "IE",
        fixed = false,
        global = true,
        counties = emptyList(),
        launchYear = 2023,
        types = arrayListOf("Public"),
        worldWide = true
    ),
    HolidaysEntity(
        holidayYear = 0,
        date = "2023-02-06",
        localName = "Día de la Constitución",
        name = "Constitution Day",
        countryCode = "MX",
        fixed = false,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public"),
        worldWide = true
    ),
    HolidaysEntity(
        holidayYear = 0,
        date = "2023-02-06",
        localName = "Waitangi Day",
        name = "Waitangi Day",
        countryCode = "NZ",
        fixed = false,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public"),
        worldWide = true
    )
)

val worldWideHolidaysModel = arrayListOf(
    HolidaysModel(
        date = "2023-02-05".dateFormatter(),
        localName = "Feast of Saint Agatha",
        name = "Feast of Saint Agatha",
        countryCode = "SM",
        fixed = true,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public")
    ),
    HolidaysModel(
        date = "2023-02-06".dateFormatter(),
        localName = "Lá Fhéile Bríde",
        name = "Saint Brigid's Day",
        countryCode = "IE",
        fixed = false,
        global = true,
        counties = emptyList(),
        launchYear = 2023,
        types = arrayListOf("Public")
    ),
    HolidaysModel(
        date = "2023-02-06".dateFormatter(),
        localName = "Día de la Constitución",
        name = "Constitution Day",
        countryCode = "MX",
        fixed = false,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public")
    ),
    HolidaysModel(
        date = "2023-02-06".dateFormatter(),
        localName = "Waitangi Day",
        name = "Waitangi Day",
        countryCode = "NZ",
        fixed = false,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public")
    )
)

val countryWideHolidays = arrayListOf(
    HolidaysEntity(
        holidayYear = 2023,
        date = "2023-02-07",
        localName = "Independence Day",
        name = "Independence Day",
        countryCode = "GD",
        fixed = true,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public"),
        worldWide = false
    ),
    HolidaysEntity(
        holidayYear = 2023,
        date = "2023-02-08",
        localName = "Prešernov dan",
        name = "Prešeren Day",
        countryCode = "SI",
        fixed = true, global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public"),
        worldWide = false
    ),
    HolidaysEntity(
        holidayYear = 2023,
        date = "2023-02-10",
        localName = "In-Nawfraġju ta’ San Pawl",
        name = "Feast of St. Paul's Shipwreck",
        countryCode = "MT",
        fixed = true,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public"),
        worldWide = false
    ),
    HolidaysEntity(
        holidayYear = 2023,
        date = "2023-02-11",
        localName = "建国記念の日",
        name = "Foundation Day",
        countryCode = "JP",
        fixed = false,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public"),
        worldWide = false
    ),
    HolidaysEntity(
        holidayYear = 2023,
        date = "2023-02-11",
        localName = "Anniversario della istituzione dello Stato della Città del Vaticano",
        name = "Anniversary of the foundation of Vatican City",
        countryCode = "VA",
        fixed = true,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public"),
        worldWide = false
    )
)

val countryWideHolidaysModel = arrayListOf(
    HolidaysModel(
        date = "2023-02-07".dateFormatter(),
        localName = "Independence Day",
        name = "Independence Day",
        countryCode = "GD",
        fixed = true,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public")
    ),
    HolidaysModel(
        date = "2023-02-08".dateFormatter(),
        localName = "Prešernov dan",
        name = "Prešeren Day",
        countryCode = "SI",
        fixed = true, global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public")
    ),
    HolidaysModel(
        date = "2023-02-10".dateFormatter(),
        localName = "In-Nawfraġju ta’ San Pawl",
        name = "Feast of St. Paul's Shipwreck",
        countryCode = "MT",
        fixed = true,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public")
    ),
    HolidaysModel(
        date = "2023-02-11".dateFormatter(),
        localName = "建国記念の日",
        name = "Foundation Day",
        countryCode = "JP",
        fixed = false,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public")
    ),
    HolidaysModel(
        date = "2023-02-11".dateFormatter(),
        localName = "Anniversario della istituzione dello Stato della Città del Vaticano",
        name = "Anniversary of the foundation of Vatican City",
        countryCode = "VA",
        fixed = true,
        global = true,
        counties = emptyList(),
        launchYear = 0,
        types = arrayListOf("Public")
    )
)
