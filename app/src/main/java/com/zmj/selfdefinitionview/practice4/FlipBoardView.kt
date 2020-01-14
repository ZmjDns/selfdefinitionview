package com.zmj.selfdefinitionview.practice4

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import com.zmj.selfdefinitionview.R

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/13
 * Description :
 */
class FlipBoardView: View {

    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet?, selfDef:Int):super(context, attrs,selfDef)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera()
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.map_icon)
    private var degree:Int = 0
    private val animator = ObjectAnimator.ofInt(this,"degree",0,180)
    init{
        animator.duration = 2500
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
    }

    fun getDegree():Int{
        return degree
    }

    fun setDegree(degree:Int){
        this.degree = degree
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        val centerX = width / 2
        val centerY = height / 2

        val x = centerX - bitmapWidth / 2f
        val y = centerY - bitmapHeight / 2f

        /*Log.i("FlipBoardView:","width:$width/height:$height")//layout_width   layout_height
        Log.i("FlipBoardView:","bitmapWidth:$bitmapWidth/bitmapHeight:$bitmapHeight")
        Log.i("FlipBoardView:","x:$x/y:$y")*/

        //第一遍绘制上半部分
        canvas!!.save()
        canvas.clipRect(0,0,width,centerY)
        canvas.drawBitmap(bitmap,x,y,paint)
        canvas.restore()

        //第二遍绘制下半部分
        if (degree < 90){
            canvas.clipRect(0,centerY,width,height)
        }else{
            canvas.clipRect(0,0,width,centerY)
        }

        canvas.save()
        camera.save()
        camera.rotateX(degree.toFloat())    //Camera绕x轴旋转的角度
        //注意canvas几何变换是倒序的
        canvas.translate(centerX.toFloat(),centerY.toFloat())
        camera.applyToCanvas(canvas)
        canvas.translate((-centerX.toFloat()),(-centerY.toFloat()))
        camera.restore()

        canvas.drawBitmap(bitmap,x,y,paint)
        canvas.restore()

    }
}