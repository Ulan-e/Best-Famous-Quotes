package com.ulan.app.quotes.di.modules.uimodules;

import com.ulan.app.quotes.adapter.ViewPagerAdapter;
import com.ulan.app.quotes.di.scopes.MainScope;
import com.ulan.app.quotes.ui.main.MainActivity;
import com.ulan.app.quotes.ui.main.MainPresenter;
import com.ulan.app.quotes.ui.main.MainPresenterImpl;
import com.ulan.app.quotes.ui.main.MainView;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {

    @MainScope
    @Binds
    abstract MainView provideView(MainActivity activity);

    @MainScope
    @Binds
    abstract MainPresenter providePresenter(MainPresenterImpl presenter);

    @MainScope
    @Provides
    static ViewPagerAdapter provideViewPagerAdapter(MainActivity activity){
        return new ViewPagerAdapter(activity.getSupportFragmentManager());
    }
}