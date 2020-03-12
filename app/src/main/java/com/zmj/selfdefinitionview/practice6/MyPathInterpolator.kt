package com.zmj.selfdefinitionview.practice6

import android.content.Intent
import android.graphics.Path
import android.os.Build
import android.view.animation.BaseInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator
import androidx.annotation.RequiresApi


/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/12/26
 * Description :自定义动画完成度 / 时间完成度曲线
 */
class MyPathInterpolator{


    fun animatePath1():Path{

        val path1 = Path()

        //先以[动画完成度：时间完成度 = 1：1]的速度匀速运行25%
        path1.lineTo(0.25f,0.25f)
        //然后瞬间跳跃到150%的动画完成度
        path1.moveTo(0.25f,1.5f)
        //再匀速倒车回到目标点
        path1.lineTo(1f,1f)

        return path1
    }

    fun linearPath(): Path{
        val path = Path()
        //匀速
        path.lineTo(1f,1f)

        return path
    }

    //获取路径速度器
    fun getPathInterpolator(): PathInterpolator{
        //通过自定义路线，实例路径加速器之后既可以得到任意模型的速度器
        return PathInterpolator(animatePath1())
    }
    //匀速
    fun getLinearInterpolator(): PathInterpolator{
        return PathInterpolator(linearPath())
    }
}
