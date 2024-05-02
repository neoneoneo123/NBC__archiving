package com.example.nbc__imagecollector__typea.view.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object UtilityFormat {
    fun formatDate(dateString: String) : String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        val date: Date = inputFormat.parse(dateString) ?: return ""

        return outputFormat.format(date)
    }
}