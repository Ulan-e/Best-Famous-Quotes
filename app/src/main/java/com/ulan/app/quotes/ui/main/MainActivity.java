package com.ulan.app.quotes.ui.main;

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
import com.ulan.app.quotes.R;
import com.ulan.app.quotes.adapter.ViewPagerAdapter;
import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.di.qualifires.filters.FilterFunny;
import com.ulan.app.quotes.di.qualifires.filters.FilterHappy;
import com.ulan.app.quotes.di.qualifires.filters.FilterLive;
import com.ulan.app.quotes.di.qualifires.filters.FilterLove;
import com.ulan.app.quotes.di.qualifires.filters.FilterMotif;
import com.ulan.app.quotes.ui.CustomViewPager;
import com.ulan.app.quotes.ui.base.BaseActivity;
import com.ulan.app.quotes.ui.home.HomeFragment;
import com.ulan.app.quotes.ui.onequote.OneQuoteFragment;
import com.ulan.app.quotes.ui.starred.StarredFragment;
import com.ulan.app.quotes.ui.listeners.FragmentLifecycle;
import com.ulan.app.quotes.ui.listeners.ShareQuotesListener;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;

public class MainActivity extends BaseActivity implements MainView, View.OnClickListener{

    private ShareQuotesListener mShareQuotesListener;

    private CustomViewPager mViewPager;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private AppBarLayout mAppBarLayout;
    private LinearLayout mLinearLayout;
    private BottomNavigationView mBottomNavigation;
    private MenuItem mBottomMenuItem;
    private Button[] mButtons;
    private Button mUntouchedButton;
    private int[] mButtonResources;

    @Inject
    public ViewPagerAdapter mViewPagerAdapter;

    @Inject
    public MainPresenter mPresenter;

    @Inject
    @FilterFunny
    public Observable<List<QuoteModel>> funnyQuotes;

    @Inject
    @FilterHappy
    public Observable<List<QuoteModel>> happyQuotes;

    @Inject
    @FilterLive
    public Observable<List<QuoteModel>> liveQuotes;

    @Inject
    @FilterLove
    public Observable<List<QuoteModel>> loveQuotes;

    @Inject
    @FilterMotif
    public Observable<List<QuoteModel>> motivQuotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.attachView(this);

        initAppBar();
        initFilterButtons();
        initViewPager();
        initBottomNavView();
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
    public void saveQuotesInPref(Observable<List<QuoteModel>> listOfQuotes) {
        mShareQuotesListener.passQuotes(listOfQuotes);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.love:
                clickFilterButton(mUntouchedButton, mButtons[0]);
                mPresenter.setQuotes(loveQuotes);
                mPresenter.loadQuotes();
                break;
            case R.id.funny:
                clickFilterButton(mUntouchedButton, mButtons[1]);
                mPresenter.setQuotes(funnyQuotes);
                mPresenter.loadQuotes();
                break;
            case R.id.life:
                clickFilterButton(mUntouchedButton, mButtons[2]);
                mPresenter.setQuotes(liveQuotes);
                mPresenter.loadQuotes();
                break;
            case R.id.motivation:
                clickFilterButton(mUntouchedButton, mButtons[3]);
                mPresenter.setQuotes(motivQuotes);
                mPresenter.loadQuotes();
                break;
            case R.id.happy:
                clickFilterButton(mUntouchedButton, mButtons[4]);
                mPresenter.setQuotes(happyQuotes);
                mPresenter.loadQuotes();
                break;
            default:
                break;
        }
    }

    // ставим листенер на AppBar
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

    // инициализируем кнопки фильтра
    private void initFilterButtons() {
        mButtonResources = new int[]{
                R.id.love,
                R.id.funny,
                R.id.life,
                R.id.motivation,
                R.id.happy
        };
        mButtons = new Button[5];
        for(int i = 0; i < mButtons.length; i++){
            mButtons[i] = findViewById(mButtonResources[i]);
            mButtons[i].setOnClickListener(this);
        }
        mUntouchedButton = mButtons[0];
    }

    // инициализируем ViewPager
    private void initViewPager() {
        mViewPager = findViewById(R.id.containerPager);
        mViewPagerAdapter.addFragment(new StarredFragment(), "");
        mViewPagerAdapter.addFragment(new HomeFragment(), "");
        mViewPagerAdapter.addFragment(new OneQuoteFragment(), "");
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(pageChangeListener);
    }

    // ставим листенер на ViewPager
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

                    FragmentLifecycle frgToShow = (FragmentLifecycle)
                            mViewPagerAdapter.getItem(position);
                    frgToShow.onResumeFragment();

                    FragmentLifecycle fragmentToHide = (FragmentLifecycle)
                            mViewPagerAdapter.getItem(currentPosition);
                    Fragment fragment = mViewPagerAdapter.getItem(mViewPager.getCurrentItem());
                    fragmentToHide.onPauseFragment(fragment);

                    currentPosition = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) { }
            };

    // инициализируем нижнее меню
    private void initBottomNavView(){
        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.star:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.home:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.one:
                    mViewPager.setCurrentItem(2);
                    break;
            }
            return false;
        });
        mBottomNavigation.setSelectedItemId(R.id.home);
        mBottomNavigation.setItemIconTintList(null);
        mBottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener);
    }

    // ставим листенеры на нижнее меню
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.star:
                            mViewPager.setCurrentItem(0);
                            break;
                        case R.id.home:
                            mViewPager.setCurrentItem(1);
                            break;
                        case R.id.one:
                            mViewPager.setCurrentItem(2);
                            break;
                    }
                    return false;
                }
            };

    // инициализируем AppBar
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

    // ставим клики на фильтры
    public void clickFilterButton(Button untouched, Button touched){
        untouched.setBackground(getResources().getDrawable(R.drawable.button_no_border));
        touched.setBackground(getResources().getDrawable(R.drawable.buttun_with_border));
        touched.setPressed(true);
        this.mUntouchedButton = touched;
    }

    // ставим листенер на отправку
    public void setOnSendListener(ShareQuotesListener sendListener){
        this.mShareQuotesListener = sendListener;
    }
}