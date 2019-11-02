package com.lessons.firebase.quotes.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lessons.firebase.quotes.ui.base.BaseFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Fragment View Pager
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final SparseArray<WeakReference<Fragment>> mInstantiatedFragments;
    private final List<Fragment> mFragmentList;
    private final List<String> mTitleList;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mInstantiatedFragments = new SparseArray<>();
        mTitleList =  new ArrayList();
        mFragmentList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(BaseFragment fragment, String title){
        mFragmentList.add(fragment);
        mTitleList.add(title);
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

    // Get fragment by position
    public Fragment getFragment(final int position){
        final WeakReference<Fragment> weakReference = mInstantiatedFragments.get(position);
        if(weakReference != null){
            return weakReference.get();
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
