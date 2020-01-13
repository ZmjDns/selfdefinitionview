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
    private val myMatrix = Matrix()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.save()
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()

        canvas.save()
        myMatrix.reset()
        //Matrix的preXXX()是按顺序执行的
        //因此执行的顺序为先平移后旋转
        /*myMatrix.preTranslate(bitmapW * 1.5f,0f)
        myMatrix.preRotate(45f)*/

        //Matrix的postXXX方法是倒序执行的
        //因此执行的顺序为，先平移后旋转
        myMatrix.postRotate(45f)
        myMatrix.postTranslate(bitmapW * 1.5f,0f)

        canvas.concat(myMatrix)
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()
    }
}