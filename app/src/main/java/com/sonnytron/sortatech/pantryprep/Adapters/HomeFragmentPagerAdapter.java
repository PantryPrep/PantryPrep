package com.sonnytron.sortatech.pantryprep.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.IngredientsAllFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.RecipeListFragment;

/**
 * Created by sonnyrodriguez on 9/4/16.
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Ingredients", "Recipes" };

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
                return new IngredientsAllFragment();
            case 1:
                return new RecipeListFragment();
            default:
                return new IngredientsAllFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
