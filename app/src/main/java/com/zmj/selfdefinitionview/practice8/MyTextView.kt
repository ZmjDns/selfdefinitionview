package com.zmj.selfdefinitionview.practice8

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.zmj.selfdefinitionview.R

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/3/3
 * Description :https://www.jianshu.com/p/705a6cb6bfee
 *
 * 继承View绘制一个正方形
 */
class MyTextView: View {





    /*constructor(context: Context,attrs: AttributeSet):super(context){
        val typeArray: TypedArray = context.obtainStyledAttributes(attrs,R.styleable.MyTextView)
        val text: String? = typeArray.getString(R.styleable.MyTextView_text)
        val textAttr: Int = typeArray.getInteger(R.styleable.MyTextView_testAttr,-1)
        val textColor: Int = typeArray.getInteger(R.styleable.MyTextView_textColor,-1)
        Log.i("MyTextView","text=$text    /textAttr=$textAttr      /textColor=$textColor")
        typeArray.recycle()
    }*/

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context):super(context){
        init()
    }

    constructor(context: Context,attrs: AttributeSet):super(context, attrs){
        init()
    }

    constructor(context: Context,attrs: AttributeSet,selfDef:Int):super(context, attrs,selfDef){
        init()
    }

    private fun init(){

        paint.color = Color.BLUE
    }


    /**
     * 在View的源码当中并没有对AT_MOST和EXACTLY两个模式做出区分，
     * 也就是说View在wrap_content和match_parent两个模式下是完全相同的，
     * 都会是match_parent，显然这与我们平时用的View不同，所以我们要重写onMeasure方法。
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthModel = MeasureSpec.getMode(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightModel = MeasureSpec.getMode(heightMeasureSpec)

        //处理wrap_content情况
        /*if (widthModel == MeasureSpec.AT_MOST && heightModel == MeasureSpec.AT_MOST){
            setMeasuredDimension(300,300)
        }else if (widthMeasureSpec == MeasureSpec.AT_MOST){

        }*/
        if (widthModel == MeasureSpec.AT_MOST){
            widthSize = 300
        }
        if(heightModel == MeasureSpec.AT_MOST){
            heightSize = 300
        }

        setMeasuredDimension(widthSize,heightSize)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //获取各个边距的padding值
        val padLeft = paddingLeft
        val padRight = paddingRight
        val padTop = paddingTop
        val padBottom = paddingBottom
        Log.i("onDraw","padLeft:$padLeft||padRight:$padRight||padTop:$padTop||padBottom:$padBottom")

        //获取绘制view的宽度
        val mWidth = width - paddingLeft - paddingRight
        val mWidth1 = width - padLeft - padRight
        Log.i("onDraw","mWidth:$mWidth||mWidth1:$mWidth1")

        //获取view的高度
        val mHeight = height - paddingTop - paddingBottom
        val mHeight1 = height - padTop - padBottom
        Log.i("onDraw","mHeight:$mHeight||mHeight1:$mHeight1")

        //绘制view

        canvas?.drawRect(0f + padLeft,0f + padTop,(padLeft + mWidth).toFloat(),(padTop + mHeight).toFloat(),paint)
    }



}