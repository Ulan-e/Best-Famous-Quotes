package com.lessons.firebase.quotes.data.network;

import com.lessons.firebase.quotes.data.network.pojo.QuotesResponse;



import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FavQsApi {

    @GET("api/quotes")
    Observable<QuotesResponse> getQuotes();
}
