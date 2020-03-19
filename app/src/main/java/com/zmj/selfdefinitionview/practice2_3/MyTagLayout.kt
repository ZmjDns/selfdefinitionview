package com.zmj.selfdefinitionview.practice2_3

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/19
 * Description :
 */
class MyTagLayout: ViewGroup {
    //存放所有view的容器    //包括多少行，每行又包含若干子View
    val allViewLists = ArrayList<ArrayList<View>>()
    //存放每行view的容器
    val lineViewLists = ArrayList<View>()
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):super(context, attrs, defStyleAttr)

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context,attrs)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        allViewLists.clear()
        lineViewLists.clear()

        //获取自身的模式和宽高
        val widthModel = MeasureSpec.getMode(widthMeasureSpec)
        val heightModel = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        //测量子view的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        //自身的宽高
        var myWidth = 0 + paddingStart + paddingEnd
        var myHeight = 0 + paddingTop + paddingBottom

        //child的宽高   layoutParams
        var cWidth = 0
        var cHeight = 0
        var cParams: LayoutParams? = null

        for (i in 0 until childCount){
            val childView = getChildAt(i)

            cWidth = childView.measuredWidth
            cHeight = childView.measuredHeight
            cParams = childView.layoutParams as MarginLayoutParams

            myWidth += cParams.marginStart + cWidth + cParams.marginEnd

            //大于自身推荐的宽度，要换行，
            if (myWidth > widthSize){
                //获取当前行的长度
                val nowLineWidth =  getNowLineWidth(lineViewLists)
                myWidth = myWidth - cParams.marginStart - cWidth - cParams.marginEnd
                //获取当前最长的一行宽度给自己
                myWidth = Math.max(myWidth,nowLineWidth)
                //获取当前行的高度，并记录下来，作为自己的高度
                myHeight += getNowLineHeight(lineViewLists)

                //copy当前一行view放在总容器中,注意如果不进行数据copy，清空lineViewLists的时候，布局只会显示最后一行的View
                allViewLists.add(copyLineView(lineViewLists))

                //清空行容器，准备加入下一行view
                lineViewLists.clear()
            }

            lineViewLists.add(childView)
        }
        //将最后一行的宽度和高度也计算在内
        //获取当前行的长度
        val nowLineWidth =  getNowLineWidth(lineViewLists)
        //获取当前最长的一行宽度给自己
        myWidth = Math.max(myWidth,nowLineWidth)
        //获取当前行的高度，并记录下来，作为自己的高度
        myHeight += getNowLineHeight(lineViewLists)

        //将最后一行View放入总容器中
        allViewLists.add(lineViewLists)

        setMeasuredDimension(if (widthModel == MeasureSpec.EXACTLY) widthSize else myWidth,
                            if (heightModel == MeasureSpec.EXACTLY) heightSize else myHeight)
    }
    //获取当前一行view的宽度
    private fun getNowLineWidth(lineViews: ArrayList<View>): Int{
        var nowLineWidth =  0 + paddingStart + paddingEnd
        for (view in lineViews){
            val cViewParams = view.layoutParams as MarginLayoutParams
            nowLineWidth += cViewParams.marginStart + view.measuredWidth + cViewParams.marginEnd
        }
        return nowLineWidth
    }
    //获取当前行的高度
    private fun getNowLineHeight(lineViews: ArrayList<View>): Int{
        var nowLineHeight = 0 + paddingTop + paddingBottom
        for (view in lineViews){
            val cParams = view.layoutParams as MarginLayoutParams
            val cHeight = cParams.topMargin + view.measuredHeight + cParams.bottomMargin
            nowLineHeight = Math.max(nowLineHeight,cHeight)
        }
        return nowLineHeight
    }
    //复制当前行的View
    private fun copyLineView(lineViews: ArrayList<View>): ArrayList<View>{
        val copyLineViews = ArrayList<View>()
        for ( i in 0 until lineViewLists.size){
            copyLineViews.add(lineViews[i])
        }

        return copyLineViews
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var ct = 0
        for (lv in 0 until allViewLists.size){
            var cl = 0;  var cr = 0; var cb = 0
            val lineViews = allViewLists[lv]

            if (lv > 0){
                ct += getNowLineHeight(allViewLists[lv -1])//上一行的高度
            }

            for (i in 0 until lineViews.size){
                val thiView = lineViews[i]
                val cParams = thiView.layoutParams as MarginLayoutParams
                if (i > 0){//view位置大于0时，要计算出所有的签名子view的宽度
                    val lastView = lineViews[i - 1]
                    val lastCParams = lastView.layoutParams as MarginLayoutParams
                    cl += cl + lastView.measuredWidth + lastCParams.rightMargin
                }else{
                    cl +=  cParams.leftMargin
                    ct += cParams.topMargin
                }

                cr = cl + thiView.measuredWidth
                cb = ct + thiView.measuredHeight

                thiView.layout(cl,ct,cr,cb)
            }
        }
    }
}