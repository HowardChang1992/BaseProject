package com.chang.template.activity.home;

import android.content.Context;
import android.os.Bundle;

import com.chang.template.R;
import com.chang.template.activity.BaseMVPFragment;
import com.chang.template.activity.BasePresenter;

/**
 * Created by Howard Chang on 2017/4/18
 */
public class MainMVPFragment extends BaseMVPFragment implements MainContract.View {

    MainContract.Presenter mPresenter;

    public static MainMVPFragment newInstance() {
        return new MainMVPFragment();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        super.setPresenter(presenter);
        mPresenter = (MainContract.Presenter) presenter;
    }

    @Override
    public void onBaseActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(Context context) {

    }

}
