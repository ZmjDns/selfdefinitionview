package com.zmj.selfdefinitionview.practice7

import android.animation.TypeEvaluator

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/3/14
 * Description :
 */
class PointEvaluator: TypeEvaluator<FloatArray> {

    private var outFloatArray = FloatArray(2)
    override fun evaluate(fraction: Float, startValue: FloatArray?, endValue: FloatArray?): FloatArray {
        outFloatArray[0] = (endValue!![0] - startValue!![0]) * fraction
        outFloatArray[1] = (endValue[1] - startValue[1]) * fraction

        return outFloatArray
    }
}