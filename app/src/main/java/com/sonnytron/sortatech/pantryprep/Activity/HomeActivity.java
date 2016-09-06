package com.sonnytron.sortatech.pantryprep.Activity;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.sonnytron.sortatech.pantryprep.Adapters.HomeFragmentPagerAdapter;

import com.sonnytron.sortatech.pantryprep.Helpers.ProgressDialogHelper;
import com.sonnytron.sortatech.pantryprep.Helpers.PushNotificationHelper;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.R;
import com.sonnytron.sortatech.pantryprep.Service.IngredientManager;

import java.util.List;

public class HomeActivity extends AppCompatActivity  {

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
        tabStrip.setBackgroundColor(ContextCompat.getColor(this, R.color.PantryOrange));

        AssetManager am = this.getAssets();

        Typeface poppinsFont = Typeface.createFromAsset(am, "fonts/Poppins-SemiBold.ttf");
        tabStrip.setTypeface(poppinsFont, 0);

        //load progress dialog to show loading screens.
        pd = new ProgressDialogHelper();
        pushNote = new PushNotificationHelper();

        //check for expiration.  
        checkExpiration();
    }

    private void checkExpiration() {
        IngredientManager ingredientManager = IngredientManager.get(this);
        List<Ingredient> expiringIngredients = ingredientManager.getExpiringIngredients();

        if (expiringIngredients.size() == 0){
           //Log.d("checkExpiration: ", expiringIngredients.get(i).getTitle() + ":" + expiringIngredients.get(i).getExpDate());
            pushNote.popNotification(this,expiringIngredients.get(0).getTitle(), false);
        }
        else if (expiringIngredients.size() > 1){
            //Log.d("checkExpiration: ", expiringIngredients.get(i).getTitle() + ":" + expiringIngredients.get(i).getExpDate());
            pushNote.popNotification(this,expiringIngredients.get(0).getTitle(), true);
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
}
