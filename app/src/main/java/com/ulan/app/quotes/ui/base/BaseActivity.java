package com.ulan.app.quotes.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ulan.app.quotes.MainApplication;
import com.ulan.app.quotes.di.component.AppComponent;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }
}
