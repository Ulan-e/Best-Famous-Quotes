package com.lessons.firebase.quotes.di.modules;

import android.util.Log;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.network.FavQsApi;
import com.lessons.firebase.quotes.data.network.PhotosApi;
import com.lessons.firebase.quotes.data.network.pojo.Photo;
import com.lessons.firebase.quotes.data.network.pojo.PhotosResponse;
import com.lessons.firebase.quotes.data.network.pojo.Quote;
import com.lessons.firebase.quotes.data.network.pojo.QuotesResponse;
import com.lessons.firebase.quotes.di.modules.source.PhotosModule;
import com.lessons.firebase.quotes.di.modules.source.QuotesModule;
import com.lessons.firebase.quotes.di.scopes.AppScope;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

import static com.lessons.firebase.quotes.utils.Constants.PIXABAY_API_KEY;
import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;

@AppScope
@Module(includes = {QuotesModule.class, PhotosModule.class})
public class RxModule {

    @AppScope
    @Provides
    public Observable<List<QuoteData>> zipObservables(Observable<PhotosResponse> photosObservable,
                                                      Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteData>>() {
            @Override
            public List<QuoteData> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteListAPI = quotesResponse.getQuotes();
                List<QuoteData> quoteDataList = populateQuotes(quoteListAPI);
                List<Photo> photosListAPI = photosResponse.getHits();
                List<QuoteData> resultList = mergeQuotesPhotos(photosListAPI, quoteDataList);
                return resultList;
            }
        });
    }

    @AppScope
    @Provides
    public List<QuoteData> populateQuotes(List<Quote> listOfQuotes){
        int size = 25;
        List<QuoteData> resultList = new ArrayList<>();
        for(int i=0; i < size; i++){
            QuoteData quoteData = new QuoteData();
            quoteData.setId(i);
            quoteData.setQuote(listOfQuotes.get(i).getBody());
            quoteData.setAuthor(listOfQuotes.get(i).getAuthor());
            quoteData.setIsLiked(0);
            resultList.add(quoteData);
        }
        Log.d(TAG_OTHER, "populateQuotes: List of Quotes " + String.valueOf(resultList.size()) );
        return resultList;
    }

    @AppScope
    @Provides
    public List<QuoteData> mergeQuotesPhotos(List<Photo> photosList, List<QuoteData> quotesList){
        for(int i =0; i < photosList.size(); i++){
            QuoteData quoteData = quotesList.get(i);
            quoteData.setUrlImage(photosList.get(i).getWebformatURL());
        }
        Log.d(TAG_OTHER, "mergeQuotesPhotos quotes " + String.valueOf(quotesList.size()));
        Log.d(TAG_OTHER, "mergeQuotesPhotos photos " + String.valueOf(photosList.size()));
        return  quotesList;
    }

    @AppScope
    @Provides
    public Observable<PhotosResponse> providePhotos(PhotosApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 25, "popular");
    }

    @AppScope
    @Provides
    public Observable<QuotesResponse> provideImages(FavQsApi favQsApi){
        return favQsApi.getQuotes();
    }
}
