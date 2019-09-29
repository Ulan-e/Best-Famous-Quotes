
package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.model.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.qualifires.LikedQuotes;
import com.lessons.firebase.quotes.di.scopes.LikedScope;
import com.lessons.firebase.quotes.di.modules.ContextModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;

import java.util.List;

import dagger.Subcomponent;
import io.reactivex.Observable;

@LikedScope
@Subcomponent(modules = {ContextModule.class})
public interface LikedComponent {

    @LikedQuotes
    Observable<List<QuoteData>> getLikedDatas();
    DaoLikedQuotes getDaoQuotes();

    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface LCBuilder{

        LCBuilder contextModule(ContextModule contextModule);
        LikedComponent build();
    }
}