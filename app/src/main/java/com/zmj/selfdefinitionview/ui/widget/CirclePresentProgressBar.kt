package com.zmj.selfdefinitionview.ui.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/5
 * Description :
 */
class CirclePresentProgressBar: View {
    constructor(context: Context):super(context)
    constructor(context: Context,attrs: AttributeSet):super(context, attrs)
    constructor(context: Context,attrs: AttributeSet,selfDf:Int):super(context, attrs,selfDf)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF(10f,10f,200f,200f)
    private var progress: Float = 0f

    fun getProgress():Float{
        return progress
    }
    fun setProgress(progress: Float){
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.strokeWidth = 10f
        paint.strokeCap = Paint.Cap.ROUND

        textPaint.style = Paint.Style.FILL
        textPaint.color = Color.RED
        textPaint.textSize = 40f

        canvas?.save()
        canvas?.drawArc(rectF,135f,progress * 3.60f,false,paint)
        canvas?.drawText("${progress.toInt()}%",85f,120f,textPaint)
        canvas?.restore()
    }
}