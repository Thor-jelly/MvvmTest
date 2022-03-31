package com.jelly.thor.base.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jelly.thor.base.dialog.LoadingProgressDialog

/**
 * 使用DataBinding 基本activity
 * 创建人：吴冬冬<br/>
 * 创建时间：2021/5/24 16:20 <br/>
 */
abstract class BaseVDBActivity<VM : BaseVm, VDB : ViewDataBinding> : BaseActivity<VM>() {
    private var mProgressDialog: LoadingProgressDialog? = null

    protected lateinit var mBinding: VDB

    override fun initBindView() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.lifecycleOwner = this
    }

    private fun dismissProgressDialog() {
        mProgressDialog?.apply {
            if (!isAdded || !isVisible) {
                return@apply
            }
            dismiss()
        }
    }

    private fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = LoadingProgressDialog.newInstance()
        }
        if (mProgressDialog!!.isAdded) {
            supportFragmentManager.beginTransaction().remove(mProgressDialog!!).commitNow()
        }
        mProgressDialog?.showNow(supportFragmentManager, "LoadingProgressDialog")
    }

    override fun afterCreateViewModel() {
        if (getVariableId() > 0) {
            mBinding.setVariable(getVariableId(), mViewModel)
        }

        mViewModel.loadingEvent.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                dismissProgressDialog()
            }
        }
    }

    abstract fun getVariableId(): Int
}