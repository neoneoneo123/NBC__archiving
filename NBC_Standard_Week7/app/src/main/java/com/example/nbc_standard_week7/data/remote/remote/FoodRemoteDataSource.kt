package com.example.nbc_standard_week7.data.remote.remote

import com.example.nbc_standard_week7.data.remote.model.B553748
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRemoteDataSource {
//    @GET("B553748/CertImgListServiceV3/getCertImgListServiceV3" +
//            "?ServiceKey=cpaEff%2BfBSNn0sePBS09fjpwKSP2XnJekX3fFbhMcL5FCB7ATlANCCM%2FVWa3k%2BJw5N4NMgFSNhVIcITL7HA5%2Bw%3D%3D" +
//            "&returnType=json")
    suspend fun getFoodItems(
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("prdlstNm") prdlstNm: String,
    ) : B553748
}