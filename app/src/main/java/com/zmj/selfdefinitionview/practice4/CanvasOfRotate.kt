package com.zmj.selfdefinitionview.practice4

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import com.zmj.selfdefinitionview.R

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/9
 * Description :
 */
class CanvasOfRotate : View{
    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context, attrs: AttributeSet?, selfDef:Int):super(context, attrs,selfDef)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.map_icon)
    private val bitmapHeight = bitmap.height
    private val bitmapWith = bitmap.width

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.save()
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()

        canvas.save()
        //canvas的几何变换顺序是反的，所以写代码的顺序要反着来
        //比如你想先平移，再旋转，你要把平移的操作写在后面，旋转的操作写在前面，最后再画图如下：

        //旋转
        canvas.rotate(45f,bitmapWith * 3 / 2f,bitmapHeight/2f)
        //平移
        canvas.translate(bitmapWith/1f,0f)
        //画图
        canvas.drawBitmap(bitmap,0f,0f,paint)
        canvas.restore()
    }
}