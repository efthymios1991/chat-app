package com.interview.chatapp.util

import android.content.Context
import com.google.gson.Gson
import java.io.BufferedReader
import java.lang.reflect.Type

object DataUtil {

    private val gson = Gson()

    fun loadJSONFromAssets(context: Context, fileName: String): String {
        val bufferedReader: BufferedReader = context.assets.open(fileName).bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    fun <T> parseJSON(json: String, typeOfT: Type): List<T> {
        return gson.fromJson(json, typeOfT)
    }
}