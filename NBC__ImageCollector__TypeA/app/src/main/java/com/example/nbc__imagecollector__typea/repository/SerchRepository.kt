package com.example.nbc__imagecollector__typea.repository

import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.model.KakaoResponse
import com.example.nbc__imagecollector__typea.service.NetWorkClient
import com.example.nbc__imagecollector__typea.service.NetWorkInterface

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

}