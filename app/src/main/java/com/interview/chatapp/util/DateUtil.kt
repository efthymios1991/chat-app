package com.interview.chatapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {

    fun formatDate(date: Date, pattern: String = "yyyy-MM-dd'T'HH:mm:ss", locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(pattern, locale)
        return formatter.format(date)
    }

    fun stringToDate(dateString: String, pattern: String = "yyyy-MM-dd'T'HH:mm:ss", locale: Locale = Locale.getDefault()): Date {
        val dateFormat = SimpleDateFormat(pattern, locale)
        return dateFormat.parse(dateString)
    }
}