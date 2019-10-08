package com.lessons.firebase.quotes.di.modules.rxmodules;

import android.util.Log;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.QuoteEntity;
import com.lessons.firebase.quotes.data.network.pojo.Photo;
import com.lessons.firebase.quotes.data.network.pojo.PhotosResponse;
import com.lessons.firebase.quotes.data.network.pojo.Quote;
import com.lessons.firebase.quotes.data.network.pojo.QuotesResponse;
import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.qualifires.RandomQuotes;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.functions.Function3;

import static com.lessons.firebase.quotes.utils.Constants.TAG_OTHER;
import static com.lessons.firebase.quotes.utils.StringUtils.exchangeSrtings;

@AppScope
@Module(includes = {ApisModule.class})
public class ObservableModule {

    @RandomQuotes
    @AppScope
    @Provides
    public Observable<List<QuoteData>> zipMultipleObservables( Observable<PhotosResponse> photosObservable,
                                                                Observable<QuotesResponse> quotesObservable,
                                                                Observable<List<QuoteEntity>> databaseObservable){
        Observable<List<QuoteData>> quoteDataObservable =  Observable.zip(photosObservable, quotesObservable, databaseObservable,
                new Function3<PhotosResponse, QuotesResponse, List<QuoteEntity>, List<QuoteData>>() {
                    @Override
                    public List<QuoteData> apply(PhotosResponse photoS, QuotesResponse quoteS, List<QuoteEntity> quoteEntities) throws Exception {
                        List<Quote> quoteListAPI = quoteS.getQuotes();
                        List<QuoteEntity> databaseFlow = quoteEntities;
                        List<Photo> photosListAPI = photoS.getHits();
                        List<QuoteData> mergedList = mergeQuotes(quoteListAPI, databaseFlow);
                        List<QuoteData> res = addPhotoToQuotes(photosListAPI, mergedList);
                        Log.d(TAG_OTHER, " Observable module: zipMultiplyObsevable " + res);
                        return res;
                    }
                });
        return quoteDataObservable;
    }

    @AppScope
    @Provides
    public List<QuoteData> mergeQuotes(List<Quote> quotesAPI, List<QuoteEntity> quotesDatabase){
        int size = 25;
        Log.d(TAG_OTHER, " from API" + String.valueOf(quotesAPI.size()));
        Log.d(TAG_OTHER, " from Database" + String.valueOf(quotesAPI.size()));
        List<QuoteData> resultList = new ArrayList<>(2*size+1);
        for(int i=0; i < size; i++){
            QuoteData quoteFromAPI = new QuoteData();
            quoteFromAPI.setId(i);
            quoteFromAPI.setQuote(quotesAPI.get(i).getBody());
            quoteFromAPI.setAuthor(quotesAPI.get(i).getAuthor());
            quoteFromAPI.setIsLiked(0);
            resultList.add(quoteFromAPI);
        }
        for(int i=0; i < size; i++){
            QuoteData quotesFromDB = new QuoteData();
            String authorFromTable = quotesDatabase.get(i).getName();
            quotesFromDB.setId(i);
            quotesFromDB.setQuote(quotesDatabase.get(i).getQuote());
            quotesFromDB.setAuthor(exchangeSrtings(authorFromTable));
            quotesFromDB.setIsLiked(0);
            resultList.add(quotesFromDB);
        }
        return resultList;
    }

    @AppScope
    @Provides
    public List<QuoteData> addPhotoToQuotes(List<Photo> photosList, List<QuoteData> listOfQuoteData) {
        for(int i =0; i < photosList.size(); i++){
            QuoteData quoteData = listOfQuoteData.get(i);
            quoteData.setUrlImage(photosList.get(i).getWebformatURL());
        }
        Log.d(TAG_OTHER, " quotes api + quotes databse" + String.valueOf(listOfQuoteData.size()));
        return  listOfQuoteData;
    }
}
