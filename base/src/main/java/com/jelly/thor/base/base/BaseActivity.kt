package com.jelly.thor.base.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * 创建人：吴冬冬<br/>
 * 创建时间：2022/3/31 15:35 <br/>
 */
abstract class BaseActivity<VM : BaseVm> : StatusBarActivity() {
    protected lateinit var mViewModel: VM
    abstract fun initView()
    abstract fun initEvent()

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindView()
        createViewModel()
        afterCreateViewModel()
        initView()
        initEvent()
    }

    protected open fun afterCreateViewModel() {

    }

    abstract fun initBindView()

    private fun createViewModel() {
        if (!::mViewModel.isInitialized) {
            val clazz: Class<VM>
            val type = this::class.java.genericSuperclass
            clazz = if (type is ParameterizedType) {
                type.actualTypeArguments[0] as Class<VM>
            } else {
                //如果没有使用泛型置顶vm 则使用默认baseVm
                BaseVm::class.java as Class<VM>
            }
            mViewModel = ViewModelProvider(this).get(clazz)
        }
    }
}