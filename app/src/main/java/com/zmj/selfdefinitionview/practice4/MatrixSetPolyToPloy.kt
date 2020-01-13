package com.zmj.selfdefinitionview.practice4

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.zmj.selfdefinitionview.R

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/13
 * Description :
 */
class MatrixSetPolyToPloy: View {

    constructor(context:Context):super(context)
    constructor(context: Context,attrs: AttributeSet?):super(context, attrs)
    constructor(context: Context,attrs: AttributeSet?,selfDef:Int):super(context, attrs,selfDef)

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.map_icon)
    private var leftF = left.toFloat()
    private var topF = top.toFloat()
    private var rightF = bitmap.width.toFloat()//right.toFloat()
    private var bottomF = bitmap.height.toFloat()//bottom.toFloat()

    private val srcPoint = floatArrayOf(leftF,topF,rightF,topF,leftF,bottomF,rightF,bottomF)
    private val destPoint = floatArrayOf(leftF + 10,topF + 50,rightF + 120,topF - 90,leftF + 20, bottomF + 30,leftF + 120,bottomF + 60)


    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val myMatrix = Matrix()

    private var srcSting = ""
    private var desString = ""



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (i in srcPoint.indices){
            srcSting += "${srcPoint[i]},"
            desString += "${destPoint[i]},"
        }


        Log.i("srcPoint:",srcSting)
        Log.i("destPoint:",desString)

        canvas!!.save()
        myMatrix.reset()
        myMatrix.setPolyToPoly(srcPoint,0,destPoint,0,4)
        canvas.concat(myMatrix)
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()

        canvas.save()
        canvas.concat(matrix)
        canvas.drawBitmap(bitmap,rightF ,0f,paint)
        canvas.restore()
    }


}