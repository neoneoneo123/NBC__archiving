package com.example.nbc__imagecollector__typea.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import java.net.URL

data class Kakao(val response: KakaoResponse<Any?>)

data class KakaoResponse<T>(
    @SerializedName("documents")
    val kakaoDocuments: MutableList<T>,
    @SerializedName("meta")
    val kakaoMeta: KakaoMeta,
)

data class KakaoDocuments(
    val collection: String,
    val datetime: String,
    val display_sitename: String?,
    val doc_url: URL?,
    val height: Int,
    val image_url: URL?,
    val thumbnail_url: URL?,
    val width: Int,
)

data class KakaoMeta(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int,
)
