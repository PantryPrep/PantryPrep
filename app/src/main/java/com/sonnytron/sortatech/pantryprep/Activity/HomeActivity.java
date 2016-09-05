package com.sonnytron.sortatech.pantryprep.Activity;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.sonnytron.sortatech.pantryprep.Adapters.HomeFragmentPagerAdapter;
import com.sonnytron.sortatech.pantryprep.Adapters.IngredientListAdapter;
import com.sonnytron.sortatech.pantryprep.Fragments.DeleteIngredientDialogFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientDialogFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.DairyFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.FruitFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.IngredientsAllFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.ProteinFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.SpicesFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientFilters.VeggieFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.IngredientsListFragment;
import com.sonnytron.sortatech.pantryprep.Fragments.RecipeListFragment;
import com.sonnytron.sortatech.pantryprep.Helpers.ProgressDialogHelper;
import com.sonnytron.sortatech.pantryprep.Helpers.PushNotificationHelper;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements IngredientDialogFragment.IngredientCallback, IngredientListAdapter.ListAdapterCallback, DeleteIngredientDialogFragment.DeleteIngredientListener {

    private ProgressDialogHelper pd;
    private PushNotificationHelper pushNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        viewPager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabMenu);
        tabStrip.setViewPager(viewPager);

        AssetManager am = this.getAssets();

        Typeface poppinsFont = Typeface.createFromAsset(am, "fonts/Poppins-SemiBold.ttf");
        tabStrip.setTypeface(poppinsFont, 0);

        //load fragment on initial load.
        if (savedInstanceState == null) {
            loadIngredientListFragment();
        }

        //load progress dialog to show loading screens.
        pd = new ProgressDialogHelper();
        pushNote = new PushNotificationHelper();

        //check for expiration.  
        checkExpiration();
    }

    private void checkExpiration() {
        IngredientManager ingredientManager = IngredientManager.get(this);
        List<Ingredient> expiringIngredients = ingredientManager.getExpiringIngredients();

        if (expiringIngredients.size() > 0){
           //Log.d("checkExpiration: ", expiringIngredients.get(i).getTitle() + ":" + expiringIngredients.get(i).getExpDate());
            pushNote.popNotification(this,expiringIngredients.get(0).getTitle());
        }
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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
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
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().replace(R.id.frame_content, new IngredientsAllFragment()).commit();
//        //create a new ingredients fragment
//        setTitle(R.string.nav_ingredients_title);
    }

    public void showDeleteIngredientAlert(Ingredient ingredient) {
        FragmentManager fm = getSupportFragmentManager();
        DeleteIngredientDialogFragment deleteFrag = DeleteIngredientDialogFragment.newInstance(ingredient);
        deleteFrag.setIngredient(ingredient);
        deleteFrag.show(fm, "fragment_alert");
    }
}
