package com.jelly.thor.base.dialog

import android.view.View
import com.jelly.thor.base.R

/**
 * 加载框  loading未添加
 */
class LoadingProgressDialog : DFBase() {
    companion object {
        fun newInstance(): LoadingProgressDialog {
            return LoadingProgressDialog()
        }
    }

    override fun initView(view: View) {
        //Glide.with(this).asGif().load(R.drawable.loading).into(view.findViewById(R.id.iv))
    }

    override fun setOutClick() {
        isCancelable = false
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun createView(): Int {
        return R.layout.bm_loading_progress_dialog
    }
}
