package com.lessons.firebase.quotes.di.components;

import android.content.SharedPreferences;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.di.modules.source.SharedPrefModule;
import com.lessons.firebase.quotes.di.modules.uimodules.HomeModule;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterRandom;
import com.lessons.firebase.quotes.di.scopes.HomeScope;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.home.HomeFragmentPresenterImpl;

import java.util.List;

import dagger.Subcomponent;
import io.reactivex.Observable;

@HomeScope
@Subcomponent(modules = {HomeModule.class, SharedPrefModule.class})
public interface HomeComponent {

    @HomeScope
    HomeFragmentPresenterImpl getPresenter();

    SharedPreferences getSharedPreference();

    @FilterRandom
    Observable<List<QuoteData>> getObservableList();

    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface ListBuilder {
        ListBuilder mainModule(HomeModule mainModule);
        ListBuilder sharedModule(SharedPrefModule shareModule);
        HomeComponent build();
    }
}
