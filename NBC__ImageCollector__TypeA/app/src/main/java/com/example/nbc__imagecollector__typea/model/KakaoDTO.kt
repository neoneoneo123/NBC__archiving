package com.example.nbc__imagecollector__typea.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.net.URL

data class Kakao(val response: KakaoResponse<Any?>)

data class KakaoResponse<T>(
    @SerializedName("documents")
    val kakaoDocuments: MutableList<T>,
    @SerializedName("meta")
    val kakaoMeta: KakaoMeta,
)

@Entity(tableName = "archive_table")
data class KakaoDocuments(
    val collection: String,
    val datetime: String,
    val display_sitename: String?,
    val doc_url: URL?,
    val height: Int,
    val image_url: URL?,
    @PrimaryKey @ColumnInfo(name = "thumbnail_url")
    val thumbnail_url: URL?,
    val width: Int,
)

data class KakaoMeta(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int,
)
