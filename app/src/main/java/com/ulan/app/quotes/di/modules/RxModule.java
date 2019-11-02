package com.ulan.app.quotes.di.modules;

import com.ulan.app.quotes.data.QuoteData;
import com.ulan.app.quotes.data.network.pojo.Photo;
import com.ulan.app.quotes.data.network.pojo.PhotosResponse;
import com.ulan.app.quotes.data.network.pojo.Quote;
import com.ulan.app.quotes.data.network.pojo.QuotesResponse;
import com.ulan.app.quotes.di.modules.filter.PhotoCalls;
import com.ulan.app.quotes.di.modules.filter.QuoteCalls;
import com.ulan.app.quotes.di.qualifires.filters.FilterFunny;
import com.ulan.app.quotes.di.qualifires.filters.FilterHappy;
import com.ulan.app.quotes.di.qualifires.filters.FilterLive;
import com.ulan.app.quotes.di.qualifires.filters.FilterLove;
import com.ulan.app.quotes.di.qualifires.filters.FilterMotif;
import com.ulan.app.quotes.di.qualifires.filters.FilterRandom;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

import static com.ulan.app.quotes.utils.ListDataHandler.mergeQuotesPhotos;
import static com.ulan.app.quotes.utils.ListDataHandler.populateQuotes;

/**
 * Module RxModule zip observable of quotes with
 * observable of photos then return new type
 * observable if quotedata
 */

@Module(includes = {QuoteCalls.class, PhotoCalls.class})
public class RxModule {

    @FilterRandom
    @Provides
    public Observable<List<QuoteData>> zipObservables(
            @FilterRandom Observable<PhotosResponse> photosObservable,
            @FilterRandom Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteData>>() {
            @Override
            public List<QuoteData> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteData> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterHappy
    @Provides
    public Observable<List<QuoteData>> zipObservablesWisdom(
            @FilterHappy Observable<PhotosResponse> photosObservable,
            @FilterHappy Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteData>>() {
            @Override
            public List<QuoteData> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteData> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterLove
    @Provides
    public Observable<List<QuoteData>> zipObservablesLove(
            @FilterLove Observable<PhotosResponse> photosObservable,
            @FilterLove Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteData>>() {
            @Override
            public List<QuoteData> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteData> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterLive
    @Provides
    public Observable<List<QuoteData>> zipObservablesLife(
            @FilterLive Observable<PhotosResponse> photosObservable,
            @FilterLive Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteData>>() {
            @Override
            public List<QuoteData> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteData> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterMotif
    @Provides
    public Observable<List<QuoteData>> zipObservablesMotif(
            @FilterMotif Observable<PhotosResponse> photosObservable,
            @FilterMotif Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteData>>() {
            @Override
            public List<QuoteData> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteData> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterFunny
    @Provides
    public Observable<List<QuoteData>> zipObservablesFunny(
            @FilterFunny Observable<PhotosResponse> photosObservable,
            @FilterFunny Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteData>>() {
            @Override
            public List<QuoteData> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteData> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }



}
