package com.zmj.selfdefinitionview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zmj.selfdefinitionview.ui.adapter.PagerAdapter
import com.zmj.selfdefinitionview.ui.fragment.PaintClipFragment
import com.zmj.selfdefinitionview.ui.fragment.PaintTextFragment
import com.zmj.selfdefinitionview.ui.fragment.ViewGroupFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fragmentList = ArrayList<Fragment>()
    val titleList = ArrayList<String>()
    lateinit var pagerAdapter:PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentList.add(PaintTextFragment())
        fragmentList.add(PaintClipFragment())
        fragmentList.add(ViewGroupFragment())

        titleList.add("PaintText")
        titleList.add("PaintClip")
        titleList.add("viewGroup")


        pagerAdapter = PagerAdapter(supportFragmentManager,0,this,fragmentList,titleList)

        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
    }
}
