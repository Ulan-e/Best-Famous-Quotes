package com.lessons.firebase.quotes.di.modules.uimodules;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.di.qualifires.RandomQuotes;
import com.lessons.firebase.quotes.di.scopes.ListScope;
import com.lessons.firebase.quotes.ui.list.MainFragment;
import com.lessons.firebase.quotes.ui.list.MainFragmentPresenterImpl;
import com.lessons.firebase.quotes.ui.list.MainFragmentView;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

@ListScope
@Module
public class MainModule {

    MainFragmentView view;

    public MainModule(MainFragmentView view) {
        this.view = view;
    }

    @ListScope
    @Provides
    public MainFragmentView view(){
        return view;
    }

    @ListScope
    @Provides
    public MainFragmentPresenterImpl presenter(MainFragmentView view, @RandomQuotes Observable<List<QuoteData>> list){
        return new MainFragmentPresenterImpl(view, list);
    }

}
