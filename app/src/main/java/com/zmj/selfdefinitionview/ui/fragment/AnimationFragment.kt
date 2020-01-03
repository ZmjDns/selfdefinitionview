package com.zmj.selfdefinitionview.ui.fragment

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import com.zmj.selfdefinitionview.R
import com.zmj.selfdefinitionview.practice7.HsvEvaluator
import kotlinx.android.synthetic.main.fragment_animation.*

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/21
 * Description :
 */
class AnimationFragment: Fragment() {
    private val TAG = this.javaClass.name

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
                .setListener(object :Animator.AnimatorListener{
                    override fun onAnimationRepeat(p0: Animator?) {
                        Log.i(TAG,"Animator Repeat...")
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        Log.i(TAG,"Animator End...")
                    }

                    override fun onAnimationCancel(p0: Animator?) {
                        Log.i(TAG,"Animator Cancel...")
                    }

                    override fun onAnimationStart(p0: Animator?) {
                        Log.i(TAG,"Animator Start...")
                    }
                })
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

            animate.addListener(object :Animator.AnimatorListener{
                override fun onAnimationRepeat(p0: Animator?) {
                    Log.i(TAG,"Animator Repeat...")
                }

                override fun onAnimationEnd(p0: Animator?) {
                    Log.i(TAG,"Animator End...")
                }

                override fun onAnimationCancel(p0: Animator?) {
                    Log.i(TAG,"Animator Cancel...")
                }

                override fun onAnimationStart(p0: Animator?) {
                    Log.i(TAG,"Animator Start...")
                }

            })

            animate.start()
        }

        btn_changeColor.setOnClickListener {
//            val animator = ObjectAnimator.ofInt(red_circle,"color",Color.RED,Color.GREEN,Color.RED)
//            animator.setEvaluator(ArgbEvaluator())
            //val animator = ObjectAnimator.ofArgb(red_circle,"color",Color.RED,Color.GREEN)
            //自定义的evaluator
            val animator = ObjectAnimator.ofInt(red_circle,"color",Color.GREEN)
            animator.setEvaluator(HsvEvaluator())
            animator.duration = 4000
            animator.start()
        }

        btn_move.setOnClickListener {
            //val animator = ObjectAnimator.ofObject(move_circle,"position",PointFEvaluator(),PointF(0f,0f),PointF(200f,200f))
            val animator = ObjectAnimator.ofObject(move_circle,"position",android.animation.PointFEvaluator(),PointF(0f,0f),PointF(200f,200f))

            animator.duration = 2000
            animator.start()
        }

    }


}