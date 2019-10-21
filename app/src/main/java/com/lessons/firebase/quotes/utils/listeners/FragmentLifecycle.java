package com.lessons.firebase.quotes.utils.listeners;

import androidx.fragment.app.Fragment;

public interface FragmentLifecycle {

    void onPauseFragment(Fragment fragment);
    void onResumeFragment();
}
