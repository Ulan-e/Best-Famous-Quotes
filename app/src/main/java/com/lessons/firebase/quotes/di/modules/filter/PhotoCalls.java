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
import com.lessons.firebase.quotes.utils.WordGenerator;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

import static com.lessons.firebase.quotes.utils.Constants.PIXABAY_API_KEY;

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
