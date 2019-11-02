package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.network.pojo.Quote;
import com.lessons.firebase.quotes.di.modules.uimodules.MainModule;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterFunny;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterHappy;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLive;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLove;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterMotif;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterRandom;
import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.scopes.MainScope;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivity;

import java.util.List;

import dagger.Subcomponent;
import io.reactivex.Observable;

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainActivityComponent {

    @FilterRandom
    Observable<List<QuoteData>> getObservableList();

    @FilterMotif
    Observable<List<QuoteData>> getObservableListMotif();

    @FilterLive
    Observable<List<QuoteData>> getObservableListLive();

    @FilterLove
    Observable<List<QuoteData>> getObservableListLove();

    @FilterHappy
    Observable<List<QuoteData>> getObservableListHappy();

    @FilterFunny
    Observable<List<QuoteData>> getObservableListFunny();

    void inject(MainActivity activity);

    @Subcomponent.Builder
    interface ActivityBuilder{
        ActivityBuilder mainModule(MainModule mainModulee);
        MainActivityComponent build();
    }
}
