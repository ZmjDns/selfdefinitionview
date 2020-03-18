package com.zmj.selfdefinitionview.practice2_3

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/18
 * https://blog.csdn.net/lmj623565791/article/details/38339817
 * Description :我们定义一个ViewGroup，内部可以传入0到4个childView，分别依次显示在左上角，右上角，左下角，右下角。
 */
class MyCustomView: ViewGroup{
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, selfDef: Int): super(context, attrs,selfDef)
    constructor(context: Context,attrs: AttributeSet,selfDef: Int,styleAttr:Int): super(context, attrs,selfDef,styleAttr)

    //支持Margin
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context,attrs)
    }

    //计算所有childView的宽、高  然后根据ChildView的宽高 设置自己的宽高
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthModel = MeasureSpec.getMode(widthMeasureSpec)
        val heightModel = MeasureSpec.getMode(heightMeasureSpec)
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)

        measureChildren(widthMeasureSpec,heightMeasureSpec)

        //记录WRAP_CONTENT时设置的宽高
        var myWith = 0
        var myHeight = 0

        var cWith = 0
        var cHeight = 0
        var cParams: MarginLayoutParams? = null

        //左边两个Child的高度
        var lHeight = 0
        //右边两个child的高度，最终高度取二者最大值
        var rHeight = 0

        //上面两个child的宽度
        var tWidth = 0
        //下面两个child的宽度，最终取二者最大值
        var bWidth = 0

        for (i in 0 until childCount){
            val childView = getChildAt(i)

            //注意一定要取MeasuredWidth/MeasuredHeight否则获取的大小为0
            cWith = childView.measuredWidth
            cHeight = childView.measuredHeight
            cParams = childView.layoutParams as MarginLayoutParams
            if (i == 0 || i == 1){
                tWidth += cWith + cParams.leftMargin + cParams.rightMargin
            }
            if (i == 2 || i == 3){
                bWidth += cWith + cParams.leftMargin + cParams.rightMargin
            }
            if (i == 0 || i == 2){
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin
            }
            if (i == 1 || i == 3){
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin
            }
        }

        myWith = Math.max(tWidth,bWidth)
        myHeight = Math.max(lHeight,rHeight)


        setMeasuredDimension(if (widthModel == MeasureSpec.EXACTLY) sizeWidth else myWith,
            if (heightModel == MeasureSpec.EXACTLY) sizeHeight else myHeight)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var cl = 0; var ct = 0; var cr = 0; var cb = 0

        for (i in 0 until childCount){
            val childView = getChildAt(i)

            //注意一定要取MeasuredWidth/MeasuredHeight否则获取的大小为0
            val cWidth = childView.measuredWidth
            val cHeight = childView.measuredHeight
            val cParams = childView.layoutParams as MarginLayoutParams

            when(i){
                0 -> {
                    /*cl = cParams.leftMargin
                    ct = cParams.topMargin
                    cr = cWidth + cl
                    cb = cHeight + ct*/
                    cl = cParams.leftMargin
                    ct = cParams.topMargin
                }
                1 -> {
                    /*cl = width - cWidth -cParams.leftMargin - cParams.rightMargin
                    ct = cParams.topMargin
                    cr = cWidth + cParams.leftMargin
                    cb = cHeight + ct*/
                    cl = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin
                    ct = cParams.topMargin
                }
                2 -> {
                    /*cl = cParams.leftMargin
                    ct = height - cHeight - cParams.topMargin - cParams.bottomMargin
                    cr = cWidth + cl
                    cb = cHeight + cParams.topMargin*/
                    cl = cParams.leftMargin
                    ct = getHeight() - cHeight - cParams.bottomMargin
                }
                3 -> {
                    /*cl = width - cWidth -cParams.leftMargin - cParams.rightMargin
                    ct = height - cHeight - cParams.topMargin - cParams.bottomMargin
                    cr = cWidth + cParams.leftMargin
                    cb = cHeight + cParams.bottomMargin*/
                    cl = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin;
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                }
            }

            cr = cl + cWidth
            cb = cHeight + ct
            childView.layout(cl,ct,cr,cb)
        }
    }
}