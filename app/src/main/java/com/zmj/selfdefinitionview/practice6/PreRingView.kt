package com.zmj.selfdefinitionview.practice6

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/3/12
 * Description :
 */
class PreRingView: View {

    constructor(context: Context):super(context)
    constructor(context: Context, attr: AttributeSet?):super(context,attr)
    constructor(context: Context, attr: AttributeSet?, sty:Int):super(context,attr,sty)

    private var progress:Float = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    fun getProgress():Float{
        return progress
    }
    fun setProgress(progress:Float){
        this.progress = progress
        invalidate()
    }

    private val rectF = RectF(10f,10f,210f,210f)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8f
        paint.color = Color.RED

        canvas?.drawArc(rectF,45f,progress,false,paint)
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 1f
        paint.textSize = 40f
        val text = "${((progress/360) * 100).toInt()}%"
        val textWidth = paint.measureText(text)
        val textHeight = paint.descent() - paint.ascent()

        canvas?.drawText(text,(110f - textWidth/2),(110f + textHeight/2),paint)
    }
}