package com.chang.template.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.afollestad.materialdialogs.MaterialDialog
import com.chang.template.R
import com.chang.template.utils.Utility

/**
 * Created by Howard Chang on 2016/11/29
 */
abstract class BaseFragment : Fragment(), BaseView {

    protected lateinit var progressDialog: ProgressDialog

    @get:LayoutRes
    protected abstract val contentView: Int


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(contentView, container, false)
        ButterKnife.bind(this, v)

        progressDialog = Utility.newProgressDialog(activity!!.applicationContext)

        return v
    }

    override fun onDestroy() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
        super.onDestroy()
    }

    override fun onInternetError() {
        progressDialog.dismiss()
        MaterialDialog.Builder(activity!!)
                .title(R.string.app_name)
                .content(R.string.g_a_network_error)
                .positiveText(R.string.g_confirm)
                .show()
    }

    override fun onRequestEnd() {
        progressDialog.dismiss()
    }

    override fun onRequestError(msg: String) {
        progressDialog.dismiss()
        MaterialDialog.Builder(activity!!)
                .title(R.string.app_name)
                .content(msg)
                .positiveText(R.string.g_confirm)
                .show()
    }

    override fun onRequestStart() {
        progressDialog.show()
    }

    override fun onRequestMessage(msg: String) {
        MaterialDialog.Builder(activity!!)
                .content(msg)
                .positiveText(R.string.g_ok)
                .show()
    }

}
