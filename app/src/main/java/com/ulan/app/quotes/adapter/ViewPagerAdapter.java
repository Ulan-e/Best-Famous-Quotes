package com.ulan.app.quotes.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ulan.app.quotes.ui.base.BaseFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Fragment View Pager
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final SparseArray<WeakReference<Fragment>> mInstantiatedFragments;
    private final List<Fragment> mFragments;
    private final List<String> mTitles;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mInstantiatedFragments = new SparseArray<>();
        mTitles =  new ArrayList();
        mFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(BaseFragment fragment, String title){
        mFragments.add(fragment);
        mTitles.add(title);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
        mInstantiatedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        mInstantiatedFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
