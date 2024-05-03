package com.example.nbc__imagecollector__typea.repository

import android.content.Context
import com.example.nbc__imagecollector__typea.model.KakaoDatabase
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.model.KakaoResponse
import com.example.nbc__imagecollector__typea.service.NetWorkClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

interface SearchRepository {
    suspend fun search(query: String, sort: String, page: Int, size: Int) : KakaoResponse<KakaoDocuments>
    fun insert(item: KakaoDocuments, context: Context)
    fun delete(item: KakaoDocuments, context: Context)
    fun check(thumbnail_url: String, context: Context) : Boolean
    fun searchRoom(context: Context) : Flow<List<KakaoDocuments>>
}

class SearchRepositoryImpl : SearchRepository {
    override suspend fun search(query: String, sort: String, page: Int, size: Int): KakaoResponse<KakaoDocuments> {
        return NetWorkClient.kakaoNetWork.getImage(query, sort, page, size)
    }

    override fun insert(item: KakaoDocuments, context: Context) {
        val kakaoDao = KakaoDatabase.getDatabase(context).getKakaoDao()

        CoroutineScope(Dispatchers.IO).launch {
            kakaoDao.insertItem(item)
        }
    }

    override fun delete(item: KakaoDocuments, context: Context) {
        val kakaoDao = KakaoDatabase.getDatabase(context).getKakaoDao()

        CoroutineScope(Dispatchers.IO).launch {
            kakaoDao.deleteItem(item)
        }
    }

    override fun check(thumbnail_url: String, context: Context) : Boolean {
        val kakaoDao = KakaoDatabase.getDatabase(context).getKakaoDao()

        val check = CoroutineScope(Dispatchers.IO).async {
            val foundItem = kakaoDao.getItemByName(thumbnail_url)
            foundItem.isNotEmpty()
        }

        return runBlocking {
            check.await()
        }
    }

    override fun searchRoom(context: Context): Flow<List<KakaoDocuments>> {
        val kakaoDao = KakaoDatabase.getDatabase(context).getKakaoDao()

        return kakaoDao.getAllItems()
    }
}