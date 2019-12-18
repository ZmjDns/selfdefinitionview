package com.zmj.selfdefinitionview.practice5

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zmj.selfdefinitionview.R
import java.util.jar.Attributes

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/17
 * Description :
 */
class SupportLinearLayout: LinearLayout {

    constructor(context: Context):super(context)
    constructor(context: Context,attr: AttributeSet?):super(context,attr)
    constructor(context: Context,attr: AttributeSet?,sty:Int):super(context,attr,sty)

    val paint = Paint()
    //val bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

        paint.style = Paint.Style.FILL
        paint.color = Color.RED

        canvas?.save()
        canvas?.drawCircle(40f,40f,10f,paint)
        canvas?.drawCircle(100f,60f,20f,paint)
        canvas?.restore()



    }
}