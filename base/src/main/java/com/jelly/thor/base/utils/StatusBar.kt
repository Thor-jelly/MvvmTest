package com.jelly.thor.base.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils


/**
 * 类描述：状态栏设置工具<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2022/3/31 15:48 <br/>
 */
object StatusBar {
    /**
     * 6.0级以上的沉浸式布局
     */
    fun fitSystemBar(activity: Activity) {
        //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
        val window: Window = activity.window
        val decorView: View = window.getDecorView()
        //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN--能够使得我们的页面布局延伸到状态栏之下，但不会隐藏状态栏。也就相当于状态栏是遮盖在布局之上的
        //View.SYSTEM_UI_FLAG_FULLSCREEN -- 能够使得我们的页面布局延伸到状态栏，但是会隐藏状态栏。
        //WindowManager.LayoutParams.FLAG_FULLSCREEN
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.TRANSPARENT
    }

    /**
     * 6.0及以上的状态栏色调
     *
     * @param light true:白底黑字,false:黑底白字
     */
    fun lightStatusBar(activity: Activity, light: Boolean) {
        //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
        val window: Window = activity.window
        val decorView: View = window.decorView
        var visibility: Int = decorView.getSystemUiVisibility()
        visibility = if (light) {
            visibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            visibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        decorView.systemUiVisibility = visibility
    }

    /**
     * 6.0及以上的状态栏色调
     *
     * @param color 颜色
     */
    fun lightStatusBar(activity: Activity, color: Int) {
        //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
        val window: Window = activity.window
        // 设置状态栏底色颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = color

        // 如果亮色，设置状态栏文字为黑色
        if (isLightColor(color)) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

    /**
     * 判断颜色是不是亮色
     *
     * @from https://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
     */
    private fun isLightColor(@ColorInt color: Int): Boolean {
        return ColorUtils.calculateLuminance(color) >= 0.5
    }
}
