package com.sonnytron.sortatech.pantryprep.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.sonnytron.sortatech.pantryprep.Fragments.IngredientsListFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.RecipeListFragment;

/**
 * Created by sonnyrodriguez on 9/4/16.
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Ingredients", "Recipes" };
    private SparseArray<Fragment> fragments;

    public HomeFragmentPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return IngredientsListFragment.newInstance(position + 1);
            case 1:
                return RecipeListFragment.newInstance(position + 2);
            default:
                return IngredientsListFragment.newInstance(position + 1);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
