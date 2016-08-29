package com.sonnytron.sortatech.pantryprep.Activity;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sonnytron.sortatech.pantryprep.Adapters.IngredientListAdapter;
import com.sonnytron.sortatech.pantryprep.Fragments.DeleteIngredientDialogFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientDialogFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientsListFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.RecipeListFragment;
import com.sonnytron.sortatech.pantryprep.Helpers.ProgressDialogHelper;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

public class HomeActivity extends AppCompatActivity implements IngredientDialogFragment.IngredientCallback, IngredientListAdapter.ListAdapterCallback, DeleteIngredientDialogFragment.DeleteIngredientListener {
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private ProgressDialogHelper pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //setup drawer listener
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(mDrawerToggle);

        //find and setup drawer
        nvDrawer = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(nvDrawer);

        //load fragment on initial load.
        loadIngredientListFragment();

        //load progress dialog to show loading screens.
        pd = new ProgressDialogHelper();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.nav_ingredients:
                fragmentClass = IngredientsListFragment.class;
                break;
            case R.id.nav_recipes:
                pd.launchProgressDialog(this);
                fragmentClass = RecipeListFragment.class;
                break;
            default:
                fragmentClass = IngredientsListFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame_content, fragment).commit();

        item.setChecked(true);

        setTitle(item.getTitle());
        mDrawer.closeDrawers();
    }

    //interface for disabling progress dialog.
    public void disableProgressDialog(){
        if (pd != null) {
            pd.disableProgressDialog();
        }
        else {
            Log.d("disableProgressDialog", "showProgressDialog: pd was null when trying to dismiss progress");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
        IngredientManager.get(this).addIngredient(ingredient);
        loadIngredientListFragment();
    }

    @Override
    public void ingredientDeleteRequest(Ingredient ingredient) {
        Toast.makeText(this, "We have frag delete request from home!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ingredientFragmentRequest(Ingredient ingredient) {
        Toast.makeText(this, "We have frag request from home!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(int position, View view, Ingredient ingredient) {
        showDeleteIngredientAlert(ingredient);
        return false;
    }

    @Override
    public void onItemClick(int position, View view) {

    }

    @Override
    public void onIngredientDeleteConfirmed(boolean delete, Ingredient ingredient) {
        if (delete) {
            IngredientManager.get(this).deleteIngredient(ingredient);
        }
        loadIngredientListFragment();
    }

    private void loadIngredientListFragment() {
        nvDrawer.getMenu().getItem(0).setChecked(true);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame_content, new IngredientsListFragment()).commit();
        setTitle(R.string.nav_ingredients_title);
    }

    public void showDeleteIngredientAlert(Ingredient ingredient) {
        FragmentManager fm = getSupportFragmentManager();
        DeleteIngredientDialogFragment deleteFrag = DeleteIngredientDialogFragment.newInstance(ingredient);
        deleteFrag.setIngredient(ingredient);
        deleteFrag.show(fm, "fragment_alert");
    }
}
