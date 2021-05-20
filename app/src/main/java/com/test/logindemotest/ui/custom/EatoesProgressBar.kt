package com.test.logindemotest.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import com.test.logindemotest.ui.gone
import com.test.logindemotest.ui.visible

class EatoesProgressBar : ProgressBar, LoadingView {
    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        isIndeterminate = true

    }

    override fun onStartLoading() {
        visible()
    }


    override fun onStopLoading(success: Boolean, message: String) {
        gone()
    }

    override fun onInit() {
        visible()
    }
}