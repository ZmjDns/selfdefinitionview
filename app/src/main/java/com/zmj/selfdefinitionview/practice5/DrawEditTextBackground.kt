package com.zmj.selfdefinitionview.practice5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.widget.EditText

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/18
 * Description :
 * 1.draw（）是所有绘制的调度包括一次为：drawBackground（）、onDraw（）、dispatchDraw（）、onDrawForeground（）
 * 在draw之前进行绘制，即在背景的后面进行绘制
 */

class DrawEditTextBackground: EditText{

    constructor(context: Context):super(context)
    constructor(context: Context, attr: AttributeSet?):super(context,attr)
    constructor(context: Context, attr: AttributeSet?, sty:Int):super(context,attr,sty)

    override fun draw(canvas: Canvas?) {

        canvas?.save()
        canvas?.drawColor(Color.parseColor("#66BB6A"))
        canvas?.save()

        super.draw(canvas)
    }


}