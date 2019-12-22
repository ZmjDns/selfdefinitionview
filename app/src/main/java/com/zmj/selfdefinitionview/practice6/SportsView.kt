package com.zmj.selfdefinitionview.practice6

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/22
 * Description :
 */
class SportsView: View {

    private var progress: Float = 0f

    constructor(context: Context):super(context)
    constructor(context: Context, attr: AttributeSet?):super(context,attr)
    constructor(context: Context, attr: AttributeSet?, sty:Int):super(context,attr,sty)

    fun getProgress():Float{
        return progress
    }
    fun setProgress(progress:Float){
        this.progress = progress
        invalidate()
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val arc = RectF(10f,10f,300f,300f)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f

        textPaint.textSize = 40f
        textPaint.style = Paint.Style.FILL
        textPaint.strokeWidth = 2f

        canvas?.save()
        canvas?.drawArc(arc,360f,progress * 3.6f,false,paint)

        canvas?.drawText("$progress%",100f,180f,textPaint)
        canvas?.restore()
    }
}