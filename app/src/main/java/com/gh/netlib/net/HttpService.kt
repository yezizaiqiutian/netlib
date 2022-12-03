//package com.gh.netlib.net
//
//import com.gh.netlib.entity.BaseEntity
//import com.gh.netlib.entity.ItemBean
//import io.reactivex.Flowable
//import retrofit2.http.*
//
//
///**
// * @author: gh
// * @description:
// * @date: 5/24/21.
// * @from:
// */
//open interface HttpService {
//
//    //http://www.izaodao.com/Api/AppFiftyToneGraph/videoLink
//    @POST("AppFiftyToneGraph/videoLink")
//    fun getAllVedio(@Body once_no: Boolean): Flowable<BaseEntity<List<ItemBean?>?>?>?
//
//}