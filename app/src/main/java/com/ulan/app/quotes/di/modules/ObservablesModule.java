package com.ulan.app.quotes.di.modules;

import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.data.network.model.Photo;
import com.ulan.app.quotes.data.network.responce.PhotosResponse;
import com.ulan.app.quotes.data.network.model.Quote;
import com.ulan.app.quotes.data.network.responce.QuotesResponse;
import com.ulan.app.quotes.di.modules.network.PhotoApi;
import com.ulan.app.quotes.di.modules.network.QuoteApi;
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

import static com.ulan.app.quotes.helpers.ListHandlerHelper.mergeQuotesWithPhotos;
import static com.ulan.app.quotes.helpers.ListHandlerHelper.populateQuotes;

/**
 * Module ObservablesModule zip observable of quotes with
 * observable of photos then return new type
 * observable if Quotedata
 */

@Module(includes = {QuoteApi.class, PhotoApi.class})
public class ObservablesModule {

    @FilterRandom
    @Provides
    public Observable<List<QuoteModel>> zipObservables(
            @FilterRandom Observable<PhotosResponse> photos,
            @FilterRandom Observable<QuotesResponse> quotes){
        return Observable.zip(photos, quotes, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesWithPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterHappy
    @Provides
    public Observable<List<QuoteModel>> zipObservablesWisdom(
            @FilterHappy Observable<PhotosResponse> photos,
            @FilterHappy Observable<QuotesResponse> quotes){
        return Observable.zip(photos, quotes, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesWithPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterLove
    @Provides
    public Observable<List<QuoteModel>> zipObservablesLove(
            @FilterLove Observable<PhotosResponse> photos,
            @FilterLove Observable<QuotesResponse> quotes){
        return Observable.zip(photos, quotes, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesWithPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterLive
    @Provides
    public Observable<List<QuoteModel>> zipObservablesLife(
            @FilterLive Observable<PhotosResponse> photos,
            @FilterLive Observable<QuotesResponse> quotes){
        return Observable.zip(photos, quotes, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesWithPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterMotif
    @Provides
    public Observable<List<QuoteModel>> zipObservablesMotif(
            @FilterMotif Observable<PhotosResponse> photos,
            @FilterMotif Observable<QuotesResponse> quotes){
        return Observable.zip(photos, quotes, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quoteList = quotesResponse.getQuotes();
                List<Photo> photosList = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quoteList);
                return mergeQuotesWithPhotos(photosList, quoteDataList);
            }
        });
    }

    @FilterFunny
    @Provides
    public Observable<List<QuoteModel>> zipObservablesFunny(
            @FilterFunny Observable<PhotosResponse> photos,
            @FilterFunny Observable<QuotesResponse> quotes){
        return Observable.zip(photos, quotes, new BiFunction<PhotosResponse, QuotesResponse, List<QuoteModel>>() {
            @Override
            public List<QuoteModel> apply(PhotosResponse photosResponse, QuotesResponse quotesResponse) throws Exception {
                List<Quote> quotes = quotesResponse.getQuotes();
                List<Photo> photos = photosResponse.getHits();
                List<QuoteModel> quoteDataList = populateQuotes(quotes);
                return mergeQuotesWithPhotos(photos, quoteDataList);
            }
        });
    }
}
