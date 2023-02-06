package com.holiday.extension

import android.content.Context

fun Any.getString(context: Context): String {
    return when (this) {
        is String -> this.toString()
        is Int -> context.getString(this)
        else -> ""
    }
}
