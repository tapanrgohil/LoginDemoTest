package com.test.logindemotest.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.test.logindemotest.R
import com.test.logindemotest.data.core.Resource
import com.test.logindemotest.data.core.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun <T> Flow<T>.launchInBackGround(
    viewModel: ViewModel,
    mutableLiveData: MutableLiveData<T>? = null
): Job {
    return if (mutableLiveData != null) {
        return flowOn(Dispatchers.IO)
            .onEach {
                mutableLiveData.postValue(it)
            }
            .launchIn(viewModel.viewModelScope)
    } else
        return flowOn(Dispatchers.IO)
            .launchIn(viewModel.viewModelScope)
}


fun <R> LifecycleOwner.handleResponse(
    liveData: LiveData<Resource<R>>,
    progressView: View? = null,
    @UiThread
    process: (R) -> Unit,
) {
    observe(liveData) {
        it?.apply {
            when (this.status) {
                Status.SUCCESS -> {
                    data?.let { it1 -> process.invoke(it1) }
                    progressView?.gone()
                }
                Status.ERROR -> {
                    throwable?.let { exception ->
                        onError(this@handleResponse, this)
                    }
                    progressView?.gone()
                }
                Status.LOADING -> {
                    progressView?.visible()
                }
            }
        }
    }
}

fun onError(resource: LifecycleOwner, exception: Resource<*>) {
    val context: Context? = when (resource) {
        is Fragment -> {
            resource.activity
        }
        is Activity -> {
            resource
        }
        else -> {
            exception.throwable?.printStackTrace()
            null
        }
    }

    context?.showAlert(
        exception.message ?: exception.throwable?.localizedMessage ?: "Something went wrong"
    )
}

fun Context.showAlert(message: String, title: String? = null, callback: (() -> Unit)? = null) {
    val builder = AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            callback?.invoke()
        })
        .setCancelable(false)

    if (title != null) {
        builder.setTitle(title)
    }

    builder.show()

}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, androidx.lifecycle.Observer(body))
