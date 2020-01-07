package com.zmj.selfdefinitionview.practice2_1

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/7
 * Description :
 */
class AdjustImageView: ImageView {

    constructor(context: Context): super(context)
    constructor(context: Context,attr:AttributeSet):super(context,attr)
    constructor(context: Context,attr: AttributeSet,selfDef:Int):super(context,attr,selfDef)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var measuredWidth = measuredWidth//View.getDefaultSize(0,widthMeasureSpec)//measuredWidth
        var measuredHeight = measuredHeight//View.getDefaultSize(0,heightMeasureSpec)//measuredHeight

        if (measuredWidth > measuredHeight){
            measuredWidth = measuredHeight
        }else{
            measuredHeight = measuredWidth
        }

        setMeasuredDimension(measuredWidth,measuredHeight)
    }

}