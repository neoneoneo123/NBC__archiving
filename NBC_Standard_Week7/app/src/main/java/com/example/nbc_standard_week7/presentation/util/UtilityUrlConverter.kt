package com.example.nbc_standard_week7.presentation.util

import java.net.URL

object UtilityUrlConverter {

    /**
     * string을 URL로 변환하는 함수입니다.
     */
    fun fromString(value: String?): URL? {
        return value?.let { URL(it) }
    }
}