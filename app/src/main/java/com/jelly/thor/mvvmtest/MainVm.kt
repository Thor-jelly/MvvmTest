package com.jelly.thor.mvvmtest

import android.app.Application
import androidx.databinding.ObservableField
import com.jelly.thor.base.base.BaseVm

/**
 * 创建人：吴冬冬<br/>
 * 创建时间：2022/3/31 16:47 <br/>
 */
class MainVm(application: Application) : BaseVm(application) {
    val textStr = ObservableField<String>("我是谁")
}