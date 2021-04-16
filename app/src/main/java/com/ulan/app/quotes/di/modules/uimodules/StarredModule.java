package com.ulan.app.quotes.di.modules.uimodules;

import com.ulan.app.quotes.di.scopes.StarredScope;
import com.ulan.app.quotes.ui.starred.StarredFragment;
import com.ulan.app.quotes.ui.starred.StarredPresenterImpl;
import com.ulan.app.quotes.ui.starred.StarredView;
import dagger.Module;
import dagger.Provides;

@Module
public class StarredModule {

    @StarredScope
    @Provides
    public StarredView starredView(StarredFragment fragment){
        return fragment;
    }

    @StarredScope
    @Provides
    public StarredPresenterImpl starredPresenter(StarredView view){
        return new StarredPresenterImpl(view);
    }
}