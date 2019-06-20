package com.chang.template.activity

import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.chang.template.R
import com.chang.template.utils.Utility
import com.google.gson.Gson

/**
 * Created by Howard Chang on 2016/11/29
 */
abstract class BaseActivity : AppCompatActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @get:LayoutRes
    protected abstract val contentView: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)

        displayHomeButton(true)

        initView()
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val color = ContextCompat.getColor(this, R.color.colorAccent)
            val contentView = findViewById<View>(android.R.id.content) as ViewGroup
            val statusBarView = View(this)
            val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    Utility.getStatusBarHeight(this))
            statusBarView.setBackgroundColor(color)
            contentView?.addView(statusBarView, lp)
        }
    }

    fun displayHomeButton(isDisplay: Boolean) {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(isDisplay)
            supportActionBar!!.setHomeButtonEnabled(isDisplay)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val upIntent = NavUtils.getParentActivityIntent(this)

                if (upIntent != null && NavUtils.shouldUpRecreateTask(this, upIntent) || isTaskRoot) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent!!)
                            // Navigate up to the closest parent
                            .startActivities()
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    onBackPressed()
                }

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun getData(key: String, type: Any): Any? {
        if (intent != null) {
            finish()
        }

        return Gson().fromJson(key, type::class.java)
    }

}
