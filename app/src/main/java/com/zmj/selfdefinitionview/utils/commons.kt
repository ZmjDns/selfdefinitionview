package com.zmj.selfdefinitionview.utils

import android.content.res.Resources

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/16
 * Description :
 */
/**
 * dp转换成像素
 */
fun dpToPx(dp:Float):Float{
    val displayMetrics = Resources.getSystem().displayMetrics

    return dp * displayMetrics.density
}