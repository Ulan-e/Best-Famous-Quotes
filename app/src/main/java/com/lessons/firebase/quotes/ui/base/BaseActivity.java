package com.lessons.firebase.quotes.ui.base;

import androidx.appcompat.app.AppCompatActivity;

import com.lessons.firebase.quotes.di.components.AppComponent;
import com.lessons.firebase.quotes.MainApplication;

public abstract class BaseActivity extends AppCompatActivity {

    private MainApplication getMainApplication(){
        return (MainApplication) getApplication();
    }

    protected AppComponent getAppComponent(){
        return  getMainApplication().getAppComponent();
    }
}
