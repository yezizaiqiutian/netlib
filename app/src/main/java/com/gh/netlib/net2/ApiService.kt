package com.gh.netlib.net2

import com.gh.net_lib.BaseApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): ApiResponseApp<User?>
//    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): Any

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }
}