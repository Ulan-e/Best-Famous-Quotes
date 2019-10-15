package com.lessons.firebase.quotes.utils.listeners;

import androidx.fragment.app.Fragment;

import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.list.MainFragment;

public interface FragmentLifecycle {

    void onPauseFragment(Fragment fragment);
    void onResumeFragment();
}
