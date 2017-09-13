package ${packageName};

import ${applicationPackage}.activity.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ${presenterModuleClass} {

    private final BaseActivity mActivity;

    private final ${contractClass}.View mView;

    public ${presenterModuleClass}(BaseActivity activity, ${contractClass}.View view) {
        mActivity = activity;
        mView = view;
    }

    @Provides
    BaseActivity provideBaseActivity() {
        return mActivity;
    }

    @Provides
    ${contractClass}.View provide${contractClass}View() {
        return mView;
    }

}
