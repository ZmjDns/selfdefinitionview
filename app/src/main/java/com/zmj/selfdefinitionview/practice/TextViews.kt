package com.zmj.selfdefinitionview.practice

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toRectF
import org.jetbrains.annotations.Nullable
import java.util.*

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/3
 * Description :
 */
class FirstText: View {
    constructor(context:Context):super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet):super(context, attrs)
    constructor(context: Context,@Nullable attrs: AttributeSet,@Nullable selfDef:Int):super(context, attrs,selfDef)

    val paint = Paint()
    val path = Path()
    val text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
    //StaticLayout(text1,TextPaint(),600,Layout.Alignment.ALIGN_NORMAL,1f,0f,true)
    var staticLayout1 = StaticLayout(text1,TextPaint(),600,Layout.Alignment.ALIGN_NORMAL,1f,0f,true)


        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            setLayerType(View.LAYER_TYPE_SOFTWARE,paint)

            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 2f
            paint.color = Color.RED

            paint.setShadowLayer(10f,12f,12f,Color.GREEN)

            //path.moveTo(20f,170f)
            //path.lineTo(350f,170f)
            //path.rLineTo(100f,-50f)

            //canvas?.drawPath(path,paint)

            //paint.textSkewX = 0.5f

            paint.textSize = 40f
            //paint.isStrikeThruText = true
            canvas?.drawText("HELLO ZMJ  普通绘制",20f,60f,paint)
            //canvas?.drawTextOnPath("HELLO",path,0f,0f,paint)
            //canvas?.drawTextOnPath("HELLO ZMJ",path,0f,0f,paint)
            //canvas?.drawColor(Color.RED)
            //canvas?.drawCircle(100f,100f,80f,paint)

            /*canvas?.save()
            canvas?.translate(50f,100f)
            staticLayout1.draw(canvas)*/
            //println("文字画完......")
        }
}

class TextOnPath:View{
    constructor(context:Context):super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet):super(context, attrs)
    constructor(context: Context,@Nullable attrs: AttributeSet,@Nullable selfDef:Int):super(context, attrs,selfDef)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)//默认抗锯齿
    val path = Path()
    val char = charArrayOf('A','B','C','D')
    val pathEffect = CornerPathEffect(10f)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //setLayerType(LAYER_TYPE_SOFTWARE,null)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f
        paint.color = Color.RED

        paint.setShadowLayer(10f,12f,12f,Color.GREEN)

        path.moveTo(20f,60f)
        //path.lineTo(400f,60f)
        path.rLineTo(120f,40f)
        path.rLineTo(120f,-70f)


        paint.pathEffect = pathEffect

        canvas?.drawPath(path,paint)


        //canvas?.save()
        paint.textSize = 40f
        //canvas?.drawText("Text on path",20f,60f,paint)
        canvas?.drawTextOnPath("Text on path",path,0f,0f,paint)
        path.rLineTo(150f,70f)
        canvas?.drawPath(path,paint)
        canvas?.drawTextOnPath(char,0,4,path,240f,16f,paint)

        canvas?.drawText("线上的文字",500f,60f,paint)
        //canvas?.restore()
    }
}

class DeleteLineText: View{
    constructor(context:Context):super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet):super(context, attrs)
    constructor(context: Context,@Nullable attrs: AttributeSet,@Nullable selfDef:Int):super(context, attrs,selfDef)

    val paint :Paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.textSize = 40f

        paint.isStrikeThruText = true

        canvas?.drawText("Delete line text",20f,60f,paint)

        paint.isStrikeThruText = false
        paint.isUnderlineText = true

        canvas?.drawText("Under Line Text",350f,60f,paint)

    }
}

class LocalText: View{
    constructor(context:Context):super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet):super(context, attrs)
    constructor(context: Context,@Nullable attrs: AttributeSet,@Nullable selfDef:Int):super(context, attrs,selfDef)

    val paint :Paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.textSize = 40f

        paint.textLocale = Locale.CHINA
        canvas?.drawText("雨骨底条今直沿微写",20f,60f,paint)

        paint.textLocale = Locale.TAIWAN
        canvas?.drawText("雨骨底条今直沿微写",20f,60f + paint.fontSpacing,paint)

        paint.textLocale = Locale.JAPAN
        canvas?.drawText("雨骨底条今直沿微写",20f,60f + paint.fontSpacing * 2,paint)
    }
}

class TextBounds: View{
    constructor(context:Context):super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet):super(context, attrs)
    constructor(context: Context,@Nullable attrs: AttributeSet,@Nullable selfDef:Int):super(context, attrs,selfDef)

    val paint: Paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    val text = "Text Bounds"
    val bounds = Rect()

    val textPaint = TextPaint(paint)
    val string = "StaticLayout:\n并不是一个 View 或者 ViewGroup ，而是 android.text.Layout 的子类\n，它是纯粹用来绘制文字的.支持换行"
    val staticLayout = StaticLayout(string, textPaint,600,Layout.Alignment.ALIGN_NORMAL,1f,0f,true)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = Color.BLACK
        paint.textSize = 40f

        canvas?.drawText(text,20f,60f,paint)

        paint.getTextBounds(text,0,text.length,bounds)
        bounds.left += 20
        bounds.top += 60
        bounds.right += 20
        bounds.bottom += 60
        paint.style = Paint.Style.STROKE
        canvas?.drawRect(bounds,paint)

        canvas?.save()
        //textPaint.textSize = 40f
        canvas?.translate(50f,100f)
        staticLayout.draw(canvas)
        canvas?.restore()




    }
}