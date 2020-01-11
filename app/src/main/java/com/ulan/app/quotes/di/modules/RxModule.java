package com.ulan.app.quotes.di.modules;

import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.data.network.model.Photo;
import com.ulan.app.quotes.data.network.PhotosResponse;
import com.ulan.app.quotes.data.network.model.Quote;
import com.ulan.app.quotes.data.network.QuotesResponse;
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
    public Observable<List<QuoteModel>> zipObservables(
            @FilterRandom Observable<PhotosResponse> photosObservable,
            @FilterRandom Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterHappy
    @Provides
    public Observable<List<QuoteModel>> zipObservablesWisdom(
            @FilterHappy Observable<PhotosResponse> photosObservable,
            @FilterHappy Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterLove
    @Provides
    public Observable<List<QuoteModel>> zipObservablesLove(
            @FilterLove Observable<PhotosResponse> photosObservable,
            @FilterLove Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterLive
    @Provides
    public Observable<List<QuoteModel>> zipObservablesLife(
            @FilterLive Observable<PhotosResponse> photosObservable,
            @FilterLive Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterMotif
    @Provides
    public Observable<List<QuoteModel>> zipObservablesMotif(
            @FilterMotif Observable<PhotosResponse> photosObservable,
            @FilterMotif Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterFunny
    @Provides
    public Observable<List<QuoteModel>> zipObservablesFunny(
            @FilterFunny Observable<PhotosResponse> photosObservable,
            @FilterFunny Observable<QuotesResponse> quotesObservable){
        return Observable.zip(photosObservable, quotesObservable, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesPhotos(photosList, quoteDataList);
            }
        });
    }



}
