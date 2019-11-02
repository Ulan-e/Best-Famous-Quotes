package com.lessons.firebase.quotes.utils.listeners;

import androidx.fragment.app.Fragment;

/**
 * Class can handle fragment lifecycle
 * and pass data between them
 */

public interface FragmentLifecycle {

    void onPauseFragment(Fragment fragment);
    void onResumeFragment();
}
