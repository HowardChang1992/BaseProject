package ${packageName}

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import ${applicationPackage}.R
import ${applicationPackage}.activity.BaseActivity
import ${applicationPackage}.utils.replaceFragmentInActivity
import java.io.Serializable

class ${activityClass} : BaseActivity() {

    private lateinit var fragment: ${fragmentClass}
    private lateinit var data: ${dataClass}

    override val contentView: Int
        get() = R.layout.${activityView}

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initViews()
    }

    private fun getData() {
        data = intent.getSerializableExtra(DATA) as ${dataClass}
    }

    private fun initViews() {

        fragment = supportFragmentManager.findFragmentById(R.id.content_view) as ${fragmentClass}?
                ?: ${fragmentClass}.newInstance().also {
                    replaceFragmentInActivity(it, R.id.content_view)
                }

        ${presenterClass}(this, fragment ,data)

    }

	
    companion object {

        fun launch(activity: Activity) {
            Intent(activity, ${activityClass}::class.java)
                .apply { putExtra(DATA, ${dataClass}() as Serializable) }
                .apply { activity.startActivity(this) }
        }
        
    }
}
