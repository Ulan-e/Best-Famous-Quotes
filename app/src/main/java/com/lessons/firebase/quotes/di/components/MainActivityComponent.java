package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.di.modules.AppModule;
import com.lessons.firebase.quotes.di.modules.RxModule;
import com.lessons.firebase.quotes.di.modules.uimodules.MainModulee;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterFunny;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterHappy;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLive;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLove;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterMotif;
import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.scopes.MainScope;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivity;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivityPresenterImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.Subcomponent;
import io.reactivex.Observable;

@MainScope
@Subcomponent(modules = {MainModulee.class})
public interface MainActivityComponent {

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
        ActivityBuilder mainModule(MainModulee mainModulee);
        MainActivityComponent build();
    }
}
