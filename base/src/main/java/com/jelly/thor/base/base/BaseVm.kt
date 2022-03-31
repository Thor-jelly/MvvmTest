package com.jelly.thor.base.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jelly.thor.base.utils.ekt.asLiveData

/**
 * 创建人：吴冬冬<br/>
 * 创建时间：2022/3/31 15:36 <br/>
 */
abstract class BaseVm(application: Application) : AndroidViewModel(application) {
    private val _loadingEvent = MutableLiveData(false)
    val loadingEvent = _loadingEvent.asLiveData()

    //展示loading
    protected fun showLoading() {
        _loadingEvent.value = true
    }

    //隐藏loading
    protected fun hintLoading() {
        _loadingEvent.value = false
    }
}