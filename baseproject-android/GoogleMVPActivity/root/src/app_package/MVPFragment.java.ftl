package ${packageName};

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ${applicationPackage}.R;
import ${applicationPackage}.activity.BaseMVPFragment;
import ${applicationPackage}.activity.BasePresenter;


public class ${fragmentClass} extends BaseMVPFragment implements ${contractClass}.View {


    ${contractClass}.Presenter mPresenter;

    public static ${fragmentClass} newInstance() {
        return new ${fragmentClass}();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
	super.setPresenter(presenter);
        mPresenter = (${contractClass}.Presenter) presenter;
    }

    @Override
    protected int getContentView() {
        return R.layout.${fragmentView};
    }

    @Override
    public void onBaseActivityCreated() {

    }

    @Override
    public void initView(Context context) {

    }

}