package com.gh.netlib.net2

import com.gh.net_lib.BaseApiResponse
import com.gh.net_lib.BaseRepository

class WxArticleRepository : BaseRepository() {

    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun login(username: String, password: String): BaseApiResponse<User?> {
        return executeHttp {
            mService.login(username, password)
        }
    }

}