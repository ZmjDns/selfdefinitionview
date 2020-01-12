package com.zmj.selfdefinitionview.practice4

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.zmj.selfdefinitionview.R


/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/9
 * Description :
 */
class MatrixShape: View {

    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet?, selfDef:Int):super(context, attrs,selfDef)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.map_icon)
    private val bitmapW = bitmap.width.toFloat()
    private val bitmapH = bitmap.height.toFloat()
    private val myMatrix = matrix


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.save()
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()

        myMatrix.reset()
        myMatrix.preTranslate(bitmapW,0f)
        canvas.concat(myMatrix)
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()


    }

}