package com.zmj.selfdefinitionview.touchfeedback

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/24
 * Description :
 */
class TouchEventView: View,GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    /**
     * 手势探测器
     * GestureDetector与GestureDetectorCompat一样，带Compat的一般是为兼容出的，
     * 使用GestureDetector使用一下三步：
     * 1.实现对应接口方法
     * 2.重写onTouchEvent()方法，修改返回值为GestureDetector.onTouchEvent(event)
     * 3.如果需要双击事件，修改返回值为GestureDetector.onDoubleTapListener(this)
     */
    private lateinit var gestureDetector: GestureDetectorCompat
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        //设置双击监听
        //gestureDetector.setOnDoubleTapListener(this)
        //只实现双击监听可使用此简单方法
        /*gestureDetector = GestureDetectorCompat(context,object :GestureDetector.SimpleOnGestureListener(){
            override fun onDown(e: MotionEvent?): Boolean {
                //return super.onDown(e)
                return true
            }

            override fun onDoubleTap(e: MotionEvent?): Boolean {
                return super.onDoubleTap(e)
            }
        })*/
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):super(context, attrs, defStyleAttr)
    {
        gestureDetector = GestureDetectorCompat(context,this)
        gestureDetector.setOnDoubleTapListener(this)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("TouchEventView","onTouchEvent()......${event?.actionMasked}")
        //使用gestureDetector
        return gestureDetector.onTouchEvent(event)
    }

    /**
     * 收到ACTION_DOWN后onDown（）会被调用
     * onDown（）决定事件是否会被消费，true为消费，false为不消费（即不处理）
     * 如果不消费，下面所有的方法都无效，所以这里设置为消费
     */
    override fun onDown(e: MotionEvent?): Boolean {
        Log.i("TouchEventView","按下事件......")
        return true
    }

    //预按下事件（父View是滑动控件时会出现预按下事件）
    override fun onShowPress(e: MotionEvent?) {
        Log.i("TouchEventView","滑动控件的预按下事件......")
    }

    /**
     *单击:每次按下抬起都会有响应(返回值无用，只有onDown（）里面的返回值决定是否消费事件)，
     * 如果支持长按事件（按下500ms后触发），在按下500ms后抬起不会触发onSingleTapUp（）
     * 如果长按事件打开，按下后500ms内抬起会触发此方法（onSingleTaoUp）
     * 如果长按事件未打开，按下后何时抬起都会触发此事件
     */
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.i("TouchEventView","单击后抬起......")
        return false
    }

    /**
     * 与onMove（）同步 和onMove（）一样
     * 实现功能：图片放大后跟随手指滑动偏移
     *
     * @param downEvent 按下事件
     * @param event   当前事件
     * @param distanceX 上一个点到当前点水平方向上的距离
     * @param distanceY 上一个点到当前点垂直方向上的距离
     */
    override fun onScroll(downEvent: MotionEvent?, event: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.i("TouchEventView","滚动......distanceX: $distanceX      distanceY: $distanceY")
        return false
    }

    //长按事件
    override fun onLongPress(e: MotionEvent?) {
        Log.i("TouchEventView","长按......")
    }

    /**
     * 惯性滑动
     * 实现fling效果
     * @param velocityX 速度：抬手一瞬间的  距离/时间
     */
    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.i("TouchEventView","惯性滑动事件......velocityX：$velocityX   velocityY: $velocityY")
        return false
    }

    /**
     * onSingleTapUp 单击事件（长按事件未开启的话，只要手指按下抬起就会触发）
     * onSingleTapConfirmed 单击确认事件
     * 当设置双击监听打开后，想要使用单击监听用onSingleTapConfirmed方法
     * 否则双击屏幕会先调用onSingleTap方法、onDoubleTap方法
     */
    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        Log.i("TouchEventView","单击确认事件......")
        return false
    }

    //双击事件（两次触摸时间 < 300ms）
    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.i("TouchEventView","双击事件（两次触摸时间 < 300ms）......")
        return true
    }

    //双击不松手滑动触发
    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        Log.i("TouchEventView","双击不松手滑动触发......")
        return false
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 4f
        paint.textSize = 40f
        canvas?.drawText("I am touch feedback.",20f,measuredHeight / 2f,paint)
    }


}