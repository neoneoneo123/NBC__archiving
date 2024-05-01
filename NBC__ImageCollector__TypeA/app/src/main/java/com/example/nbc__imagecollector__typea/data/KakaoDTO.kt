package com.example.nbc__imagecollector__typea.data

import com.google.gson.annotations.SerializedName

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
    val displaySitename: String,
    val docUrl: String,
    val height: Int,
    val imageUrl: String,
    val thumbnailUrl: String,
    val width: Int,
)

data class KakaoMeta(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int,
)
