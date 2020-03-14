package com.zmj.selfdefinitionview.practice7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/14
 * Description :
 */
class MoveCircle: View {

    constructor(context: Context):super(context)
    constructor(context: Context,attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context,attrs: AttributeSet?,selfDef: Int):super(context, attrs,selfDef)

    private var position = FloatArray(2)//PointF(0f,0f)

    fun getPosition(): FloatArray{
        return position
    }
    fun setPosition(position: FloatArray){
        this.position = position
        invalidate()
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL
        paint.color = Color.BLUE

        canvas?.drawCircle(position[0],position[1],4f,paint)
    }
}