package com.chang.template.activity.home;

import com.chang.template.activity.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Howard Chang on 2017/4/18
 */
@Module
public class MainPresenterModule {

    private final BaseActivity mActivity;

    private final MainContract.View mView;

    public MainPresenterModule(BaseActivity activity, MainContract.View view) {
        mActivity = activity;
        mView = view;
    }

    @Provides
    BaseActivity provideBaseActivity() {
        return mActivity;
    }

    @Provides
    MainContract.View provideMainContractView() {
        return mView;
    }

}
