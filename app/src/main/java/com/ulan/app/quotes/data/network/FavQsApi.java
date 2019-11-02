package com.ulan.app.quotes.data.network;

import com.ulan.app.quotes.data.network.pojo.QuotesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FavQsApi {

    @GET("api/quotes")
    Observable<QuotesResponse> getQuotes();

    @GET("api/quotes/?filter=motivation")
    Observable<QuotesResponse> getMotivationalQuotes();

    @GET("api/quotes/?filter=life")
    Observable<QuotesResponse> getLifeQuotes();

    @GET("api/quotes/?filter=love")
    Observable<QuotesResponse> getLoveQuotes();

    @GET("api/quotes/?filter=wisdom")
    Observable<QuotesResponse> getWisdomQuotes();

    @GET("api/quotes/?filter=funny")
    Observable<QuotesResponse> getFunnyQuotes();
}
