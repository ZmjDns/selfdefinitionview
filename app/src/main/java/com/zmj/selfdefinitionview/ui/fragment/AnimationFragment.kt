package com.zmj.selfdefinitionview.ui.fragment

import android.animation.*
import android.graphics.Color
import android.graphics.PointF
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.zmj.selfdefinitionview.R
import com.zmj.selfdefinitionview.practice6.MyPathInterpolator
import com.zmj.selfdefinitionview.practice7.HsvEvaluator
import com.zmj.selfdefinitionview.practice7.PointEvaluator
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

        btn_complexAnim.setOnClickListener {
            //vc_cover.animate().setDuration(2000).scaleX(0f).scaleY(0f).alpha(0f)
            /*vc_cover.animate()
                .setDuration(4000)
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)*/

            //PropertyAnimator实现多个动画
            /*iv_origin.animate()
                .setDuration(2000)
                .scaleX(0f)
                .scaleY(0f)
                .alpha(0f)*/

            //ObjectAnimator实现多个动画
            /*val holder1 = PropertyValuesHolder.ofFloat("scaleX",0f)
            val holder2 = PropertyValuesHolder.ofFloat("scaleY",0f)
            val holder3 = PropertyValuesHolder.ofFloat("alpha",0f)

            val animator = ObjectAnimator.ofPropertyValuesHolder(iv_origin,holder1,holder2,holder3)
            animator.duration = 2000
            animator.start()*/

            iv_origin.scaleX = 0f
            iv_origin.scaleY = 0f
            iv_origin.alpha = 0f

            iv_origin.animate().setDuration(2000).scaleXBy(1f).scaleYBy(1f).alpha(1f)
        }

        anims_link.setOnClickListener {
            iv_origin.scaleX = 0f
            iv_origin.scaleY = 0f
            iv_origin.alpha = 0f

            val holderX = PropertyValuesHolder.ofFloat("scaleX",1f)
            val holderY = PropertyValuesHolder.ofFloat("scaleY",1f)
            val holderA = PropertyValuesHolder.ofFloat("alpha",1f)

            val animatorS = ObjectAnimator.ofPropertyValuesHolder(iv_origin,holderX,holderY,holderA)

            val animator1 = ObjectAnimator.ofFloat(iv_origin,"translationX",100f)
            animator1.interpolator = LinearInterpolator()
            val animator2 = ObjectAnimator.ofFloat(iv_origin,"translationX",100f)
            animator2.interpolator = LinearInterpolator()

            val animatorSet = AnimatorSet()
            //两个动画依次执行
            animatorSet.playSequentially(animatorS,animator1,animator2)
            animatorSet.setDuration(4000).start()
        }

        //设置关键帧，控制动画完成度
        btn_progressControl.setOnClickListener {
            //在0处开始
            val keyFrame = Keyframe.ofFloat(0f,0f)
            //时间经过50%的时候，动画完成100%
            val keyframe2 = Keyframe.ofFloat(0.5f,100f)
            //时间过100%的时候，动画完成度倒退20%
            val keyFrame3 = Keyframe.ofFloat(1f,80f)

            val holder = PropertyValuesHolder.ofKeyframe("progress",keyFrame,keyframe2,keyFrame3)

            val animator = ObjectAnimator.ofPropertyValuesHolder(cpb_bar,holder)
            animator.setDuration(2000).start()
        }

        btn_drawAni.setOnClickListener {

            ai_animationDrawable.apply {
                setBackgroundResource(R.drawable.animation_drawable)
                val animationDrawable = background as AnimationDrawable

                animationDrawable.start()
            }
        }

        btn_vectorAni.setOnClickListener {

            //iv_vectorAnimator.setImageResource(R.drawable.animatorvectordrawable)

            //(iv_vectorAnimator.drawable as AnimatedVectorDrawable).start()
            (iv_vectorAnimator.getDrawable() as AnimatedVectorDrawableCompat).start()
        }

        btn_preRing.setOnClickListener {
            //ringPrePropertyAnimator()
            //ringPreObjAnimator()
            setKeyframe()
        }

        btn_movePoint.setOnClickListener {

            //圆点移动动画
            val objectAnimator = ObjectAnimator.ofObject(point,"position",PointEvaluator(),floatArrayOf(10f,10f),floatArrayOf(200f,200f))
            objectAnimator.duration = 1000
            //原点水平左移
            val objectAnimator2 = ObjectAnimator.ofObject(point,"position",PointEvaluator(), floatArrayOf(200f,200f),floatArrayOf(10f,200f))
            objectAnimator2.duration = 1000
            //回到原点
            val objectAnimator3 = ObjectAnimator.ofObject(point,"positon",PointEvaluator(),floatArrayOf(0f,200f),floatArrayOf(0f,0f))
            objectAnimator3.duration = 1000
            val animatorSet = AnimatorSet()//.play(objectAnimator).after(objectAnimator2).after(objectAnimator3)
            //animatorSet.play(objectAnimator).before(objectAnimator2)//.before(objectAnimator3)
            //objectAnimator.start()
            animatorSet.playSequentially(objectAnimator,objectAnimator2)
            animatorSet.start()
        }
    }

    //PropertyAnimator实现动画
    private fun ringPrePropertyAnimator(){
        prv.animate()
            .setDuration(3000)
            .scaleX(0.5f)
            .scaleY(0.5f)
            .setListener(object :Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {
                    Log.i("ringPrePropertyAnimator","AnimationRepeat........")
                }

                override fun onAnimationEnd(animation: Animator?) {
                    Log.i("ringPrePropertyAnimator","AnimationEnd........")
                }

                override fun onAnimationCancel(animation: Animator?) {
                    Log.i("ringPrePropertyAnimator","AnimationCancel........")
                }

                override fun onAnimationStart(animation: Animator?) {
                    Log.i("ringPrePropertyAnimator","AnimationStart........")
                }

            }).start()
    }


    //ObjectAnimator实现动画
    private fun ringPreObjAnimator(){
        val objectAnimator = ObjectAnimator.ofFloat(prv,"progress",0f,270f)
        objectAnimator.duration = 2000
        //带施法前摇和结束回退的速度器
        objectAnimator.interpolator = AnticipateOvershootInterpolator()
        //pathInterpolator路径速度器
        //objectAnimator.interpolator = MyPathInterpolator().getLinearInterpolator()//.getPathInterpolator()

        //设置动画重复执行的模式  RESTART：重启模式      REVERSE：来回反复模式
        //objectAnimator.repeatMode = ValueAnimator.RESTART//.REVERSE//.RESTART
        //objectAnimator.repeatCount = 3
        objectAnimator.addListener(object :Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
                Log.i("ringPreObjAnimator","AnimationRepeat........")
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.i("ringPreObjAnimator","AnimationEnd........")
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.i("ringPreObjAnimator","AnimationCancel........")
            }

            override fun onAnimationStart(animation: Animator?) {
                Log.i("ringPreObjAnimator","AnimationStart........")
            }
        })

        objectAnimator.addUpdateListener {
            val fraction =  it.animatedFraction
            val progress = it.getAnimatedValue("progress")

            Log.i("ringPreObjAnimator","animatedFraction：$fraction   progress:$progress")
        }

        objectAnimator.start()
    }

    //设置关键帧
    fun setKeyframe(){
        //在0%处开始
        val keyframe1 = Keyframe.ofFloat(0f,0f)
        //时间经过50%的时候，动画经过100%
        val keyframe2 = Keyframe.ofFloat(0.5f,360f)//此处的values其实圆要转过的角度，应该在自定义view中进行转化的
        //时间经过100%的时候在回弹20%
        val keyframe3 = Keyframe.ofFloat(1f,280f)

        val propertyValuesHolder =  PropertyValuesHolder.ofKeyframe("progress",keyframe1,keyframe2,keyframe3)

        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(prv,propertyValuesHolder)
        objectAnimator.duration = 2000
        objectAnimator.start()
    }

}