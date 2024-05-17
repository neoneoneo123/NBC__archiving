package com.example.nbc_standard_week7.data.remote.retrofit

import okhttp3.Interceptor
import okhttp3.Response

internal class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .run {
                    this.addHeader("Token", "B553748/CertImgListServiceV3/getCertImgListServiceV3?ServiceKey=cpaEff%2BfBSNn0sePBS09fjpwKSP2XnJekX3fFbhMcL5FCB7ATlANCCM%2FVWa3k%2BJw5N4NMgFSNhVIcITL7HA5%2Bw%3D%3D&returnType=json")
                }
                .build()
        )
    }
}