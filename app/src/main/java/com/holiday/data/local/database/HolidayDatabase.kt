package com.holiday.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.holiday.data.local.dao.BordersDao
import com.holiday.data.local.dao.CountryDao
import com.holiday.data.local.dao.HolidaysDao
import com.holiday.data.local.dao.LongWeekendDao
import com.holiday.data.local.entity.BordersEntity
import com.holiday.data.local.entity.CountryEntity
import com.holiday.data.local.entity.HolidaysEntity
import com.holiday.data.local.entity.LongWeekendEntity
import com.holiday.data.local.type_converters.ArrayListConverter

@Database(
    entities = [
        CountryEntity::class,
        BordersEntity::class,
        HolidaysEntity::class,
        LongWeekendEntity::class
    ],
    version = 1
)
@TypeConverters(ArrayListConverter::class)
abstract class HolidayDatabase : RoomDatabase() {
    abstract fun bordersDao(): BordersDao
    abstract fun countryDao(): CountryDao
    abstract fun holidayDao(): HolidaysDao
    abstract fun longWeekendDao(): LongWeekendDao
}
