package com.example.nbc__imagecollector__typea.data

import com.google.gson.annotations.SerializedName

data class Kakao(val response: KakaoResponse)

data class KakaoResponse(
    @SerializedName("documents")
    val kakaoDocuments: KakaoDocuments,
    @SerializedName("meta")
    val kakaoMeta: KakaoMeta,
)

data class KakaoDocuments(
    val collection: String,
    val datetime: String,
    val display_sitename: String,
    val doc_url: String,
    val height: Int,
    val image_url: String,
    val thumbnail_url: String,
    val width: Int,
)

data class KakaoMeta(
    val isend: Boolean,
    val pageableCount: Int,
    val totalCount: Int,
)
