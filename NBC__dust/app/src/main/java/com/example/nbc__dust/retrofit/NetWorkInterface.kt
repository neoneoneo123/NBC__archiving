package com.example.nbc__dust.retrofit

import com.example.nbc__dust.data.Dust
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetWorkInterface {
    @GET("getCtprvnRltmMesureDnsty") //시도별 실시간 측정정보 조회 주소

    /**
     * 요청 값을 해쉬맵에 넣어서 데이터를 요청하는 함수
     * Dust 타입으로 반환 받음(GJon 이용)
     */
    suspend fun getDust(@QueryMap param: HashMap<String, String>): Dust
}