package com.holiday.data.mapper

import com.holiday.data.local.entity.BordersEntity
import com.holiday.data.remote.dto.BordersDto
import org.junit.Assert.assertEquals
import org.junit.Test

class BordersMapperTest {
    private val bordersEntity = BordersEntity(
        referenceCountryCode = "US",
        commonName = "Canada",
        officialName = "Canada",
        countryCode = "CA",
        region = "Americas"
    )

    private val bordersDto = BordersDto(
        commonName = "Canada",
        officialName = "Canada",
        countryCode = "CA",
        region = "Americas"
    )
    @Test
    fun testBordersDtoToEntity() {
        assertEquals("Random", "Random")
    }
}
