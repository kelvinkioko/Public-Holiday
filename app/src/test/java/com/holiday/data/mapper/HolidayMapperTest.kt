package com.holiday.data.mapper

import com.holiday.data.local.entity.HolidaysEntity
import com.holiday.data.remote.dto.HolidaysDto
import com.holiday.domain.model.HolidaysModel
import com.holiday.extension.dateFormatter
import org.junit.Assert
import org.junit.Test

class HolidayMapperTest {
    private val holidaysDto = HolidaysDto(
        date = "2015-01-01",
        localName = "Viti i Ri",
        name = "New Year's Day",
        countryCode = "AL",
        fixed = true,
        global = true,
        counties = arrayListOf(),
        launchYear = 0,
        types = arrayListOf("Public")
    )

    private val holidaysEntity = HolidaysEntity(
        holidayYear = 2015,
        date = "2015-01-01",
        localName = "Viti i Ri",
        name = "New Year's Day",
        countryCode = "AL",
        fixed = true,
        global = true,
        counties = arrayListOf(),
        launchYear = 0,
        types = arrayListOf("Public"),
        worldWide = false
    )

    private val holidaysModel = HolidaysModel(
        date = "2015-01-01".dateFormatter(),
        localName = "Viti i Ri",
        name = "New Year's Day",
        countryCode = "AL",
        fixed = true,
        global = true,
        counties = arrayListOf(),
        launchYear = 0,
        types = arrayListOf("Public")
    )

    @Test
    fun `test HolidayDto To Entity`() {
        val mappedEntity = holidaysDto.mapToHolidaysEntity(
            holidayYear = 2015,
            worldWide = false
        )
        Assert.assertEquals(holidaysEntity, mappedEntity)
    }

    @Test
    fun `test HolidayEntity To Model`() {
        val mappedModel = holidaysEntity.mapToHolidaysModel()
        Assert.assertEquals(holidaysModel, mappedModel)
    }
}
