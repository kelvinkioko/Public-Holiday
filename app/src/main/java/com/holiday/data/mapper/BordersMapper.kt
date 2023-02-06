package com.holiday.data.mapper

import com.holiday.data.local.entity.BordersEntity
import com.holiday.data.remote.dto.BordersDto
import com.holiday.domain.model.BordersModel

class BordersMapper {
    companion object Mapper {
        fun BordersDto.mapToBordersEntity(): BordersEntity {
            return BordersEntity(
                commonName = commonName,
                officialName = officialName,
                countryCode = countryCode,
                region = region
            )
        }

        fun BordersEntity.mapToBordersModel(): BordersModel {
            return BordersModel(
                commonName = commonName,
                officialName = officialName,
                countryCode = countryCode,
                region = region
            )
        }
    }
}
