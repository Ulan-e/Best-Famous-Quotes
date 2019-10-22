package com.lessons.firebase.quotes.di.modules.uimodules;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.DaoStarredQuotes;
import com.lessons.firebase.quotes.di.qualifires.LikedQuotes;
import com.lessons.firebase.quotes.di.scopes.StarredScope;
import com.lessons.firebase.quotes.ui.starred.StarredFragmentView;
import com.lessons.firebase.quotes.ui.starred.StarredPresenterImpl;

import java.util.List;
import java.util.concurrent.Callable;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

@StarredScope
@Module
public class StarredModule {

    public StarredFragmentView view;

    public StarredModule(StarredFragmentView view) {
        this.view = view;
    }

    @StarredScope
    @Provides
    public StarredFragmentView provideView(){
        return view;
    }

    @StarredScope
    @LikedQuotes
    @Provides
    public Observable<List<QuoteData>> listObservable(DaoStarredQuotes daoQuotes){
            return Observable.fromCallable(new Callable<List<QuoteData>>() {
                @Override
                public List<QuoteData> call() throws Exception {
                    return daoQuotes.getLikedQuotes();
                }
            });
    }

    @StarredScope
    @Provides
    public StarredPresenterImpl likedPresenter(StarredFragmentView view, @LikedQuotes Observable<List<QuoteData>> listObservable){
        return new StarredPresenterImpl(view, listObservable);
    }


}
