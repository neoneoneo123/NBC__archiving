package com.example.nbc__imagecollector__typea.service

import com.example.nbc__imagecollector__typea.BuildConfig
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.model.KakaoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Retrofit을 사용하여 API와 통신하는 역할을 하는 object입니다.
 */
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

/**
 * 통신 시 사용하게 되는 key와 query의 형태를 정의한 interface입니다.
 */
interface NetWorkInterface {
    @Headers("Authorization: KakaoAK 8677a42abc5b052fb04aea5a157212bc")
    @GET("v2/search/image")
    suspend fun getImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): KakaoResponse<KakaoDocuments>
}