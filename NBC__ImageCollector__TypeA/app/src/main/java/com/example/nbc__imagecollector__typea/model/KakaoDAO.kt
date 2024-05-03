package com.example.nbc__imagecollector__typea.model

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

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