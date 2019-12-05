package com.zmj.selfdefinitionview.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/4
 * Description :
 */
class PagerAdapter : FragmentPagerAdapter {
    private var context: Context
    private var fragmentList:List<Fragment>
    private var titleList:List<String>

    constructor(fm: FragmentManager, behavior: Int,context: Context,fragmentList: List<Fragment>,titleList:List<String>):super(fm, behavior){
        this.context = context
        this.fragmentList = fragmentList
        this.titleList = titleList
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList.get(position)
    }
}