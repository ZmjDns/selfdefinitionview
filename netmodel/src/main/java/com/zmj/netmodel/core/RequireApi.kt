package com.zmj.netmodel.core

import com.zmj.netmodel.entry.BaseResp
import retrofit2.http.*

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/3/6
 * Description :
 */
interface RequireApi {

    @GET
    fun <T>get(@Url url: String,@QueryMap params: MutableMap<String,Any>): BaseResp<T>

    @FormUrlEncoded
    @POST
    fun <T>post(@Url url: String,@QueryMap params: MutableMap<String,Any>):BaseResp<T>

}