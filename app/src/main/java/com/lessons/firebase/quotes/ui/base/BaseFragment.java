package com.lessons.firebase.quotes.ui.base;

import androidx.fragment.app.Fragment;

import com.lessons.firebase.quotes.di.components.AppComponent;
import com.lessons.firebase.quotes.MainApplication;

public abstract class BaseFragment extends Fragment {

    private MainApplication getMainApplication(){
        return (MainApplication) getActivity().getApplication();
    }

    protected AppComponent getAppComponent(){
        return  getMainApplication().getAppComponent();
    }
}
