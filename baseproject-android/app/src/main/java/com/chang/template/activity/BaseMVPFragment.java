package com.chang.template.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Howard Chang on 2016/11/29
 */
public abstract class BaseMVPFragment extends BaseFragment implements BaseView {

    private BasePresenter mPresenter;

    public abstract void onBaseActivityCreated();

    public void setPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.onActivityCreated();
            onBaseActivityCreated();
        }
    }

    @Override
    public void onDestroy() {

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }

        super.onDestroy();
    }

}
