package com.lessons.firebase.quotes.ui.mainactivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lessons.firebase.quotes.CustomViewPager;
import com.lessons.firebase.quotes.utils.listeners.FragmentLifecycle;
import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.utils.listeners.ShareObservableListener;
import com.lessons.firebase.quotes.adapter.ViewPagerAdapter;
import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.di.components.AppComponent;
import com.lessons.firebase.quotes.ui.base.BaseActivity;
import com.lessons.firebase.quotes.ui.liked.LikedFragment;
import com.lessons.firebase.quotes.ui.list.MainFragment;
import com.lessons.firebase.quotes.ui.quoteofday.QuoteOfDay;

import java.util.List;

import io.reactivex.Observable;

public class MainActivity extends BaseActivity implements MainActivityView {

    ShareObservableListener sendListener;

    private BottomNavigationView mBottomNavigation;
    private MainActivityPresenter presenter;
    private CustomViewPager customViewPager;
    private ViewPagerAdapter viewPagerAdapter;


    private Observable<List<QuoteData>> observableListMotif;
    private Observable<List<QuoteData>> observableListLive;
    private Observable<List<QuoteData>> observableListLove;
    private Observable<List<QuoteData>> observableListHappy;

    Button buttonMotif;
    Button buttonLove;
    Button buttonLive;
    Button buttonHappy;
    Button buttonFunny;

    private LinearLayout linearLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();

        AppComponent appComponent = getAppComponent();
        appComponent.inject(this);
        observableListHappy = appComponent.getObservableListHappy();
        observableListLive = appComponent.getObservableListLife();
        observableListLove = appComponent.getObservableListLove();
        observableListMotif = appComponent.getObservableListMotif();
        presenter = new MainActivityPresenterImpl(this);
        presenter.setTitleToolbar();

        //Collapsed Zone
        Toolbar toolbar = findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        appBarLayout.setExpanded(false);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.coll_toolbar);
        collapsingToolbarLayout.setTitle("Quotes");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));
        linearLayout = (LinearLayout) findViewById(R.id.filter_layout);

        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener);
        mBottomNavigation.setSelectedItemId(R.id.home);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(Math.abs(i) == appBarLayout.getTotalScrollRange()){
                    linearLayout.setVisibility(View.GONE);
                    collapsingToolbarLayout.setTitle("Quotes");
                }else if(i == 0){
                    linearLayout.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle("");
                }else{

                }
            }
        });

        buttonHappy = findViewById(R.id.happiness);
        buttonLive = findViewById(R.id.live);
        buttonLove = findViewById(R.id.love);
        buttonMotif = findViewById(R.id.motivation);
        buttonHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendListener.passObservable(observableListHappy);
            }
        });

        buttonMotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendListener.passObservable(observableListMotif);
            }
        });

        buttonLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendListener.passObservable(observableListLove);
            }
        });

        buttonLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendListener.passObservable(observableListLive);
            }
        });

    }

    public void setOnSendListener(ShareObservableListener sendListener){
        this.sendListener = sendListener;
    }

    private void initViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        customViewPager = findViewById(R.id.containerPager);
        viewPagerAdapter.addFragment(new LikedFragment(), "title");
        viewPagerAdapter.addFragment(new MainFragment(), "title");
        viewPagerAdapter.addFragment(new QuoteOfDay(), "title");
        customViewPager.setOffscreenPageLimit(2);
        customViewPager.setAdapter(viewPagerAdapter);
        customViewPager.setOnPageChangeListener(pageChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter:
               if(appBarLayout.getTop() < 0) {
                    appBarLayout.setExpanded(true);
                }else{
                    appBarLayout.setExpanded(false);
                }
                return true;
            default:
                break;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.star:
                            customViewPager.setCurrentItem(0);
                            return true;
                        case R.id.home:
                            customViewPager.setCurrentItem(1);
                            return true;
                        case R.id.one:
                            customViewPager.setCurrentItem(2);
                            return true;
                    }
                    return false;
                }
            };

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        int currentPosition = 0;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            FragmentLifecycle fragmentToShow = (FragmentLifecycle) viewPagerAdapter.getItem(position);
            fragmentToShow.onResumeFragment();

            FragmentLifecycle fragmentToHide = (FragmentLifecycle) viewPagerAdapter.getItem(currentPosition);
            Fragment fragment = viewPagerAdapter.getItem(customViewPager.getCurrentItem());
            fragmentToHide.onPauseFragment(fragment);

            currentPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}