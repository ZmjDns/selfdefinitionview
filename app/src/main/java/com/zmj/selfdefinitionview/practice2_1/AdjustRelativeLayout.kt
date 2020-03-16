package com.zmj.selfdefinitionview.practice2_1

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import com.zmj.selfdefinitionview.R
import com.zmj.selfdefinitionview.utils.dpToPx
import kotlinx.android.synthetic.main.sample_adjust_panel.view.*

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

        parentLayout = findViewById(R.id.parentLayout)
        widthSeekBar = findViewById(R.id.widthBar)
        heightSeekBar = findViewById(R.id.heightBar)

        val listener: SeekBar.OnSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val layoutParams = parentLayout.layoutParams
                layoutParams.width = (minWidth + (this@AdjustRelativeLayout.width - minWidth) * widthSeekBar.progress / 100).toInt()
                layoutParams.height = (minHeight + (this@AdjustRelativeLayout.height - bottomMargin ) * heightSeekBar.progress / 100).toInt()

                parentLayout.layoutParams = layoutParams

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        }
        widthSeekBar.setOnSeekBarChangeListener(listener)
        heightSeekBar.setOnSeekBarChangeListener(listener)

    }
}