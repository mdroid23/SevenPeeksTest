package com.sevenpeakssoftware.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.sevenpeakssoftware.mahesh.BuildConfig;
import com.sevenpeakssoftware.mahesh.R;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!BuildConfig.DEBUG)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        mProgressDialog = new ProgressDialog(this);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo() == null) {
            return;
        }

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }

    public void showProgressDialog(String message) {
        if (isFinishing()) {
            return;
        }
        closeKeyBoard();
        mProgressDialog.show();
        mProgressDialog.setMessage(message);
    }


    public void showProgressDialog() {
        showProgressDialog("");
    }


    public void showProgressDialog(@StringRes int messageResId) {
        showProgressDialog(getResString(messageResId));
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public void showToast(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int stringRes) {
        showToast(getResString(stringRes));
    }

    public String getResString(int stringId) {
        return getResources().getString(stringId);
    }

    public void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void nextActivity(Class targetClass, boolean killMe) {
        Intent intent = new Intent(this, targetClass);
        nextActivity(intent, killMe);
    }

    public void nextActivity(Intent intent, boolean killMe) {
        nextActivity(intent, killMe, 0);
    }

    public void nextActivity(Intent intent, boolean killMe, int requestCode) {
        closeKeyBoard();
        if (requestCode == 0)
            startActivity(intent);
        else
            startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.in, R.anim.out);
        closeKeyBoard();
        if (killMe) {
            this.finish();
            return;
        }
    }

    public boolean hasConnectivity() {
        connectivityManager.getActiveNetworkInfo();
        return connectivityManager.getActiveNetworkInfo() != null;
    }

}
