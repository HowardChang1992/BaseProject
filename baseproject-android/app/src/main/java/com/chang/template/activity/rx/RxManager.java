package com.chang.template.activity.rx;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by Howard Chang on 2016/12/7
 */
public class RxManager {

    public RxBus mRxBus = RxBus.getInstance();

    private Map<Objects, Observable<?>> mObservables = new HashMap<>(); //管理被觀察者
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable(); //管理觀察者

    public void on(Objects eventName, Consumer<Object> consumer) {
        Observable<?> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName, mObservable);
        mCompositeDisposable
                .add(mObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer, new Consumer<Throwable>() {

                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }));
    }

    public void add(Disposable m) {
        mCompositeDisposable.add(m);
    }

    public void clear() {
        mCompositeDisposable.dispose(); //取消訂閱
        for (Map.Entry<Objects, Observable<?>> entry : mObservables.entrySet()) {
            mRxBus.unregister(entry.getKey(), entry.getValue()); //移除觀察
        }
    }

    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }
}
