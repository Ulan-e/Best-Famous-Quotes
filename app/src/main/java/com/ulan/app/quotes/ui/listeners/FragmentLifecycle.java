package com.ulan.app.quotes.ui.listeners;

import androidx.fragment.app.Fragment;

/**
 * Class can handle fragment lifecycle
 * and pass data between fragments
 */
public interface FragmentLifecycle {

    void onPauseFragment(Fragment fragment);
    void onResumeFragment();
}