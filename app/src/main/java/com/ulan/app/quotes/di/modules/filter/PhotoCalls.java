package com.ulan.app.quotes.di.modules.filter;

import com.ulan.app.quotes.data.network.api.PhotosApi;
import com.ulan.app.quotes.data.network.PhotosResponse;
import com.ulan.app.quotes.di.modules.source.PhotosModule;
import com.ulan.app.quotes.di.qualifires.filters.FilterFunny;
import com.ulan.app.quotes.di.qualifires.filters.FilterHappy;
import com.ulan.app.quotes.di.qualifires.filters.FilterLive;
import com.ulan.app.quotes.di.qualifires.filters.FilterLove;
import com.ulan.app.quotes.di.qualifires.filters.FilterMotif;
import com.ulan.app.quotes.di.qualifires.filters.FilterRandom;
import com.ulan.app.quotes.di.scopes.AppScope;
import com.ulan.app.quotes.utils.WordGenerator;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

import static com.ulan.app.quotes.utils.Constants.PIXABAY_API_KEY;

/**
 * Module PhotosCalls return different type of photos
 * Types: popular, life, wisdom, love, motivation, funny
 */

@AppScope
@Module(includes = {PhotosModule.class})
public class PhotoCalls {

    @FilterRandom
    @AppScope
    @Provides
    public Observable<PhotosResponse> popularPhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25,
                "nature+" + WordGenerator.getRandomWord(), WordGenerator.getRandomBoolean());
    }

    @FilterLive
    @AppScope
    @Provides
    public Observable<PhotosResponse> lifePhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25,
                "life+"+ WordGenerator.getLifeWord(), WordGenerator.getRandomBoolean());
    }

    @FilterHappy
    @AppScope
    @Provides
    public Observable<PhotosResponse> wisdomPhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25,
                "wisdom+"+ WordGenerator.getWisdomWord(), WordGenerator.getRandomBoolean());
    }

    @FilterLove
    @AppScope
    @Provides
    public Observable<PhotosResponse> lovePhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25,
                "love+"+ WordGenerator.getLoveWord(), WordGenerator.getRandomBoolean());
    }

    @FilterMotif
    @AppScope
    @Provides
    public Observable<PhotosResponse> motivationPhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25,
                "motivation+"+ WordGenerator.getMotivationalWord(), WordGenerator.getRandomBoolean());
    }

    @FilterFunny
    @AppScope
    @Provides
    public Observable<PhotosResponse> funnyPhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25,
                "funny+"+ WordGenerator.getFunnyWord(), WordGenerator.getRandomBoolean());
    }




}
