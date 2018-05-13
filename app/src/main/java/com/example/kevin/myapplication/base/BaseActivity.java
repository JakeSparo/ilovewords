package com.example.kevin.myapplication.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.kevin.myapplication.R;
import com.example.kevin.myapplication.appManager.AppManager;
import com.example.kevin.myapplication.ui.dialog.DialogControl;
import com.example.kevin.myapplication.util.DialogHelp;

import butterknife.ButterKnife;

/**
 * Created by ted on 2018/5/8.
 */

public abstract class BaseActivity extends AppCompatActivity implements DialogControl, View.OnClickListener, BaseViewInterface {
    protected LayoutInflater mInflater;
    private ProgressDialog waitDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        mInflater = getLayoutInflater();

        // 通过注解绑定此控件
        ButterKnife.bind(this);

        onInitView();
        onInitData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public ProgressDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    @Override
    public ProgressDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public ProgressDialog showWaitDialog(String message) {
        if (waitDialog == null) {
            waitDialog = DialogHelp.getWaitDialog(this, message);
            waitDialog.setCancelable(false);
        }
        if (waitDialog != null) {
            waitDialog.setMessage(message);
            waitDialog.setCancelable(false);
            waitDialog.show();
        }
        return waitDialog;
    }

    @Override
    public void hideWaitDialog() {
        if (waitDialog != null) {
            try {
                waitDialog.dismiss();
                waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // 获取跟布局ID
    protected abstract int getLayoutId();
}
