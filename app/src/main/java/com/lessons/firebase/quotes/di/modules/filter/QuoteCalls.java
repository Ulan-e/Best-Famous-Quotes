package com.lessons.firebase.quotes.di.modules.filter;

import com.lessons.firebase.quotes.data.network.FavQsApi;
import com.lessons.firebase.quotes.data.network.pojo.QuotesResponse;
import com.lessons.firebase.quotes.di.modules.source.QuotesModule;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterHappy;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLive;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLove;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterMotif;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterRandom;
import com.lessons.firebase.quotes.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

@AppScope
@Module(includes = {QuotesModule.class})
public class QuoteCalls {

    @FilterRandom
    @AppScope
    @Provides
    public Observable<QuotesResponse> provideQuotes(FavQsApi favQsApi){
        return favQsApi.getQuotes();
    }

    @FilterHappy
    @AppScope
    @Provides
    public Observable<QuotesResponse> happyQuotes(FavQsApi favQsApi){
        return favQsApi.getHappyQuotes();
    }

    @FilterLive
    @AppScope
    @Provides
    public Observable<QuotesResponse> lifeQuotes(FavQsApi favQsApi){
        return favQsApi.getLifeQuotes();
    }

    @FilterLove
    @AppScope
    @Provides
    public Observable<QuotesResponse> loveQuotes(FavQsApi favQsApi){
        return favQsApi.getLoveQuotes();
    }

    @FilterMotif
    @AppScope
    @Provides
    public Observable<QuotesResponse> motifQuotes(FavQsApi favQsApi){
        return favQsApi.getMotivationalQuotes();
    }
}
