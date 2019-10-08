
package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.data.database.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.modules.uimodules.LikedModule;
import com.lessons.firebase.quotes.di.qualifires.LikedQuotes;
import com.lessons.firebase.quotes.di.scopes.LikedScope;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.liked.LikedPresenterImpl;

import dagger.Subcomponent;

@LikedScope
@Subcomponent(modules = {LikedModule.class})
public interface LikedComponent {

    @LikedScope
    DaoLikedQuotes getDaoQuotes();


    @LikedScope
    LikedPresenterImpl getPresenter();

    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface LCBuilder{
        LCBuilder likedModule(LikedModule likedModule);
        LikedComponent build();
    }
}