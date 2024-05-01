package com.example.nbc__imagecollector__typea.service.repository

import com.example.nbc__imagecollector__typea.BuildConfig
import com.example.nbc__imagecollector__typea.service.model.Kakao
import com.example.nbc__imagecollector__typea.service.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.service.model.KakaoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

object NetWorkClient {

    private const val KAKAO_BASE_URL = "https://dapi.kakao.com/"

    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val kakaoRetrofit = Retrofit.Builder()
        .baseUrl(KAKAO_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()

    val kakaoNetWork: NetWorkInterface = kakaoRetrofit.create(NetWorkInterface::class.java)
}

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