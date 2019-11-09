package com.ulan.app.quotes.ui.mainactivity;

import android.os.Bundle;
import android.util.Log;
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
import com.ulan.app.quotes.R;
import com.ulan.app.quotes.adapter.ViewPagerAdapter;
import com.ulan.app.quotes.data.QuoteData;
import com.ulan.app.quotes.di.components.MainActivityComponent;
import com.ulan.app.quotes.di.modules.uimodules.MainModule;
import com.ulan.app.quotes.ui.CustomViewPager;
import com.ulan.app.quotes.ui.base.BaseActivity;
import com.ulan.app.quotes.ui.home.HomeFragment;
import com.ulan.app.quotes.ui.onequote.OneQuoteFragment;
import com.ulan.app.quotes.ui.starred.StarredFragment;
import com.ulan.app.quotes.utils.listeners.FragmentLifecycle;
import com.ulan.app.quotes.utils.listeners.ShareObservableListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.ulan.app.quotes.utils.notification.NotificationHelper.setNotification;
import static com.ulan.app.quotes.utils.Constants.TAG_OTHER;


public class MainActivity extends BaseActivity implements MainActivityView, View.OnClickListener {

    private MainActivityComponent mActivityComponent;
    private ShareObservableListener mShareObservableListener;
    private CustomViewPager mCustomViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private CollapsingToolbarLayout mCollapsingToolbar;
    private AppBarLayout mAppBarLayout;
    private LinearLayout mLinearLayout;
    private BottomNavigationView mBottomNavigation;
    private MenuItem mBottomMenuItem;

    private Button[] mListButtons = new Button[5];
    private Button mUntouchedButton;
    private int[] mButtonResources = {R.id.love, R.id.funny, R.id.life, R.id.motivation, R.id.wisdom};

    @Inject
    public MainActivityPresenterImpl mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMainComponent();
        initViewPager();
        initAppBar();
        initBottomNav();
        initFilterButtons();
        setFutureNotification();
    }

    public void getMainComponent(){
        mActivityComponent = getAppComponent().activityBuilder()
                .mainModule(new MainModule(this))
                .build();
        mActivityComponent.inject(this);
    }

    private void initFilterButtons() {
        for(int i = 0; i < mListButtons.length; i++){
            mListButtons[i] = findViewById(mButtonResources[i]);
            mListButtons[i].setOnClickListener(this);
        }
        mUntouchedButton = mListButtons[0];
    }

    private void initAppBar(){
        Toolbar toolbar = findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        mAppBarLayout.setExpanded(false);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.coll_toolbar);
        mCollapsingToolbar.setTitle(getResources().getString(R.string.app_name));
        mCollapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        mCollapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.transparent));
        mLinearLayout = (LinearLayout) findViewById(R.id.filter_layout);
        mAppBarLayout.addOnOffsetChangedListener(appBarChangeListener);
    }

    private void initViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mCustomViewPager = findViewById(R.id.containerPager);
        mViewPagerAdapter.addFragment(new StarredFragment(), "title");
        mViewPagerAdapter.addFragment(new HomeFragment(), "title");
        mViewPagerAdapter.addFragment(new OneQuoteFragment(), "title");
        mCustomViewPager.setOffscreenPageLimit(2);
        mCustomViewPager.setAdapter(mViewPagerAdapter);
        mCustomViewPager.setOnPageChangeListener(pageChangeListener);
    }

    private void initBottomNav(){
        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener);
        mBottomNavigation.setSelectedItemId(R.id.home);
        mBottomNavigation.setItemIconTintList(null);
    }

    public void setOnSendListener(ShareObservableListener sendListener){
        this.mShareObservableListener = sendListener;
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
               if(mAppBarLayout.getTop() < 0) {
                    mAppBarLayout.setExpanded(true);
                }else{
                    mAppBarLayout.setExpanded(false);
                }
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void setListOfQuotes(Observable<List<QuoteData>> listOfQuotes) {
        mShareObservableListener.passObservable(listOfQuotes);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.love:
                clickHandle(mUntouchedButton, mListButtons[0]);
                mPresenter.setListObservable(mActivityComponent.getObservableListLove());
                mPresenter.loadObservable();
                break;
            case R.id.funny:
                clickHandle(mUntouchedButton, mListButtons[1]);
                mPresenter.setListObservable(mActivityComponent.getObservableListFunny());
                mPresenter.loadObservable();
                break;
            case R.id.life:
                clickHandle(mUntouchedButton, mListButtons[2]);
                mPresenter.setListObservable(mActivityComponent.getObservableListLive());
                mPresenter.loadObservable();
                break;
            case R.id.motivation:
                clickHandle(mUntouchedButton, mListButtons[3]);
                mPresenter.setListObservable(mActivityComponent.getObservableListMotif());
                mPresenter.loadObservable();

                break;
            case R.id.wisdom:
                clickHandle(mUntouchedButton, mListButtons[4]);
                mPresenter.setListObservable(mActivityComponent.getObservableListHappy());
                mPresenter.loadObservable();

                break;
            default:

                break;
        }
    }

    public void clickHandle(Button untouched, Button touched){
        untouched.setBackground(getResources().getDrawable(R.drawable.button_no_border));
        touched.setBackground(getResources().getDrawable(R.drawable.buttun_with_border));
        touched.setPressed(true);
        this.mUntouchedButton = touched;
    }

    public void setFutureNotification(){
        mActivityComponent.getObservableList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<QuoteData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<QuoteData> quoteDataList) {
                        List<QuoteData> list = new ArrayList<>();
                        list.addAll(quoteDataList);
                        setNotification(getApplicationContext(), list.get(0).getQuote());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG_OTHER, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private AppBarLayout.OnOffsetChangedListener appBarChangeListener =
            new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                    if(Math.abs(i) == appBarLayout.getTotalScrollRange()){
                        mLinearLayout.setVisibility(View.GONE);
                        mCollapsingToolbar.setTitle(getResources().getString(R.string.app_name));
                    }else if(i == 0){
                        mLinearLayout.setVisibility(View.VISIBLE);
                        mCollapsingToolbar.setTitle("");
                    }
                }
            };

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.star:
                            mCustomViewPager.setCurrentItem(0);
                            break;
                        case R.id.home:
                            mCustomViewPager.setCurrentItem(1);
                            break;
                        case R.id.one:
                            mCustomViewPager.setCurrentItem(2);
                            break;
                    }
                    return false;
                }
            };

    private ViewPager.OnPageChangeListener pageChangeListener =
            new ViewPager.OnPageChangeListener() {
                int currentPosition = 0;
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(mBottomMenuItem != null){
                        mBottomMenuItem.setChecked(false);
                        mAppBarLayout.setExpanded(false);
                    }else {
                        mBottomNavigation.getMenu().getItem(position);
                    }

                    mBottomNavigation.getMenu().getItem(position).setChecked(true);
                    mBottomMenuItem = mBottomNavigation.getMenu().getItem(position);

                    FragmentLifecycle fragmentToShow = (FragmentLifecycle) mViewPagerAdapter.getItem(position);
                    fragmentToShow.onResumeFragment();

                    FragmentLifecycle fragmentToHide = (FragmentLifecycle) mViewPagerAdapter.getItem(currentPosition);
                    Fragment fragment = mViewPagerAdapter.getItem(mCustomViewPager.getCurrentItem());
                    fragmentToHide.onPauseFragment(fragment);

                    currentPosition = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            };

}