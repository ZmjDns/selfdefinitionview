package com.zmj.selfdefinitionview.ui.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import com.zmj.selfdefinitionview.R
import kotlinx.android.synthetic.main.fragment_animation.*

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/21
 * Description :
 */
class AnimationFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animation,null,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAnmationTools()
    }

    private fun initAnmationTools() {
        btn_transX.setOnClickListener {
            batman_1.animate()
                .translationX(300f)
//            batman_1.animate()
//                .translationY(300f)
//                .translationX(-300f)
//                .translationY(-300f)

        }

        btn_circle.setOnClickListener {
            val objectAnimator = ObjectAnimator.ofFloat(sports,"progress",100f)
            objectAnimator.setDuration(2000).start()
        }

        btn_interpolator.setOnClickListener {
            batman_1.animate()
                .translationX(500f)
                .setInterpolator(LinearInterpolator())//线性速度器  即匀速
        }

        btn_qianYaoHuiTan.setOnClickListener {
            val animate = ObjectAnimator.ofFloat(batman_1,"translationX",500f)
            animate.interpolator = AnticipateOvershootInterpolator()  //前摇后弹
            animate.start()
        }

    }


}