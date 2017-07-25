package com.chang.template.activity.home;

import android.os.Bundle;

import com.chang.template.R;
import com.chang.template.activity.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    MainPresenter mPresenter;

    MainMVPFragment fragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {

        fragment = (MainMVPFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_view);

        if (fragment == null) {
            fragment = MainMVPFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_view, fragment)
                    .commit();
        }

        DaggerMainComponent.builder()
                .mainPresenterModule(new MainPresenterModule(this, fragment))
                .build()
                .inject(this);

    }

}
