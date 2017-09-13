package ${packageName};

import ${applicationPackage}.activity.BaseActivity;
import ${applicationPackage}.activity.BaseMVPFragment;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ${presenterClass} extends ${contractClass}.Presenter {

    private ${contractClass}.View mView;
    private ${contractClass}.Model mModel;

    @Inject
    ${presenterClass}(BaseActivity activity
            , ${contractClass}.View view) {
        context = activity;
        mView = view;
        mModel = new ${modelClass}();
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
