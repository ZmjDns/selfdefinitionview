package com.zmj.selfdefinitionview.practice2_1

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import com.zmj.selfdefinitionview.utils.dpToPx

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/1/16
 * Description :
 */
class AdjustRelativeLayout: RelativeLayout{

    private lateinit var parentLayout: FrameLayout
    private lateinit var heightSeekBar: AppCompatSeekBar
    private lateinit var widthSeekBar: AppCompatSeekBar

    constructor(context: Context):super(context)
    constructor(context: Context,attrs: AttributeSet?):super(context,attrs)
    constructor(context: Context,attrs: AttributeSet?,selfDef: Int):super(context, attrs,selfDef)

    private val bottomMargin = dpToPx(48f)
    private var minWidth = dpToPx(80f)
    private var minHeight = dpToPx(100f)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()



    }


}