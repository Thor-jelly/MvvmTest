package com.jelly.thor.base.utils.ekt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * 创建人：吴冬冬<br/>
 * 创建时间：2022/3/31 16:06 <br/>
 */
fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> {
    return this
}