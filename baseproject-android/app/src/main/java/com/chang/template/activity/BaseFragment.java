package com.chang.template.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chang.template.R;
import com.chang.template.utils.Utility;

import butterknife.ButterKnife;

/**
 * Created by Howard Chang on 2016/11/29
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    protected ProgressDialog progressDialog;

    @LayoutRes
    protected abstract int getContentView();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, v);

        progressDialog = Utility.newProgressDialog(getActivity());

        return v;
    }


    @Override
    public void onDestroy() {

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public void onInternetError() {
        progressDialog.dismiss();
        new MaterialDialog.Builder(getActivity())
                .title(R.string.app_name)
                .content(R.string.g_a_network_error)
                .positiveText(R.string.g_confirm)
                .show();
    }

    @Override
    public void onRequestEnd() {
        progressDialog.dismiss();
    }

    @Override
    public void onRequestError(String msg) {
        progressDialog.dismiss();
        new MaterialDialog.Builder(getActivity())
                .title(R.string.app_name)
                .content(msg)
                .positiveText(R.string.g_confirm)
                .show();
    }

    @Override
    public void onRequestStart() {
        progressDialog.show();
    }

    @Override
    public void onRequestMessage(String msg) {
        new MaterialDialog.Builder(getActivity())
                .content(msg)
                .positiveText(R.string.g_ok)
                .show();
    }

}
