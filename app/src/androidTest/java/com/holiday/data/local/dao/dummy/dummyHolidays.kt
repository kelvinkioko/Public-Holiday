package com.holiday.data.local.dao.dummy

import com.holiday.data.local.entity.HolidaysEntity

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
