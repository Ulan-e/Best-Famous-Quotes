package com.lessons.firebase.quotes.ui.mainactivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import com.lessons.firebase.quotes.di.modules.uimodules.MainModule;
import com.lessons.firebase.quotes.ui.base.BaseActivity;
import com.lessons.firebase.quotes.ui.home.HomeFragment;
import com.lessons.firebase.quotes.ui.onequote.OneQuoteFragment;
import com.lessons.firebase.quotes.ui.starred.StarredFragment;
import com.lessons.firebase.quotes.utils.listeners.FragmentLifecycle;
import com.lessons.firebase.quotes.utils.listeners.ShareObservableListener;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainActivity extends BaseActivity implements MainActivityView, View.OnClickListener {

    private MainActivityComponent mActivityComponent;
    private ShareObservableListener mShareObservableListener;
    private BottomNavigationView mBottomNavigation;
    private CustomViewPager mCustomViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private LinearLayout mLinearLayout;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private AppBarLayout mAppBarLayout;
    private MenuItem mBottomMenuItem;

    private Button[] buttons = new Button[5];
    private Button unfocused_button;
    private int[] button_res = {R.id.love, R.id.funny, R.id.life, R.id.motivation, R.id.wisdom};
    boolean isChecked = true;

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
    }

    public void getMainComponent(){
        mActivityComponent = getAppComponent().activityBuilder()
                .mainModule(new MainModule(this))
                .build();
        mActivityComponent.inject(this);
    }

    private void initFilterButtons() {
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = findViewById(button_res[i]);
            buttons[i].setOnClickListener(this);
        }

        unfocused_button = buttons[0];
    }

    private void initAppBar(){
        Toolbar toolbar = findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        mAppBarLayout.setExpanded(false);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.coll_toolbar);
        mCollapsingToolbar.setTitle("Quotes");
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

    private AppBarLayout.OnOffsetChangedListener appBarChangeListener =
            new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                    if(Math.abs(i) == appBarLayout.getTotalScrollRange()){
                        mLinearLayout.setVisibility(View.GONE);
                        mCollapsingToolbar.setTitle("Quotes");
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
                            return true;
                        case R.id.home:
                            if(isChecked){
                                mCustomViewPager.setCurrentItem(1);
                                isChecked = false;
                            }else{
                                mPresenter.setListObservable(mActivityComponent.getObservableList());
                                mPresenter.loadObservable();
                            }
                            return true;
                        case R.id.one:
                            mCustomViewPager.setCurrentItem(2);
                            return true;
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

    @Override
    public void setListOfQuotes(Observable<List<QuoteData>> listOfQuotes) {
        mShareObservableListener.passObservable(listOfQuotes);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.love:
                setFocus(unfocused_button, buttons[0]);
                mPresenter.setListObservable(mActivityComponent.getObservableListLove());
                mPresenter.loadObservable();
                break;
            case R.id.funny:
                setFocus(unfocused_button, buttons[1]);
                mPresenter.setListObservable(mActivityComponent.getObservableListFunny());
                mPresenter.loadObservable();
                break;
            case R.id.life:
                setFocus(unfocused_button, buttons[2]);
                mPresenter.setListObservable(mActivityComponent.getObservableListLive());
                mPresenter.loadObservable();
                break;
            case R.id.motivation:
                setFocus(unfocused_button, buttons[3]);
                mPresenter.setListObservable(mActivityComponent.getObservableListMotif());
                mPresenter.loadObservable();

                break;
            case R.id.wisdom:
                setFocus(unfocused_button, buttons[4]);
                mPresenter.setListObservable(mActivityComponent.getObservableListHappy());
                mPresenter.loadObservable();

                break;
            default:

                break;
        }
    }

    public void setFocus(Button unfocused, Button focus){
        unfocused.setBackground(getResources().getDrawable(R.drawable.button_no_border));
        focus.setBackground(getResources().getDrawable(R.drawable.buttun_with_border));
        this.unfocused_button = focus;
    }

}