package com.lessons.firebase.quotes.di.modules.source;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lessons.firebase.quotes.data.network.PixabayApi;
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
public class ImagesAPIModule {

    @AppScope
    @Provides
    public PixabayApi pixabayPhotoApi(@Named("photosApi") Retrofit retrofit){
        return retrofit.create(PixabayApi.class);
    }

    @AppScope
    @Named("photosApi")
    @Provides
    public Retrofit retrofit(@Named("http") OkHttpClient client,
                             @Named("image_gson") Gson gson){
        return new Retrofit.Builder()
                .baseUrl(Constants.PIXABAY_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @AppScope
    @Named("image_gson")
    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }
}
