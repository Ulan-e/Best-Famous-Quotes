
package com.lessons.firebase.quotes.di.components;

import android.content.SharedPreferences;

import com.lessons.firebase.quotes.data.database.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.modules.source.SharedPrefModule;
import com.lessons.firebase.quotes.di.modules.uimodules.StarredModule;
import com.lessons.firebase.quotes.di.scopes.StarredScope;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.starred.StarredPresenterImpl;

import dagger.Subcomponent;

@StarredScope
@Subcomponent(modules = {StarredModule.class, SharedPrefModule.class})
public interface StarredComponent {

    @StarredScope
    DaoLikedQuotes getDaoQuotes();

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