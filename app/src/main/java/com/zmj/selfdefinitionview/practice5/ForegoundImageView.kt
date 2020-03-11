package com.zmj.selfdefinitionview.practice5

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.zmj.selfdefinitionview.R

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/3/11
 * Description :
 */
class ForegoundImageView: ImageView{
    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet):super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, selfDef:Int):super(context,attrs,selfDef)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //val bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)

    override fun onDrawForeground(canvas: Canvas?) {
        //在前景的下面 （先绘制）
        canvas?.save()
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas?.drawRect(0f,10f,80f,50f,paint)
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.textSize = 30f
        canvas?.drawText("NEW_A",0f,40f,paint)
        canvas?.restore()
        //对前景进行绘制
        super.onDrawForeground(canvas)
        //在前景的上面
        /*canvas?.save()
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas?.drawRect(0f,10f,80f,50f,paint)
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.textSize = 30f
        canvas?.drawText("NEW_A",0f,40f,paint)
        canvas?.restore()*/

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /*canvas?.save()
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas?.drawRect(0f,10f,80f,50f,paint)
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.textSize = 30f
        canvas?.drawText("NEW",0f,40f,paint)
        canvas?.restore()*/


    }

}