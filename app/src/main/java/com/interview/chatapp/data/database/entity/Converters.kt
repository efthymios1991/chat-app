package com.interview.chatapp.data.database.entity

import androidx.room.TypeConverter
import com.interview.chatapp.util.DateUtil
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return value?.let { DateUtil.stringToDate(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): String? {
        return date?.let { DateUtil.formatDate(it) }
    }
}