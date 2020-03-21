package com.zmj.selfdefinitionview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zmj.selfdefinitionview.R
import com.zmj.selfdefinitionview.practice2_3.MyFlowLayout
import kotlinx.android.synthetic.main.sample_adjust_panel.*

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/16
 * Description :
 */
class AdjustLayoutFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sample_adjust_panel,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addView.setOnClickListener {
//            val tv = TextView(context)
//            tv.text = "I am added View"
//
//            tv.layoutParams = ViewGroup.MarginLayoutParams(150,50)
//            flowLayout.addView(tv)
            flowLayout.setTag("hahhahahahahhaha")
        }

        flowLayout.setTagClickListener(object : MyFlowLayout.TagClick{
            override fun onTagClick(tagView: View) {
                val tagStr = (tagView as TextView).text.toString()

                Toast.makeText(context,tagStr,Toast.LENGTH_SHORT).show()
            }
        })
    }

}