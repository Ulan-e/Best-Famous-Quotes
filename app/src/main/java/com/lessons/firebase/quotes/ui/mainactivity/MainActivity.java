package com.lessons.firebase.quotes.ui.mainactivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lessons.firebase.quotes.CustomViewPager;
import com.lessons.firebase.quotes.FragmentLifecycle;
import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.adapter.ViewPagerAdapter;
import com.lessons.firebase.quotes.di.components.AppComponent;
import com.lessons.firebase.quotes.ui.base.BaseActivity;
import com.lessons.firebase.quotes.ui.liked.LikedFragment;
import com.lessons.firebase.quotes.ui.list.MainFragment;
import com.lessons.firebase.quotes.ui.quoteofday.QuoteOfDay;
import com.lessons.firebase.quotes.utils.CheckInternetConnection;

public class MainActivity extends BaseActivity implements MainActivityView {

    private Toolbar mTopToolbar;
    private BottomNavigationView mBottomNavigation;
    private MainActivityPresenter presenter;
    private CustomViewPager customViewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager();

        AppComponent appComponent = getAppComponent();
        appComponent.inject(this);
        presenter = new MainActivityPresenterImpl(this);
        presenter.setTitleToolbar();
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
    public void showToolbar(String toolbarTitle) {
        mTopToolbar = findViewById(R.id.toolbarTop);
        setSupportActionBar(mTopToolbar);
        mTopToolbar.setTitle(toolbarTitle);
        mTopToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener);
        mBottomNavigation.setSelectedItemId(R.id.home);
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

    @Override
    public void showInternetConnection() {
        String internetResult = CheckInternetConnection.getConnectionStatusString(this);
        Toast.makeText(this, internetResult, Toast.LENGTH_LONG).show();
    }

}