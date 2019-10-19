package com.lessons.firebase.quotes.di.components;

import android.content.SharedPreferences;

import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.di.modules.source.SharedPrefModule;
import com.lessons.firebase.quotes.di.modules.uimodules.ListModule;
import com.lessons.firebase.quotes.di.qualifires.filters.FilterRandom;
import com.lessons.firebase.quotes.di.scopes.ListScope;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.list.MainFragmentPresenterImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.Subcomponent;
import io.reactivex.Observable;

@ListScope
@Subcomponent(modules = {ListModule.class, SharedPrefModule.class})
public interface ListComponent {

    @ListScope
    MainFragmentPresenterImpl getPresenter();

    SharedPreferences getSharedPreference();

    @FilterRandom
    Observable<List<QuoteData>> getObservableList();

    void inject(BaseFragment mainFragment);

    @Subcomponent.Builder
    interface MABuilder{
        MABuilder mainModule(ListModule mainModule);
        MABuilder sharedModule(SharedPrefModule shareModule);
        ListComponent build();
    }
}
