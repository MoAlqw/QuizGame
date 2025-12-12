package com.example.data.database.converters

import androidx.room.TypeConverter

class ListConverter {
    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString(";;")

    @TypeConverter
    fun toList(data: String): List<String> = data.split(";;")
}