package com.lessons.firebase.quotes.ui.mainactivity;

import androidx.fragment.app.Fragment;

public interface MainActivityView {

    void showToolbar(String toolbarTitle);
    boolean showFragment(Fragment fragment);
    void showInternetConnection();
}
