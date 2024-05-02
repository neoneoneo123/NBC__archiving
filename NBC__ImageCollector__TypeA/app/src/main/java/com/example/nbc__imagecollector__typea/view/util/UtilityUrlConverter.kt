package com.example.nbc__imagecollector__typea.view.util

import androidx.room.TypeConverter
import java.net.URL

object UtilityUrlConverter {
    @TypeConverter
    fun fromString(value: String?): URL? {
        return value?.let { URL(it) }
    }

    @TypeConverter
    fun fromURL(url: URL?) : String? {
        return url?.toString()
    }
}