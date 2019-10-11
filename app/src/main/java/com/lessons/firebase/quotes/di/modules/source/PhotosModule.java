package com.lessons.firebase.quotes.di.modules.source;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lessons.firebase.quotes.data.network.PhotosApi;
import com.lessons.firebase.quotes.di.qualifires.HttpInterceptor;
import com.lessons.firebase.quotes.di.qualifires.PhotoGson;
import com.lessons.firebase.quotes.di.qualifires.Photos;
import com.lessons.firebase.quotes.di.qualifires.PhotosGson;
import com.lessons.firebase.quotes.di.qualifires.PhotosOkHttp;
import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.utils.Constants;

import javax.inject.Named;

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
                             @Named("photos_gson") Gson gson){
        return new Retrofit.Builder()
                .baseUrl(Constants.PIXABAY_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @AppScope
    @Named("photos_gson")
    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }
}
