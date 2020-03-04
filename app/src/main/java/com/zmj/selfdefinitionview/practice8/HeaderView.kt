package com.zmj.selfdefinitionview.practice8

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.zmj.selfdefinitionview.R


/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2020/3/4
 * Description :
 */
class HeaderView: RelativeLayout {

    private lateinit var img_left:ImageView
    private lateinit var img_right: ImageView
    private lateinit var text_center: TextView
    private lateinit var layout_root: RelativeLayout

    private  var showView: Int = 0
    private lateinit var element: String


    constructor(context: Context):super(context){
        initView(context)
    }

    constructor(context: Context,attrs:AttributeSet):super(context,attrs){
        initView(context)
        initAttrs(context, attrs)
    }

    constructor(context: Context,attrs: AttributeSet,selfDef: Int):super(context, attrs,selfDef){
        initView(context)
        initAttrs(context, attrs)
    }

    private fun initView(context: Context){
        LayoutInflater.from(context).inflate(R.layout.self_def_header,this,true)

        img_left = findViewById(R.id.header_left_img)
        img_right = findViewById(R.id.header_right_img)
        text_center = findViewById(R.id.header_center_text)
        layout_root = findViewById(R.id.header_root_layout)

        layout_root.setBackgroundColor(Color.GRAY)
        text_center.setTextColor(Color.WHITE)
    }

    private fun initAttrs(context: Context,attrs: AttributeSet){
        val typeArray: TypedArray = context.obtainStyledAttributes(attrs,R.styleable.HeaderView)
        val title: String? = typeArray.getString(R.styleable.HeaderView_title_text)
        if (!TextUtils.isEmpty(title)){
            text_center.text = title
        }
        showView = typeArray.getInt(R.styleable.HeaderView_show_views,0x26)
        text_center.setTextColor(typeArray.getColor(R.styleable.HeaderView_title_text_color,Color.WHITE))

        typeArray.recycle()

        Log.i("initAttrs","title:$title  /showView:$showView  /text_center:$text_center")
        showHeaderView(showView)
    }

    private fun showHeaderView(showView: Int){
        val data = java.lang.Long.valueOf(Integer.toBinaryString(showView))
        element = String.format("%06d", data)
        /*for (i in element.indices) {
            if (i == 0);
            if (i == 1) text_center.visibility = if (element.substring(i,i + 1) == "1") View.VISIBLE else View.GONE
            if (i == 2) img_right.visibility = if (element.substring(i,i + 1) == "1") View.VISIBLE else View.GONE
            if (i == 3);
            if (i == 4) img_left.visibility = if (element.substring(i,i + 1) == "1") View.VISIBLE else View.GONE
            if (i == 5);
        }*/
    }


    private fun setTitle(title: String){
        if (title.isNotEmpty()){
            text_center.text = title
        }
    }

    private fun setLeftListener(onClickListener: OnClickListener){
        img_left.setOnClickListener(onClickListener)
    }

    private fun setRightListener(onClickListener: OnClickListener){
        img_right.setOnClickListener(onClickListener)
    }
}