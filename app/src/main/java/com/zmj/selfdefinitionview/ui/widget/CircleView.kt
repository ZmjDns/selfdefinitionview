package com.zmj.selfdefinitionview.ui.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/29
 * Description :
 */
class CircleView : View{

    private var color:Int = Color.RED

    private var position = PointF(80f,80f)

    fun getPosition():PointF{
        return position
    }

    fun setPosition(p:PointF){
        this.position = p
        invalidate()
    }

    fun getColor():Int{
        return color
    }

    fun setColor(color:Int){
        this.color = color
        invalidate()
    }

    constructor(context:Context):super(context)
    constructor(context: Context,attr: AttributeSet?):super(context,attr)
    constructor(context: Context,attr: AttributeSet?,selfDef:Int):super(context,attr,selfDef)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL

        paint.color = color

        canvas?.save()
        canvas?.drawCircle(position.x,position.y,80f,paint)
        canvas?.restore()
    }
}