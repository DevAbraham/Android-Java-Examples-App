package com.dev_abraham.toolbar_and_tabs.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dev_abraham.toolbar_and_tabs.Fragments.FirstFragment;
import com.dev_abraham.toolbar_and_tabs.Fragments.SecondFragment;
import com.dev_abraham.toolbar_and_tabs.Fragments.ThirdFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm,int tabs) {
        super(fm);
        this.numberOfTabs=tabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 :
                return new FirstFragment();
            case 1 :
                return new SecondFragment();
            case 2 :
                return new ThirdFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
