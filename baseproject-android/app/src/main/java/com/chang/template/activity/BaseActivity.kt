package com.chang.template.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import com.chang.template.R
import com.chang.template.utils.Utility
import kotlinx.android.synthetic.main.view_toolbar.*

/**
 * Created by Howard Chang on 2016/11/29
 */
abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val contentView: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)

        setSupportActionBar(toolbar)

        displayHomeButton(true)

        initView()
    }

    private fun initView() {
        val color = ContextCompat.getColor(this, R.color.colorAccent)
        val contentView = findViewById<View>(android.R.id.content) as ViewGroup
        val statusBarView = View(this)
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                Utility.getStatusBarHeight(this))
        statusBarView.setBackgroundColor(color)
        contentView.addView(statusBarView, lp)
    }

    fun displayHomeButton(isDisplay: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isDisplay)
        supportActionBar?.setHomeButtonEnabled(isDisplay)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.getParentActivityIntent(this)?.apply {
                    if (NavUtils.shouldUpRecreateTask(this@BaseActivity, this) || isTaskRoot) {
                        // This activity is NOT part of this app's task, so create a new task
                        // when navigating up, with a synthesized back stack.
                        TaskStackBuilder.create(this@BaseActivity)
                                // Add all of this activity's parents to the back stack
                                .addNextIntentWithParentStack(this)
                                // Navigate up to the closest parent
                                .startActivities()
                    } else {
                        // This activity is part of this app's task, so simply
                        // navigate up to the logical parent activity.
                        onBackPressed()
                    }
                }

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        val DATA = "data"
    }

}
