package com.jelly.thor.base.utils

import com.jelly.thor.base.base.StatusBarActivity
import org.jetbrains.annotations.NotNull
import kotlin.system.exitProcess

/**
 * 类描述： Activity管理类<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2022/3/31 15:35 <br/>
 */
object ActivityUtils {
    /**
     * 所有打开的Activity
     */
    private val mActivityList = mutableListOf<StatusBarActivity>()

    /**
     * 添加统一管理
     */
    @Synchronized
    fun attach(@NotNull activity: StatusBarActivity) {
        mActivityList.indexOf(activity).takeIf {
            it == -1
        }?.let {
            mActivityList.add(activity)
        }
    }

    /**
     * 移除解绑 - 防止内存泄漏
     */
    @Synchronized
    fun detach(@NotNull activity: StatusBarActivity) {
        mActivityList.indexOf(activity).takeIf {
            it > -1
        }?.let {
            mActivityList.remove(activity)
        }
    }

    /**
     * 根据Activity的类名关闭 Activity
     */
    fun finish(activityClass: Class<out StatusBarActivity>) {
        val iterator = mActivityList.iterator()
        for (activity in iterator) {
            //如果遍历删除有问题，可以参考EventBus取消注册的方法
            activity.takeIf {
                //getCanonicalName 可以获取对应包名下的activity 这样可以防止关闭不同包下相同名称的activity
                it::class.java.canonicalName == activityClass.canonicalName
            }?.let {
                it.finish()
                iterator.remove()
            }
        }
    }

    /**
     * 根据Activity的类名关闭 Activity
     */
    fun finishOther(noFinishActivityClass: Class<out StatusBarActivity>) {
        val iterator = mActivityList.iterator()
        for (activity in iterator) {
            //如果遍历删除有问题，可以参考EventBus取消注册的方法
            activity.takeIf {
                //getCanonicalName 可以获取对应包名下的activity 这样可以防止关闭不同包下相同名称的activity
                it::class.java.canonicalName != noFinishActivityClass.canonicalName
            }?.let {
                it.finish()
                iterator.remove()
            }
        }
    }

    /**
     * 退出整个应用
     */
    fun finishAllActivity() {
        for (activity in mActivityList) {
            activity.finish()
        }
        mActivityList.clear()
        // 杀死该应用进程
        //android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(0);
    }

    /**
     * 退出整个应用
     */
    fun exit() {
        for (activity in mActivityList) {
            activity.finish()
        }
        mActivityList.clear()
        // 杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
        exitProcess(0);
    }

    /**
     * 获取当前的Activity（最前面）
     */
    fun getCurrentActivity(): StatusBarActivity? {
        return if (mActivityList.isEmpty()) {
            null
        } else mActivityList[mActivityList.lastIndex]
    }

}