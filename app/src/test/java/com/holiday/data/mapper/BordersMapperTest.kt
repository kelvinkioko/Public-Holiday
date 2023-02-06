package com.holiday.data.mapper

import com.holiday.data.local.entity.BordersEntity
import com.holiday.data.remote.dto.BordersDto
import com.holiday.domain.model.BordersModel
import org.junit.Assert.assertEquals
import org.junit.Test

class BordersMapperTest {
    private val bordersDto = BordersDto(
        commonName = "Canada",
        officialName = "Canada",
        countryCode = "CA",
        region = "Americas"
    )

    private val bordersEntity = BordersEntity(
        referenceCountryCode = "US",
        commonName = "Canada",
        officialName = "Canada",
        countryCode = "CA",
        region = "Americas"
    )

    private val bordersModel = BordersModel(
        commonName = "Canada",
        officialName = "Canada",
        countryCode = "CA",
        region = "Americas"
    )

    private val countryCode = "US"

    @Test
    fun `test BordersDto To Entity`() {
        val mappedEntity = bordersDto.mapToBordersEntity().also {
            it.referenceCountryCode = countryCode
        }
        assertEquals(bordersEntity, mappedEntity)
    }

    @Test
    fun `test BordersEntity To Model`() {
        val mappedModel = bordersEntity.mapToBordersModel()
        assertEquals(bordersModel, mappedModel)
    }
}
