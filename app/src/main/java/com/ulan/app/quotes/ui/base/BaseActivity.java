package com.ulan.app.quotes.ui.base;

import androidx.appcompat.app.AppCompatActivity;

import com.ulan.app.quotes.di.components.AppComponent;
import com.ulan.app.quotes.MainApplication;

public abstract class BaseActivity extends AppCompatActivity {

    private MainApplication getMainApplication(){
        return (MainApplication) getApplication();
    }

    protected AppComponent getAppComponent(){
        return  getMainApplication().getAppComponent();
    }
}
