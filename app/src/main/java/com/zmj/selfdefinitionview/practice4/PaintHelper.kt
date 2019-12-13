package com.zmj.selfdefinitionview.practice4

import android.content.Context
import android.graphics.*
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

class CanvasRotate:View{
    constructor(context:Context):super(context)
    constructor(context: Context,attr:AttributeSet?):super(context,attr)
    constructor(context: Context,attr: AttributeSet?,defStyle:Int):super(context,attr,defStyle)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bitmap = BitmapFactory.decodeResource(resources,R.drawable.map_icon)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.rotate(45f,100f,100f)
        canvas?.drawBitmap(bitmap,0f,0f,paint)

        canvas?.save()
        canvas?.scale(0.6f,0.6f,bitmap.width/2f,bitmap.height/2f)
        canvas?.drawBitmap(bitmap,400f,-300f,paint)
        canvas?.restore()

        canvas?.save()
        //错切
        canvas?.skew(0f,0.5f)
        canvas?.drawBitmap(bitmap,200f,-800f,paint)
        canvas?.restore()
    }
}

class CanvasMatrix: View{
    constructor(context:Context):super(context)
    constructor(context: Context,attr:AttributeSet?):super(context,attr)
    constructor(context: Context,attr: AttributeSet?,defStyle:Int):super(context,attr,defStyle)

    val paint = Paint()
    val myMatrix = Matrix()
    val bitmap = BitmapFactory.decodeResource(resources,R.drawable.map_icon)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        myMatrix.reset()
        myMatrix.postTranslate(10f,10f)//平移
        myMatrix.postRotate(45f,100f,100f)//旋转
        myMatrix.postScale(0.6f,0.6f,bitmap.width/2f,bitmap.height/2f)//缩放


        canvas?.save()
        canvas?.concat(myMatrix)
        canvas?.drawBitmap(bitmap,0f,0f,paint)
        canvas?.restore()
    }

}