package com.lessons.firebase.quotes.di.modules.filter;

import com.lessons.firebase.quotes.data.network.PhotosApi;
import com.lessons.firebase.quotes.data.network.pojo.PhotosResponse;
import com.lessons.firebase.quotes.di.modules.source.PhotosModule;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterFunny;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterHappy;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLive;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLove;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterMotif;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterRandom;
import com.lessons.firebase.quotes.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

import static com.lessons.firebase.quotes.utils.Constants.PIXABAY_API_KEY;

@AppScope
@Module(includes = {PhotosModule.class})
public class PhotoCalls {

    @FilterRandom
    @AppScope
    @Provides
    public Observable<PhotosResponse> popularPhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25, "nature");
    }

    @FilterLive
    @AppScope
    @Provides
    public Observable<PhotosResponse> lifePhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25, "life");
    }

    @FilterHappy
    @AppScope
    @Provides
    public Observable<PhotosResponse> happyPhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25, "happiness");
    }

    @FilterLove
    @AppScope
    @Provides
    public Observable<PhotosResponse> lovePhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25, "love");
    }

    @FilterMotif
    @AppScope
    @Provides
    public Observable<PhotosResponse> motivationPhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25, "motivation");
    }

    @FilterFunny
    @AppScope
    @Provides
    public Observable<PhotosResponse> funnyPhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25, "funny");
    }




}
