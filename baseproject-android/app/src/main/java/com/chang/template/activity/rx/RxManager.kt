package com.chang.template.activity.rx

import java.util.HashMap
import java.util.Objects

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer


/**
 * Created by Howard Chang on 2016/12/7
 */
class RxManager {

    var mRxBus = RxBus.getInstance()

    private val mObservables = HashMap<Objects, Observable<*>>() //管理被觀察者
    private val mCompositeDisposable = CompositeDisposable() //管理觀察者

    fun on(eventName: Objects, consumer: Consumer<Any>) {
        val mObservable = mRxBus.register(eventName)
        mObservables[eventName] = mObservable
        mCompositeDisposable
                .add(mObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer, Consumer { throwable -> throwable.printStackTrace() }))
    }

    fun add(m: Disposable) {
        mCompositeDisposable.add(m)
    }

    fun clear() {
        mCompositeDisposable.dispose() //取消訂閱
        for ((key, value) in mObservables) {
            mRxBus.unregister(key, value) //移除觀察
        }
    }

    fun post(tag: Any, content: Any) {
        mRxBus.post(tag, content)
    }
}
