package com.zmj.selfdefinitionview.practice7

import android.animation.TypeEvaluator
import android.graphics.PointF

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/30
 * Description :
 */
class PointFEvaluator: TypeEvaluator<PointF> {

    private var pointF = PointF()
    override fun evaluate(fraction : Float, startValue: PointF?, endValue: PointF?): PointF {
        val x = startValue!!.x + (fraction * (endValue!!.x - startValue.x))
        val y = startValue.y + (fraction * (endValue.y - startValue.y))

        pointF.set(x,y)

        return pointF
    }


}