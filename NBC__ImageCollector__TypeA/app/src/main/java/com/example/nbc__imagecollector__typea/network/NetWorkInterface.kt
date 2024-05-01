package com.example.nbc__imagecollector__typea.network

import com.example.nbc__imagecollector__typea.data.Kakao
import retrofit2.http.QueryMap

interface NetWorkInterface {
    suspend fun getKakao(@QueryMap param: HashMap<String, String>) : Kakao
}