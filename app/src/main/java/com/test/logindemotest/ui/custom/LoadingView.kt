package com.test.logindemotest.ui.custom

interface LoadingView {
    fun onStartLoading()
    fun onStopLoading(success: Boolean, message: String = "")
    fun onInit()
}