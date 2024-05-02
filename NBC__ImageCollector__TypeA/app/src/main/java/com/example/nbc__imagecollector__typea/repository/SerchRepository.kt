package com.example.nbc__imagecollector__typea.repository

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.nbc__imagecollector__typea.model.KakaoDatabase
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.model.KakaoResponse
import com.example.nbc__imagecollector__typea.service.NetWorkClient
import com.example.nbc__imagecollector__typea.service.NetWorkInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 쿼리에 들어갈 데이터를 view로부터 받아와야함
 * API에 뭐리를 날려서 데이터를 받아와야함
 *
 */

interface SearchRepository {
    suspend fun search(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ) : KakaoResponse<KakaoDocuments>

    fun insert(item: KakaoDocuments, context: Context)
}

class SearchRepositoryImpl(
) : SearchRepository {
    override suspend fun search(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): KakaoResponse<KakaoDocuments> {
        return NetWorkClient.kakaoNetWork.getImage(query, sort, page, size)
    }

    override fun insert(item: KakaoDocuments, context: Context) {

        val kakaoDao = KakaoDatabase.getDatabase(context).getKakaoDao()

        CoroutineScope(Dispatchers.IO).launch {
            kakaoDao.insertItem(item)
        }
    }
}