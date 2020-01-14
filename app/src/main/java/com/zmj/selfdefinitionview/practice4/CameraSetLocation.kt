package com.zmj.selfdefinitionview.practice4

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.zmj.selfdefinitionview.R

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/13
 * Description :
 */
class CameraSetLocation: View {

    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet?, selfDef:Int):super(context, attrs,selfDef)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera()
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.map_icon)

    private val bitWidth = bitmap.width.toFloat()
    private val bitHeight = bitmap.height.toFloat()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.save()
        camera.save()

        camera.rotateX(30f)
        camera.setLocation(0f,0f,-900f)
        camera.applyToCanvas(canvas)
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()


        canvas.save()
        camera.save()

        camera.rotateX(30f)
        camera.setLocation(0f,0f,-100f)
        camera.applyToCanvas(canvas)
        canvas.drawBitmap(bitmap,bitWidth,0f,paint)
        canvas.restore()
    }
}