package com.zmj.selfdefinitionview.practice2_3

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import com.zmj.selfdefinitionview.R


/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/17
 * Description :继承ViewGroup实现自定义layout
 * https://www.jianshu.com/p/af8e14ff5f0c
 */
class MyViewPager: ViewGroup {

    private val pics = arrayListOf<Int>(R.drawable.avatar_rengwuxian/*,R.drawable.batman,R.drawable.map_icon*/)

    constructor(context: Context): super(context){init()}
    constructor(context: Context,attrs: AttributeSet): super(context, attrs){init()}
    constructor(context: Context,attrs: AttributeSet,selfDef: Int): super(context, attrs,selfDef){init()}

    private fun init() {
        Log.i("MyViewPager","init.......")
        for (item in pics.indices){
            val iv=  ImageView(context)
            //iv.setImageResource(pics[item]) //= resources.getDrawable(item,null)
            iv.setBackgroundResource(pics[item]) //= resources.getDrawable(,null)
            this.addView(iv)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (index in 0 until childCount){
            val childView = getChildAt(index)
            childView.layout(index * childView.width,t,childView.width * (index + 1),b)
        }
        Log.i("MyViewPager","onLayout.......")
    }

    //手势监听
    private val mGestureDetector = GestureDetector(object : SimpleOnGestureListener(){
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            //相对滑动：x方向滑动多少距离，view就跟着滑动多少
            scrollBy(distanceX.toInt(),distanceY.toInt())
            Log.i("MyViewPager","onScroll.......$distanceX")
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    })

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("MyViewPager","onTouchEvent.......")
        mGestureDetector.onTouchEvent(event)
        return true
    }
}