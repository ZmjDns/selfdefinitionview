package com.zmj.selfdefinitionview.practice5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/18
 * Description :
 */
class ForegroundView: ImageView {
    constructor(context: Context):super(context)
    constructor(context: Context, attr: AttributeSet?):super(context,attr)
    constructor(context: Context, attr: AttributeSet?, sty:Int):super(context,attr,sty)

    val paint = Paint()

    override fun onDrawForeground(canvas: Canvas?) {
        super.onDrawForeground(canvas)

        paint.style = Paint.Style.FILL
        paint.color = Color.RED

        canvas?.save()
        canvas?.drawRect(0f,10f,80f,40f,paint)
        canvas?.restore()

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f
        paint.color = Color.WHITE

        canvas?.save()
        canvas?.drawText("New",10f,38f,paint)
        canvas?.restore()


    }



}