package com.example.nbc__imagecollector__typea.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Room에 요청할 쿼리와 함수를 관리합니다.
 */
@Dao
interface KakaoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: KakaoDocuments)

    @Query("SELECT * FROM archive_table")
    fun getAllItems(): Flow<List<KakaoDocuments>>

    @Query("SELECT * FROM archive_table WHERE thumbnail_url = :sname")
    suspend fun getItemByName(sname: String) : List<KakaoDocuments>

    @Delete
    suspend fun deleteItem(item: KakaoDocuments)
}