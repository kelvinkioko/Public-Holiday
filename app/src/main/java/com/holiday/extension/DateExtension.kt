package com.holiday.extension

import android.os.Build
import com.holiday.util.DateResource
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Necessary because LocalDateTime is only available from API 26 onwards
 * we'd like to support API 21 onwards
 */

fun String.dateFormatter(): DateResource {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateResource.LocalDateFormat(
            localDate = LocalDate.parse(
                this,
                DateTimeFormatter.ofPattern(DateConstants.YMD_HYPHEN)
            )
        )
    } else {
        DateResource.StringDate(stringDate = this)
    }
}

fun DateResource.dateFormatter(format: String = DateConstants.YMD_HYPHEN): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        (this as DateResource.LocalDateFormat).localDate.format(
            DateTimeFormatter.ofPattern(format)
        )
    else
        (this as DateResource.StringDate).stringDate
}

object DateConstants {
    const val YMD_HYPHEN = "yyyy-MM-dd"
    const val DAY_DMY_HYPHEN = "EEE dd, MMM yyyy"
}
