package com.example.nbc__imagecollector__typea.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Kakao(val response: KakaoResponse<Any?>)

data class KakaoResponse<T>(
    @SerializedName("documents")
    val kakaoDocuments: MutableList<T>,
    @SerializedName("meta")
    val kakaoMeta: KakaoMeta,
)

@Entity(tableName = "archive_table")
data class KakaoDocuments(
    val collection: String?,
    val datetime: String?,
    val display_sitename: String?,
    val doc_url: String?,
    val height: Int?,
    val image_url: String?,
    @PrimaryKey @ColumnInfo(name = "thumbnail_url")
    val thumbnail_url: String,
    val width: Int,
)

data class KakaoMeta(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int,
)
