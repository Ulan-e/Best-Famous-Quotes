package com.lessons.firebase.quotes.di.modules.rxmodules;

import com.lessons.firebase.quotes.data.database.DaoQuotes;
import com.lessons.firebase.quotes.data.database.QuoteEntity;
import com.lessons.firebase.quotes.data.network.FavQsApi;
import com.lessons.firebase.quotes.data.network.PixabayApi;
import com.lessons.firebase.quotes.data.network.pojo.PhotosResponse;
import com.lessons.firebase.quotes.data.network.pojo.QuotesResponse;
import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.modules.source.ImagesAPIModule;
import com.lessons.firebase.quotes.di.modules.source.QuotesAPIModule;
import com.lessons.firebase.quotes.di.modules.source.RoomModule;

import java.util.List;
import java.util.concurrent.Callable;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

import static com.lessons.firebase.quotes.utils.Constants.PIXABAY_API_KEY;

@AppScope
@Module(includes =  {QuotesAPIModule.class, ImagesAPIModule.class, RoomModule.class})
public class ApisModule{

    @AppScope
    @Provides
    public Observable<PhotosResponse> getPhotosObservable(PixabayApi pixabayApi){
        return pixabayApi.getPhotos(PIXABAY_API_KEY, "photo", 50, "popular");
    }

    @AppScope
    @Provides
    public Observable<QuotesResponse> getQuotesObservable(FavQsApi favQsApi){
        return favQsApi.getQuotes();
    }


    @AppScope
    @Provides
    public Observable<List<QuoteEntity>> getQuotesDatabase(DaoQuotes daoQuotes){
        return Observable.fromCallable(new Callable<List<QuoteEntity>>() {
            @Override
            public List<QuoteEntity> call() throws Exception {
                return daoQuotes.getAllQuotes();
            }
        });
    }



}
