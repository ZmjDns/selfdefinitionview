package com.zmj.selfdefinitionview.practice4

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.zmj.selfdefinitionview.R

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/4
 * Description :
 */

class PaintClipView:View{
    constructor(context:Context):super(context)
    constructor(context: Context,attr:AttributeSet?):super(context,attr)
    constructor(context: Context,attr: AttributeSet?,defStyle:Int):super(context,attr,defStyle)

    val paint = Paint()
    val bitmap = BitmapFactory.decodeResource(resources,R.drawable.map_icon)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        //矩形裁切
        canvas?.clipRect(20f,20f,180f,180f)
        //二维平移，即上下左右平移
        //canvas?.translate(80f,0f)
        canvas?.drawBitmap(bitmap,0f,0f,paint)
        canvas?.restore()
    }
}

class CanvasClipPath: View{
    constructor(context:Context):super(context)
    constructor(context: Context,attr:AttributeSet?):super(context,attr)
    constructor(context: Context,attr: AttributeSet?,defStyle:Int):super(context,attr,defStyle)

    val paint = Paint()
    val path = Path()
    val path2 = Path()
    val bitmap = BitmapFactory.decodeResource(resources,R.drawable.map_icon)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f

        path.addCircle(100f,100f,80f,Path.Direction.CCW)
        canvas?.drawPath(path,paint)

        canvas?.save()
        canvas?.clipPath(path)
        canvas?.drawBitmap(bitmap,10f,10f,paint)
        canvas?.restore()

        path2.addCircle(300f,100f,40f,Path.Direction.CW)

        canvas?.drawPath(path2,paint)

        canvas?.save()
        canvas?.clipPath(path2)
        canvas?.drawBitmap(bitmap,200f,0f,paint)
        canvas?.restore()
    }
}