package com.zmj.selfdefinitionview.practice5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/3/9
 * Description :
 */
class SelfEditTextView: EditText {

    constructor(context:Context):super(context)
    constructor(context: Context,attrs: AttributeSet):super(context, attrs)
    constructor(context: Context,attrs: AttributeSet,selfDef:Int):super(context,attrs,selfDef)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)



    }


    override fun onDraw(canvas: Canvas?) {

        paint.color = Color.RED
        paint.textSize = 40f
        canvas?.drawText("dispatchDraw",20f,40f,paint)

        super.onDraw(canvas)
    }




}