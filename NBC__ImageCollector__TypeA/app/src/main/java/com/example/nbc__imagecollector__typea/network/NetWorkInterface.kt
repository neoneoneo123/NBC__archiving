package com.example.nbc__imagecollector__typea.network

import com.example.nbc__imagecollector__typea.data.Kakao
import com.example.nbc__imagecollector__typea.data.KakaoDocuments
import com.example.nbc__imagecollector__typea.data.KakaoResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NetWorkInterface {
    suspend fun getKakao(@QueryMap param: HashMap<String, String>) : Kakao

    @Headers("Authorization: KakaoAK 8677a42abc5b052fb04aea5a157212bc")
    @GET("v2/search/image")
    suspend fun getImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): KakaoResponse<KakaoDocuments>
}