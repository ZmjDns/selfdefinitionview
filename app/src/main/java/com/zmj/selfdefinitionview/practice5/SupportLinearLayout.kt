package com.zmj.selfdefinitionview.practice5

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout
import com.zmj.selfdefinitionview.R
import java.util.jar.Attributes

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/17
 * Description :
 */
class SupportLinearLayout: LinearLayout {

    constructor(context: Context):super(context)
    constructor(context: Context,attr: AttributeSet?):super(context,attr)
    constructor(context: Context,attr: AttributeSet?,sty:Int):super(context,attr,sty)

    val paint = Paint()
    //val bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)


    /**
     * 每一个view和ViewGroup都会先调用onDraw来绘制自己的主题
     * 再调用dispatchDraw来绘制子View
     * 其中ViewGroup的子类（LinearLayout）的dispatchDraw（）方法中的super.dispatchDraw(canvas)就是对子view的绘制
     *
     * 如果想在LinearLayout的子view上面绘制图像分两种情况
     * 1.继承LinearLayout,重载dispatchDraw（）方法，在super.dispatchDraw（）方法下绘制内容
     * 2.继承已存在的view(如EditText),直接重写onDrawForeground()在super.onDrawForeground(canvas)下面绘制,或者在onDraw方法的super.onDraw(canvas)的上面绘制
     *  直接继承View，直接重写onDrawForeground()在super.onDrawForeground(canvas)下面绘制,或者在onDraw方法按照canvas的顺序绘制
     *
     */
    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

        paint.style = Paint.Style.FILL
        paint.color = Color.RED

        canvas?.save()
        canvas?.drawCircle(40f,40f,10f,paint)
        canvas?.drawCircle(100f,60f,20f,paint)
        canvas?.restore()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }
}