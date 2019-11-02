package com.ulan.app.quotes.di.modules.source;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ulan.app.quotes.data.network.FavQsApi;
import com.ulan.app.quotes.di.qualifires.QuoteGson;
import com.ulan.app.quotes.di.qualifires.Quotes;
import com.ulan.app.quotes.di.qualifires.TokenInterceptor;
import com.ulan.app.quotes.di.scopes.AppScope;
import com.ulan.app.quotes.utils.Constants;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@AppScope
@Module(includes = {OkHttpModule.class})
public class QuotesModule {

    @AppScope
    @Provides
    public FavQsApi favQsApi(@Quotes Retrofit retrofit){
        return retrofit.create(FavQsApi.class);
    }

    @AppScope
    @Quotes
    @Provides
    public Retrofit retrofit(@TokenInterceptor OkHttpClient client,
                             @QuoteGson Gson gson){
        return new Retrofit.Builder()
                .baseUrl(Constants.FAVQS_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @AppScope
    @QuoteGson
    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }
}
