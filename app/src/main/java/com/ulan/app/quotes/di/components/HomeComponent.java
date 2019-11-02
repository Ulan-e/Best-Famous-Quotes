package com.ulan.app.quotes.di.components;

import android.content.SharedPreferences;

import com.ulan.app.quotes.data.QuoteData;
import com.ulan.app.quotes.di.modules.source.SharedPrefModule;
import com.ulan.app.quotes.di.modules.uimodules.HomeModule;
import com.ulan.app.quotes.di.qualifires.filters.FilterRandom;
import com.ulan.app.quotes.di.scopes.HomeScope;
import com.ulan.app.quotes.ui.base.BaseFragment;
import com.ulan.app.quotes.ui.home.HomeFragmentPresenterImpl;

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
