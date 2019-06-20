package com.chang.template.activity.home

import android.os.Bundle

import com.chang.template.R
import com.chang.template.activity.BaseActivity
import com.chang.template.utils.replaceFragmentInActivity
import com.google.gson.Gson

class MainActivity : BaseActivity() {

    private lateinit var mainFragment: MainFragment

    override val contentView: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {

        mainFragment = supportFragmentManager.findFragmentById(R.id.content_view) as MainFragment?
                ?: MainFragment.newInstance().also {
                    replaceFragmentInActivity(it, R.id.content_view)
                }

        MainPresenter(this, mainFragment)
    }

}
