package com.jelly.thor.base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jelly.thor.base.utils.StatusBar

/**
 * 类描述：状态栏控制activity<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2022/3/31 15:45 <br/>
 */
open class StatusBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //启用沉浸式布局，白底黑字
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
    }
}