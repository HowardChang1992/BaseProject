package com.chang.template.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.chang.template.R
import com.chang.template.activity.home.MainFragment
import com.chang.template.utils.Utility

/**
 * Created by Howard Chang on 2016/11/29
 */
abstract class BaseFragment : Fragment(), BaseView {

    protected var progressDialog: ProgressDialog? = null

    @get:LayoutRes
    protected abstract val contentView: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(contentView, container, false)

        activity?.apply {
            progressDialog = Utility.newProgressDialog(this.applicationContext)
        }

        return v
    }

    override fun onDestroy() {
        progressDialog?.apply {
            if (this.isShowing) {
                this.dismiss()
            }
        }

        super.onDestroy()
    }

    override fun onInternetError() {
        progressDialog?.dismiss()
        activity?.apply {
            MaterialDialog(this).show {
                title(R.string.app_name)
                message(R.string.g_a_network_error)
                positiveButton(R.string.g_confirm)
            }
        }
    }

    override fun onRequestEnd() {
        progressDialog?.dismiss()
    }

    override fun onRequestError(msg: String) {
        progressDialog?.dismiss()
        activity?.apply {
            MaterialDialog(this).show {
                title(R.string.app_name)
                message(text = msg)
                positiveButton(R.string.g_confirm)
            }
        }
    }

    override fun onRequestStart() {
        progressDialog?.show()
    }

    override fun onRequestMessage(msg: String) {
        activity?.apply {
            MaterialDialog(this).show {
                title(R.string.app_name)
                message(text = msg)
                positiveButton(R.string.g_confirm)
            }
        }
    }

    companion object {
        const val DATA = "fragment_data"
    }
}
