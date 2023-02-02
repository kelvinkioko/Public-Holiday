package com.holiday.data.local.type_converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@ProvidedTypeConverter
class ArrayListConverter {
    @TypeConverter
    fun fromStringArrayList(value: ArrayList<String>): String = Gson().toJson(value)

    @TypeConverter
    fun toStringArrayList(value: String): ArrayList<String> {
        return try {
            val listType: Type = object : TypeToken<ArrayList<String>>() {}.type
            Gson().fromJson(value, listType)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}
