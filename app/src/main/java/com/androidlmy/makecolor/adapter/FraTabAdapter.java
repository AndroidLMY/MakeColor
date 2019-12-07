package com.androidlmy.makecolor.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ViewPager和Tabblayout结合的Adapter
 *
 */
public class FraTabAdapter extends FragmentPagerAdapter {
   private List<Fragment> mFragmentList;

   public FraTabAdapter(FragmentManager manager, List<Fragment> mFragmentList) {
       super(manager);
       this.mFragmentList = mFragmentList;
   }

   @Override
   public Fragment getItem(int position) {
       return mFragmentList.get(position);
   }

   @Override
   public int getCount() {
       return mFragmentList.size();
   }
}
