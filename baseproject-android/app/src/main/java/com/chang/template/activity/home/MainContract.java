package com.chang.template.activity.home;

import android.content.Context;

import com.chang.template.activity.BaseModel;
import com.chang.template.activity.BasePresenter;
import com.chang.template.activity.BaseView;
import com.chang.template.net.model.DemoResponse;

import io.reactivex.Observable;

/**
 * Created by Howard Chang on 2017/4/18
 */
public interface MainContract {

    interface Model extends BaseModel {

        Observable<DemoResponse> performRequest(Context context);

    }

    interface View extends BaseView {

        void initView(Context context);

    }

    abstract class Presenter extends BasePresenter {

        public abstract void performRequest();

    }

}
