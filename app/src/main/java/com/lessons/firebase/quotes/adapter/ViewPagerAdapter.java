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

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final SparseArray<WeakReference<Fragment>> instantietedFragments = new SparseArray<>();
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> list =  new ArrayList();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
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
        list.add(title);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
        instantietedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        instantietedFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getFragment(final int position){
        final WeakReference<Fragment> weakReference = instantietedFragments.get(position);
        if(weakReference != null){
            return weakReference.get();
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
