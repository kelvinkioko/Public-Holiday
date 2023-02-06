package com.holiday.data.mapper

import com.holiday.data.local.entity.CountryEntity
import com.holiday.data.remote.dto.BordersDto
import com.holiday.data.remote.dto.CountryDto
import com.holiday.data.remote.dto.CountryInfoDto
import com.holiday.domain.model.CountryModel
import org.junit.Assert
import org.junit.Test
import kotlin.random.Random

class CountryMapperTest {

    private val countriesDTO = arrayListOf(
        CountryDto(countryCode = "AD", name = "Andorra"),
        CountryDto(countryCode = "AL", name = "Albania"),
        CountryDto(countryCode = "AR", name = "Argentina")
    )

    private val countriesEntity = arrayListOf(
        CountryEntity(commonName = "Andorra", officialName = "", countryCode = "AD", region = ""),
        CountryEntity(commonName = "Albania", officialName = "", countryCode = "AL", region = ""),
        CountryEntity(commonName = "Argentina", officialName = "", countryCode = "AR", region = "")
    )

    private val countriesModel = arrayListOf(
        CountryModel(commonName = "Andorra", officialName = "", countryCode = "AD", region = ""),
        CountryModel(commonName = "Albania", officialName = "", countryCode = "AL", region = ""),
        CountryModel(commonName = "Argentina", officialName = "", countryCode = "AR", region = "")
    )

    private val bordersDto = mutableListOf(
        BordersDto(
            commonName = "France",
            officialName = "French Republic",
            countryCode = "FR",
            region = "Europe"
        ),
        BordersDto(
            commonName = "Spain",
            officialName = "Kingdom of Spain",
            countryCode = "ES",
            region = "Europe"
        )
    )

    private val countryInfoDto = CountryInfoDto(
        commonName = "Andorra",
        officialName = "Principality of Andorra",
        countryCode = "AD",
        region = "Europe",
        borders = bordersDto
    )

    private val countryEntity = CountryEntity(
        commonName = "Andorra",
        officialName = "Principality of Andorra",
        countryCode = "AD",
        region = "Europe"
    )

    @Test
    fun `test countryDto to Entity`() {
        val randomPosition = Random.nextInt(0, 2)

        val mappedEntity = countriesDTO[randomPosition].mapToCountryEntity()
        Assert.assertEquals(countriesEntity[randomPosition], mappedEntity)
    }

    @Test
    fun `test countryInfoDto to Entity`() {
        val mappedEntity = countryInfoDto.mapToCountryEntity()

        Assert.assertEquals(countryEntity, mappedEntity)
    }

    @Test
    fun `test CountryEntity To Model`() {
        val randomPosition = Random.nextInt(0, 2)

        val mappedModel = countriesEntity[randomPosition].mapToCountryModel()
        Assert.assertEquals(countriesModel[randomPosition], mappedModel)
    }
}
