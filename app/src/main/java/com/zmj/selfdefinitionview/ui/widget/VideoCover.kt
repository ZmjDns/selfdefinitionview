package com.zmj.selfdefinitionview.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.ImageView
import java.util.jar.Attributes

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/2
 * Description :
 */
class VideoCover: ImageView {

    constructor(context:Context):super(context)
    constructor(context: Context,attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context,attrs: AttributeSet?,selfDef:Int):super(context, attrs,selfDef)

    private val paint = Paint()
    private val path = Path()

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 2f

        path.moveTo(100f,20f)
        path.rLineTo(80f,50f)
        path.rLineTo(-80f,50f)

        canvas?.save()
        //canvas?.drawCircle(400f,400f,100f,paint)
        canvas?.drawPath(path,paint)
        canvas?.restore()
    }

}