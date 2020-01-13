package com.ulan.app.quotes.ui.base;

import com.ulan.app.quotes.MainApplication;
import com.ulan.app.quotes.di.components.AppComponent;

import dagger.android.DaggerActivity;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private MainApplication getMainApplication(){
        return (MainApplication) getApplication();
    }

    protected AppComponent getAppComponent(){
        return  getMainApplication().getAppComponent();
    }
}
