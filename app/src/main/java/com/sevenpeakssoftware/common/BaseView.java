package com.sevenpeakssoftware.common;

import android.support.annotation.StringRes;

public interface BaseView {

    void showToast(@StringRes int stringResId);

    void showToast(String message);

    void showProgressDialog(int resId);

    void showProgressDialog();

    void dismissProgressDialog();

}
