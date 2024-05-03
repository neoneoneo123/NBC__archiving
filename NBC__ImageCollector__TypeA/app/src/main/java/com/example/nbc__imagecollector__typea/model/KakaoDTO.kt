package com.example.nbc__imagecollector__typea.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * API를 통해 받아오는 데이터 항목들을 정의한 data class입니다.
 */


data class KakaoResponse<T>(
    @SerializedName("documents")
    val kakaoDocuments: MutableList<T>,
    @SerializedName("meta")
    val kakaoMeta: KakaoMeta,
)

/**
 * 받아오는 데이터 중 핵심이 되는 항목들을 정의한 data class입니다.
 * null이 아니면서 고유값을 가지게 되는 thumbnail_url을 PrimaryKey로 사용합니다.
 */
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
