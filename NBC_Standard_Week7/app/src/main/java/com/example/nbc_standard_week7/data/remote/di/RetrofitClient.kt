@file:Suppress("ANNOTATION_TARGETS_NON_EXISTENT_ACCESSOR")

package com.example.nbc_standard_week7.data.remote.di

import com.example.nbc_standard_week7.BuildConfig
import com.example.nbc_standard_week7.data.remote.remote.FoodRemoteDataSource
import com.example.nbc_standard_week7.data.remote.retrofit.HttpRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module //retrofit, room 사용할 때 - 여기서 binding 정보를 사용할 때 달아줌
@InstallIn(SingletonComponent::class) //오브젝트 클래스가 힐트에 사용될 때 이 컴포넌트를 사용함을 명시
object RetrofitClient {
    private const val OPEN_BASE_URL = "https://apis.data.go.kr/"

    @Provides //외부 라이브러리 쓸 때 달아줌
    fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient
            .Builder()
//            .addNetworkInterceptor(HttpRequestInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    @Provides
    fun b553748Retrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(OPEN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    @Provides
    fun provideFoodRemoteDataSource(retrofit: Retrofit) : FoodRemoteDataSource {
        return retrofit.create(FoodRemoteDataSource::class.java)
    }
}