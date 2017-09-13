package ${packageName};

import android.content.Context;

import ${applicationPackage}.activity.BaseModel;
import ${applicationPackage}.activity.BasePresenter;
import ${applicationPackage}.activity.BaseView;

import io.reactivex.Observable;

public interface ${contractClass} {

    interface Model extends BaseModel {

        Observable<Object> performRequest(Context context);

    }

    interface View extends BaseView {

        void initView(Context context);

    }

    abstract class Presenter extends BasePresenter {

        public abstract void performRequest();

    }

}
