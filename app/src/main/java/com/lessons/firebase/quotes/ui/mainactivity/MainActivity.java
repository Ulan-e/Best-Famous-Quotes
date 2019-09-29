package com.lessons.firebase.quotes.ui.mainactivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lessons.firebase.quotes.R;
import com.lessons.firebase.quotes.di.components.AppComponent;
import com.lessons.firebase.quotes.ui.base.BaseActivity;
import com.lessons.firebase.quotes.ui.base.BaseFragment;
import com.lessons.firebase.quotes.ui.liked.LikedFragment;
import com.lessons.firebase.quotes.ui.list.MainFragment;
import com.lessons.firebase.quotes.ui.quoteofday.QuoteOfDay;
import com.lessons.firebase.quotes.utils.CheckInternetConnection;

public class MainActivity extends BaseActivity implements MainActivityView {

    private Toolbar mTopToolbar;
    private BottomNavigationView mBottomNavigation;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppComponent appComponent = getAppComponent();
        appComponent.inject(this);
        showInternetConnection();
        presenter = new MainActivityPresenterImpl(this);
        presenter.setTitleToolbar();
    }

    @Override
    public void showToolbar(String toolbarTitle) {
        mTopToolbar = findViewById(R.id.toolbarTop);
        setSupportActionBar(mTopToolbar);
        mTopToolbar.setTitle(toolbarTitle);
        mTopToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigation.setSelectedItemId(R.id.home);
    }

    public boolean showFragment(Fragment fragment) {
        if(fragment != null){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    BaseFragment fragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.star:
                            fragment = new LikedFragment();
                            Toast.makeText(getApplicationContext(), "Star", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.home:
                            fragment = new MainFragment();
                            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.add:
                            fragment = new QuoteOfDay();
                            Toast.makeText(getApplicationContext(), "Star", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    return showFragment(fragment);
                }
            };

    @Override
    public void showInternetConnection() {
        String internetResult = CheckInternetConnection.getConnectionStatusString(this);
        Toast.makeText(this, internetResult, Toast.LENGTH_SHORT).show();
    }
}