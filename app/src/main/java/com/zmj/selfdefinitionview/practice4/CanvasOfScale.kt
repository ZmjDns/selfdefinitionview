package com.zmj.selfdefinitionview.practice4

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.zmj.selfdefinitionview.R

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/9
 * Description :  Canvas几何变换之缩放
 */
class CanvasOfScale: View {

    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet?, selfDef:Int):super(context, attrs,selfDef)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = BitmapFactory.decodeResource(resources,R.drawable.map_icon)
    private val bitmapH = bitmap.height
    private val bitmapW = bitmap.width


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.save()
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()

        canvas.save()

        //几何变换的顺序是倒置的，切记
        canvas.scale(1.5f,1.5f)
        canvas.translate(bitmapW.toFloat(),0f)
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()

        canvas.save()
        canvas.scale(0.5f,0.5f)
        canvas.translate(bitmapW * 6f,0f)
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()


    }
}