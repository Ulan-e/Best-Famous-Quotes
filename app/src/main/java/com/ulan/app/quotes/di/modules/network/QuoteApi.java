package com.ulan.app.quotes.di.modules.network;

import com.ulan.app.quotes.data.network.api.FavQsApi;
import com.ulan.app.quotes.data.network.responce.QuotesResponse;
import com.ulan.app.quotes.di.qualifires.filters.FilterFunny;
import com.ulan.app.quotes.di.qualifires.filters.FilterHappy;
import com.ulan.app.quotes.di.qualifires.filters.FilterLive;
import com.ulan.app.quotes.di.qualifires.filters.FilterLove;
import com.ulan.app.quotes.di.qualifires.filters.FilterMotif;
import com.ulan.app.quotes.di.qualifires.filters.FilterRandom;
import com.ulan.app.quotes.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

/**
 * Module QuotesCalls return different type of quotes
 * Types: popular, life, wisdom, love, motivation, funny
 */

@AppScope
@Module(includes = {QuotesModule.class})
public class QuoteApi {

    @FilterRandom
    @AppScope
    @Provides
    public Observable<QuotesResponse> provideQuotes(FavQsApi favQsApi){
        return favQsApi.getQuotes();
    }

    @FilterHappy
    @AppScope
    @Provides
    public Observable<QuotesResponse> wisdomQuotes(FavQsApi favQsApi){
        return favQsApi.getWisdomQuotes();
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

    @FilterFunny
    @AppScope
    @Provides
    public Observable<QuotesResponse> funnyQuotes(FavQsApi favQsApi){
        return favQsApi.getFunnyQuotes();
    }
}
