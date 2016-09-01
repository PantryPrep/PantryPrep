package com.sonnytron.sortatech.pantryprep.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.astuetz.PagerSlidingTabStrip;
import com.sonnytron.sortatech.pantryprep.Adapters.SmartFragmentStatePagerAdapter;
import com.sonnytron.sortatech.pantryprep.Fragments.RecipeListFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.nutritionInfoFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.recipeDetailFragment;
import com.sonnytron.sortatech.pantryprep.R;

import butterknife.BindView;
import butterknife.BindViews;


public class RecipeLookupActivity extends AppCompatActivity {

    private String recipeID;
    private SmartFragmentStatePagerAdapter adapterViewPager;
    private PagerSlidingTabStrip tabStrip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_lookup);

        ViewPager vpRecipeViewPager = (ViewPager) findViewById(R.id.recipeViewpager);
        adapterViewPager = new RecipePagerAdapter(getSupportFragmentManager());
        vpRecipeViewPager.setAdapter(adapterViewPager);

        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabsRecipeDetail);
        tabStrip.setViewPager(vpRecipeViewPager);
        tabStrip.setTextColor(Color.WHITE);

        //retrieve ID, maybe check if null.
        //move this logic into tab
        recipeID = getIntent().getStringExtra("recipe_id");


        //pass recipe ID to fragment.
    }

    public String getRecipeID(){
        return recipeID;

    }

    //return order of the fragments in the view
    public class RecipePagerAdapter extends SmartFragmentStatePagerAdapter {
        private String tabTitles[] = {"Ingredients & Link", "Nutritional Info"};

        //adapter gets the manager insert or remove fragment from activity
        public RecipePagerAdapter(FragmentManager fm){
            super(fm);
        }

        //control order and creation of fragments within the pager
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (recipeID == null) {
                    Toast.makeText(getApplicationContext(), "No recipe ID found! ", Toast.LENGTH_LONG).show();
                    finish();
                }
                return new recipeDetailFragment();
            } else if (position == 1) {
                //create new fragment for nutritional facts
                return new nutritionInfoFragment();
            } else {
                return null;
            }
        }

        //return tab title
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        //how many fragments are here to swipe between
        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }



}
