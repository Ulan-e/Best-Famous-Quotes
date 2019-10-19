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
import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.adapter.ViewPagerAdapter;
import com.lessons.firebase.quotes.data.QuoteData;
import com.lessons.firebase.quotes.di.components.MainActivityComponent;
import com.lessons.firebase.quotes.di.modules.uimodules.MainModulee;
import com.lessons.firebase.quotes.ui.base.BaseActivity;
import com.lessons.firebase.quotes.ui.liked.LikedFragment;
import com.lessons.firebase.quotes.ui.list.MainFragment;
import com.lessons.firebase.quotes.ui.quoteofday.QuoteOfDay;
import com.lessons.firebase.quotes.utils.listeners.FragmentLifecycle;
import com.lessons.firebase.quotes.utils.listeners.ShareObservableListener;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainActivity extends BaseActivity implements MainActivityView, View.OnClickListener {

    private MainActivityComponent appComponent;
    private ShareObservableListener sendListener;
    private BottomNavigationView mBottomNavigation;
    private CustomViewPager customViewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private LinearLayout linearLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private MenuItem prevMenuItem;

    @Inject
    public MainActivityPresenterImpl presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMainComponent();
        initViewPager();
        initAppBar();
        initBottomNav();
        initFilterButtons();
    }

    public void getMainComponent(){
        appComponent = getAppComponent().activityBuilder()
                .mainModule(new MainModulee(this))
                .build();
        appComponent.inject(this);
    }

    private void initFilterButtons() {
        Button buttonMotif = findViewById(R.id.motivation);
        Button buttonLove = findViewById(R.id.love);
        Button buttonLive = findViewById(R.id.live);
        Button buttonHappy = findViewById(R.id.happiness);
        Button buttonFunny = findViewById(R.id.funny);

        buttonFunny.setOnClickListener(this);
        buttonLove.setOnClickListener(this);
        buttonLive.setOnClickListener(this);
        buttonMotif.setOnClickListener(this);
        buttonHappy.setOnClickListener(this);
    }

    private void initAppBar(){
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
        appBarLayout.addOnOffsetChangedListener(appBarChangeListener);
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

    private void initBottomNav(){
        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener);
        mBottomNavigation.setSelectedItemId(R.id.home);
        mBottomNavigation.setItemIconTintList(null);
    }

    public void setOnSendListener(ShareObservableListener sendListener){
        this.sendListener = sendListener;
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

    private AppBarLayout.OnOffsetChangedListener appBarChangeListener =
            new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                    if(Math.abs(i) == appBarLayout.getTotalScrollRange()){
                        linearLayout.setVisibility(View.GONE);
                        collapsingToolbarLayout.setTitle("Quotes");
                    }else if(i == 0){
                        linearLayout.setVisibility(View.VISIBLE);
                        collapsingToolbarLayout.setTitle("");
                    }
                }
            };

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
            if(prevMenuItem != null){
                prevMenuItem.setChecked(false);
                appBarLayout.setExpanded(false);
            }else {
                mBottomNavigation.getMenu().getItem(position);
            }

            mBottomNavigation.getMenu().getItem(position).setChecked(true);
            prevMenuItem = mBottomNavigation.getMenu().getItem(position);

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

    @Override
    public void setListOfQuotes(Observable<List<QuoteData>> listOfQuotes) {
        sendListener.passObservable(listOfQuotes);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.love:
                presenter.setListObservable(appComponent.getObservableListLove());
                presenter.loadObservable();
                break;
            case R.id.funny:
                presenter.setListObservable(appComponent.getObservableListFunny());
                presenter.loadObservable();
                break;
            case R.id.live:
                presenter.setListObservable(appComponent.getObservableListLive());
                presenter.loadObservable();
                break;
            case R.id.motivation:
                presenter.setListObservable(appComponent.getObservableListMotif());
                presenter.loadObservable();
                break;
            case R.id.happiness:
                presenter.setListObservable(appComponent.getObservableListHappy());
                presenter.loadObservable();
                break;
            default:
                break;
        }
    }
}