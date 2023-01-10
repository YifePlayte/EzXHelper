@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.github.kyuubiran.ezxhelper.utils

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.github.kyuubiran.ezxhelper.EzXHelper
import com.github.kyuubiran.ezxhelper.interfaces.IXposedScope

object AndroidUtils {
    val mainHandler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    val runtimeProcess: Runtime by lazy {
        Runtime.getRuntime()
    }

    /**
     * 将runnable放到主线程执行
     */
    fun runOnMainThread(runnable: Runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run()
        } else {
            mainHandler.post(runnable)
        }
    }

    /**
     * 扩展函数 显示一个Toast
     * @param msg Toast显示的消息
     * @param length Toast显示的时长
     */
    fun Context.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) = runOnMainThread {
        Toast.makeText(this, msg, length).show()
    }

    /**
     * 扩展函数 显示一个Toast
     * @param msg Toast显示的消息
     * @param args 格式化的参数
     * @param length Toast显示的时长
     */
    fun Context.showToast(msg: String, vararg args: Any?, length: Int = Toast.LENGTH_SHORT) = runOnMainThread {
        Toast.makeText(this, msg.format(args), length).show()
    }

    /**
     * 扩展函数 将模块的资源路径添加到Context.resources内 允许直接以R.xx.xxx获取资源
     * @see EzXHelper.addModuleAssetPath
     */
    fun Context.addModuleAssetPath() {
        EzXHelper.addModuleAssetPath(this)
    }

    /**
     * 扩展函数 将模块的资源路径添加到resources内 允许直接以R.xx.xxx获取资源
     * @see EzXHelper.addModuleAssetPath
     */
    context(IXposedScope)
    fun Resources.addModuleAssetPath() {
        EzXHelper.addModuleAssetPath(this)
    }
}