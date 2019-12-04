package com.zmj.selfdefinitionview

import android.app.Application



/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/3
 * Description :
 */
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        //rddks.init("907241844", MainActivity::class.java)
    }
}