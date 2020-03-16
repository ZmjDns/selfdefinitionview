package com.zmj.selfdefinitionview.practice2_1

import android.content.Context
import android.util.AttributeSet

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/7
 * Description :
 */
class AdjustImageView: androidx.appcompat.widget.AppCompatImageView {

    constructor(context: Context): super(context)
    constructor(context: Context,attr:AttributeSet):super(context,attr)
    constructor(context: Context,attr: AttributeSet,selfDef:Int):super(context,attr,selfDef)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var measuredWidth = getMeasuredWidth()//View.getDefaultSize(0,widthMeasureSpec)//measuredWidth
        var measuredHeight = getMeasuredHeight()//View.getDefaultSize(0,heightMeasureSpec)//measuredHeight

        if (measuredWidth > measuredHeight){
            measuredHeight = measuredWidth
        }else{
            measuredWidth = measuredHeight
        }

        setMeasuredDimension(measuredWidth,measuredHeight)
    }

}