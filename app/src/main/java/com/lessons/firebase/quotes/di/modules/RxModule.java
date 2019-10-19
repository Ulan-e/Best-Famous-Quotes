package com.lessons.firebase.quotes.di.modules;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.network.pojo.Photo;
import com.lessons.firebase.quotes.data.network.pojo.PhotosResponse;
import com.lessons.firebase.quotes.data.network.pojo.Quote;
import com.lessons.firebase.quotes.data.network.pojo.QuotesResponse;
import com.lessons.firebase.quotes.di.modules.filter.PhotoCalls;
import com.lessons.firebase.quotes.di.modules.filter.QuoteCalls;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterFunny;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterHappy;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLive;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLove;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterMotif;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterRandom;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

import static com.lessons.firebase.quotes.utils.StringUtils.mergeQuotesPhotos;
import static com.lessons.firebase.quotes.utils.StringUtils.populateQuotes;


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
    public Observable<List<QuoteData>> zipObservablesHappy(
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
