package ${packageName};

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ${applicationPackage}.R;
import ${applicationPackage}.activity.BaseActivity;
import ${applicationPackage}.utils.replaceFragmentInActivity

class ${activityClass} : BaseActivity() {

    private lateinit var fragment: ${fragmentClass}

    override val contentView: Int
        get() = R.layout.${activityView}

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {

        fragment = supportFragmentManager.findFragmentById(R.id.content_view) as ${fragmentClass}?
                ?: ${fragmentClass}.newInstance().also {
                    replaceFragmentInActivity(it, R.id.content_view)
                }

        ${presenterClass}(this, fragment)
    }

	
    companion object {

        fun launch(activity: Activity) {
            var intent = Intent(activity, TestActivity::class.java)
            activity.startActivity(intent)
        }
        
    }
}
