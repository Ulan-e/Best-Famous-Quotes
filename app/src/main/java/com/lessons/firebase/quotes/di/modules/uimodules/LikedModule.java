package com.lessons.firebase.quotes.di.modules.uimodules;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.DaoLikedQuotes;
import com.lessons.firebase.quotes.di.qualifires.LikedQuotes;
import com.lessons.firebase.quotes.di.scopes.LikedScope;
import com.lessons.firebase.quotes.ui.liked.LikedFragmentView;
import com.lessons.firebase.quotes.ui.liked.LikedPresenterImpl;

import java.util.List;
import java.util.concurrent.Callable;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

@LikedScope
@Module
public class LikedModule {

    private LikedFragmentView view;

    public LikedModule(LikedFragmentView view) {
        this.view = view;
    }

    @LikedScope
    @Provides
    public LikedFragmentView provideView(){
        return view;
    }

    @LikedScope
    @LikedQuotes
    @Provides
    public Observable<List<QuoteData>> listObservable(DaoLikedQuotes daoQuotes){
            return Observable.fromCallable(new Callable<List<QuoteData>>() {
                @Override
                public List<QuoteData> call() throws Exception {
                    return daoQuotes.getLikedQuotes();
                }
            });
    }

    @LikedScope
    @Provides
    public LikedPresenterImpl likedPresenter(LikedFragmentView view, @LikedQuotes Observable<List<QuoteData>> listObservable){
        return new LikedPresenterImpl(view, listObservable);
    }


}
