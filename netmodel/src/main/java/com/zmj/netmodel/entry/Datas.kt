package com.zmj.netmodel.entry

import java.lang.StringBuilder

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/3/6
 * Description :
 */

data class BaseResp<T>(var code: Int,var msg: String,var data: T?)