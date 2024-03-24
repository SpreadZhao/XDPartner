package com.spread.xdplib.adapter.utils

import android.graphics.Color
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

object StatusBarUtil {
    fun setImmersionStatusBa(activity: AppCompatActivity){
        // 设置状态栏透明
        activity.window.statusBarColor = Color.TRANSPARENT
        // 开启UI布局填充进状态栏
        activity.enableEdgeToEdge()
        val controller = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        controller.apply {
            // 设置状态栏字体为深色
            isAppearanceLightStatusBars = true
//            hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
    fun setImmersionStatusBar(activity: AppCompatActivity){
        // 设置状态栏透明
        activity.window.statusBarColor = Color.TRANSPARENT
        // 开启UI布局填充进状态栏
        activity.enableEdgeToEdge()
        val controller = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        controller.apply {
            // 设置状态栏字体为深色
            isAppearanceLightStatusBars = true
//            hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}