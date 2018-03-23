package ${packageName};

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ${applicationPackage}.R;
import ${applicationPackage}.activity.BaseActivity;

import javax.inject.Inject;


public class ${activityClass} extends BaseActivity {

    @Inject
    ${presenterClass} mPresenter;

    ${fragmentClass} fragment;

    @Override
    protected int getContentView() {
        return R.layout.${activityView};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {

        if (fragment == null) {
            fragment = ${fragmentClass}.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_view, fragment)
                    .commit();
        }


        Dagger${componentClass}.builder()
                .${presenterMethodClass}(new ${presenterModuleClass}(this, fragment))
                .build()
                .inject(this);

    }
	
    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ${activityClass}.class);
        activity.startActivity(intent);
    }
}
