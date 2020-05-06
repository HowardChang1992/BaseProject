package com.chang.template.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T : Any> LiveData<T>.observeWith(
    lifecycleOwner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit) {
    observe(
        lifecycleOwner,
        Observer {
            it ?: return@Observer
            onChanged.invoke(it)
        }
    )
}

inline fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
    removeObserver(observer)
    observe(owner, observer)
}