package com.lessons.firebase.quotes.di.components;

import android.content.SharedPreferences;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.data.database.DaoLikedQuotes;
import com.lessons.firebase.quotes.data.database.DaoQuotes;
import com.lessons.firebase.quotes.di.modules.RxModule;
import com.lessons.firebase.quotes.di.modules.source.RoomModule;
import com.lessons.firebase.quotes.di.modules.source.SharedPrefModule;
import com.lessons.firebase.quotes.di.modules.uimodules.MainModule;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterHappy;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLive;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterLove;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterMotif;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterRandom;
import com.lessons.firebase.quotes.di.scopes.AppScope;
import com.lessons.firebase.quotes.di.scopes.ListScope;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.list.MainFragmentPresenterImpl;

import java.util.List;

import dagger.Subcomponent;
import io.reactivex.Observable;

@ListScope
@Subcomponent(modules = {MainModule.class, SharedPrefModule.class})
public interface ListComponent {

    @ListScope
    MainFragmentPresenterImpl getPresenter();
    SharedPreferences getSharedPreference();

    @FilterRandom
    Observable<List<QuoteData>> getObservableList();

    @AppScope
    DaoQuotes getDaoLikedQuotes();

    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface MABuilder{
        MABuilder mainModule(MainModule mainModule);
        MABuilder sharedModule(SharedPrefModule shareModule);
        ListComponent build();
    }
}
