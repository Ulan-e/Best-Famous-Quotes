
package com.ulan.app.quotes.di.components;

import android.content.SharedPreferences;

import com.ulan.app.quotes.data.database.DaoStarredQuotes;
import com.ulan.app.quotes.di.modules.source.SharedPrefModule;
import com.ulan.app.quotes.di.modules.uimodules.StarredModule;
import com.ulan.app.quotes.di.scopes.StarredScope;
import com.ulan.app.quotes.ui.base.BaseFragment;
import com.ulan.app.quotes.ui.starred.StarredPresenterImpl;

import dagger.Subcomponent;

@StarredScope
@Subcomponent(modules = {StarredModule.class, SharedPrefModule.class})
public interface StarredComponent {

    @StarredScope
    DaoStarredQuotes getDaoQuotes();

    SharedPreferences getSharedPreferences();

    @StarredScope
    StarredPresenterImpl getPresenter();

    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface LikedBuilder {
        LikedBuilder likedModule(StarredModule likedModule);
        LikedBuilder sharedModule(SharedPrefModule sharedPrefModule);
        StarredComponent build();
    }
}