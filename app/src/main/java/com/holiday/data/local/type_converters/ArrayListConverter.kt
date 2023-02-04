package com.holiday.data.local.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ArrayListConverter {
    @TypeConverter
    fun fromStringArrayList(value: List<String>? = null): String {
        value?.let {
            return Gson().toJson(value)
        } ?: run {
            return ""
        }
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<String> {
        return try {
            val listType: Type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(value, listType)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}
