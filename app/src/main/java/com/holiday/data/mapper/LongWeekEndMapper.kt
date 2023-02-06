package com.holiday.data.mapper

import com.holiday.data.local.entity.LongWeekendEntity
import com.holiday.data.remote.dto.LongWeekendDto
import com.holiday.domain.model.LongWeekendModel
import com.holiday.extension.dateFormatter

class LongWeekEndMapper {
    companion object Mapper {
        fun LongWeekendDto.mapToLongWeekEndEntity(
            year: Int = 0,
            countryCode: String
        ): LongWeekendEntity {
            return LongWeekendEntity(
                startDate = startDate,
                endDate = endDate,
                dayCount = dayCount,
                needBridgeDay = needBridgeDay,
                year = year,
                countryCode = countryCode
            )
        }

        fun LongWeekendEntity.mapToLongWeekEndModel(): LongWeekendModel {
            return LongWeekendModel(
                startDate = startDate.dateFormatter(),
                endDate = endDate.dateFormatter(),
                dayCount = dayCount,
                needBridgeDay = needBridgeDay
            )
        }
    }
}
