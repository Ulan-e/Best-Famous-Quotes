package com.lessons.firebase.quotes.di.components;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.di.modules.source.RoomModule;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterHappy;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLive;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLove;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterMotif;
import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.modules.AppModule;
import com.lessons.firebase.quotes.di.modules.RxModule;
import com.lessons.firebase.quotes.ui.mainactivity.MainActivity;

import java.util.List;

import dagger.Component;
import io.reactivex.Observable;


@AppScope
@Component(modules = {AppModule.class, RxModule.class, RoomModule.class})
public interface AppComponent {

    void inject(MainActivity activity);

    ListComponent.MABuilder mainActivityComponentBuilder();
    DayQuoteComponent.DQBuilder dayQuoteComponentBuilder();
    LikedComponent.LCBuilder listComponentBuilder();

    @FilterMotif
    Observable<List<QuoteData>> getObservableListMotif();

    @FilterLive
    Observable<List<QuoteData>> getObservableListLife();

    @FilterLove
    Observable<List<QuoteData>> getObservableListLove();

    @FilterHappy
    Observable<List<QuoteData>> getObservableListHappy();
}

