package com.example.data.util

import android.content.Context
import com.google.gson.Gson

object JsonLoader {
    fun <T> loadJson(context: Context, fileName: String, clazz: Class<Array<T>>): List<T> {
        val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
        return Gson().fromJson(json, clazz).toList()
    }
}