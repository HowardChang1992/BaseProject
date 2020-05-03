package com.chang.template.activity.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.chang.template.R
import com.chang.template.activity.BaseActivity
import com.chang.template.utils.replaceFragmentInActivity
import java.io.Serializable

class MainActivity : BaseActivity() {

    private lateinit var mainFragment: MainFragment
    private lateinit var data: MainData

    override val contentView: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initViews()
    }

    private fun getData() {
        data = intent.getSerializableExtra(DATA) as MainData
    }

    private fun initViews() {

        mainFragment = supportFragmentManager.findFragmentById(R.id.content_view) as MainFragment?
                ?: MainFragment.newInstance().also {
                    replaceFragmentInActivity(it, R.id.content_view)
                }

        MainPresenter(this, mainFragment, data)

    }

    companion object {

        fun launch(activity: Activity) {
            Intent(activity, MainActivity::class.java)
                    .apply { putExtra(DATA, MainData() as Serializable) }
                    .apply { activity.startActivity(this) }
        }

    }

}
