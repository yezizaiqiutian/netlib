package com.gh.netlib.net2

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): ApiResponseApp<User?>

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }
}