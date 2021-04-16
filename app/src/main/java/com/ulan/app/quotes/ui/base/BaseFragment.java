package com.ulan.app.quotes.ui.base;

import android.content.Context;
import androidx.fragment.app.Fragment;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        setHasOptionsMenu(true);
    }
}