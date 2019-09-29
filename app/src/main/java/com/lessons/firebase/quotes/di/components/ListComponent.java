package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.model.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.scopes.ListScope;
import com.lessons.firebase.quotes.di.qualifires.RandomQuotes;
import com.lessons.firebase.quotes.di.modules.ContextModule;
import com.lessons.firebase.quotes.ui.base.BaseFragment;

import java.util.List;

import dagger.Subcomponent;
import io.reactivex.Observable;

@ListScope
@Subcomponent(modules = {ContextModule.class})
public interface ListComponent {

    @RandomQuotes
    Observable<List<QuoteData>> getObshiiData();

    DaoLikedQuotes getDaoChoosedQuotes();

    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface MABuilder{
        MABuilder contextModule(ContextModule contextModule);
        ListComponent build();
    }
}
