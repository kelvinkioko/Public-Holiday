package com.holiday.util

import android.os.Build
import com.holiday.extension.DateConstants
import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed class DateResource {
    data class StringDate(val stringDate: String) : DateResource()

    data class LocalDateFormat(val localDate: LocalDate) : DateResource()

    fun asDate(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            (this as LocalDateFormat).localDate.format(
                DateTimeFormatter.ofPattern(DateConstants.YMD_HYPHEN)
            )
        else
            (this as StringDate).stringDate
    }
}
