package com.holiday.data.mapper

import com.holiday.data.local.entity.HolidaysEntity
import com.holiday.data.remote.dto.HolidaysDto
import com.holiday.domain.model.HolidaysModel
import com.holiday.extension.dateFormatter

class HolidayMapper {
    companion object Mapper {
        fun HolidaysDto.mapToHolidaysEntity(holidayYear: Int, worldWide: Boolean): HolidaysEntity {
            return HolidaysEntity(
                holidayYear = holidayYear,
                date = date,
                localName = localName,
                name = name,
                countryCode = countryCode,
                fixed = fixed,
                global = global,
                counties = counties,
                launchYear = launchYear ?: 0,
                types = types,
                worldWide = worldWide
            )
        }

        fun HolidaysEntity.mapToHolidaysModel(): HolidaysModel {
            return HolidaysModel(
                date = date.dateFormatter(),
                localName = localName,
                name = name,
                countryCode = countryCode,
                fixed = fixed,
                global = global,
                counties = counties ?: emptyList(),
                launchYear = launchYear,
                types = types ?: emptyList()
            )
        }
    }
}
