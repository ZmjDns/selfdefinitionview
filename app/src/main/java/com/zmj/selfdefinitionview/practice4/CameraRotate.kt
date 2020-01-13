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
class CameraRotate :View{

    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet?, selfDef:Int):super(context, attrs,selfDef)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera()
    private val bitmap = BitmapFactory.decodeResource(resources,R.drawable.map_icon)

    private val bitWidth = bitmap.width.toFloat()
    private val bitHeight = bitmap.height.toFloat()



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.save()

        camera.save()


        //将Camera沿着X轴旋转30度投影得到的图片
        camera.rotateX(30f)         //沿着x轴正向旋转30度
        camera.applyToCanvas(canvas)     //把旋转投影到canvas
        camera.restore()                //恢复camera的状态
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()


        //如果想得到对称的图片，需要先将canva移动到原点，画图之后再移动回来
        //canvas的几何变换是反的，因此需要将移动到原点的代码写到下面，把从原点移回来的代码写到上面
        canvas.save()
        camera.save()
        camera.rotateX(30f)
        canvas.translate(bitWidth *3 /2,bitHeight/2)
        camera.applyToCanvas(canvas)
        canvas.translate(-(bitWidth *3 /2),-(bitHeight/2))
        camera.restore()

        canvas.drawBitmap(bitmap,bitWidth * 1.2f,0f,paint)

        canvas.restore()

        canvas.save()
        canvas.drawBitmap(bitmap,bitWidth * 2.3f,0f,paint)
        canvas.restore()
    }



}