package com.chang.template.activity.home;

import com.chang.template.activity.BaseActivity;
import com.chang.template.activity.BaseMVPFragment;
import com.chang.template.net.model.DemoResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Howard Chang on 2017/4/18
 */
public class MainPresenter extends MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    @Inject
    MainPresenter(BaseActivity activity
            , MainContract.View view) {
        context = activity;
        mView = view;
        mModel = new MainModel();
    }

    @Inject
    void setupVM() {
        setView((BaseMVPFragment) mView, mModel);
        ((BaseMVPFragment) mView).setPresenter(this);
    }

    @Override
    public void onActivityCreated() {
        mView.initView(context);
    }

    @Override
    public void performRequest() {
        mRxManager.add(mModel.performRequest(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(
                        new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                mView.onRequestStart();
                            }
                        })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.onRequestEnd();
                    }
                })
                .subscribe(
                        new Consumer<DemoResponse>() {
                            @Override
                            public void accept(DemoResponse response) throws Exception {

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.onInternetError();
                            }
                        }));
    }
}
