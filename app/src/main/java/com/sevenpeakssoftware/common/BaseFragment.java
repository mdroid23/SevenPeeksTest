package com.sevenpeakssoftware.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.sevenpeakssoftware.mahesh.BuildConfig;
import com.sevenpeakssoftware.mahesh.R;


public class BaseFragment extends Fragment {


    private ProgressDialog mProgressDialog;
    private ConnectivityManager connectivityManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!BuildConfig.DEBUG)
            ((BaseActivity) getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        mProgressDialog = new ProgressDialog(((BaseActivity) getActivity()));
        connectivityManager = (ConnectivityManager) ((BaseActivity) getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo() == null) {
            return;
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dismissProgressDialog();
    }

    public void showProgressDialog(String message) {
        if (((BaseActivity) getActivity()).isFinishing()) {
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
        Toast.makeText(((BaseActivity) getActivity()), message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int stringRes) {
        showToast(getResString(stringRes));
    }

    public String getResString(int stringId) {
        return getResources().getString(stringId);
    }

    public void closeKeyBoard() {
        View view = ((BaseActivity) getActivity()).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) ((BaseActivity) getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void nextActivity(Class targetClass, boolean killMe) {
        Intent intent = new Intent( ((BaseActivity) getActivity()), targetClass);
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
        ((BaseActivity) getActivity()).overridePendingTransition(R.anim.in, R.anim.out);
        closeKeyBoard();
        if (killMe) {
            ((BaseActivity) getActivity()).finish();
            return;
        }
    }

    public boolean hasConnectivity() {
        connectivityManager.getActiveNetworkInfo();
        return connectivityManager.getActiveNetworkInfo() != null;
    }

}
