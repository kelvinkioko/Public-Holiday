package com.holiday.data.mapper

import com.holiday.util.longWeekendDto
import com.holiday.util.longWeekendEntity
import com.holiday.util.longWeekendModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.random.Random

@RunWith(JUnit4::class)
class LongWeekEndMapperTest {

    private val australiaCountryCode = "AU"

    @Test
    fun `test LongWeekendDto To Entity`() {
        val randomPosition = Random.nextInt(0, longWeekendDto.size - 1)

        val mappedEntity = longWeekendDto[randomPosition].mapToLongWeekEndEntity(
            year = 2015,
            countryCode = australiaCountryCode
        )
        Assert.assertEquals(longWeekendEntity[randomPosition], mappedEntity)
    }

    @Test
    fun `test LongWeekendEntity To Model`() {
        val randomPosition = Random.nextInt(0, longWeekendEntity.size - 1)

        val mappedEntity = longWeekendEntity[randomPosition].mapToLongWeekEndModel()
        Assert.assertEquals(longWeekendModel[randomPosition], mappedEntity)
    }
}
