package com.chang.template.activity.rx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.*
import java.util.concurrent.ConcurrentHashMap


/**
 * Created by Howard Chang on 2016/12/7
 */
class RxBus private constructor() {

    private val subjectMapper = ConcurrentHashMap<Any, MutableList<Subject<Any>>>()

    /**
     * 訂閱事件源
     *
     * @param mObservable
     * @param mAction1
     * @return
     */
    fun OnEvent(mObservable: Observable<*>, mAction1: Consumer<Any>): RxBus {
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(mAction1, Consumer { throwable -> throwable.printStackTrace() })
        return getInstance()
    }

    /**
     * 註冊事件源
     *
     * @param tag
     * @return
     */
    fun register(tag: Any): Observable<*> {
        var subjectList: MutableList<Subject<Any>>? = subjectMapper[tag]
        if (null == subjectList) {
            subjectList = ArrayList()
            subjectMapper[tag] = subjectList
        }

        var subject = PublishSubject.create<Any>()

        subjectList.add(subject)

        return subject
    }

    fun unregister(tag: Any) {
        val subjects = subjectMapper[tag]
        if (null != subjects) {
            subjectMapper.remove(tag)
        }
    }

    /**
     * 取消監聽
     *
     * @param tag
     * @param observable
     * @return
     */
    fun unregister(tag: Any,
                   observable: Observable<*>): RxBus {
        if (null == observable) {
            return getInstance()
        }
        val subjects = subjectMapper[tag]
        if (null != subjects) {
            subjects.remove(observable as Subject<*>)
            if (isEmpty(subjects)) {
                subjectMapper.remove(tag)

            }
        }
        return getInstance()
    }

    fun post(content: Any) {
        post(content.javaClass.getName(), content)
    }

    /**
     * 觸發事件
     *
     * @param content
     */
    fun post(tag: Any, content: Any) {

        val subjectList = subjectMapper[tag]

        if (!isEmpty(subjectList)) {
            for (subject in subjectList!!) {
                subject.onNext(content)
            }
        }
    }

    companion object {

        private  var instance = RxBus()

        @Synchronized
        fun getInstance(): RxBus {
            return instance
        }

        fun isEmpty(collection: Collection<Subject<*>>?): Boolean {
            return null == collection || collection.isEmpty()
        }
    }
}
