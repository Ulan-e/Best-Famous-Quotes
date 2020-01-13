package com.ulan.app.quotes.ui.base;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.ulan.app.quotes.di.components.AppComponent;
import com.ulan.app.quotes.MainApplication;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends Fragment {

    private MainApplication getMainApplication(){
        return (MainApplication) getActivity().getApplication();
    }

    protected AppComponent getAppComponent(){
        return  getMainApplication().getAppComponent();
    }
}
