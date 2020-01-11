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
 * Time : 2020/1/9
 * Description :  Canvas辅助方法
 */
class CanvasOfClipPath: View {

    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet?, selfDef:Int):super(context, attrs,selfDef)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path1 = Path()
    private val path2 = Path()
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.map_icon)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE
        paint.color = Color.WHITE

        path1.addCircle(100f,100f,80f,Path.Direction.CCW)
        path2.addCircle(350f,150f,80f,Path.Direction.CW)

        canvas?.save()
        canvas?.clipPath(path1)
        canvas?.drawBitmap(bitmap,0f,0f,paint)
        canvas?.restore()

        canvas?.save()

        //canvas?.clipOutPath(path2)
        canvas?.clipPath(path2,Region.Op.DIFFERENCE)
        canvas?.drawBitmap(bitmap,200f,0f,paint)
        canvas?.restore()






    }

}