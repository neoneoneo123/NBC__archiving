package com.example.nbc_standard_week7.network

import com.example.nbc_standard_week7.BuildConfig
import com.example.nbc_standard_week7.data.DTO.B553748
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object B553748ApiServiceClient {
    private const val OPEN_BASE_URL = "https://apis.data.go.kr/"

    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val b553748Retrofit = Retrofit.Builder()
        .baseUrl(OPEN_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()

    val b553748NetWork: B553748ApiService = b553748Retrofit.create(B553748ApiService::class.java)
}

interface B553748ApiService {
    @GET("B553748/CertImgListServiceV3/getCertImgListServiceV3" +
            "?ServiceKey=cpaEff%2BfBSNn0sePBS09fjpwKSP2XnJekX3fFbhMcL5FCB7ATlANCCM%2FVWa3k%2BJw5N4NMgFSNhVIcITL7HA5%2Bw%3D%3D" +
            "&returnType=json")
    suspend fun getB553748(
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("prdlstNm") prdlstNm: String,
    ) : B553748
}