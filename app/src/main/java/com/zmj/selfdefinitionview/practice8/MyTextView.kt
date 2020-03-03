package com.zmj.selfdefinitionview.practice8

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.zmj.selfdefinitionview.R

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/3/3
 * Description :https://www.jianshu.com/p/705a6cb6bfee
 */
class MyTextView: View {

    constructor(context: Context,attrs: AttributeSet):super(context){
        val typeArray: TypedArray = context.obtainStyledAttributes(attrs,R.styleable.MyTextView)
        val text: String? = typeArray.getString(R.styleable.MyTextView_text)
        val textAttr: Int = typeArray.getInteger(R.styleable.MyTextView_testAttr,-1)
        val textColor: Int = typeArray.getInteger(R.styleable.MyTextView_textColor,-1)
        Log.i("MyTextView","text=$text    /textAttr=$textAttr      /textColor=$textColor")
        typeArray.recycle()
    }


}