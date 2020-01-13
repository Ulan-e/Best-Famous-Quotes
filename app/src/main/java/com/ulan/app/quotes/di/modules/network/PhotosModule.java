package com.ulan.app.quotes.di.modules.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ulan.app.quotes.data.network.api.PhotosApi;
import com.ulan.app.quotes.di.qualifires.Photos;
import com.ulan.app.quotes.di.qualifires.PhotosGson;
import com.ulan.app.quotes.di.qualifires.PhotosOkHttp;
import com.ulan.app.quotes.di.scopes.AppScope;
import com.ulan.app.quotes.helpers.Constants;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@AppScope
@Module(includes = {OkHttpModule.class})
public class PhotosModule {

    @AppScope
    @Provides
    public PhotosApi photoApi(@Photos Retrofit retrofit){
        return retrofit.create(PhotosApi.class);
    }

    @AppScope
    @Photos
    @Provides
    public Retrofit retrofit(@PhotosOkHttp OkHttpClient client,
                             @PhotosGson Gson gson){
        return new Retrofit.Builder()
                .baseUrl(Constants.PIXABAY_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @AppScope
    @PhotosGson
    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }
}
