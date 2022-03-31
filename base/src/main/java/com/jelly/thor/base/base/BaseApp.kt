package com.jelly.thor.base.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.jelly.thor.base.utils.ActivityUtils

/**
 * 创建人：吴冬冬<br/>
 * 创建时间：2022/3/31 15:31 <br/>
 */
class BaseApp : Application() {
    companion object {
        lateinit var context: Application
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initActivityUtils()
    }

    //初始化activity管理类
    private fun initActivityUtils() {
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity !is StatusBarActivity) {
                    return
                }
                ActivityUtils.attach(activity)
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (activity !is StatusBarActivity) {
                    return
                }
                ActivityUtils.detach(activity)
            }
        })
    }
}