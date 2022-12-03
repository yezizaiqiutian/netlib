package com.gh.netlib.net2

import androidx.lifecycle.LifecycleOwner

/**
 * @author: gh
 * @description:
 * @date: 2022/12/1.
 * @from:
 */
interface IUIView : LifecycleOwner {

    fun showLoading()

    fun dismissLoading()

}